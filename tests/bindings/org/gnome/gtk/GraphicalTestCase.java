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
 */
package org.gnome.gtk;

/*
 * The virtual X server initialization code adapted from the graphical test
 * harness in Quill and Parchment's tests/quill/ui/GraphicalTestCase.java,
 * 
 * Copyright © 2009-2011      Operational Dynamics Consulting, Pty Ltd and Others
 * 
 * and licenced under the terms of the "GNU General Public Licence, version
 * 2" only.
 * 
 * Thanks to Mariano Suárez-Alvarez and Rémi Cardona for their guidance on a
 * technique to find an available X display number.
 */

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.freedesktop.bindings.Debug;
import org.gnome.gdk.Event;
import org.gnome.gdk.Keyval;
import org.gnome.gdk.ModifierType;

import static java.lang.Thread.sleep;

/**
 * Ensure that GTK has already been initialized so that things like
 * <code>new Button()</code> don't throw <code>UnsatisfiedLinkError</code>.
 * 
 * @author Andrew Cowie
 */
public abstract class GraphicalTestCase extends TestCase
{
    private static boolean initialized;

    private static Process virtual;

    static {
        initialized = false;
    }

    /**
     * If you try to run a single Test Case (rather than using the top level
     * UnitTests launcher), then you need to initialize GTK (and GLib along
     * with it). This will take care of that as all JUnit test cases are
     * instantiated once for each text fixture.
     */
    protected GraphicalTestCase() {
        if (initialized) {
            checkVirtualServerRunning();
        } else {
            setupVirtualServerAndToolkit();
        }
    }

    private static void setupVirtualServerAndToolkit() {
        final int MAX = 30;
        int i;
        File target;
        final String DISPLAY;
        final Runtime runtime;

        try {
            /*
             * This seems quite the kludge, but apparently this is the
             * algorithm used by X itself to find an available display number.
             * It's also used by Gentoo's virtualx eclass.
             */

            for (i = 0; i < MAX; i++) {
                target = new File("/tmp/.X" + i + "-lock");
                if (!(target.exists())) {
                    break;
                }
            }
            if (i == MAX) {
                fail("\n" + "Can't find an available X server display number to use");
            }
            DISPLAY = ":" + i;

            /*
             * Xvfb arguments:
             * 
             * -ac disable access control (necessary so that other program can
             * draw there)
             * 
             * -wr white background
             * 
             * -fp built-ins workaround "fixed" font not being present.
             * 
             * Also, don't try to force Xvfb to 32 bits per pixed in -screen;
             * for some reason this makes it unable to start.
             */

            runtime = Runtime.getRuntime();

            virtual = runtime.exec("/usr/bin/Xvfb " + DISPLAY
                    + " -ac -dpi 96 -screen 0 800x600x24 -wr -fp built-ins");

            sleep(100);

            checkVirtualServerRunning();

            /*
             * Attempt to terminate the virtual X server when the tests are
             * complete. This is far from bullet proof. It would be better if
             * we knew when all the tests were done running and called
             * destroy() then.
             */

            runtime.addShutdownHook(new Thread() {
                public void run() {
                    if (virtual == null) {
                        return;
                    }
                    try {
                        virtual.destroy();
                        virtual.waitFor();
                    } catch (InterruptedException e) {
                        // already exiting
                    }
                }
            });

            /*
             * Finally, initialize GTK. We close stderr to prevent noise from
             * Xlib (as used by GTK) about "XRANR not being available". This
             * of course means we're missing anything else to stderr. That
             * seems like a bad idea, but what else can we do?
             */

            System.err.close();

            Gtk.init(new String[] {
                "--display=" + DISPLAY
            });

            initialized = true;
        } catch (IOException ioe) {
            fail("Unexpected I/O problem: " + ioe.getMessage());
        } catch (InterruptedException ie) {
            fail("How did this Thread get interrupted?");
        }

    }

    private static void checkVirtualServerRunning() {
        final String msg;

        msg = "\n" + "Xvfb not running!";

        if (virtual == null) {
            fail(msg);
        }
        try {
            virtual.exitValue();
            fail(msg);
        } catch (IllegalThreadStateException itse) {
            // good
        }
    }

    /*
     * If you override this and you are debugging, you'd probably better call
     * <code>super.setUp()</code>.
     */
    public void setUp() {
        System.out.flush();
    }

    public void tearDown() {
        if (Debug.MEMORY_MANAGEMENT) {
            System.gc();
            System.err.flush();
        }
        System.out.flush();
    }

    /**
     * Iterates the main loop through how ever many events are pending, and
     * then returns. This way, unit tests that need to have the main loop
     * "run" to evaluate whether or not something worked can cause those
     * iterations to take place.
     */
    protected static void cycleMainLoop() {
        /*
         * Lead off with one iteration no matter what. Pending events aren't
         * the only thing that the main loop does! Then continue by working
         * off whatever has accumulated.
         * 
         * The business with the yields and sleeps is a bit of a stab in the
         * dark. I'm not sure why some tests fail without this - and we keep
         * having to tweak it further. It'd be nice not to have to mess with
         * this (other than the fact that we shouldn't really have to do this
         * at all except that we can't fire off a main loop for real).
         */
        do {
            try {
                Thread.yield();
                Thread.sleep(25);

                GtkMain.mainIterationDo(false);

                Thread.yield();
                Thread.sleep(25);
            } catch (InterruptedException e) {
                //
            }
        } while (GtkMain.eventsPending());
    }

    /**
     * Iterate the garbage collector. Of course, some platforms ignore this,
     * which is going to make the unit tests which depend on it running
     * somewhat difficult to pass. Too bad.
     */
    protected static void cycleGarbageCollector() {
        System.gc();
        try {
            /*
             * this is needed because GC runs asynchronously on some
             * platforms. So we make sure it has an opportunity to run.
             */
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    /**
     * Just for hacking... sometimes when you're creating a unit test you need
     * to see it run to make sure it looks the way it should before probing
     * its qualities.
     */
    protected static void runMainLoop(Window w) {
        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
        Gtk.main();
        System.exit(0);
    }

    /**
     * Send a keystroke to the given Widget. It has to be packed in a Window,
     * show()n and not minimized.
     */
    /*
     * Exposing these on Test is not yet a given, so use our own direct call
     * to the functions on GtkTest, and while we're at it throw in a few
     * sanity checks.
     */
    protected static boolean sendKeystroke(Widget widget, Keyval keyval, ModifierType modifiers) {
        assertTrue(widget.getToplevel() instanceof Window);
        assertTrue(widget.getAllocation().getWidth() > 0);
        return GtkTest.widgetSendKey(widget, keyval, modifiers);
    }
}
