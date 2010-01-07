/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2009 Vreixo Formoso
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
}
