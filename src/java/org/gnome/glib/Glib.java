/*
 * Glib.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.glib;

/**
 * Static methods to initialize the Java bindings around GLib
 * 
 * @author Andrew Cowie
 */
public final class Glib {
    /**
     * A guard against someone calling init() twice
     */
    private static boolean initialized = false;

    /**
     * No instantiation. Static methods only!
     */
    private Glib() {
    }

    /**
     * Initialize GLib's internal subsystems. To simplify things, this is called
     * automatically by
     * {@link org.gnome.gtk.Gtk#init(java.lang.String[]) Gtk.init()}, so the
     * occasions to call this directly should be pretty rare.
     */
    public static void init(String[] args) {
        if (initialized) {
            return;
        }
        initialized = true;
    }
}
