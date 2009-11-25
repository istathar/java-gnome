/*
 * ValidateBoxeds.java
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
