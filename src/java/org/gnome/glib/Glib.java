/*
 * Glib.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

/**
 * Static methods to initialize the Java bindings around GLib
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public class Glib
{
    /**
     * A guard against someone calling init() twice
     */
    private static boolean initialized = false;

    static {
        // FIXME: call g_type_init()
    }

    /**
     * No instantiation. Static methods only!
     */
    protected Glib() {}

    /**
     * Initialize GLib's internal subsystems. To simplify things, this is
     * called automatically by
     * {@link org.gnome.gtk.Gtk#init(java.lang.String[]) Gtk.init()}, so the
     * occasions to call this directly should be pretty rare.
     * 
     * @throws IllegalStateException
     *             if GLib has already been initialized, ie you either called
     *             this twice by accident, or you already initialized GLib by
     *             calling Gtk.init() or Program.init().
     * @since 4.0.0
     */
    protected static void init(String[] args) {
        if (initialized) {
            throw new IllegalStateException("Glib already initialized");
        }

        // TODO: other initializations?

        /*
         * Prevent subsequent manual initialization.
         */
        initialized = true;
    }

    /**
     * Notify org.gnome.glib.Glib that it will be initialized care of a sub
     * library's initialization. <i>This is so Gtk or Gnome can just carry on
     * calling gtk_init() or gnome_program_init(), both of which initialize
     * <code>GLib</code>, <code>GType</code>, etc</i>
     * 
     * @since 4.0.1
     */
    protected static void skipInit() {
        /*
         * Prevent subsequent manual initialization.
         */
        initialized = true;
    }
}
