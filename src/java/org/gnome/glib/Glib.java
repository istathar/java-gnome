/*
 * Glib.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
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
 */
public final class Glib
{
    /**
     * A guard against someone calling init() twice
     */
    private static boolean initialized = false;

    /**
     * No instantiation. Static methods only!
     */
    private Glib() {}

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
