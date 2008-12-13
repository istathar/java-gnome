/*
 * PaperName.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Defined names for the standard paper types.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public class PaperName
{
    private final String name;

    private PaperName(String name) {
        this.name = name;
    }

    String valueOf() {
        return name;
    }

    public static final PaperName A4 = new PaperName("iso_a4");

    public static final PaperName LETTER = new PaperName("na_letter");

    /*
     * Does anyone really need the other ones?
     */
}
