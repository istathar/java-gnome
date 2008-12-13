/*
 * Unit.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Constants describing different units which are be used when giving the
 * dimensions of a piece of paper. Used when getting dimensions from a
 * {@link PaperSize}.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public final class Unit extends Constant
{
    private Unit(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Size in "points", which are defined as 1/72<sup>nd</sup> of an inch,
     * which works out to 35.2 milimetres.
     */
    public static final Unit POINTS = new Unit(GtkUnit.POINTS, "POINTS");

    /**
     * Size in milimetres, which are 1/10<sup>th</sup> of a centimetre and
     * 1/1000<sup>th</sup> of a metre.
     */
    public static final Unit MM = new Unit(GtkUnit.MM, "MM");

    /*
     * There are a few others...
     */
}
