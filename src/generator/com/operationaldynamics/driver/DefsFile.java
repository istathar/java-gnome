/*
 * DefsFile.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.driver;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.operationaldynamics.codegen.FixmeThing;
import com.operationaldynamics.codegen.FundamentalThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.defsparser.Block;
import com.operationaldynamics.defsparser.TypeBlock;

/**
 * A wrapper class representing a parsed .defs file that contained a single
 * native type. We use this to provide a clean mechanism to pass data between
 * the parser and generator parts of the program.
 * 
 * TODO: needs a better name.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public final class DefsFile
{
    /**
     * The type that this class represents.
     */
    private final Thing forObject;

    /**
     * The source array of parsed .defs Blocks
     */
    private final Block[] blocks;

    /**
     * Create a new wrapper class representing the parsed .defs file for a
     * single type. Along the way this registers the type in the Thing
     * database.
     * 
     * @throws ImproperDefsFileException
     *             if the supplised blocks array doesn't match our
     *             expectations for ordering.
     */
    public DefsFile(final Block[] blocks) throws ImproperDefsFileException {
        this.blocks = blocks;

        if (blocks.length == 0) {
            throw new ImproperDefsFileException("No data was parsed in this defs file");
        }

        if (!(blocks[0] instanceof TypeBlock)) {
            throw new ImproperDefsFileException("First block in defs file didn't describe a type");
        }

        this.forObject = blocks[0].createThing();
        Thing.register(forObject);
    }

    public final Thing getType() {
        return forObject;
    }

    /**
     * Iterate over all the Blocks in this DefsFile and return a Set with the
     * Thing for each one. Assumes all types already registered. This is used
     * to come up with the required set of Java import statements, see {@link 
     */
    public Set usesTypes() {
        final Set types;
        Iterator iter;

        types = new HashSet();

        for (int i = 0; i < blocks.length; i++) {
            List things;

            things = blocks[i].usesTypes();

            iter = things.iterator();
            while (iter.hasNext()) {
                Thing t = (Thing) iter.next();

                // As a Set it won't do duplicates. Ta-da.
                if (t.isBlacklisted()) {
                    types.add(Thing.lookup("Blacklist"));
                    types.add(Thing.lookup("FIXME"));
                } else if (t instanceof FundamentalThing) {
                    continue;
                } else {
                    types.add(t);
                }
            }
        }

        return types;
    }

    /**
     * Generate a stub for the public API class that corresponds to a type
     * defined in a given .defs file so that the generated translation layer
     * classes will compile.
     * 
     * <p>
     * <b>WARNING</b>
     * <p>
     * This is <b>not</b> for regular use. It is only here for the very rare
     * occasions when we get a new set of .defs data describing an entirely
     * new native library which we are adding to java-gnome for the first
     * time. In such an event, it will be used <b>once and only once</b> to
     * create the necessary stubs.
     * 
     * <p>
     * <i>The presence of this method and associated hooks in TypeGenerators
     * are not an invitation to automatically write public API methods. Those
     * <b>must</b> be written by hand: only a human can make the appropirate
     * choices about whether to expose a method in the java-gnome's public
     * API, and with what signature. This is a defining characteristic for
     * achieving our project's approachability goal; coverage alone is
     * meaningless (and indeed harmful) if not done with exquisite care. See
     * {@link org.gnome.gtk.Button#setAlignmentX() Button's setAlignmentX()}
     * (where we changed the method name slightly for good reason) and
     * {@link org.gnome.gtk.FileChooser#getURI() FileChooser's getURI()} where
     * we bridge from strings to a more appropriate Java type in the return
     * value) as examples.</i>
     * 
     * @param out
     *            the destination for the generated code.
     */
    /*
     * In other words, if you're not the maintainer of java-gnome, don't
     * touch. I'm really not kidding. Any patches changing the visibility of
     * this method or adding to its cability will be immediately rejected
     * without further discussion. Thank you.
     */
    public final void generatePublicLayer(final PrintWriter out) {
        Generator gen;

        gen = blocks[0].createGenerator(this);
        gen.writePublicCode(out);
    }

    /**
     * Generate the Java code that goes with a given .defs file. This is the
     * main entry point into the Java side of the code generator classes, and
     * runs through the sequence of iterating across the Blocks and
     * instantiating the appropriate generator for each one.
     * 
     * @param out
     *            the destination for the generated code. [This is
     *            parameterzied to facilitate testing and debugging]
     */
    public final void generateTranslationLayer(final PrintWriter out) {
        Generator gen;

        for (int i = 0; i < blocks.length; i++) {
            gen = blocks[i].createGenerator(this);
            gen.writeTranslationCode(out);

            out.flush(); // FIXME hmm
        }
        out.println("}\n");
    }

    /**
     * Generate the JNI layer code that goes with a given .defs file. This is
     * the other main entry point into the code generator classes, and runs
     * through the sequence of iterating across the Blocks and instantiating
     * the appropriate generator for each one.
     * 
     * @param out
     *            the destination for the generated code. [This is
     *            parameterzied to facilitate testing and debugging]
     */
    public final void generateJniLayer(final PrintWriter out) {
        Generator gen;

        for (int i = 0; i < blocks.length; i++) {
            gen = blocks[i].createGenerator(this);
            gen.writeJniCode(out);

            out.flush(); // FIXME hmm
        }
    }
}
