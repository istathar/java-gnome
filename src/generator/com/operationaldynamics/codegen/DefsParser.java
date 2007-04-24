/*
 * DefsParser.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A .defs file parser: convert a stream of s-expression defines data into
 * Block objects suitable to be used to instantiate code generators.
 * 
 * <p>
 * Observations:
 * <ul>
 * <li>We don't have include directives in our defs files. They're all flat,
 * one type per file.
 * </ul>
 * 
 * @author Andrew Cowie
 */
public class DefsParser
{
    private BufferedReader source;

    /**
     * The Strings comprising a s-expression define block
     */
    private List lines;

    /**
     * The current input line being parsed, for debugging. TODO replace this
     * with a custom BufferedReader or PushbackReader which keeps track of the
     * source file line number.
     */
    private String current;

    /*
     * Java is a bit annoying about caching constant things, since they [have
     * to] end up as class members, but to do the regex compile only once, its
     * best done elsewhere.
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

        // __(c-name "gtk_button_new_with_label")
        // __(return-type "GtkWidget*")
        // __(return-type "const-gchar")
        characteristicLine = Pattern.compile("^\\s+\\((\\S+)\\s+\"?([\\w#\\-\\*]+)\"?\\)");

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

        // ____'("const-gchar*" "label")
        subCharacteristicsLine = Pattern.compile("^\\s+'?\\(\"?([\\w\\-\\*]+)\"?\\s+\"?([\\w]+)\"?\\)");

        // __)
        subEnds = Pattern.compile("^\\s+\\)");

    }

    /**
     * Initialize the parser for a stream of .defs data.
     * 
     * @param source
     *            a data stream containing s-expression .defs data.
     */
    public DefsParser(BufferedReader source) throws IOException {
        this.source = source;
    }

    /**
     * Read a series of lines from the input source comprising a complete
     * (define- ...) block, storing them in the parser state.
     * 
     * @return whether or not a complete ( ) balanced stanza was read. If
     *         there was, there might be another one.
     */
    boolean readNextStanza() {
        String line;

        /*
         * Clear the parser state:
         */

        lines = new ArrayList();

        /*
         * Read lines out of the .defs stream, adding them to the lines list
         * until a block-end is encountered:
         */

        try {
            while ((line = source.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                if (line.equals(")")) {
                    return true;
                }
                if (line.substring(0, 2).equals(";;")) {
                    continue;
                }
                lines.add(line);
            }
        } catch (IOException ioe) {
            // ignore. Either way, it's end of file
        }

        return false;
    }

    /**
     * Given a stanza of .defs data containing a single (define- ...) stored
     * in the parser state variable lines, and do the magic to instantiate a
     * Block object and set its member fields.
     * 
     * @param data
     *            a List containing Strings comprising a stanza of .defs data.
     */
    /*
     * We don't necessarily have sufficient information to figure out what
     * sort of Block this data stanza is until we've parsed in all the lines.
     * So we do that (to local variables), and then go through the logic to
     * figure out what it should be.
     */
    Block parseStanza() throws ParseException {
        Block block = null;
        Matcher m;
        final String phylum;
        final String name;
        final List characteristics, values, fields, parameters;
        List l;
        int i;

        characteristics = new ArrayList();
        values = new ArrayList();
        fields = new ArrayList();
        parameters = new ArrayList();

        /*
         * Handle the first line, always of the form "(define-phylum name"
         */

        current = (String) lines.get(0);
        m = defineLine.matcher(current);

        if (!m.matches()) {
            throw new ParseException("Parse failed to grok \"" + current + "\"", 0);
        }

        phylum = m.group(1);
        name = m.group(2).intern();

        /*
         * Run through subsequent lines, sorting the key/values in the
         * characteristics list. If we hit a fields or parameters sublist,
         * change the target list accordingly and carry on.
         */

        l = characteristics;

        for (i = 1; i < lines.size(); i++) {
            String key, value;

            current = (String) lines.get(i);

            if (parametersBegin.matcher(current).matches()) {
                l = parameters;
                continue;
            } else if (fieldsBegin.matcher(current).matches()) {
                l = fields;
                continue;
            } else if (valuesBegin.matcher(current).matches()) {
                l = values;
                continue;
            } else if (subEnds.matcher(current).matches()) {
                l = characteristics;
                continue;
            }

            if (l == characteristics) {
                m = characteristicLine.matcher(current);
            } else {
                m = subCharacteristicsLine.matcher(current);
            }

            if (!m.matches()) {
                throw new ParseException("Couldn't match characteristics line \"" + current + "\"", i);
            }

            key = m.group(1).intern();
            value = m.group(2).intern();

            if ((key == null) || (value == null)) {
                throw new ParseException("Parsed key/value null on line \"" + current + "\"", i);
            }

            /*
             * reduce the String pressure by normalizing the Strings to
             * intern() before placing them into the arrays used for
             * subsequent manipulation.
             */
            l.add(new String[] {
                    key.intern(), value.intern()
            });
        }

        /*
         * And now, with the stanza's data organized into Lists, instantiate
         * the appropriate Block object to represent the data. Block's
         * processCharacteristics() and FunctionBlock's processParameters()
         * complete the parsing by allocating key/value pairs to fields in the
         * Block objects.
         */

        if (phylum.equals("object")) {
            block = new ObjectBlock(name, characteristics, fields);
        } else if (phylum.equals("method")) {
            block = new MethodBlock(name, characteristics, parameters);
        } else if (phylum.equals("function")) {
            /*
             * FUTURE what about other function types? Part of the reason
             * things were laid out in the sequence they are here was so that
             * we could get all the information needed before deciding the
             * type. As things stand now, however, we don't have things in a
             * usable form until after Block.processCharacteristics() has run
             * care of Block's constructor. As the only (define-function ...)
             * type we deal with are GObject constructors, so it's not a
             * problem at the moment.
             */
            block = new ConstructorBlock(name, characteristics, parameters);
        } else if (phylum.equals("virtual")) {
            block = new VirtualBlock(name, characteristics, parameters);
        } else if (phylum.equals("enum")) {
            // FIXME
            block = new EnumBlock(name, characteristics, values);
        } else {
            // etc
            throw new ParseException("What kind of block was \"" + phylum + "\"?", 0);
        }

        if (block == null) {
            throw new IllegalStateException("No Block object created");
        }

        /*
         * And now reflection to call the setters on the object.
         */

        return block;
    }

    /**
     * Run the parser across the input data stream and return an array of
     * Block objects representing the (define- ...) blocks found there.
     */
    public Block[] parseData() {
        List blocks;
        Block block;

        blocks = new ArrayList();

        while (readNextStanza()) {
            try {
                block = parseStanza();
                blocks.add(block);
            } catch (ParseException pe) {
                throw new RuntimeException(pe);
            }
        }

        // Wow. Eclipse gave me this as a template. Nice.
        return (Block[]) blocks.toArray(new Block[blocks.size()]);
    }

}
