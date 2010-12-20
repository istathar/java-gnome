/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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

    public final void testMaskingModifierType() {
        ModifierType one, two, three, four, five;

        one = ModifierType.CONTROL_MASK;
        two = ModifierType.or(one, ModifierType.ALT_MASK);

        assertTrue(two.contains(ModifierType.CONTROL_MASK));
        assertTrue(two.contains(ModifierType.ALT_MASK));

        three = ModifierType.mask(two, ModifierType.CONTROL_MASK);

        assertFalse(three.contains(ModifierType.CONTROL_MASK));
        assertTrue(three.contains(ModifierType.ALT_MASK));
        assertTrue(three == ModifierType.ALT_MASK);

        four = ModifierType.mask(three, ModifierType.ALT_MASK);
        assertFalse(four.contains(ModifierType.ALT_MASK));
        assertTrue(four == ModifierType.NONE);

        five = ModifierType.mask(four, ModifierType.ALT_MASK);
        assertTrue(five == ModifierType.NONE);
    }
}
