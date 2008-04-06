/*
 * Style.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.freedesktop.bindings.Constant;

/**
 * Possible slant styles of a Font.
 * 
 * <p>
 * Note that most fonts will either have a ITALIC Style or an OBLIQUE Style,
 * but not both, and font matching in Pango will match italic specifications
 * with oblique fonts and vice-versa if an exact match is not found.
 * 
 * @author Vreixo Formoso
 * @since 4.0.8
 */
public final class Style extends Constant
{
    private Style(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The font is upright.
     */
    public final static Style NORMAL = new Style(PangoStyle.NORMAL, "NORMAL");

    /**
     * The font is slanted, but in a roman Style.
     */
    public final static Style OBLIQUE = new Style(PangoStyle.OBLIQUE, "OBLIQUE");

    /**
     * The font is slanted in an italic Style.
     */
    public final static Style ITALIC = new Style(PangoStyle.ITALIC, "ITALIC");
}
