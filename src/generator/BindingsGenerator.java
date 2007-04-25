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
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.operationaldynamics.codegen.Block;
import com.operationaldynamics.codegen.DefsParser;
import com.operationaldynamics.codegen.FundamentalThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.codegen.TypeBlock;

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
        loadDummyTypes();
        demoRunGeneratorForOneFile();
    }

    // FIXME demo
    private static void loadDummyTypes() throws IOException {
        Block[] blocks;
        DefsParser parser;

        BufferedReader in = new BufferedReader(new FileReader("tests/generator/DummyTypes.defs"));

        parser = new DefsParser(in);
        blocks = parser.parseData();

        registerTypes(blocks);
    }

    // FIXME demo
    private static void demoRunGeneratorForOneFile() throws IOException {
        Block[] blocks;
        DefsParser parser;

        BufferedReader in = new BufferedReader(new FileReader("tests/generator/GtkButton.defs"));

        parser = new DefsParser(in);
        blocks = parser.parseData();

        // first pass
        registerTypes(blocks);

        // second pass
        generateCode(blocks);
    }

    /**
     * Iterate over all the Blocks and register a Thing in the type database
     * for each one. This needs to be done across the entire universe of types
     * before generation can begin. Yes, that sounds painful, and yes it is,
     * but otherwise we don't have the information we need about how to
     * convert types from one layer to another. <i>Shrug</i> that's why this
     * is done ahead of time in the BindingsGenerator, and not at an
     * application's runtime!
     */
    static void registerTypes(Block[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            Thing t;

            if (blocks[i] instanceof TypeBlock) {
                t = blocks[i].createThing();
                Thing.register(t);
            }
        }
    }

    /**
     * Generate the code that goes with a given .defs file, represnting one
     * type being bound.
     * 
     * @param blocks
     *            the array of Blocks you wish to transform into bindings
     *            code. The obvious assumption is that the first block in the
     *            array is a type definition, from which we determine the
     *            target file names.
     */
    /*
     * This could have been a single call to writeJava() and writeC(), but we
     * had to split things up to generate the include statements for the Java
     * files.
     */
    static void generateCode(final Block[] blocks) {
        PrintWriter out, sink, trans, jni;
        /*
         * This is still in flux and a work in progress. For now, just send
         * one to stdout.
         */
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
        sink = new PrintWriter(new StringWriter());

        // switch
        trans = out;
        jni = sink;

        writeFileHeaders(blocks, trans, jni);

        writeImportStatements(blocks, trans);

        writeBindingsCode(blocks, trans, jni);

        trans.close();
        jni.close();
    }

    private static void writeFileHeaders(Block[] blocks, PrintWriter trans, PrintWriter jni) {
        Generator gen;

        if (!(blocks[0] instanceof TypeBlock)) {
            throw new IllegalStateException("First (define-...) block must be type information");
        }

        gen = blocks[0].createGenerator();

        gen.writeJavaHeader(trans);
        gen.writeCHeader(jni);
    }

    /*
     * TODO I hate the fact that this had to be here, but I can't figure out
     * any way to get it down into the Generator hierarchy because they, by
     * design, output the code for a single (define-) block at a time, and are
     * _created_ by each individual Block. Perhaps Block[] needs to be
     * abstracted into a class.
     */
    private static void writeImportStatements(final Block[] blocks, final PrintWriter trans) {
        final Set types;
        Iterator iter;

        types = new HashSet();

        for (int i = 0; i < blocks.length; i++) {
            List things;

            things = blocks[i].usesTypes();

            iter = things.iterator();
            while (iter.hasNext()) {
                Thing t = (Thing) iter.next();
                if (t instanceof FundamentalThing) {
                    continue;
                }
                // As a Set it won't do duplicates. Ta-da.
                types.add(t);
            }
        }

        /*
         * And now output the actual code for the include statements. TODO
         * More than anything, this is what shouldn't be here. FUTURE sort the
         * includes, perhaps with TreeSet, but that will need compareTo() in
         * Thing.
         */
        iter = types.iterator();

        while (iter.hasNext()) {
            Thing t = (Thing) iter.next();

            trans.print("import ");

            trans.print(t.fullyQualifiedJavaClassName());
            trans.print(";\n");
        }
    }

    private static void writeBindingsCode(final Block[] blocks, final PrintWriter trans,
            final PrintWriter jni) {
        for (int i = 0; i < blocks.length; i++) {
            Generator gen;

            gen = blocks[i].createGenerator();
            gen.writeJavaBody(trans);
            gen.writeCBody(jni);

            trans.flush(); // hmm
        }

        trans.println("}");

    }
}
