/*
 * Justification.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
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
 * Constants that represent the justification of text.
 * 
 * @see Label#getJustify()
 * @see Label#setJustify(Justification)
 * 
 * @author Nat Pryce
 */
public final class Justification extends Constant
{
    public static final Justification LEFT = new Justification(GtkJustification.LEFT, "LEFT");

    public static final Justification RIGHT = new Justification(GtkJustification.RIGHT, "RIGHT");

    public static final Justification CENTER = new Justification(GtkJustification.CENTER, "CENTER");

    public static final Justification FILL = new Justification(GtkJustification.FILL, "FILL");

    private Justification(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
}
