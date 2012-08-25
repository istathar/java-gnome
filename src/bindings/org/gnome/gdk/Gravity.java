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

import org.freedesktop.bindings.Constant;

/**
 * Gravity determines the location of the reference point in root window
 * co-ordinates and which point of the Window is positioned at the reference
 * point. This impacts the co-ordinates used when moving Windows with
 * {@link org.gnome.gtk.Window#move(int, int) move()}. The gravity for a
 * Window is set by {@link org.gnome.gtk.Window#setGravity(Gravity)
 * setGravity()}.
 * 
 * <p>
 * <i>It turns out the whole gravity concept is unreliable; luckily you don't
 * really need it for much. And in any case, if you're trying to move the
 * Window around; what you probably want is</i>
 * {@link org.gnome.gtk.Window#setPosition(org.gnome.gtk.WindowPosition)
 * setPosition()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.4
 */
public final class Gravity extends Constant
{
    private Gravity(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Orientation to the top left corner of the screen and co-ordinates
     * running right and down. <b>This is the default</b> gravity and matches
     * what you'd normally expect <code>x</code>,<code>y</code> to mean on an
     * X display: horizontal and vertical distance from the top-left corner.
     */
    public static final Gravity NORTH_WEST = new Gravity(GdkGravity.NORTH_WEST, "NORTH_WEST");

    public static final Gravity SOUTH_WEST = new Gravity(GdkGravity.SOUTH_WEST, "SOUTH_WEST");

    public static final Gravity NORTH_EAST = new Gravity(GdkGravity.NORTH_EAST, "NORTH_EAST");

    public static final Gravity SOUTH_EAST = new Gravity(GdkGravity.SOUTH_EAST, "SOUTH_EAST");

    /**
     * Reference point is with respect to the center of the screen. If you're
     * trying to center your Window on the screen this probably isn't what you
     * want; see Window's
     * {@link org.gnome.gtk.Window#setPosition(org.gnome.gtk.WindowPosition)
     * setPosition()} with an argument of
     * {@link org.gnome.gtk.WindowPosition#CENTER CENTER} or
     * {@link org.gnome.gtk.WindowPosition#CENTER_ALWAYS CENTER_ALWAYS}
     * instead.
     */
    public static final Gravity CENTER = new Gravity(GdkGravity.CENTER, "CENTER");

    /**
     * Co-ordinates are with respect to the center of the top edge of the
     * Window. This is rarely useful.
     */
    public static final Gravity NORTH = new Gravity(GdkGravity.NORTH, "NORTH");

    /**
     * Co-ordinates are with respect to the center of the bottom edge of the
     * Window. This is rarely useful.
     */
    public static final Gravity SOUTH = new Gravity(GdkGravity.SOUTH, "SOUTH");

    /**
     * Co-ordinates are with respect to the center of the right-hand edge of
     * the Window. This is rarely useful.
     */
    public static final Gravity EAST = new Gravity(GdkGravity.EAST, "EAST");

    /**
     * Co-ordinates are with respect to the center of the left-hand edge of
     * the Window. This is rarely useful.
     */
    public static final Gravity WEST = new Gravity(GdkGravity.WEST, "WEST");

    /**
     * This is a weird one: it is in reference to the top-left corner of the
     * Window itself (like {@link #NORTH_WEST} but ignoring whatever
     * decorations etc have been added outside by the window manager. At first
     * this would seem brilliantly useful, but since it ignores window
     * decorations, moving to the co-ordinates returned by
     * {@link org.gnome.gtk.Window#getPositionX() getPosition()} will actually
     * cause the Window to move slightly on the screen, rather than staying
     * still as you might have expected. So even when you think you want this,
     * you really want NORTH_WEST, which is why it's the default.
     */
    public static final Gravity STATIC = new Gravity(GdkGravity.STATIC, "STATIC");

}
