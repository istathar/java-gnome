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
 * "Claspath Exception"), the copyright holders of this library give you
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
 * That which indicateth where your mouse is pointing!
 * 
 * <p>
 * A Cursor object must be bound to a underlying Window before [changing it]
 * will actually cause what the user sees to change; see
 * {@link Window#setCursor(Cursor) setCursor()} on the Window here in
 * <code>org.gnome.gdk</code>.
 * 
 * <p>
 * Quite frequently you want to change the Cursor for the entire application
 * (in a manner reminiscent of modal behaviour). This is trickier than it
 * should be, but you've got a couple possibilities. You can either
 * <ul>
 * <li>maintain a list of all the significant <code>[org.gnome.gtk]</code>
 * Windows being displayed by your application and then call
 * <code>getWindow().setCursor(BLAH)</code> on each of them, or you can
 * <li>use {@link Window#getToplevels() getToplevels()} on
 * <code>[org.gnome.gdk]</code> Window and then similarly just call
 * <code>setCursor(BLAH)</code> as you iterate over the returned set.
 * </ul>
 * The first option is a bit more cumbersome, but many people find themselves
 * maintaining a list of "major" Windows for other purposes, so it can serve.
 * 
 * <p>
 * Almost inevitably the Cursor your want is {@link Cursor#BUSY BUSY} which is
 * the spinning "busy" pointer. You can revert to "normal" by setting
 * {@link Cursor#NORMAL NORMAL} directly (which is the default cursor you
 * spend most of your time looking at), or by passing <code>null</code> to
 * <code>setCursor()</code>.
 * 
 * <p>
 * Note that different theme engines (let alone different Linux vendors) tend
 * to screw with the default pointer icons set quite a bit, so you may find
 * that pointers appear very different for users on different distributions.
 * 
 * <p>
 * <i>Our implementation of Cursor assumes you want to manipulate
 * <code>GdkCursors</code> on the "default" <code>GdkDisplay</code>. Where
 * else would you be working?</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public final class Cursor extends Boxed
{
    protected Cursor(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Cursor with the specified CursorType.
     * 
     * @since 4.0.6
     */
    public Cursor(CursorType type) {
        super(GdkCursor.createCursor(type));
    }

    private static Display getDefaultDisplay() {
        final Screen screen;
        final Display display;

        screen = GdkScreen.getDefault();
        display = GdkScreen.getDisplay(screen);

        return display;
    }

    /**
     * Create a new Cursor from the one so named in the cursor theme.
     * 
     * @since 4.0.14
     */
    private Cursor(String name) {
        super(GdkCursor.createCursorFromName(getDefaultDisplay(), name));
    }

    protected void release() {
        GdkCursor.unref(this);
    }

    /**
     * The normal left-hand pointer.
     * 
     * <p>
     * This is a type {@link CursorType#LEFT_PTR LEFT_PTR}.
     * 
     * @since 4.0.14
     */
    public static Cursor NORMAL = new Cursor(CursorType.LEFT_PTR);

    /**
     * The spinning cursor showing that the application is busy (and unable to
     * be responsive to user input).
     * 
     * <p>
     * This is type {@link CursorType#WATCH WATCH}.
     * 
     * @since 4.0.14
     */
    public static Cursor BUSY = new Cursor(CursorType.WATCH);

    /**
     * A pointer indicating that a hyperlink can be clicked and followed. Not
     * used for Buttons.
     * 
     * <p>
     * This is type {@link CursorType#HAND2 HAND2}.
     * 
     * @since 4.0.14
     */
    public static Cursor LINK = new Cursor(CursorType.HAND2);

    /**
     * A pointer that also has a busy spinner. This is used to indicate that
     * the application is working, but that the user can still carry out
     * actions.
     * 
     * <p>
     * This is <code>"left_ptr_watch"</code> from the cursor theme.
     * 
     * @since 4.0.14
     */
    public static Cursor WORKING = new Cursor("left_ptr_watch");

    /**
     * The vertical bar pointer used in text entry Widgets such as Entry and
     * TextView.
     * 
     * <p>
     * This is type {@link CursorType#XTERM XTERM}.
     * 
     * @since 4.0.14
     */
    public static Cursor TEXT = new Cursor(CursorType.XTERM);
}
