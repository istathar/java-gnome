/*
 * ValidateKeyboardHandling.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gdk;

import org.gnome.gtk.TestCaseGtk;

/**
 * @author Andrew Cowie
 */
public class ValidateKeyboardHandling extends TestCaseGtk
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
         * This is a guess of a symbol that will *never* be exposed in
         * java-gnome :)
         */
        i = new Keyval("ISO_Level3_Latch");
        assertEquals(0xFE04, GdkKeyvalOverride.numOf(i));
    }
}
