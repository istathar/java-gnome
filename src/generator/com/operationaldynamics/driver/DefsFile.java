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
                if (t instanceof FundamentalThing) {
                    continue;
                }

                // As a Set it won't do duplicates. Ta-da.
                if (t.isBlacklisted()) {
                    types.add(Thing.lookup("Blacklist"));
                    types.add(Thing.lookup("FIXME"));
                } else {
                    types.add(t);
                }
            }
        }

        return types;
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
