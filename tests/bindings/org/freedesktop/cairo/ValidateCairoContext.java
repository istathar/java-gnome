/*
 * ValidateCairoContext.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2009 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
}
