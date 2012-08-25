/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

/**
 * Test coverage of Entry icon and progress methods.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 */
public class ValidateEntry extends GraphicalTestCase
{
    public final void skipEntryIcon() {
        final Entry entry;

        entry = new Entry();

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
        final Entry entry;

        entry = new Entry();

        entry.setProgressFraction(0.5);
        assertEquals(0.5, entry.getProgressFraction());

        entry.setProgressPulseStep(0.1);
        assertEquals(0.1, entry.getProgressPulseStep());
    }

    public final void testEntryPosition() {
        final Entry entry;
        final int result, start, end;

        entry = new Entry();

        entry.setText("Hello world");
        entry.setPosition(6);

        result = entry.getPosition();
        assertEquals(6, result);

        entry.selectRegion(6, 10);

        start = entry.getSelectionBoundsStart();
        end = entry.getSelectionBoundsEnd();
        assertEquals(6, start);
        assertEquals(10, end);
    }
}
