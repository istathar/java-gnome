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
public class ValidateBoxeds extends GraphicalTestCase
{
    /*
     * Given that we have a few public constants in Color, chances are that if
     * the allocator breaks things will crash before we get to this test.
     */
    public final void testColorInstantiation() {
        final Color blue;

        blue = new Color(0, 0, 65535);

        assertEquals(0, blue.getRed());
        assertEquals(0, blue.getGreen());
        assertEquals(65535, blue.getBlue());
    }

    public final void testColorConstants() {
        final Color black, white;

        black = Color.BLACK;

        assertEquals(0, black.getRed());
        assertEquals(0, black.getGreen());
        assertEquals(0, black.getBlue());

        white = new Color(65535, 65535, 65535);
        assertEquals(65535, white.getRed());
        assertEquals(65535, white.getGreen());
        assertEquals(65535, white.getBlue());

        // Boxeds aren't unique
        assertNotSame(white, Color.WHITE);

        /*
         * Test the equals override
         */
        assertFalse(white.equals(Color.BLACK));
        assertTrue(white.equals(Color.WHITE));
    }

    public final void testRectangle() {
        final Rectangle original;

        original = new Rectangle(26, 127, 228, 329);

        assertEquals(26, original.getX());
        assertEquals(127, original.getY());
        assertEquals(228, original.getWidth());
        assertEquals(329, original.getHeight());
    }
}
