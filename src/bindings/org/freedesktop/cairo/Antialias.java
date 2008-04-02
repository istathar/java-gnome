/*
 * Antialias.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * The type of antialiasing to do when rendering text or shapes.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public class Antialias extends Constant
{
    private Antialias(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Use the default antialiasing for the subsystem and target device.
     */
    public static final Antialias DEFAULT = new Antialias(CairoAntialias.DEFAULT, "DEFAULT");

    /**
     * Perform single-color antialiasing (using shades of gray for black text
     * on a white background, for example).
     */
    public static final Antialias GRAY = new Antialias(CairoAntialias.GRAY, "GRAY");

    /**
     * Use a bilevel alpha mask.
     */
    public static final Antialias NONE = new Antialias(CairoAntialias.NONE, "NONE");

    /**
     * Perform antialiasing by taking advantage of the order of subpixel
     * elements on devices such as LCD panels
     */
    public static final Antialias SUBPIXEL = new Antialias(CairoAntialias.SUBPIXEL, "SUBPIXEL");
}
