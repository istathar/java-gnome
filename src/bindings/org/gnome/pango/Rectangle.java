/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.pango;

import org.gnome.glib.Boxed;

/**
 * Information about the size of an area rendered by Pango. These are returned
 * by the various extents methods in Layout and related classes. See
 * LayoutLine's {@link LayoutLine#getExtentsLogical() getExtentsLogical()} for
 * an example.
 * 
 * <p>
 * The origin of a Rectangle is the base line of the rendered glyphs, with
 * positive directions being to the right and down. This means that in
 * left-to-right text, a Rectangle representing a glyph that lies above the
 * base line (which most do) will have a negative <code>y</code> value.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
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
        return PangoRectangle.getWidth(this) / Pango.SCALE;
    }

    /**
     * The height of the box described by this Rectangle.
     * 
     * @since 4.0.10
     */
    public double getHeight() {
        return PangoRectangle.getHeight(this) / Pango.SCALE;
    }

    /**
     * The horizontal co-ordinate of the top left corner of the box described
     * by this Rectangle.
     * 
     * @since 4.0.10
     */
    public double getX() {
        return PangoRectangle.getX(this) / Pango.SCALE;
    }

    /**
     * The vertical co-ordinate of the box described by this Rectangle.
     * 
     * @since 4.0.10
     */
    public double getY() {
        return PangoRectangle.getY(this) / Pango.SCALE;
    }

    /*
     * these are a series of conveniences and equate to various C side macros.
     */

    /**
     * Get the <var>ascent</var>, which is the distance that this Rectangle
     * [describing one or more glyphs] rises above the font's base line.
     * 
     * @since 4.0.10
     */
    public double getAscent() {
        return -getY();
    }

    /**
     * Get the <var>descent</var>, which is the distance that this Rectangle
     * [describing one or more glyphs] descends below the font's base line.
     * 
     * @since 4.0.10
     */
    public double getDescent() {
        return getHeight() + getY();
    }

    private static String oneDecimal(double d) {
        return String.format("%1.1f", d);
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": " + oneDecimal(getWidth()) + "x"
                + oneDecimal(getHeight()) + " at " + oneDecimal(getX()) + "," + oneDecimal(getY());
    }
}
