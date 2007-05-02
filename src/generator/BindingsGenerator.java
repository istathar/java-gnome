/*
 * BindingsGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.operationaldynamics.defsparser.Block;
import com.operationaldynamics.defsparser.DefsParser;
import com.operationaldynamics.driver.DefsFile;
import com.operationaldynamics.driver.ImproperDefsFileException;

/**
 * The java-gnome code generator
 * 
 * <p>
 * The biggest problem architecturally is transforming information from the
 * form that the upstream .defs data provides to one we can do something
 * useful with in our context. The code generator, then is essentially two
 * separate pieces that have nothing to do with each other:
 * 
 * <ul>
 * <li>At the front end is the .defs file parser. DefsParser takes an input
 * stream of GNOME defs metadata s-expressions and turns it into an array of
 * Block objects. Blocks are Java objects representing the contents of a given
 * (define-...) stanza.
 * 
 * <li>Completely independent of the parser is the code generator. A
 * hierarchy of Generator objects exist with the code to output the necessary
 * Java and C code They have constructors which minutely specifiy the
 * information they require (and with variables names that means something to
 * the task of bindings generation, rather than whatever the origin .defs data
 * might have called it). The types information describing the underlying
 * library is stored in a hash table of which uses the underlying type as key,
 * and a Thing object as the value containing all the necessary mappings of
 * that type to the actual Java or C language type used at each layer of the
 * bindings.
 * </ul>
 * 
 * <p>
 * The link between the two sides are the createThing() and createGenerator()
 * methods in each Block object; this is where we translate from the
 * characteristic key names in the .defs data to the names we use in the
 * generator.
 * 
 * <p>
 * To generate the java-gnome bindings, we therefore do two passes:
 * 
 * <ol>
 * <li>register all the type information: Given the full array of Blocks of
 * .defs data, we do a first pass over it calling each Block's createThing()
 * and then Thing.register() to store teh the resultant in our lookup table.
 * Obviously this only concerns (define-...) blocks which declare type
 * information.
 * 
 * <li>with a full database of type information in hand, we can then do a
 * second pass over the Block array to actually generate the code that goes
 * with each stanza. We get the appropriate Generator object by calling each
 * Block's createGenerator() and then run its writeJava() and writeC() methods
 * to finally emit the generated translation and jni code.
 * </ol>
 * <p>
 * Since the .defs data has already been demuxed into one .defs file per type,
 * we use the TypeBlock heading each file to identify it and to name the
 * appropriate output files. That setup, and the driving of the two passes
 * accordingly, is the task of this class, which is the [only] public entry
 * point into the code generator.
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
/*
 * This classes name implies it is a subclass of Generator, which of course is
 * not the case. It is, however, a good name to have show up when building the
 * library via `make`, so BindingsGenerator it is.
 */
public class BindingsGenerator
{
    public static void main(String[] args) throws IOException {
        demoRunGeneratorOutputToFiles();
    }

    /**
     * This is building towards the main loop that will drive the .defs file
     * parser and subsequent runs of the bindings code generators, but it is
     * still an intermediate form.
     */
    private static void demoRunGeneratorOutputToFiles() {
        Block[] blocks;
        DefsParser parser;
        File dir;
        File[] files;
        BufferedReader in;
        DefsFile data;
        List all;
        Iterator iter;

        dir = new File("tests/generator/");

        files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.endsWith(".defs")) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        all = new ArrayList(files.length);

        /*
         * Load the all the .defs files into DefsFile objects, one per type.
         * Along the way, this registers the type information.
         */

        for (int i = 0; i < files.length; i++) {
            try {
                in = new BufferedReader(new FileReader(files[i]));

                parser = new DefsParser(in);
                blocks = parser.parseData();

                data = new DefsFile(blocks);
                all.add(data);

                in.close();
            } catch (IOException ioe) {
                System.err.println("I/O problem when trying to parse " + files[i]);
                continue;
            } catch (ImproperDefsFileException idfe) {
                System.err.println("Couldn't recognize the layout of " + files[i]);
                continue;
            }
        }

        /*
         * Now, with the meta data completely loaded, we can generate the
         * bindings code.
         */

        for (iter = all.iterator(); iter.hasNext();) {
            PrintWriter out;

            data = (DefsFile) iter.next();

            // TODO use data.getTranslationTargetName(); until then,
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);

            try {
                data.generateTranslationLayer(out);
            } catch (UnsupportedOperationException uoe) {
                // act to remove that file? Or close it off, or...
            }

            try {
                data.generateJniLayer(out);
            } catch (UnsupportedOperationException uoe) {
                // act to remove the file in the even there was nothing printed?
            }

            /*
             * Don't close stdout! :)
             */
            // out.close();
        }
    }

}
