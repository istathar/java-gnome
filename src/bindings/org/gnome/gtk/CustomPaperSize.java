/*
 * CustomPaperSize.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import java.util.Locale;

/**
 * Page sizes different than the established standards available as constants
 * in the PaperSize, InternationalPaperSize, and NorthAmericanPaperSize
 * classes.
 * 
 * <p>
 * 
 * <pre>
 * paper = new CustomPaperSize(&quot;Business Card&quot;, 90, 55, Unit.MM);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 */
public class CustomPaperSize extends PaperSize
{
    /**
     * Create a PaperSize constant (or, at least, once you've created one you
     * don't need to create any more) for a custom size.
     * 
     * <p>
     * The <code>width</code> and <code>height</code> parameters are in terms
     * of <code>units</code>, obviously. Note that you must use a physical
     * measurement; you can't specify pixels.
     * 
     * <p>
     * You need to specify a human readable display name describing this
     * PaperSize in a word or two. You should mark it for translation. Try
     * <code>"Custom"</code> if you're lacking for inspiration.
     * 
     * @since 4.0.14
     */
    /*
     * We don't expose internal identifier strings or ordinals in java-gnome.
     * So, to create the handle we need for the first argument of the native
     * constructor function, munge the given display name into something
     * identifier-ish.
     */
    public CustomPaperSize(String name, double width, double height, Unit units) {
        super(GtkPaperSize.createPaperSizeCustom(mungeIntoHandle(name), name, width, height,
                checkUnits(units)));
    }

    private static String mungeIntoHandle(String name) {
        String str;

        if ((name == null) || (name.equals(""))) {
            throw new IllegalArgumentException("Must specify a human readable display name");
        }

        str = name.toLowerCase(Locale.ENGLISH);
        str = str.replaceAll(" ", "_");

        return str;
    }

    private static Unit checkUnits(Unit units) {
        if (units == Unit.PIXEL) {
            throw new IllegalArgumentException(
                    "Size can't be specified in pixels. You have to use a physical measurement");
        }
        return units;
    }
}
