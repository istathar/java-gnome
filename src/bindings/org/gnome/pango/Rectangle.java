/*
 * Rectangle.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.gnome.glib.Boxed;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public final class Rectangle extends Boxed
{
    protected Rectangle(long pointer) {
        super(pointer);
    }

    Rectangle() {
        super(PangoRectangleOverride.createRectangle());
    }

    protected void release() {
        PangoRectangleOverride.free(this);
    }

    /**
     * The width of the box described by this Rectangle.
     * 
     * @since 4.0.10
     */
    public double getWidth() {
        return ((double) PangoRectangle.getWidth(this)) / Pango.SCALE;
    }

    /**
     * The height of the box described by this Rectangle.
     * 
     * @since 4.0.10
     */
    public double getHeight() {
        return ((double) PangoRectangle.getHeight(this)) / Pango.SCALE;
    }

    /**
     * The horizontal co-ordinate of the top left corner of the box described
     * by this Rectangle.
     * 
     * @since 4.0.10
     */
    public double getX() {
        return ((double) PangoRectangle.getX(this)) / Pango.SCALE;
    }

    /**
     * The vertical co-ordinate of the box described by this Rectangle.
     * 
     * @since 4.0.10
     */
    public double getY() {
        return ((double) PangoRectangle.getY(this)) / Pango.SCALE;
    }

    private static String oneDecimal(double d) {
        return String.format("%5.1f", d);
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": " + oneDecimal(getWidth()) + " x "
                + oneDecimal(getHeight()) + " at " + oneDecimal(getX()) + ", " + oneDecimal(getY());
    }
}
