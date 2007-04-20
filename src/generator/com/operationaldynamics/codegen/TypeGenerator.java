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
    protected static String commonFileHeader(String fileName) {
        StringBuffer buf;

        buf = new StringBuffer(750);

        buf.append("/*\n");

        buf.append(" * ");
        buf.append(fileName);
        buf.append("\n\n");
        buf.append(" * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd\n");
        buf.append(" *\n");
        buf.append(" * The code in this file, and the library it is a part of, are made available\n");
        buf.append(" * to you by the authors under the terms of the \"GNU General Public Licence,\n");
        buf.append(" * version 2\" plus the \"Classpath Exception\" (you may link to this code as a\n");
        buf.append(" * library into other programs provided you don't make a derivation of it).\n");
        buf.append(" * See the LICENCE file for the terms governing usage and redistribution.\n");
        buf.append(" *\n");
        buf.append(" *                      THIS FILE IS GENERATED CODE!\n");
        buf.append(" *\n");
        buf.append(" * To modify its contents or behaviour, either update the generation program,\n");
        buf.append(" * change the information in the source defs file, or implement an override for\n");
        buf.append(" * this class.\n");
        buf.append(" */\n");

        return buf.toString();
    }

}
