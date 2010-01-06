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
package org.gnome.gtk;

import junit.framework.TestCase;

/**
 * Validate the handling of Stock items and other GTK constants.
 * 
 * @author Andrew Cowie
 */
public class ValidateStockItems extends TestCase
{
    public final void testStockRegistry() {
        assertEquals("gtk-apply", Stock.APPLY.getStockId());
        assertEquals("gtk-quit", Stock.QUIT.getStockId());
        assertFalse(Stock.APPLY == Stock.QUIT);

        assertSame(Stock.APPLY, Stock.instanceFor("gtk-apply"));
        assertSame(Stock.QUIT, Stock.instanceFor("gtk-quit"));

        assertNull(Stock.instanceFor("boooga-boooga"));
    }

    public final void testStockEquals() {
        assertTrue(Stock.COPY.equals(Stock.COPY));
        assertFalse(Stock.COPY.equals(Stock.CUT));
    }
}
