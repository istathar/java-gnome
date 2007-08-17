/*
 * Alignment.java
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
 * The Alignment widget controls the alignment and size of its child widget.
 * It has four settings: xscale, yscale, xalign, and yalign.
 * 
 * <p>
 * The scale settings are used to specify how much the child widget should
 * expand to fill the space allocated to the Alignment. The values can range
 * from 0 (meaning the child doesn't expand at all) to 1 (meaning the child
 * expands to fill all of the available space).
 * </p>
 * 
 * <p>
 * The align settings are used to place the child widget within the available
 * area. The values range from 0 (top or left) to 1 (bottom or right). Of
 * course, if the scale settings are both set to 1, the alignment settings
 * have no effect.
 * </p>
 * 
 * @author Nat Pryce
 * @since 4.0.4
 */
public class Alignment extends Bin
{
    /**
     * Creates an empty Alignment.  The child widget can later be added
     * by calling the {@link #add} method.
     * 
     * @param xalign
     *            the horizontal alignment of the child widget, from 0 (left)
     *            to 1 (right).
     * @param yalign
     *            the vertical alignment of the child widget, from 0 (top) to
     *            1 (bottom).
     * @param xscale
     *            the amount that the child widget expands horizontally to
     *            fill up unused space, from 0 to 1. A value of 0 indicates
     *            that the child widget should never expand. A value of 1
     *            indicates that the child widget will expand to fill all of
     *            the space allocated for the Alignment.
     * @param yscale
     *            the amount that the child widget expands vertically to fill
     *            up unused space, from 0 to 1. A value of 0 indicates that
     *            the child widget should never expand. A value of 1 indicates
     *            that the child widget will expand to fill all of the space
     *            allocated for the Alignment.
     */
    public Alignment(float xalign, float yalign, float xscale, float yscale) {
        this(GtkAlignment.createAlignment(xalign, yalign, xscale, yscale));
    }

    /**
     * Creates an an Alignment for an existing child widget.
     * @param xalign
     *            the horizontal alignment of the child widget, from 0 (left)
     *            to 1 (right).
     * @param yalign
     *            the vertical alignment of the child widget, from 0 (top) to
     *            1 (bottom).
     * @param xscale
     *            the amount that the child widget expands horizontally to
     *            fill up unused space, from 0 to 1. A value of 0 indicates
     *            that the child widget should never expand. A value of 1
     *            indicates that the child widget will expand to fill all of
     *            the space allocated for the Alignment.
     * @param yscale
     *            the amount that the child widget expands vertically to fill
     *            up unused space, from 0 to 1. A value of 0 indicates that
     *            the child widget should never expand. A value of 1 indicates
     *            that the child widget will expand to fill all of the space
     *            allocated for the Alignment.
     * @param child
     *            the child widget to align.
     */
    public Alignment(float xalign, float yalign, float xscale, float yscale, Widget child) {
        this(GtkAlignment.createAlignment(xalign, yalign, xscale, yscale));
        add(child);
    }
    
    protected Alignment(long pointer) {
        super(pointer);
    }

    /**
     * Sets the padding on the different sides of the widget. The padding adds
     * blank space to the sides of the widget. For instance, this can be used
     * to indent the child widget towards the right by adding padding on the
     * left.
     * 
     * @param paddingTop
     *            the padding at the top of the widget
     * @param paddingBottom
     *            the padding at the bottom of the widget
     * @param paddingLeft
     *            the padding at the left of the widget
     * @param paddingRight
     *            the padding at the right of the widget.
     */
    public void setPadding(int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        GtkAlignment.setPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
    }

    /**
     * Returns the padding to the top of the child widget
     * 
     * @return the padding to the top of the child widget
     */
    public int getPaddingTop() {
        int[] paddingTop = new int[0];
        int[] paddingBottom = new int[0];
        int[] paddingLeft = new int[0];
        int[] paddingRight = new int[0];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingTop[0];
    }

    /**
     * Returns the padding to the bottom of the child widget
     * 
     * @return the padding to the bottom of the child widget
     */
    public int getPaddingBottom() {
        int[] paddingTop = new int[0];
        int[] paddingBottom = new int[0];
        int[] paddingLeft = new int[0];
        int[] paddingRight = new int[0];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingBottom[0];
    }

    /**
     * Returns the padding to the left of the child widget
     * 
     * @return the padding to the left of the child widget
     */
    public int getPaddingLeft() {
        int[] paddingTop = new int[0];
        int[] paddingBottom = new int[0];
        int[] paddingLeft = new int[0];
        int[] paddingRight = new int[0];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingLeft[0];
    }

    /**
     * Returns the padding to the right of the child widget
     * 
     * @return the padding to the right of the child widget
     */
    public int getPaddingRight() {
        int[] paddingTop = new int[0];
        int[] paddingBottom = new int[0];
        int[] paddingLeft = new int[0];
        int[] paddingRight = new int[0];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingRight[0];
    }

    /**
     * Sets the Alignment values.
     * 
     * @param xalign
     *            the horizontal alignment of the child widget, from 0 (left)
     *            to 1 (right).
     * @param yalign
     *            the vertical alignment of the child widget, from 0 (top) to
     *            1 (bottom).
     * @param xscale
     *            the amount that the child widget expands horizontally to
     *            fill up unused space, from 0 to 1. A value of 0 indicates
     *            that the child widget should never expand. A value of 1
     *            indicates that the child widget will expand to fill all of
     *            the space allocated for the Alignment.
     * @param yscale
     *            the amount that the child widget expands vertically to fill
     *            up unused space, from 0 to 1. A value of 0 indicates that
     *            the child widget should never expand. A value of 1 indicates
     *            that the child widget will expand to fill all of the space
     *            allocated for the Alignment.
     */
    public void set(float xalign, float yalign, float xscale, float yscale) {
        GtkAlignment.set(this, xalign, yalign, xscale, yscale);
    }
}
