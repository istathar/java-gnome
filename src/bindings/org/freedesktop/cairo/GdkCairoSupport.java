/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2011 Operational Dynamics Consulting, Pty Ltd
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
package org.freedesktop.cairo;

import org.gnome.gdk.Pixbuf;
import org.gnome.gdk.RGBA;
import org.gnome.gdk.Window;

/**
 * Hack to allow us to get at various gdk_cairo_*() functions.
 * 
 * @author Andrew Cowie
 */
/*
 * Playing with an alternate naming pattern, suffix "Support" for the really
 * weird corner cases. We are not, after all, overriding some capability in
 * CairoContext's generated layers.
 */
final class GdkCairoSupport extends Plumbing
{
    private GdkCairoSupport() {}

    static final long createContext(Window window) {
        if (window == null) {
            /*
             * This check is, unfortunately, particularly important. If you've
             * gotten this far with a null Window, that means that the state
             * you think you're in isn't what it should be - in otherwords,
             * you don't _really_ have a Window yet.
             */
            throw new IllegalArgumentException("window can't be null");
        }

        synchronized (lock) {
            return gdk_cairo_create(pointerOf(window));
        }
    }

    private static native final long gdk_cairo_create(long window);

    static final void setSourcePixbuf(Context self, Pixbuf pixbuf, double x, double y) {
        if (pixbuf == null) {
            throw new IllegalArgumentException("pixbuf can't be null");
        }

        synchronized (lock) {
            gdk_cairo_set_source_pixbuf(pointerOf(self), pointerOf(pixbuf), x, y);
        }
    }

    private static native final void gdk_cairo_set_source_pixbuf(long context, long pixbuf, double x,
            double y);

    static final void setSourceRgba(Context self, RGBA color) {
        if (color == null) {
            throw new IllegalArgumentException("color can't be null");
        }

        synchronized (lock) {
            gdk_cairo_set_source_rgba(pointerOf(self), pointerOf(color));
        }
    }

    private static native final void gdk_cairo_set_source_rgba(long context, long color);

}
