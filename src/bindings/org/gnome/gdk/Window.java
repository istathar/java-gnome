/*
 * Window.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

/**
 * The underlying native resource driving a rectangular region on a screen.
 * These are notable as being what powers the display of Widgets, being both
 * that upon which drawing is done, and that to which user actions are
 * delivered.
 * 
 * <p>
 * In theory, some, but not all, Widgets have their own native windows to draw
 * upon and from whence events originate. Thus Widgets such as Buttons have
 * their own underlying native resource, whereas Labels and various Containers
 * do not. In practise, this distinction is blurred and newer versions of GTK
 * do all sorts of strange things under the hood for greater efficiency. You
 * don't need to worry about any of this.
 * 
 * <p>
 * These are wrappers around Xlib's <code>Window</code> object. They can be
 * arranged in tree structures, wrapping and overlapping one another, with
 * parents cropping children, etc. You don't need to worry about any of this
 * either.
 * 
 * <p>
 * What this <i>is</i> useful for is as a way to get to the state of top
 * level windows and various low level drawing functions.
 * 
 * <p>
 * <i>Since the C name of this class is <code>GdkWindow</code>, the
 * unavoidable consequence of the java-gnome mapping algorithm is that the
 * name of this class Java is Window. This can be a bit of a pain if you're
 * working in a piece of code where</i> <code>org.gnome.gtk.Window</code>
 * <i>is already imported, but c'est la vie.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.4
 */
public class Window extends Drawable
{
    protected Window(long pointer) {
        super(pointer);
    }

    /**
     * Return a flags object representing the current state of the Window
     * (maximized, iconified, etc). See {@link WindowState WindowState} for
     * the individual constants, obviously, but be aware that an unmapped
     * Window will have no WindowState bits set.
     * 
     * @since 4.0.4
     */
    public WindowState getState() {
        return GdkWindow.getState(this);
    }

    /**
     * Set the Cursor that will be shown when the pointer hovers over this
     * Drawable Window.
     * 
     * @param cursor
     *            Passing <code>null</code> will cause this Window to
     *            [revert to] using the Cursor default inherited from its
     *            parent.
     * @since 4.0.6
     */
    public void setCursor(Cursor cursor) {
        GdkWindow.setCursor(this, cursor);
    }
}
