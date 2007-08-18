/*
 * Alignment.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Control the alignment and size of a child Widget. It has four settings:
 * <var>xscale</var>, <var>yscale</var>, <var>xalign</var>, and <var>yalign</var>.
 * 
 * <p>
 * The scale settings are used to specify how much the child should expand to
 * fill the space allocated to the Alignment. The values can range from
 * <code>0.0f</code> (meaning the child doesn't expand at all) to
 * <code>1.0f</code> (meaning the child will expand to fill all of the
 * allocated space).
 * 
 * <p>
 * The alignment settings are used to place the child within the available
 * area. The values range from <code>0.0f</code> (top or left) to
 * <code>1.0f</code> (bottom or right). If both scale settings are set to
 * <code>1.0f</code>, the Alignment will have no effect on the child
 * Widget.
 * 
 * @author Nat Pryce
 * @since 4.0.4
 */
public class Alignment extends Bin
{
    protected Alignment(long pointer) {
        super(pointer);
    }

    /**
     * Creates an empty Alignment. The child Widget can later be added by
     * calling the {@link Container#add(Widget) add()} method.
     */
    public Alignment(float xalign, float yalign, float xscale, float yscale) {
        this(GtkAlignment.createAlignment(xalign, yalign, xscale, yscale));
    }

    /**
     * Creates an an Alignment for an existing child Widget.
     */
    public Alignment(float xalign, float yalign, float xscale, float yscale, Widget child) {
        this(GtkAlignment.createAlignment(xalign, yalign, xscale, yscale));
        add(child);
    }

    /**
     * Sets the padding on the different sides of the widget. The padding adds
     * blank space to the sides of the widget. For instance, this can be used
     * to indent the child widget towards the right by adding padding on the
     * left.
     */
    public void setPadding(int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        GtkAlignment.setPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
    }

    /**
     * Returns the padding to the top of the child widget
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
     * Returns the padding to the left of the child Widget
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
     * Returns the padding to the right of the child Widget
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
     * Sets the Alignment values. See the discussion at {@link Alignment top}
     * for the interpretation of the values. All parameters must be within the
     * range of <code>0.0f</code> to <code>1.0f</code>.
     */
    public void set(float xalign, float yalign, float xscale, float yscale) {
        GtkAlignment.set(this, xalign, yalign, xscale, yscale);
    }
}
