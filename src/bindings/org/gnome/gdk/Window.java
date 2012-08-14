/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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

import org.gnome.glib.Object;

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
 * What this <i>is</i> useful for is as a way to get to the state of top level
 * windows and various low level drawing functions.
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
public class Window extends Object
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
     * Window.
     * 
     * @param cursor
     *            Passing <code>null</code> will cause this Window to [revert
     *            to] using the Cursor default inherited from its parent.
     * @since 4.0.6
     */
    public void setCursor(Cursor cursor) {
        GdkWindow.setCursor(this, cursor);
    }

    /**
     * Get the horizontal position of this Window in terms of the root
     * Window's co-ordinates. The root window is what you might think of as
     * the desktop background, although in X terms it really is the parent of
     * all windows.
     * 
     * @since 4.0.6
     */
    public int getOriginX() {
        final int x[], y[];

        x = new int[1];
        y = new int[1];

        GdkWindow.getOrigin(this, x, y);

        return x[0];
    }

    /**
     * Get the vertical position of this Window in terms of the root Window's
     * co-ordinates.
     * 
     * @since 4.0.6
     */
    public int getOriginY() {
        final int x[], y[];

        x = new int[1];
        y = new int[1];

        GdkWindow.getOrigin(this, x, y);

        return y[0];
    }

    /**
     * Get the horizontal position of this Window relative to its parent
     * Window. Given that many Widgets draw directly on their parent's
     * <code>org.gnome.gdk</code> Window, you may at times be surprised at
     * what this reports.
     * 
     * @since 4.0.6
     */
    public int getPositionX() {
        final int x[], y[];

        x = new int[1];
        y = new int[1];

        GdkWindow.getPosition(this, x, y);

        return x[0];
    }

    /**
     * Get the vertical position of this Window relative to its parent Window.
     * 
     * @since 4.0.6
     */
    public int getPositionY() {
        final int x[], y[];

        x = new int[1];
        y = new int[1];

        GdkWindow.getPosition(this, x, y);

        return y[0];
    }

    /**
     * Mark the given area as damaged and needing redrawing. Calling this
     * method will ultimately result in <code>Widget.Draw</code> being emitted
     * on Widgets that are present in the area being invalidated.
     * 
     * @param recursive
     *            If <code>true</code>, calling this method will invalidate
     *            not only the described area in this [org.gnome.gdk] Window,
     *            but also the corresponding areas of any child
     *            [org.gnome.gdk] Windows that overlap it. This is mostly an
     *            implementation detail, but occasionally you need to find
     *            tune the control. We tend to use <code>true</code>.
     * @since 4.0.8
     */
    /*
     * TODO this needs a much stronger description, linked to wherever else we
     * end up discussing drawing, regions, and invalidation.
     */
    /*
     * If we expose Region then there will be an invalidate(Region, boolean)
     * in due course corresponding to this method, hence the name change to
     * invalidate().
     */
    public void invalidate(Rectangle area, boolean recursive) {
        GdkWindow.invalidateRect(this, area, recursive);
    }

    /**
     * Has this underlying resouce been mapped? This will return
     * <code>true</code> if <code>show()</code> has been called on the Widget
     * that draws on this [org.gnome.gdk] Window <i>and on all its parents, if
     * this Window happens to be a sub-Window.</i>
     * 
     * @since 4.0.10
     */
    public boolean isViewable() {
        return GdkWindow.isViewable(this);
    }

    /**
     * Force GDK to use the old X Window allocation behaviour for this Window.
     * This reverts the GDK Window backing a GTK Widget to the pre-2.18
     * behaviour, analogous to setting the <code>GDK_NATIVE_WINDOW</code>
     * environment variable but not nearly as invasive.
     * 
     * <p>
     * Theoretically you should not need to use this; the whole idea of the
     * "client-side windows" patch to GTK was to avoid having to use
     * unnecesssary intermediate X Windows, and calling this method
     * circumvents this optimization. Most GTK Widgets should perform just
     * fine without reverting to the old behaviour, but in isolated corner
     * cases it may be necessary to workaround obscure bugs.
     * 
     * @return This will return <code>false</code> should GDK be unable to
     *         allocate native resources as requested. The consequences of
     *         that are unclear.
     * @since 4.0.16
     */
    public boolean ensureNative() {
        return GdkWindow.ensureNative(this);
    }

    /**
     * Get the width of this Window.
     * 
     * @since 4.1.1
     */
    /*
     * TODO document the impact of this reporting the most recent
     * CONFIGURE_EVENT, not necesarily live X server information.
     */
    public int getWidth() {
        return GdkWindow.getWidth(this);
    }

    /**
     * Get the height of this Window.
     * 
     * @since 4.1.1
     */
    public int getHeight() {
        return GdkWindow.getHeight(this);
    }
}
