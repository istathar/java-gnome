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
package org.gnome.gdk;

import org.gnome.glib.Boxed;

/**
 * An object describing a rectangular area. While superficially similar to
 * {@link org.gnome.gtk.Allocation Allocation}, this class is in fact
 * different. It's primary use is in describing an area that has been exposed
 * and needs to be [re]drawn. You normally get one of these from the
 * {@link EventExpose#getArea() getArea()} method on EventExpose, though in
 * rare situations you need to describe an area based on your own calculations
 * and there is a constructor for that case.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public final class Rectangle extends Boxed
{
    protected Rectangle(long pointer) {
        super(pointer);
    }

    /**
     * Create a Rectanlge. This is principally used so that you can describe
     * an area that needs to be redrawn, passing it to the
     * {@link Window#invalidate(Rectangle, boolean) invalidate()} method of an
     * [org.gnome.gdk] Window.
     * 
     * <p>
     * As usual, measurements are in pixels.
     * 
     * @since 4.0.8
     */
    public Rectangle(int x, int y, int width, int height) {
        super(GdkRectangleOverride.createRectangle(x, y, width, height));
    }

    protected void release() {
        GdkRectangleOverride.free(this);
    }

    /**
     * The width of the box described by this Rectangle.
     * 
     * @since 4.0.7
     */
    public int getWidth() {
        return GdkRectangle.getWidth(this);
    }

    /**
     * The height of the box described by this Rectangle.
     * 
     * @since 4.0.7
     */
    public int getHeight() {
        return GdkRectangle.getHeight(this);
    }

    /**
     * The horizontal co-ordinate of the top left corner of the box described
     * by this Rectangle.
     * 
     * @since 4.0.7
     */
    public int getX() {
        return GdkRectangle.getX(this);
    }

    /**
     * The vertical co-ordinate of the box described by this Rectangle.
     * 
     * @since 4.0.7
     */
    public int getY() {
        return GdkRectangle.getY(this);
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": " + getWidth() + "x" + getHeight() + " at " + getX()
                + "," + getY();
    }
}
