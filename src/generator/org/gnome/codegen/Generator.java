/*
 * Generator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.codegen;

import java.io.PrintStream;

/**
 * Base class of the code generator classes. Also houses numerous utility
 * functions used to convert from the C names used in the .defs data to Java
 * style names according to our requirements.
 * 
 * @author Andrew Cowie
 */
abstract class Generator
{
    abstract void writeJava(PrintStream out);

    abstract void writeC(PrintStream out);

    
    /**
     * Turn "org.gnome.glib", "GtkButton" into "org_gnome_glib_GtkButton"
     */
    protected static final String encodeJavaClassName(String javaPackageName, String javaClassName) {
        StringBuffer buf;
        int i;

        buf = new StringBuffer(javaPackageName);
        i = 0;

        while ((i = buf.indexOf(".", i)) != -1) {
            buf.setCharAt(i, '_');
        }

        buf.append("_");
        buf.append(javaClassName);

        return buf.toString();
    }

    /**
     * Turn "gtk_button_set_label" into "gtk_1button_1set_1label"
     */
    protected static final String encodeJavaMethodName(String javaClassName) {
        StringBuffer buf;
        int i;

        buf = new StringBuffer(javaClassName);
        i = 0;

        while ((i = buf.indexOf("_", i)) != -1) {
            i++;
            buf.insert(i, '1');
        }

        return buf.toString();
    }

    /**
     * Convert a short ["python"] name to the camel case used by convention in
     * the Java world.
     */
    protected static final String toCamel(String shortName) {
        StringBuffer buf;
        int i;
        char ch;

        buf = new StringBuffer(shortName);
        i = 0;

        while ((i = buf.indexOf("_", i)) != -1) {
            buf.deleteCharAt(i);
            ch = buf.charAt(i);
            buf.setCharAt(i, Character.toUpperCase(ch));
        }

        return buf.toString();
    }
}
