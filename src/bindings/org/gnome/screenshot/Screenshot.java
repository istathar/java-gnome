/*
 * Screenshot.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.screenshot;

import org.gnome.gdk.Pixbuf;
import org.gnome.glib.Glib;

/**
 * Take screenshots. This class is explicitly here to support taking the
 * screenshots used as illustrations in the java-gnome API documentation.
 * 
 * <p>
 * Usage is simple: given a Window,
 * {@link org.gnome.gtk.Window#present() present()} it, and then call the
 * <code>capture</code> method here.
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
 * <b>LICENCE WARNING</b><br>
 * This native code used to take screenshots is licened under the GNU General
 * Public Licence. As a result, use of this class will mean that your entire
 * application will need to be available under a GPL compatible licence.
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
