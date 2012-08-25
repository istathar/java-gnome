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
package org.gnome.screenshot;

import org.gnome.gdk.Pixbuf;
import org.gnome.glib.Glib;

/**
 * Take screenshots. This class is explicitly here to support taking the
 * screenshots used as illustrations in the java-gnome API documentation.
 * 
 * <p>
 * Usage is simple: given a Window, {@link org.gnome.gtk.Window#present()
 * present()} it, and then call the <code>capture</code> method here.
 * 
 * <pre>
 * final Window window;
 * final Pixbuf result;
 * 
 * window.present();
 * result = Screenshot.capture();
 * result.save(...);
 * </pre>
 * 
 * <p>
 * Note that for some reason taking screenshots is a very slow and CPU
 * intensive process. Unfortunately, it occurs within the GDK lock and thus
 * <i>will</i> block your UI for a few seconds. Threading can't help you
 * parallelize this.
 * 
 * <p style="margin: 10px; border: dashed 3px red; padding: 10px; * background-color: #DDDDDD; max-width: 600px;">
 * <b>LICENCE WARNING</b><br>
 * This native code used to take screenshots is licenced under the GNU General
 * Public Licence, version 2. As a result, use of this class in an application
 * will mean that entire application will need to be available under a GPL v2
 * compatible licence.
 * </p>
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
/*
 * This is somewhat in the same style as org.gnome.glade.Glade; by extending
 * Glib we force library initialization in some hypothetical future where
 * Gtk.init() is optional.
 */
public final class Screenshot extends Glib
{
    /*
     * No instantiation. Static methods only!
     */
    private Screenshot() {}

    /**
     * Take a screenshot of the Window which has focus. The window decorations
     * drawn by the window manager will be captured (so if you don't want
     * that, be sure to call
     * {@link org.gnome.gtk.Window#setDecorated(boolean) setDecorated(false)}
     * on your Window). An alpha blended drop-shadow will be added for effect.
     */
    public static Pixbuf capture() {
        return ScreenshotCapture.capture(true, true, "shadow");
    }
}
