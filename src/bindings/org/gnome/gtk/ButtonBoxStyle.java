/*
 * ButtonBoxStyle.java
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
 * The style used to layout buttons in a {@link ButtonBox}.
 * 
 * @see HButtonBox
 * @see VButtonBox
 */
public class ButtonBoxStyle extends Constant
{
    /**
     * Default packing
     */
    public static final ButtonBoxStyle DEFAULT_STYLE = new ButtonBoxStyle(
            GtkButtonBoxStyle.DEFAULT_STYLE, "DEFAULT_STYLE");

    /**
     * Buttons are evenly spread across the box.
     */
    public static final ButtonBoxStyle SPREAD = new ButtonBoxStyle(GtkButtonBoxStyle.SPREAD, "SPREAD");

    /**
     * Buttons are placed at the edges of the box.
     */
    public static final ButtonBoxStyle EDGE = new ButtonBoxStyle(GtkButtonBoxStyle.EDGE, "EDGE");

    /**
     * Buttons are grouped towards the start of the box, (on the left for a
     * HBox, or the top for a VBox).
     */
    public static final ButtonBoxStyle START = new ButtonBoxStyle(GtkButtonBoxStyle.START, "START");

    /**
     * Buttons are grouped towards the end of the box, (on the right for a
     * HBox, or the bottom for a VBox).
     */
    public static final ButtonBoxStyle END = new ButtonBoxStyle(GtkButtonBoxStyle.END, "END");

    private ButtonBoxStyle(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
}
