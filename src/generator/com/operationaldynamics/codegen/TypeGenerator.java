/*
 * TypeGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintStream;

/**
 * Base class for the Generators which create the files for types we are
 * rendering into Java classes: GObjects, boxeds/structs, enums, etc
 * 
 * @author Andrew Cowie
 */
abstract class TypeGenerator extends Generator
{
    /**
     * Compose the copyright header common to all generated sources files.
     */
    /*
     * I truely cannot believe that I just converted a here doc into a series
     * of single line strings with \n at the end of each. Oh well, it's done
     * now, and this was really the only long block of text.
     */
    protected static void commonFileHeader(PrintStream out, String fileName) {
        out.print("/*\n");
        out.print(" * ");
        out.print(fileName);
        out.print("\n\n");
        out.print(" * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd\n");
        out.print(" *\n");
        out.print(" * The code in this file, and the library it is a part of, are made available\n");
        out.print(" * to you by the authors under the terms of the \"GNU General Public Licence,\n");
        out.print(" * version 2\" plus the \"Classpath Exception\" (you may link to this code as a\n");
        out.print(" * library into other programs provided you don't make a derivation of it).\n");
        out.print(" * See the LICENCE file for the terms governing usage and redistribution.\n");
        out.print(" *\n");
        out.print(" *                      THIS FILE IS GENERATED CODE!\n");
        out.print(" *\n");
        out.print(" * To modify its contents or behaviour, either update the generation program,\n");
        out.print(" * change the information in the source defs file, or implement an override for\n");
        out.print(" * this class.\n");
        out.print(" */\n");
    }
}
