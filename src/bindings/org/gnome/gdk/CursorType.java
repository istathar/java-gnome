/*
 * CursorType.java
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
package org.gnome.gdk;

import org.freedesktop.bindings.Flag;

/**
 * Constants representing the different forms of Cursor that can indicate
 * where the mouse is pointing. Note that this is distinct from that blinking
 * vertical bar that is the text position cursor in Entry and TextView
 * Widgets.
 * 
 * <p>
 * The "normal" Cursor is {@link #LEFT_PTR LEFT_PTR}. That's the one you want
 * to switch back to if you've changed the Cursor to something exceptional.
 * 
 * <p>
 * <i>The fact that "cursor" is used to name this class and the Cursor class
 * which manipulates them is a bit strange seeing as how the term used in user
 * interface design for the thing where your mouse is indicating is
 * "pointer".</i>
 * 
 * <p>
 * <i>It was quite tempting to mess with the constant names here; after all
 * <code>LEFT_PTR</code> is a poor substitute for "normal", but that would
 * screw up the algorithmic mapping of the underlying library that we have
 * worked so hard to maintain. The names are less than ideal in GDK, but so be
 * it.</i>.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.6
 */
/*
 * The underlying enum is a zoo, so this is just a start. Frankly, most of the
 * preexisting Cursor constants are completely unnecessary cruft left over
 * from the early days of X Windows. Yeech!
 */
public final class CursorType extends Flag
{
    private CursorType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The standard arrow pointer used by default for most user interface
     * purposes. This is the one you want to reset the cursor to if you have
     * been using {@link #WATCH WATCH} as the "busy" pointer.
     */
    public static final CursorType LEFT_PTR = new CursorType(GdkCursorType.LEFT_PTR, "LEFT_PTR");

    /**
     * This is the cursor you change to when you want to show that the
     * application is "busy"; be aware that different themes shipped by
     * various Linux distributions override it in many different ways.
     * 
     * @since 4.0.6
     */
    public static final CursorType WATCH = new CursorType(GdkCursorType.WATCH, "WATCH");

    /**
     * This is the standard "text" Cursor, used in Widgets which display or
     * edit text such as Entry and TextView.
     * 
     * @since 4.0.6
     */
    public static final CursorType XTERM = new CursorType(GdkCursorType.XTERM, "XTERM");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the top left corner of the
     * Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType TOP_LEFT_CORNER = new CursorType(GdkCursorType.TOP_LEFT_CORNER,
            "TOP_LEFT_CORNER");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the top right corner of the
     * Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType TOP_RIGHT_CORNER = new CursorType(GdkCursorType.TOP_RIGHT_CORNER,
            "TOP_RIGHT_CORNER");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the bottom left corner of the
     * Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType BOTTOM_LEFT_CORNER = new CursorType(GdkCursorType.BOTTOM_LEFT_CORNER,
            "BOTTOM_LEFT_CORNER");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the bottom right corner of the
     * Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType BOTTOM_RIGHT_CORNER = new CursorType(
            GdkCursorType.BOTTOM_RIGHT_CORNER, "BOTTOM_RIGHT_CORNER");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the bottom side of the Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType BOTTOM_SIDE = new CursorType(GdkCursorType.BOTTOM_SIDE, "BOTTOM_SIDE");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the top side of the Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType TOP_SIDE = new CursorType(GdkCursorType.TOP_SIDE, "TOP_SIDE");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the right side of the Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType RIGHT_SIDE = new CursorType(GdkCursorType.RIGHT_SIDE, "RIGHT_SIDE");

    /**
     * This Cursor is typically used with Widgets whose size can be changed
     * manually when the mouse pointer is near the left side of the Widget.
     * 
     * @since 4.0.12
     */
    public static final CursorType LEFT_SIDE = new CursorType(GdkCursorType.LEFT_SIDE, "LEFT_SIDE");

    /**
     * The Cursor used to indicate a "move" operation. It is used when moving
     * the mouse pointer would cause a Widget to be moved.
     * 
     * @since 4.0.12
     */
    public static final CursorType FLEUR = new CursorType(GdkCursorType.FLEUR, "FLEUR");

    /**
     * The hand, open. Suggests something that can be grabbed.
     * 
     * @since 4.0.14
     */
    public static final CursorType HAND1 = new CursorType(GdkCursorType.HAND1, "HAND1");

    /**
     * The hand, index finger pointing. Typically used to suggest a clickable
     * link.
     * 
     * @since 4.0.14
     */
    public static final CursorType HAND2 = new CursorType(GdkCursorType.HAND2, "HAND2");

    static final CursorType CURSOR_IS_PIXMAP = new CursorType(GdkCursorType.CURSOR_IS_PIXMAP,
            "CURSOR_IS_PIXMAP");
}
