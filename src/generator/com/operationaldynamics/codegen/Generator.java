/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;
import java.util.Locale;

import com.operationaldynamics.driver.DefsFile;

/**
 * Base class of the code generator classes. Also houses numerous utility
 * functions used to convert from the C names used in the .defs data to Java
 * style names according to our requirements.
 * 
 * <p>
 * An UnsupportedOperationException will bubbled out of the public writeXXX()
 * methods if they're not supposed to do anything. You can use that as a
 * signal not to write a file for that layer.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public abstract class Generator
{
    /**
     * A reference to the parsed defs data that this particular Block's
     * Generator came from.
     */
    protected final DefsFile data;

    protected Generator(final DefsFile data) {
        this.data = data;
    }

    public abstract void writePublicCode(PrintWriter out);

    /**
     * Generate Java code!
     */
    public abstract void writeTranslationCode(PrintWriter out);

    /**
     * Generate C code!
     */
    public abstract void writeJniCode(PrintWriter out);

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
            if (buf.length() > i) {
                ch = buf.charAt(i);
                buf.setCharAt(i, Character.toUpperCase(ch));
            }
        }

        return buf.toString();
    }

    public static String toPascalCase(String lowerCaseName) {
        StringBuffer buf;
        int i;
        char ch;

        buf = new StringBuffer(lowerCaseName);

        ch = buf.charAt(0);
        buf.setCharAt(0, Character.toUpperCase(ch));

        i = 1;

        while ((i = buf.indexOf("_", i)) != -1) {
            buf.deleteCharAt(i);
            if (buf.length() > i) {
                ch = buf.charAt(i);
                buf.setCharAt(i, Character.toUpperCase(ch));
            }
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
        return lowerCaseName.toUpperCase(Locale.ENGLISH).replace('-', '_');
    }
}
