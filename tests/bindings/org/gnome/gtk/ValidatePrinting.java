/*
 * ValidatePrinting.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test our binding of paper size and other printing related classes.
 * 
 * @author Andrew Cowie
 */
public class ValidatePrinting extends GraphicalTestCase
{
    public final void testPaperSizeConstants() {
        assertNotSame(PaperSize.A4, PaperSize.LETTER);
        assertEquals(PaperSize.A4, new PaperSize("iso_a4"));
        assertFalse(PaperSize.A4.equals(PaperSize.LETTER));
        assertNotNull(PaperSize.getDefault());
        assertEquals("A4", PaperSize.A4.getDisplayName());
        assertEquals("A3", InternationalPaperSize.A3.getDisplayName());

        assertNotNull(NorthAmericanPaperSize.LEGAL);
        /*
         * Is this locale dependent? If so, scratch this test.
         */
        assertEquals("US Legal", NorthAmericanPaperSize.LEGAL.getDisplayName());
    }

    public final void testPaperSizeDimensions() {
        assertEquals(210.0, PaperSize.A4.getWidth(Unit.MM), 0.0000001);
        assertEquals(297.0, PaperSize.A4.getHeight(Unit.MM), 0.0000001);

        assertEquals(595, (int) PaperSize.A4.getWidth(Unit.POINTS));
        assertEquals(841, (int) PaperSize.A4.getHeight(Unit.POINTS));

        assertEquals(8.5, PaperSize.LETTER.getWidth(Unit.INCH), 0.001);
        assertEquals(11.0, PaperSize.LETTER.getHeight(Unit.INCH), 0.001);
    }

    public final void testCustomPaperDefinition() {
        PaperSize paper, another;

        try {
            paper = new CustomPaperSize(null, 400, 300, Unit.INCH);
            fail("guard failed");
            return;
        } catch (IllegalArgumentException iae) {
            // good
        }

        paper = new CustomPaperSize("Blah", 400, 300, Unit.INCH);
        assertEquals("Blah", paper.getDisplayName());

        try {
            paper = new CustomPaperSize("Failure", 400, 300, Unit.PIXEL);
            fail("Cannot accept pixels");
            return;
        } catch (IllegalArgumentException iae) {
            // good
        }

        paper = new CustomPaperSize("Custom", 400, 300, Unit.POINTS);
        assertEquals(400.0, paper.getWidth(Unit.POINTS), 0.001);
        assertEquals(300.0, paper.getHeight(Unit.POINTS), 0.001);
        assertEquals("Custom", paper.getDisplayName());

        /*
         * What happens if we attempt to reuse a name?
         */

        another = new CustomPaperSize("Custom", 500, 600, Unit.MM);
        assertEquals(500.0, another.getWidth(Unit.MM), 0.001);
        assertEquals(600.0, another.getHeight(Unit.MM), 0.001);
        assertNotSame(paper, another);
        assertEquals("Custom", paper.getDisplayName());

        // it's ok, apparently
    }
}
