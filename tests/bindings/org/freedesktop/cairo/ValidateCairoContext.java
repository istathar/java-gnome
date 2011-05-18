/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2009      Vreixo Formoso
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
package org.freedesktop.cairo;

import org.gnome.gtk.GraphicalTestCase;

/**
 * Unit tests for org.freedesktop.cairo.Context functions.
 * 
 * @author Vreixo Formoso
 */
public class ValidateCairoContext extends GraphicalTestCase
{
    public final void testSaveRestore() {
        final Context cr;
        final ImageSurface s;

        s = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(s);

        /* set some parameters */
        cr.setLineWidth(2.5);

        assertEquals(2.5, cr.getLineWidth());

        /* save context */
        cr.save();

        /* change parameters */
        cr.setLineWidth(38.0);
        assertEquals(38.0, cr.getLineWidth());

        /* save again */
        cr.save();

        cr.setLineWidth(1.2);
        assertEquals(1.2, cr.getLineWidth());

        /* restore */
        cr.restore();
        assertEquals(38.0, cr.getLineWidth());

        cr.restore();
        assertEquals(2.5, cr.getLineWidth());

        try {
            cr.restore();
            fail("restore() without matching save() should fail");
        } catch (IllegalStateException e) {
            // ok
        }
    }

    public final void testCairoInFill() {
        final Context cr;
        final ImageSurface s;

        s = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(s);

        cr.moveTo(10.0, 10.0);
        cr.rectangle(10.0, 10.0, 80.0, 80.0);

        assertTrue(cr.inFill(50.0, 50.0));
        assertFalse(cr.inFill(5.0, 5.0));
        assertFalse(cr.inFill(95.0, 95.0));

        /*
         * Now, some curiousity about corner cases, established while
         * documenting.
         */

        assertFalse(cr.inFill(0.0, 0.0));
        assertFalse(cr.inFill(9.9, 9.9));
        assertTrue(cr.inFill(10.1, 10.1));
        assertTrue(cr.inFill(10.01, 10.01));

        assertTrue(cr.inFill(90.0, 90.0));
        assertFalse(cr.inFill(90.1, 90.1));
        assertFalse(cr.inFill(90.01, 90.01));
        assertFalse(cr.inFill(100.0, 100.0));
    }

    public final void testCairoInStroke() {
        final Context cr;
        final ImageSurface s;

        s = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(s);

        cr.moveTo(10.0, 10.0);
        cr.setLineWidth(1.0);
        cr.rectangle(10.0, 10.0, 80.0, 80.0);

        assertFalse(cr.inStroke(50.0, 50.0));
        assertFalse(cr.inStroke(5.0, 5.0));
        assertFalse(cr.inStroke(95.0, 95.0));

        /*
         * Corner cases.
         */

        assertFalse(cr.inStroke(0.0, 0.0));
        assertTrue(cr.inStroke(9.9, 9.9));
        assertTrue(cr.inStroke(10.1, 10.1));
        assertTrue(cr.inStroke(10.5, 10.5));
        assertFalse(cr.inStroke(10.51, 10.51));

        assertTrue(cr.inStroke(90.0, 90.0));
        assertTrue(cr.inStroke(90.1, 90.1));
        assertTrue(cr.inStroke(90.5, 90.5));
        assertFalse(cr.inStroke(90.51, 90.51));
        assertTrue(cr.inStroke(89.51, 89.51));
        assertFalse(cr.inStroke(89.49, 89.49));
    }

    public final void testCairoFillClears() {
        final Context cr;
        final ImageSurface s;

        s = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(s);

        cr.rectangle(10.0, 10.0, 80.0, 80.0);

        assertTrue(cr.inFill(50.0, 50.0));
        cr.fill();
        assertFalse(cr.inFill(50.0, 50.0));
    }

    public final void testCairoFillPreserve() {
        final Context cr;
        final ImageSurface s;

        s = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(s);

        cr.rectangle(10.0, 10.0, 80.0, 80.0);

        assertTrue(cr.inFill(50.0, 50.0));
        cr.fillPreserve();
        assertTrue(cr.inFill(50.0, 50.0));
    }
}
