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
 * <i>This is not a JUnit TestCase; using that, or any other test framework,
 * is your problem.</i>
 * 
 * @author Andrew Cowie
 * @since <span style="color: red">unstable</span>
 */
/*
 * It was very tempting to make these methods on Widget.
 */
public final class Test
{
    private Test() {}

    public static void init(String[] args) {
        if (args == null) {
            GtkTest.init(0, null);
        } else {
            GtkTest.init(args.length, args);
        }
    }

    /**
     * Send a keystroke to a Widget.
     * 
     * <p>
     * The Widget needs to be in a Window that is <code>show()</code>n on
     * screen for the X server's event system to work and for the keystroke to
     * go anywhere. Which makes sense; you can't type in a minimized Window.
     * 
     * @since <span style="color: red">unstable</span>
     */
    public static boolean sendKey(Widget widget, Keyval keyval, ModifierType modifiers) {
        return GtkTest.widgetSendKey(widget, keyval, modifiers);
    }
}
