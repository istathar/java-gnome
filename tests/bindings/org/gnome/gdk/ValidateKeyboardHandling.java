/*
 * ValidateKeyboardHandling.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gdk;

import org.gnome.gtk.GraphicalTestCase;

/**
 * @author Andrew Cowie
 */
public class ValidateKeyboardHandling extends GraphicalTestCase
{
    public final void testTheLetterA() {
        assertNotNull(Keyval.a);
        assertEquals(0x61, GdkKeyvalOverride.numOf(Keyval.a));
    }

    public final void testTheUnicodeOfA() {
        assertEquals('a', Keyval.a.toUnicode());
        assertEquals(0, Keyval.ControlRight.toUnicode());
    }

    public final void testSubclassInstantiateByName() {
        final Keyval i;
        /*
         * This is a guess of a symbol that will NEVER be exposed in
         * java-gnome :)
         */
        i = new Keyval("ISO_Level3_Latch");
        assertEquals(0xFE04, GdkKeyvalOverride.numOf(i));
    }

    /**
     * Beware that if you output this string to terminal, you'll only get
     * "Hello". That was unexpected, hence this test, which shows that the
     * data after \0 isn't lost (it would be in C), but \0 will screw up your
     * output.
     */
    public final void testNullInString() {
        final StringBuilder terminated;

        terminated = new StringBuilder();

        terminated.append("Hello");
        terminated.append((char) 0);
        terminated.append("World");

        assertEquals("Hello\0World", terminated.toString());
        assertEquals(11, terminated.length());
    }
}
