/*
 * ToolbarStyle.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c)      2009 Vreixo Formoso
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
 * Constants that can be used to customize the look-and-feel of a Toolbar, by
 * calling its {@link Toolbar#setStyle(ToolbarStyle) setStyle()} method.
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public final class ToolbarStyle extends Constant
{
    private ToolbarStyle(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Toolbar buttons will only display an icon.
     */
    public static ToolbarStyle ICONS = new ToolbarStyle(GtkToolbarStyle.ICONS, "ICONS");

    /**
     * Toolbar buttons will only display a text label.
     */
    public static ToolbarStyle TEXT = new ToolbarStyle(GtkToolbarStyle.TEXT, "TEXT");

    /**
     * Toolbar buttons will display both an icon and text label. The text will
     * appear below the icon.
     */
    public static ToolbarStyle BOTH = new ToolbarStyle(GtkToolbarStyle.BOTH, "BOTH");

    /**
     * Toolbar buttons will display both an icon and text label. The text will
     * appear alongside the icon.
     */
    public static ToolbarStyle BOTH_HORIZ = new ToolbarStyle(GtkToolbarStyle.BOTH_HORIZ, "BOTH_HORIZ");

}
