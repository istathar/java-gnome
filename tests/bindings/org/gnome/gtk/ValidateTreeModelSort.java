/*
 * ValidateTreeModeSort.java
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
                nameOfBeach = new DataColumnString(), lengthOfBeach = new DataColumnInteger(),
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
