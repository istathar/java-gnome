/*
 * DefsParser.java
 *
 * Copyright (c) 2006-2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

/*
 * This code started life as prototype written in Perl by Andrew Cowie. The
 * parser portion was later ported to Python by Srichand Pendyala when we
 * realized that trying to do object-oriented programming in Perl was a
 * non-starter. Although we had a second working prototype in Python, it in
 * turn was abandonded when I realized that my governing assumption - that we
 * needed here docs to output code cleanly - wasn't necessarily true. While
 * here docs indeed made for clean templates in the code generator, working in
 * Python was not really much more productive that Perl had been. On the other
 * hand, accepting some inconveniences in exchange for being able to work in
 * Java with the powerful tools we have available to develop and debug seems a
 * good trade. While nothing beyond the original regular expressions has
 * survived from the Perl program, and the Python program only has echos here,
 * thanks are nevertheless due to Srichand for his hard work assisting in the
 * prototyping process.
 */

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A .defs file parser: convert a stream of s-expression defines data into an
 * array of Block objects suitable to be used to instantiate code generators.
 * 
 * <p>
 * Observations:
 * <ul>
 * <li>We don't have include directives in our defs files. They're all flat.
 * <li>At the moment, we have one type per file, but this class does not rely
 * on that; it's just a .defs parser.
 * </ul>
 * 
 * @author Andrew Cowie
 */
public class DefsParser
{
    private final DefsLineNumberReader source;

    /*
     * Parser state
     */

    private List<String[]> characteristics, values, fields, parameters;

    private String phylum;

    private String name;

    /*
     * Java is a bit annoying about caching constant things, since they [have
     * to] end up as class members, but to do the regex compile only once, its
     * best done this way.
     */
    private static final Pattern defineLine;

    private static final Pattern characteristicLine;

    private static final Pattern parametersBegin;

    private static final Pattern fieldsBegin;

    private static final Pattern valuesBegin;

    private static final Pattern subCharacteristicsLine;

    private static final Pattern subEnds;

    static {
        // (define-method get_some
        defineLine = Pattern.compile("^\\(define-(\\w+)\\s+(\\w+)");

        /*
         * (?:X) is a "non-capturing group".
         */
        // __(c-name "gtk_button_new_with_label")
        // __(return-type "GtkWidget*")
        // __(return-type "const-gchar")
        // __(deprecated)
        characteristicLine = Pattern.compile("^\\s+\\((\\S+)(?:\\s+\"?([\\w #\\(\\)'/\\.\\-\\*]+)\"?)?\\)");

        /*
         * TODO: it's not entirely clear that we actually need to support
         * arbitrary sub-characteristic types; now that I've gone and coded
         * all this and we differentiate when we call the Block constructors,
         * I wonder if a three-way-or in the regex would have done well
         * enough. Might simplify things.
         */

        // __(parameters
        parametersBegin = Pattern.compile("^\\s+\\(parameters");

        // __(fields
        fieldsBegin = Pattern.compile("^\\s+\\(fields");

        // __(values
        valuesBegin = Pattern.compile("^\\s+\\(values");

        /*
         * FIXME The final dot-star excludes qualifying information as found
         * in lines like the second following line; we need to figure out
         * whether we need to capture and use this information; while defaul
         * values are meaningless in Java (except in so far as advising that
         * an overloaded public API method with less parameters and using that
         * default might make sense), we'll need it for others (ie (null-ok),
         * which would allow us to put an assert elsewhere, and (read-write)
         * if we add it to struct fields to indicate that it's ok to create a
         * setter)
         */
        // ____'("const-gchar*" "label")
        // ____'("gfloat[]" "vector")
        // ____'("GtkButtonsType" "buttons" (default "GTK_BUTTONS_NONE"))
        subCharacteristicsLine = Pattern.compile("^\\s+'\\(\"([\\w\\-\\*\\[\\]]+)\"\\s+\"([\\w]+)\".*\\)");

        // __)
        subEnds = Pattern.compile("^\\s+\\)");
    }

    /**
     * Initialize the parser for a stream of .defs data.
     * 
     * @param source
     *            a data stream containing s-expression .defs data.
     * @param filename
     *            name of the .defs file being parsed (for debug purposes)
     */
    public DefsParser(DefsLineNumberReader source) {
        this.source = source;
    }

    /**
     * Read a series of lines from the input source comprising a complete
     * (define- ...) block, storing them in the parser state.
     * 
     * @return whether or not a complete ( ) balanced stanza was read. If
     *         there was, there might be another one.
     */
    boolean readNextStanza() throws ParseException {
        String line;
        Matcher m;
        List<String[]> l;

        /*
         * Clear the parser state:
         */

        phylum = null;
        name = null;

        characteristics = new ArrayList<String[]>();
        values = new ArrayList<String[]>();
        fields = new ArrayList<String[]>();
        parameters = new ArrayList<String[]>();

        try {

            /*
             * Handle the first line, always of the form "(define-phylum name"
             */

            while ((line = source.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                if (line.startsWith(";;")) {
                    continue;
                }
                break;
            }
            if (line == null) {
                return false;
            }

            m = defineLine.matcher(line);

            if (!m.matches()) {
                throw new DefsParseException("Parser failed to find a proper define line", line, source);
            }

            phylum = m.group(1);
            name = m.group(2).intern();

            /*
             * Run through subsequent lines, sorting the key/values in the
             * characteristics list. If we hit a fields or parameters sublist,
             * change the target list accordingly and carry on.
             */

            l = characteristics;

            while ((line = source.readLine()) != null) {
                String key, value;

                if (line.equals(")")) {
                    return true;
                }
                if (line.startsWith(";;")) {
                    continue;
                }

                if (parametersBegin.matcher(line).matches()) {
                    l = parameters;
                    continue;
                } else if (fieldsBegin.matcher(line).matches()) {
                    l = fields;
                    continue;
                } else if (valuesBegin.matcher(line).matches()) {
                    l = values;
                    continue;
                } else if (subEnds.matcher(line).matches()) {
                    l = characteristics;
                    continue;
                }

                if (l == characteristics) {
                    m = characteristicLine.matcher(line);
                } else {
                    m = subCharacteristicsLine.matcher(line);
                }

                if (!m.matches()) {
                    throw new DefsParseException("Couldn't match characteristics line", line, source);
                }

                key = m.group(1);
                if (key != null) {
                    key = key.intern();
                }
                value = m.group(2);
                if (value != null) {
                    value = value.intern();
                }

                if (key == null) {
                    throw new DefsParseException("Parsed key turned out to be null", line, source);
                }

                /*
                 * reduce the String pressure by normalizing the Strings to
                 * intern() before placing them into the arrays used for
                 * subsequent manipulation.
                 */
                if (l == parameters) {
                    // TODO could null-ok be present in other than parameters
                    l.add(new String[] {
                            key, value,
                            /* 3rd value is used to take null-ok under control */
                            line.indexOf("(null-ok)") != -1 ? "yes" : "no"
                    });
                } else {
                    l.add(new String[] {
                            key, value
                    });
                }
            }
        } catch (IOException ioe) {
            // ignore? Either way, it's end of file, right?
        }

        return false;
    }

    /**
     * Run the parser across the input data stream and return an array of
     * Block objects representing the (define- ...) blocks found there.
     */
    /*
     * FIXME the fact that there are two exceptions that come out of this,
     * both for the same problem needs to be changed.
     */
    public Block[] parseData() {
        List<Block> blocks;
        Block block;
        BoxedBlock parent;
        Iterator<String[]> iter;
        String[] field;

        blocks = new ArrayList<Block>();

        while (true) {
            try {
                if (!readNextStanza()) {
                    break;
                }

                /*
                 * And now, with the stanza's data organized into Lists,
                 * instantiate the appropriate Block object to represent the
                 * data. Block's processCharacteristics() and FunctionBlock's
                 * processParameters() complete the parsing by allocating
                 * key/value pairs to fields in the Block objects.
                 */

                if (phylum.equals("object")) {
                    block = new ObjectBlock(name, characteristics, fields);
                    blocks.add(block);
                } else if (phylum.equals("interface")) {
                    block = new InterfaceBlock(name, characteristics);
                    blocks.add(block);
                } else if (phylum.equals("method")) {
                    block = new MethodBlock(name, characteristics, parameters);
                    blocks.add(block);
                } else if (phylum.equals("function")) {
                    block = new FunctionBlock(name, characteristics, parameters);
                    blocks.add(block);
                } else if ((phylum.equals("virtual")) || (phylum.equals("signal"))) {
                    block = new VirtualBlock(name, characteristics, parameters);
                    blocks.add(block);
                } else if (phylum.equals("enum")) {
                    block = new EnumBlock(name, characteristics, values);
                    blocks.add(block);
                } else if (phylum.equals("flags")) {
                    block = new FlagsBlock(name, characteristics, values);
                    blocks.add(block);
                } else if (phylum.equals("boxed")) {
                    block = new BoxedBlock(name, characteristics);
                    blocks.add(block);

                    parent = (BoxedBlock) block;

                    iter = fields.iterator();
                    while (iter.hasNext()) {
                        field = iter.next();

                        block = new GetterBlock(parent, field[0], field[1]);
                        blocks.add(block);

                        /*
                         * FIXME this is just for testing! Most GTK fields are
                         * read only! Field setters must ONLY be generated if
                         * it is known safe to do so and so annotated in the
                         * fields line in the defs data.
                         */
                        block = new SetterBlock(parent, field[0], field[1]);
                        blocks.add(block);
                    }
                } else if (phylum.equals("entity")) {
                    block = new EntityBlock(name, characteristics);
                    blocks.add(block);
                } else {
                    // etc
                    throw new ParseException("What kind of block was \"" + phylum + "\"?", 0);
                }

            } catch (ParseException pe) {
                System.out.println("Failed to parse");
                System.out.print(source.getFilename() + ", ");
                System.out.println(pe.getMessage());
                System.out.println("[continuing next block]\n");
            } catch (UnnecessaryCodeException uce) {
                // TODO skip to next file?
            } catch (IllegalStateException ise) {
                System.out.println("Failed parsing (an internal problem? FIXME!):");
                System.out.print(source.getFilename() + ", ");
                System.out.println(ise.getMessage());
                System.out.println("[continuing next block]\n");
            }
        }

        // Wow. Eclipse gave me this as a template. Nice.
        return blocks.toArray(new Block[blocks.size()]);
    }
}
