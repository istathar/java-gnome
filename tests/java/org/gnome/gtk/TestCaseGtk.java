/*
 * TestCaseGtk.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import junit.framework.TestCase;

import org.freedesktop.bindings.Debug;
import org.gnome.gtk.Gtk;

/**
 * Ensure that GTK has already been initialized so that things like
 * <code>new Button()</code> don't throw <code>UnsatisfiedLinkError</code>.
 * 
 * @author Andrew Cowie
 * @since 4.0.2
 */
public class TestCaseGtk extends TestCase
{
    private static boolean initialized = false;

    /**
     * Called by <code>UnitTests.suite()</code> to in turn call
     * <code>Gtk.init()</code>. This allows the command line arguments to
     * be passed if necessary.
     */
    public static void init(String[] args) {
        Gtk.init(args);
        initialized = true;
    }

    /**
     * If you try to run a single Test Case (rather than using the top level
     * UnitTests launcher), then you need to initialize Gtk (and GLib along
     * with it). This will take care of that. If you override this, you'd
     * probably better call <code>super.setUp()</code>.
     */
    public void setUp() {
        if (!initialized) {
            init(null);
        }
    }

    public void tearDown() {
        if (Debug.MEMORY_MANAGMENT) {
            System.gc();
            System.err.flush();
        }
    }
}
