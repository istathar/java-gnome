/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import java.net.URI;

import org.gnome.gdk.Pixbuf;
import org.gnome.glib.Glib;
import org.gnome.glib.GlibException;

/**
 * The GTK widget toolkit initialization and main loop entry point. A typical
 * program written with java-gnome will boil down to this:
 * 
 * <pre>
 * public class ComeOnBabyLightMyFire
 * {
 *     public static void main(String[] args) {
 *         Gtk.init(args);
 * 
 *         // build user interface
 * 
 *         Gtk.main();
 *     }
 * }
 * </pre>
 * 
 * There. Now you know everything you need to know. <code>:)</code> In due
 * course we will write some tutorials to help you along.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public final class Gtk extends Glib
{
    /**
     * No instantiation. Static methods only!
     */
    private Gtk() {}

    /**
     * A guard against someone calling init() twice
     */
    private static boolean initialized = false;

    /**
     * Initialize the GTK libraries. <b>This must be called before any other
     * org.gnome.* classes are used.</b>
     * 
     * @param args
     *            The command line arguments array. This is passed to the
     *            underlying library to allowing user (or window manager) to
     *            alter GTK's behaviour.
     * @since 4.0.0
     */
    public static void init(String[] args) {
        if (initialized) {
            throw new IllegalStateException("Gtk already initialized");
        }
        initialized = true;

        /*
         * Initialize GTK and along with it GLib, GObject, etc.
         */
        GtkMain.init(args);
    }

    /**
     * Has GTK been initialized yet?
     * 
     * @since 4.0.15
     */
    public static boolean isInitialized() {
        return initialized;
    }

    /**
     * This method blocks, ie, it does not return until the GTK main loop is
     * terminated.
     * <p>
     * You can nest calls to <code>Gtk.main()</code>! If you do, then calling
     * {@link #mainQuit() mainQuit()} will make the innermost invocation of
     * the main loop return. (This is how modal Dialog boxes run and block the
     * rest of the application while still accepting events themselves)
     * 
     * @since 4.0.0
     */
    public static void main() {
        GtkMain.main();
    }

    /**
     * Exit the main loop. Since main loops can be nested, this does not
     * necessarily imply application termination, but if you have a typical
     * GTK program with a single call to <code>Gtk.main()</code> at the end of
     * your Java <code>main()</code> function, then calling
     * <code>Gtk.mainQuit()</code> in a signal handler somewhere will return
     * the program flow to <code>main()</code> on your way exiting.
     * 
     * @since 4.0.0
     */
    public static void mainQuit() {
        GtkMain.mainQuit();
    }

    /**
     * Are there any internal Events queued up waiting to be processed? Use
     * this in conjunction with {@link #mainIterationDo(boolean)
     * Gtk.mainIterationDo()}.
     * 
     * @since 4.0.19
     */
    public static boolean eventsPending() {
        return GtkMain.eventsPending();
    }

    /**
     * Run a single iteration of the GTK main loop.
     * 
     * <p>
     * In GUI programming we work extremely hard <i>not</i> to block the main
     * loop since this will cause the user interface to "freeze"; ordinarily
     * we do intensive computations in a worker thread and since java-gnome is
     * thread safe you can simply call your GUI updates from there.
     * 
     * <p>
     * Occasionally, however, there is a circumstance where you are in the
     * midst of a busy computation and need to explicitly let the GUI update
     * itself and to let other idle handlers run. If so you can use the
     * following idiom:
     * 
     * <pre>
     * while (Gtk.eventsPending()) {
     *     Gtk.mainIterationDo(false);
     * }
     * </pre>
     * 
     * <p>
     * This function is <b>not</b> a replacement for using {@link #main()
     * Gtk.main()} to initiate event handling in your program.
     * 
     * <p>
     * <i>Because we have worker threads you really should not need it in
     * normal work. We have exposed it as we found it a workaround for
     * TextView not doing its vertical size allocation until after the current
     * signal handlers have run.</i>
     * 
     * @since 4.0.19
     * @return <code>true</code> if {@link Gtk#mainQuit() Gtk.mainQuit()} was
     *         called inside the inner-most main loop.
     */
    public static boolean mainIterationDo(boolean block) {
        return GtkMain.mainIterationDo(block);
    }

    /**
     * Set the icon that will be used for all Windows in this application that
     * do not have an one explicitly set. See the documentation for Window's
     * {@link Window#setIcon(Pixbuf) setIcon()} for further details about how
     * to specify icons.
     * 
     * @since 4.0.5
     */
    /*
     * YES this is a function on GtkWindow, but it has such global impact that
     * this is a better place for it. If we ever get a real GtkApplication
     * class, we might move it there instead.
     */
    public static void setDefaultIcon(Pixbuf icon) {
        GtkWindow.setDefaultIcon(icon);
    }

    /**
     * Lookup the Pixbuf corresponding to a stock icon of a certain size.
     * 
     * <p>
     * You need to specify a Widget in order that the most correct theme
     * engine and Style are employed to pick the appropriate image. This is
     * redundant in most programs where we don't interfere with the theming or
     * styling; just pass in your top level Window (or for that matter, any
     * other Widget you have handy).
     * 
     * @since 4.0.9
     */
    /*
     * YES this is a function on GtkWidget, but it really has nothing to do
     * with Widgets (and certainly is not a method that every single Widget
     * subclass needs to inherit or have visible).
     */
    public static Pixbuf renderIcon(Widget source, Stock stock, IconSize size) {
        return GtkWidget.renderIcon(source, stock.getStockId(), size, null);
    }

    /**
     * Launch the user's preferred application to handle (display) the the
     * supplied URI. This is most commonly used for raising URLs in the user's
     * web browser, but the capability is more general than that; any URI
     * conveying a MIME type that the desktop knows how to interpret will be
     * handled.
     * 
     * <p>
     * Typical examples for URIs understood by GNOME are:<br>
     * <br>
     * <code>file:///home/george/Desktop/image.png</code><br>
     * <code>http://java-gnome.sourceforge.net/</code><br>
     * <code>mailto:george@example.com</code><br>
     * 
     * <p>
     * The launching will take appreciable real time, but this call does not
     * block on the application being launched terminating. Think fork+exec.
     * 
     * <p>
     * This function will return <code>true</code> if the call succeeds, and
     * <code>false</code> otherwise.
     * 
     * @since 4.0.9
     */
    public static boolean showURI(URI uri) {
        try {
            return GtkMain.showURI(uri.toString());
        } catch (GlibException e) {
            // This will fall through to return false
        }

        return false;
    }

    /**
     * Get the Settings object for the default Screen.
     * 
     * @since 4.0.14
     */
    public static Settings getSettings() {
        return GtkSettings.getDefault();
    }

    /**
     * Get the Settings object for the given Screen.
     */
    /*
     * We still haven't really exposed Screen. Do we need this?
     */
    // static Settings getSettings(Screen screen) {
    // return GtkSettings.getForScreen(screen);
    // }
}
