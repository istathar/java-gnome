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
package com.operationaldynamics.codegen;

import java.io.PrintWriter;

/**
 * Base class of the code generator classes. Also houses numerous utility
 * functions used to convert from the C names used in the .defs data to Java
 * style names according to our requirements.
 * 
 * @author Andrew Cowie
 */
public abstract class Generator
{

    /**
     * Generate Java code!
     * 
     * @param out
     *      Where code is to be written
     * @return
     *      <code>true</code> if some code has been written, 
     *      <code>false</code> otherwise. This can be used to decide wheter
     *      to create the .java file or not.
     */
    public abstract boolean writeJavaCode(PrintWriter out);
    
    /**
     * Generate C (JNI) code.
     * 
     * @param out
     *      Where code is to be written
     * @return
     *      <code>true</code> if some code has been written, 
     *      <code>false</code> otherwise. This can be used to decide wheter
     *      to create the .c file or not.
     */
    public abstract boolean writeCCode(PrintWriter out);
    
    /**
     * Turn "org.gnome.glib", "GtkButton" into "org_gnome_glib_GtkButton"
     */
    protected static final String encodeJavaClassName(String javaPackageName, String javaClassName) {

        return javaPackageName.replace('.', '_') + "_" + javaClassName;
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

        // TODO this method can be written like this from java 1.5
        // return javaClassName.replace("_", "_1");
        // From 1.4 exists a similar function, that uses regular expresions.
        // This leads to simpler code, but probably more inefficient, so I
        // prefer current code
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

    /**
     * We map a number of .defs file entities from lower case with a hyphen to
     * upper case with an underscore. Notable examples are enums, where we map
     * from "select-folder" -> "SELECT_FOLDER", and signal names, such as
     * "delete-event" -> "DELETE_EVENT". This helper function does it for us.
     */
    protected static final String toAllCaps(String lowerCaseName) {
        return lowerCaseName.toUpperCase().replace('-', '_');
    }
}
