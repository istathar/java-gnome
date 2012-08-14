/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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

import org.gnome.glib.GlibException;

/*
 * This code originally lived in Gtk.java
 */

/**
 * Crafted to avail ourselves of a dependency on Plumbing, whose ultimate
 * class initializer loads our shared library, which of course we need to have
 * completed before any of the native methods here will be available.
 * 
 * @author Andrew Cowie
 * @since 4.0.9
 */
final class GtkMain extends Plumbing
{
    private GtkMain() {}

    static final void init(String[] args) {
        gtk_init(lock, args);
    }

    /*
     * This is one of the rarer cases where the arguments we pass to the JNI
     * side have little relation to the signature of the actual target
     * function. In this case, the first argument is a reference to the GDK
     * lock used to permit multithreaded access to the GTK library.
     */
    private static native final void gtk_init(java.lang.Object lock, String[] args);

    /*
     * Note that although this code is marked as being within our Gdk$Lock,
     * there is, in effect, a wait() within this call: as the GTK main loop
     * cycles it releases the lock. The effect is that the monitor on Gdk.lock
     * is frequently relinquished, which is the behaviour that is expected if
     * a piece of Java code object executes wait() within a monitor block.
     * Which is exactly what we need!
     * 
     * We'd rather have a synchronized (lock) {...} block here like we do for
     * every other translation layer method in java-gnome; ensuring the main
     * loop is within the GDK lock is critical to our successful thread safety
     * story. For obscure reasons relating to optimizing Eclipse's debugging
     * behaviour, for this one method ONLY we enter the monitor on the JNI
     * side.
     */
    static final void main() {
        // enter synchronized block
        gtk_main();
        // leave synchronized block
    }

    private static native final void gtk_main();

    static final void mainQuit() {
        synchronized (lock) {
            gtk_main_quit();
        }
    }

    private static native final void gtk_main_quit();

    /**
     * Are there any events pending for the main loop to process?
     * 
     * <p>
     * <b>This is not for general use! Do not expose this and do not encourage
     * anyone to use this to hack into the main loop.</b>
     * 
     * <p>
     * In a test case, this could be used as follows; see
     * <code>TestCaseGtk.cycleMainLoop()</code> in the <code>tests/</code>
     * tree for details:
     * 
     * <pre>
     * while (Gtk.eventsPending()) {
     *     Gtk.mainIterationDo(false);
     * }
     * </pre>
     */
    static final boolean eventsPending() {
        synchronized (lock) {
            return gtk_events_pending();
        }
    }

    private static native final boolean gtk_events_pending();

    /**
     * Run a single iteration of the main loop.
     * 
     * <p>
     * Not public! This is for internal use only, notably by test cases.
     * 
     * @param block
     *            Whether to block or not. If <code>true</code>, this method
     *            will block until an event is processed.
     * @return Will result in <code>true</code> if <code>Gtk.mainQuit()</code>
     *         (aka <code>gtk_main_quit()</code>) has been called on the
     *         innermost active main loop. <code>true</code> will also be
     *         returned if there <i>is</i> no main loop running.
     */
    static final boolean mainIterationDo(boolean block) {
        synchronized (lock) {
            return gtk_main_iteration_do(block);
        }
    }

    private static native final boolean gtk_main_iteration_do(boolean blocking);

    static final boolean showURI(String uri) throws GlibException {
        boolean result;

        if (uri == null) {
            throw new IllegalArgumentException("uri can't be null");
        }

        synchronized (lock) {
            result = gtk_show_uri(uri);
            return result;
        }
    }

    private static native final boolean gtk_show_uri(String uri) throws GlibException;
}
