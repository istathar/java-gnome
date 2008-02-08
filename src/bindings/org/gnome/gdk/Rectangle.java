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
package org.gnome.gdk;

import org.freedesktop.bindings.Proxy;
import org.gnome.glib.Boxed;
import org.gnome.gtk.Allocation;

/**
 * An object describing a rectangular area. While superficially similar to
 * {@link Allocation}, this class is in fact different. It's primary use is
 * in describing an area that has been exposed and needs to be [re]drawn. You
 * get one of these from the {@link EventExpose#getArea() getArea()} method on
 * EventExpose.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
/*
 * FUTURE WARNING This implementation is a bit of a hack and is currently
 * operating on the assumption that the only origin for these is the override
 * code in EventExpose's getArea(). There *are* other scenarios where these
 * are returned, so be careful. Whatever you do get this from, make sure you
 * set the origin field.
 */
public final class Rectangle extends Boxed
{
    /**
     * Back reference to the enclosing struct.
     */
    Proxy origin;

    protected Rectangle(long pointer) {
        super(pointer);
    }

    protected void release() {
        origin = null;
    }

    /**
     * The width of the box described by this Rectangle.
     */
    public int getWidth() {
        return GdkRectangle.getWidth(this);
    }

    /**
     * The height of the box described by this Rectangle.
     */
    public int getHeight() {
        return GdkRectangle.getHeight(this);
    }

    /**
     * The horizontal co-ordinate of the top left corner of the box described
     * by this Rectangle.
     */
    public int getX() {
        return GdkRectangle.getX(this);
    }

    /**
     * The vertical co-ordinate of the box described by this Rectangle.
     */
    public int getY() {
        return GdkRectangle.getY(this);
    }
}
