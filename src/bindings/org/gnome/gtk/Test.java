/*
 * Test.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Keyval;
import org.gnome.gdk.ModifierType;
import org.gnome.gdk.MouseButton;

/**
 * Support for testing GTK programs.
 * 
 * <p>
 * These functions mess with things pretty heavily; for one thing the mouse
 * will be warped to a position somewhere within the Widget. Needless to say ,
 * the average user doesn't view it very kindly to have their pointer woosh
 * off to another part of the screen. You therefore need to be using this
 * where the display is actually some sort of virtual server, say Xvfb, and
 * not with a live display.
 * 
 * <p>
 * Using <code>sendKey()</code> and <code>sendClick()</code> will inevitably
 * cause actions to occur that are not compatible with simultaneous human user
 * driven input. Obviously these are here to allow you to attempt to simulate
 * user actions, but if you need to click a button programmatically in a
 * normal application, call Button's <code>emitClicked()</code>.
 * 
 * <p>
 * <i>This is not a JUnit TestCase; using that, or any other test framework,
 * is your problem.</i>
 * 
 * @author Andrew Cowie
 * @since <span style="color: red">unstable</span>
 */
/*
 * It was very tempting to make these methods on Widget. Alas.
 */
public final class Test
{
    private Test() {}

    /**
     * Send a keystroke to a Widget.
     * 
     * <p>
     * This should result in <code>Widget.KeyPressEvent</code> and
     * <code>Widget.KeyReleaseEvent</code> being emitted.
     * 
     * <p>
     * The Widget needs to be in a Window that is <code>show()</code>n on
     * screen for the X server's event system to work and for the keystroke to
     * go anywhere. Which makes sense; you can't type in a minimized Window.
     * 
     * @since <span style="color: red">unstable</span>
     * @throws IllegalStateException
     *             If sending the keystroke fails.
     */
    public static void sendKey(Widget widget, Keyval keyval, ModifierType modifiers) {
        final boolean result;

        result = GtkTest.widgetSendKey(widget, keyval, modifiers);

        if (!result) {
            throw new IllegalStateException();
        }
    }

    /**
     * Send a mouse button click to a Widget.
     * 
     * @since <span style="color: red">unstable</span>
     * @throws IllegalStateException
     *             If sending the button click fails.
     */
    public static void sendClick(Widget widget, MouseButton button, ModifierType modifiers) {
        final boolean result;

        result = GtkTest.widgetClick(widget, button, modifiers);

        if (!result) {
            throw new IllegalStateException();
        }
    }

    /**
     * Cause the GTK main loop to cycle sufficiently to consume and action any
     * pending events.
     * 
     * <p>
     * 
     * <p>
     * Sometimes when writing procedural test code you call GTK methods whose
     * logic will not be executed until their idle handlers have a chance to
     * run, which will be sometime after the main loop finishes propegating
     * the current round of event signals. This allows you to stimulate the
     * main loop to iterate over pending events and run its idle handlers as
     * well.
     * 
     * <p>
     * <b>Do not call this within a signal handler callback!</b>
     * 
     * <p>
     * <b>WARNING</b><br>
     * This is a utility function for use in a test harness only. Don't even
     * think about using this in a normal GUI program. This function is
     * <i>not</i> a way to workaround the imperatives of event-driven
     * programming.
     * 
     * @since <span style="color: red">unstable</span>
     */
    public static void cycleMainLoop() {
        while (GtkMain.eventsPending()) {
            GtkMain.mainIterationDo(false);
        }
    }
}
