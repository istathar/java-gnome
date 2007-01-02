/*
 * Box.java
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

/**
 * Base class for Containers which organize a variable number of Widgets into
 * a rectangular area. This is either a single row of child Widgets (in the
 * case of {@link HBox HBox}), or a single column (for the case of
 * {@link VBox VBox}). All the children of a Box are allocated one dimension
 * in common, being the height of a row, or the width of a column,
 * respectively.
 * 
 * @author Andrew Cowie
 * @since 4.0.1
 */
public abstract class Box extends Container
{
    protected Box(long pointer) {
        super(pointer);
    }

    /**
     * Add a Widget to the beginning of the Box, with default padding values.
     * With only the <code>child</code> Widget to specify, this is easy to
     * use and suffices for most cases.
     * 
     * <p>
     * <i>This is the same as calling
     * <code>packStart(child, true, true, 0)</code>; see the full
     * {@link #packStart(Widget, boolean, boolean, int) packStart()} for
     * details.</i>
     */
    public void packStart(Widget child) {
        GtkBox.packStart(this, child, false, false, 0);
    }

    /**
     * Add a Widget to the beginning of the Box. Widget child will be ordered
     * after any other Widgets that have already been packed with respect to
     * the start of the Box, but before any Widgets that are packed at the end
     * of the Box with {@link #packEnd() packEnd()}.
     * 
     * @param child
     *            the Widget to be added
     * @param expand
     *            Whether the new <code>child</code> is to be given extra
     *            space allocated to Box. The extra space will be divided
     *            evenly between all children of this Box that were added with
     *            <code>expand</code> set to <code>true</code>.
     * @param fill
     *            Whether space given to <code>child</code> by the
     *            <code>expand</code> option is actually allocated to child.
     *            If you specify <code>false</code> here, then any extra
     *            space will padding the Widget, rather than causing it to
     *            grow larger.
     * @param padding
     *            extra space (in pixels) to put between this child and its
     *            neighbours. This is over and above the global amount of
     *            padding that was specified by the <code>spacing</code>
     *            parameter when the Box was constructed. If
     *            <code>child</code> is the Widget at one of the start of
     *            the Box, then <code>padding</code> pixels are also put
     *            between the Widget and the leading edge.
     * 
     */
    public void packStart(Widget child, boolean expand, boolean fill, int padding) {
        GtkBox.packStart(this, child, expand, fill, padding);
    }
}
