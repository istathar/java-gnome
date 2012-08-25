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
package org.gnome.gtk;

/**
 * Expose a custom workaround for the craziness of the real gtk_menu_popup()'s
 * required arguments.
 * 
 * @author Andrew Cowie
 */
final class GtkMenuOverride extends Plumbing
{
    private GtkMenuOverride() {}

    static final void popup(Menu self) {
        synchronized (lock) {
            gtk_menu_popup(pointerOf(self));
        }
    }

    private static native final void gtk_menu_popup(long self);

    /**
     * Call gtk_menu_popup(), but hardwired to use
     * gtk_status_icon_position_menu() as the (*GtkMenuPositionFunc).
     */
    static final void popupStatusIcon(Menu self, StatusIcon status) {
        synchronized (lock) {
            gtk_menu_popup_status_icon(pointerOf(self), pointerOf(status));
        }
    }

    private static native final void gtk_menu_popup_status_icon(long self, long status);

    /**
     * Call gtk_menu_popup(), with native code taking care of composing a
     * one-time (*GtkMenuPositionFunc) to place the menu at x,y.
     */
    static final void popupAtPosition(Menu self, int x, int y) {
        gtk_menu_popup_at_position(pointerOf(self), x, y);
    }

    private static native final void gtk_menu_popup_at_position(long self, int x, int y);

}
