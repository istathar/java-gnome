/*
 * ValidateStockItems.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
