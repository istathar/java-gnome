/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 * @author Andrew Cowie
 */
public class ValidateTreeModelSort extends GraphicalTestCase
{
    private final DataColumnString nameOfBeach;

    private final DataColumnInteger lengthOfBeach;

    private final ListStore base;

    private final TreeModelSort sort;

    public ValidateTreeModelSort() {
        TreeIter row;

        base = new ListStore(new DataColumn[] {
            nameOfBeach = new DataColumnString(),
            lengthOfBeach = new DataColumnInteger(),
        });

        row = base.appendRow();
        base.setValue(row, nameOfBeach, "Hermosa");
        base.setValue(row, lengthOfBeach, 3000);

        row = base.appendRow();
        base.setValue(row, nameOfBeach, "Manly");
        base.setValue(row, lengthOfBeach, 1500);

        row = base.appendRow();
        base.setValue(row, nameOfBeach, "Bondi");
        base.setValue(row, lengthOfBeach, 800);

        row = base.appendRow();
        base.setValue(row, nameOfBeach, "Seven Mile");
        base.setValue(row, lengthOfBeach, 7 * 1601);

        sort = new TreeModelSort(base);
        sort.setSortColumn(lengthOfBeach, SortType.ASCENDING);
    }

    private static int sizeOfModel(TreeModel model) {
        TreeIter pointer;
        int i;

        pointer = model.getIterFirst();
        assertNotNull(pointer);

        i = 0;
        do {
            i++;
        } while (pointer.iterNext());

        return i;
    }

    public final void testSorting() {
        TreeIter row;

        assertEquals(4, sizeOfModel(base));
        assertEquals(4, sizeOfModel(sort));

        row = base.getIterFirst();
        assertEquals("Hermosa", base.getValue(row, nameOfBeach));

        row = sort.getIterFirst();
        assertEquals("Bondi", sort.getValue(row, nameOfBeach));
        row.iterNext();
        assertEquals("Manly", sort.getValue(row, nameOfBeach));
        row.iterNext();
        assertEquals("Hermosa", sort.getValue(row, nameOfBeach));
        row.iterNext();
        assertEquals("Seven Mile", sort.getValue(row, nameOfBeach));
    }

    public final void testIterConversion() {
        TreeIter rowInSort, rowInBase;
        TreePath pathInSort, pathInBase;

        /*
         * Test TreeIter conversion functions.
         */

        rowInSort = sort.getIterFirst();
        assertEquals("Bondi", sort.getValue(rowInSort, nameOfBeach));

        rowInBase = sort.convertIterSortToBase(rowInSort);
        assertEquals("Bondi", base.getValue(rowInBase, nameOfBeach));

        rowInSort = sort.convertIterBaseToSort(rowInBase);
        assertEquals("Bondi", sort.getValue(rowInSort, nameOfBeach));

        rowInBase = base.getIterFirst();
        assertEquals("Hermosa", base.getValue(rowInBase, nameOfBeach));

        rowInSort = sort.convertIterBaseToSort(rowInBase);
        assertEquals("Hermosa", sort.getValue(rowInSort, nameOfBeach));

        rowInBase = sort.convertIterSortToBase(rowInSort);
        assertEquals("Hermosa", base.getValue(rowInBase, nameOfBeach));

        /*
         * Test TreePath conversion functions
         */

        rowInSort = sort.getIterFirst();
        pathInSort = sort.getPath(rowInSort);

        pathInBase = sort.convertPathSortToBase(pathInSort);
        assertEquals("0", pathInSort.toString());
        assertEquals("2", pathInBase.toString());
        assertFalse(pathInBase.equals(pathInSort));

        rowInBase = base.getIter(pathInBase);
        assertEquals("Bondi", base.getValue(rowInBase, nameOfBeach));

        pathInSort = sort.convertPathBaseToSort(pathInBase);
        rowInSort = sort.getIter(pathInSort);
        assertEquals("Bondi", sort.getValue(rowInSort, nameOfBeach));
    }
}
