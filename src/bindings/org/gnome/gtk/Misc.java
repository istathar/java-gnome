/*
 * Misc.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Base class for Widgets that have notions of alignment and padding.
 * 
 * <p>
 * The horizontal and vertical padding attributes allows extra space to be
 * added around the widget.
 * </p>
 * 
 * <p>
 * The horizontal and vertical alignment attributes enable the widget to be
 * positioned within its allocated area. Note that if the widget is added to a
 * container in such a way that it expands automatically to fill its allocated
 * area, the alignment settings will not alter the widgets position.
 * </p>
 * 
 * @author Andrew Cowie
 * @author Nat Pryce
 * @since 4.0.0
 */
public abstract class Misc extends Widget
{
    protected Misc(long pointer) {
        super(pointer);
    }

    /**
     * Sets the alignment of the widget.
     * 
     * @param xalign
     *            the horizontal alignment, from 0 (left) to 1 (right).
     * @param yalign
     *            the vertical alignment, from 0 (top) to 1 (bottom).
     */
    public void setAlignment(float xalign, float yalign) {
        GtkMisc.setAlignment(this, xalign, yalign);
    }

    /**
     * Gets the horizontal alignment of the widget within its allocation.
     * 
     * @return the horizontal alignment, from 0 (left) to 1 (right).
     * @see {@link #setAlignment()}
     */
    public float getAlignmentX() {
        float[] xalign = new float[1];
        float[] yalign = new float[1];
        getAlignment(xalign, yalign);
        return xalign[0];
    }

    /**
     * Gets the vertical alignment of the widget within its allocation.
     * 
     * @return the vertical alignment, from 0 (top) to 1 (bottom).
     * @see {@link #setAlignment()}
     */
    public float getAlignmentY() {
        float[] xalign = new float[1];
        float[] yalign = new float[1];
        getAlignment(xalign, yalign);
        return yalign[0];
    }

    private void getAlignment(float[] xalign, float[] yalign) {
        GtkMisc.getAlignment(this, xalign, yalign);
    }

    /**
     * Sets the amount of space to add around the widget.
     * 
     * @param xpad
     *            the amount of space to add on the left and right of the
     *            widget, in pixels.
     * @param ypad
     *            the amount of space to add on the top and bottom of the
     *            widget, in pixels.
     */
    public void setPadding(int xpad, int ypad) {
        GtkMisc.setPadding(this, xpad, ypad);
    }

    /**
     * Returns the horizontal padding of the widget,
     * 
     * @return amount of space to add on the left and right of the widget, in
     *         pixels.
     */
    public int getPaddingX() {
        int[] xpad = new int[1];
        int[] ypad = new int[1];
        getPadding(xpad, ypad);
        return xpad[0];
    }

    /**
     * Returns the vertical padding of the widget,
     * 
     * @return amount of space to add on the top and bottom of the widget, in
     *         pixels.
     */
    public int getPaddingY() {
        int[] xpad = new int[1];
        int[] ypad = new int[1];
        getPadding(xpad, ypad);
        return ypad[0];
    }

    private void getPadding(int[] xpad, int[] ypad) {
        GtkMisc.getPadding(this, xpad, ypad);
    }
}
