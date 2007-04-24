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
package com.operationaldynamics.codegen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

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
        theRealThing();
    }

    
    // FIXME demo
    private static void loadDummyTypes() throws IOException {
        Block[] blocks;
        DefsParser parser;
        
        BufferedReader in = new BufferedReader(new FileReader("tests/generator/DummyTypes.defs"));

        parser = new DefsParser(in);
        blocks = parser.parseData();

        registerTypes(blocks);

        // debug
        for (int i = 0; i < blocks.length; i++) {
            System.out.println(blocks[i]);
        }
    }
   
    // FIXME demo
    private static void theRealThing() throws IOException {
        Block[] blocks;
        DefsParser parser;
        
        BufferedReader in = new BufferedReader(new FileReader("tests/generator/GtkButton.defs"));

        parser = new DefsParser(in);
        blocks = parser.parseData();

        registerTypes(blocks);

        // debug
        for (int i = 0; i < blocks.length; i++) {
            System.out.println(blocks[i]);
        }

        generateCode(blocks);
    }

    static void registerTypes(Block[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            Thing t;

            if (blocks[i] instanceof TypeBlock) {
                t = blocks[i].createThing();
                Thing.register(t);
            }
        }
    }

    static void generateCode(Block[] blocks) {
        /*
         * This is still in flux and a work in progress. For now, just send
         * one to stdout.
         */
        Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
        Writer sink = new StringWriter(); // for now

        PrintWriter java = new PrintWriter(out, true);
        PrintWriter jni = new PrintWriter(sink);

        for (int i = 0; i < blocks.length; i++) {
            Generator gen;

            gen = blocks[i].createGenerator();
            gen.writeJava(java);
            gen.writeC(jni);

            java.flush(); // hm
        }

        java.println("}");

        java.close();
        jni.close();
        
        // FIXME remove
        try {
            out.close();
            sink.close();
        } catch (IOException e) {
        }
      
    }
}
