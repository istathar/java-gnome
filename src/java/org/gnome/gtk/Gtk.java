/*
 * Gtk.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Glib;

/**
 * The GTK widget toolkit initialization and main loop entry point.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
/*
 * Extremely atypically, this class contains native declarations because a)
 * There's nothing left if you strip off the Gtk prefix from Gtk, b) no reason
 * to use a static class GtkMain or whatever as none of these methods need
 * access to Plumbing.
 */
public final class Gtk extends Glib
{

    private static final String APIVERSION = "4.0";

    static final String VERSION = "4.0.2";

    static {
        System.loadLibrary("gtkjni-" + APIVERSION);
    }

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

        /*
         * Notify org.gnome.glib.Glib that we don't need it to do anything
         */
        Glib.skipInit();

        /*
         * Initialize GTK and along with it GLib, GObject, etc.
         */
        gtk_init(args);

        initialized = true;
    }

    private static native final void gtk_init(String[] args);

    /**
     * This method blocks, ie, it does not return until the GTK main loop is
     * terminated.
     * <p>
     * You can nest calls to Gtk.main()! If you do, then calling
     * {@link #mainQuit() mainQuit()} will make the innermost invocation of
     * the main loop return. (This is how modal Dialog boxes run and block the
     * rest of the application while still accepting events themselves)
     * 
     * @since 4.0.0
     */
    public static void main() {
        gtk_main();
    }

    private static native final void gtk_main();

    public static void mainQuit() {
        gtk_main_quit();
    }

    private static native final void gtk_main_quit();
}
