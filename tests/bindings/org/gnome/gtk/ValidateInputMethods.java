/*
 * ValidateInputMethods.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.EventKey;
import org.gnome.gdk.Keyval;
import org.gnome.gdk.ModifierType;

/**
 * Test GTK's input methods APIs.
 * 
 * @author Andrew Cowie
 */
public class ValidateInputMethods extends GraphicalTestCase
{
    public final void testConstructors() {
        InputMethod im;

        im = new SimpleInputMethod();
        assertTrue(im instanceof SimpleInputMethod);

        im = new MulticontextInputMethod();
        assertTrue(im instanceof MulticontextInputMethod);
    }

    private boolean composed;

    private String received;

    public final void testKeystrokes() {
        final InputMethod im;
        final DrawingArea da;
        final Window w;
        boolean result;

        da = new DrawingArea();
        da.setCanFocus(true);

        w = new Window();
        w.add(da);

        im = new SimpleInputMethod();

        da.connect(new Widget.KeyPressEvent() {
            public boolean onKeyPressEvent(Widget source, EventKey event) {
                if (im.filterKeypress(event)) {
                    return true;
                }
                return false;
            }
        });

        /*
         * Connecting to release was not actually necessary to this test; it
         * was added it after the test was already pasing. That's somewhat not
         * cool from an isolation perspective. However, calling
         * filterKeypress() in both Widget.KeyPressEvent AND
         * Widget.KeyReleaseEvent is the API, so keep it here.
         */
        da.connect(new Widget.KeyReleaseEvent() {
            public boolean onKeyReleaseEvent(Widget source, EventKey event) {
                if (im.filterKeypress(event)) {
                    return true;
                }
                return false;
            }
        });

        im.connect(new InputMethod.Commit() {
            public void onCommit(InputMethod source, String str) {
                composed = true;
                received = str;
            }
        });

        composed = false;
        received = null;

        w.showAll();

        cycleMainLoop();
        assertFalse(composed);
        assertNull(received);

        /*
         * All clear. So send a keystroke!
         */

        result = Test.sendKey(da, Keyval.a, ModifierType.NONE);
        assertTrue(result);

        // but since
        assertFalse(composed);
        assertNull(received);

        // we apparently need to
        cycleMainLoop();

        // because now ok
        assertTrue("Compose sequence not received by InputMethod", composed);
        assertEquals("Normal keystroke not received", "a", received);

        /*
         * Reset, and try again with a capital letter. They're funny, because
         * they are awarded a different keyval, but the shift mask also has to
         * be present or it is interpreted as a normal lower case key.
         * Presumably CapsLock gets involved as an alternative case.
         */

        composed = false;
        received = null;

        result = sendKeystroke(da, Keyval.A, ModifierType.SHIFT_MASK);
        assertTrue(result);
        assertFalse(composed);
        assertNull(received);

        // so let it fly
        cycleMainLoop();

        // and?
        assertTrue("Compose sequence not received by InputMethod", composed);
        assertEquals("Shifted upper case keystroke not received", "A", received);

        /*
         * Finally, give the Euro currency symbol compose sequence a try
         */

        composed = false;
        received = null;

        result = sendKeystroke(da, Keyval.Compose, ModifierType.NONE);
        assertTrue(result);
        assertFalse(composed);
        assertNull(received);

        // this should be swollowed, no InputMethod.Commit yet
        cycleMainLoop();
        assertFalse(composed);
        assertNull(received);

        // ok, good. Now the first of the specifying keystrokes
        result = sendKeystroke(da, Keyval.e, ModifierType.NONE);
        assertTrue(result);
        assertFalse(composed);
        assertNull(received);
        cycleMainLoop();

        // still swollowing?
        assertFalse(composed);
        assertNull(received);

        // ok, still good. Send the second keystroke in the sequence
        result = sendKeystroke(da, Keyval.Equal, ModifierType.NONE);
        assertTrue(result);
        assertFalse(composed);
        assertNull(received);

        // but this time, Compose + e + = should yield Euro
        cycleMainLoop();
        assertTrue("Compose sequence not received by InputMethod", composed);
        assertEquals("Compose key sequence didn't result in expected codepoint", "€", received);
    }
}
