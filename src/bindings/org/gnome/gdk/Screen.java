/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Representation of a physical monitor screen. You can get the Screen object
 * for one of your application's Windows by calling Window's
 * {@link org.gnome.gtk.Window#getScreen() getScreen()} method; it you want
 * the width and height of the screen your Window is on, you're in the right
 * place.
 * 
 * <p>
 * A Screen is typically one monitor, but could actually be more; it depends
 * on how your X server is configured. A Display, in turn, is made up of one
 * or more Screens; again it depends.
 * 
 * <p>
 * <i>With the advent of the</i> <code>XINERAMA</code> <i>extension in the</i>
 * XFree <i>and later</i> X.org <i>X Windows servers, you tend to find that
 * what would have been multiple Screens have been (transparently) merged and
 * stretched to run over an entire multi-headed Display. This works out better
 * (single mouse and keyboard works over the entire desktop, as does cut and
 * paste, dragging, etc) and since the window manager is aware of the
 * situation, it can maximize Windows properly to be only on one physical
 * screen as you'd expect and desire.</i>
 * 
 * <p>
 * <i>As a result, the distinction between Screen and Display is nowadays
 * somewhat blurred. In practise you can treat them synonymously especially
 * since their methods don't overlap. Frankly, this is all another classic
 * case of "don't second guess the window manager"; just let it do it's job
 * and leave the Window placement alone.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.4
 * @see Display
 * @see <span>The <code>X</code>(7) man page on your system</span>
 */
public class Screen extends Object
{
    protected Screen(long pointer) {
        super(pointer);
    }

    /**
     * Get the horizontal width of this Screen, in pixels.
     * 
     * @since 4.0.4
     */
    public int getWidth() {
        return GdkScreen.getWidth(this);
    }

    /**
     * Get the vertical height of this Screen, in pixels.
     * 
     * @since 4.0.4
     */
    public int getHeight() {
        return GdkScreen.getHeight(this);
    }

    /*
     * Functions being used in debugging Pixmaps. Not clear if they need to be
     * public; doesn't seem so.
     */

    /**
     * Get the default Screen on the default Display.
     */
    static Screen getDefault() {
        return GdkScreen.getDefault();
    }
}
