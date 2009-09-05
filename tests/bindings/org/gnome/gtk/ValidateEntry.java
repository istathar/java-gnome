/*
 * ValidateEntry.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test coverage of Entry icon and progress methods.
 * 
 * @author Guillaume Mazoyer
 */
public class ValidateEntry extends GraphicalTestCase
{
    public final void testEntryIcon() {
        final Entry entry = new Entry();

        entry.setIconFromStock(EntryIconPosition.PRIMARY, Stock.FIND);
        assertSame(Stock.FIND, entry.getIconStock(EntryIconPosition.PRIMARY));
        assertSame(ImageType.STOCK, entry.getIconStorageType(EntryIconPosition.PRIMARY));

        entry.setIconFromStock(EntryIconPosition.SECONDARY, Stock.CLEAR);
        assertSame(Stock.CLEAR, entry.getIconStock(EntryIconPosition.SECONDARY));
        assertSame(ImageType.STOCK, entry.getIconStorageType(EntryIconPosition.SECONDARY));
        
        entry.setIconFromPixbuf(EntryIconPosition.PRIMARY, null);
        assertSame(null, entry.getIconPixbuf(EntryIconPosition.PRIMARY));
        assertSame(ImageType.EMPTY, entry.getIconStorageType(EntryIconPosition.PRIMARY));
    }

    public final void testEntryProgress() {
        final Entry entry = new Entry();

        entry.setProgressFraction(0.5);
        assertEquals(0.5, entry.getProgressFraction());

        entry.setProgressPulseStep(0.1);
        assertEquals(0.1, entry.getProgressPulseStep());
    }
}
