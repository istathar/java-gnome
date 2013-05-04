/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.freedesktop.bindings.Flag;

/**
 * Constants describing the state of an underlying resource. You can access
 * most of these by calling methods available on [org.gnome.gtk] Window.
 * 
 * @author Vreixo Formoso
 * @since 4.0.3
 */
/*
 * How on earth did these get in without documentation? FIXME!
 */
public final class WindowState extends Flag
{
    private WindowState(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    public static final WindowState WITHDRAWN = new WindowState(GdkWindowState.WITHDRAWN, "WITHDRAWN");

    public static final WindowState ICONIFIED = new WindowState(GdkWindowState.ICONIFIED, "ICONIFIED");

    public static final WindowState MAXIMIZED = new WindowState(GdkWindowState.MAXIMIZED, "MAXIMIZED");

    public static final WindowState STICKY = new WindowState(GdkWindowState.STICKY, "STICKY");

    public static final WindowState FULLSCREEN = new WindowState(GdkWindowState.FULLSCREEN, "FULLSCREEN");

    public static final WindowState ABOVE = new WindowState(GdkWindowState.ABOVE, "ABOVE");

    public static final WindowState BELOW = new WindowState(GdkWindowState.BELOW, "BELOW");

    public static final WindowState FOCUSED = new WindowState(GdkWindowState.FOCUSED, "FOCUSED");

    /**
     * Creates a new WindowState flag as the OR'ing or combination of two
     * WindowState flags.
     */
    public static WindowState or(WindowState one, WindowState two) {
        return (WindowState) Flag.orTwoFlags(one, two);
    }
}
