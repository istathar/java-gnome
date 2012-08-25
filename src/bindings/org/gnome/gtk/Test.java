/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2011 Operational Dynamics Consulting, Pty Ltd
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
}
