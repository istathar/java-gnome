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
public class ValidateTreeModelFilter extends GraphicalTestCase
{
    private final DataColumnString name;

    private final DataColumnInteger age;

    private final DataColumnBoolean useful;

    private final ListStore base;

    private final TreeModelFilter filter;

    public ValidateTreeModelFilter() {
        TreeIter row;

        base = new ListStore(new DataColumn[] {
            name = new DataColumnString(),
            age = new DataColumnInteger(),
            useful = new DataColumnBoolean()
        });

        row = base.appendRow();
        base.setValue(row, name, "Peter");
        base.setValue(row, age, 60);
        base.setValue(row, useful, false);

        row = base.appendRow();
        base.setValue(row, name, "Paul");
        base.setValue(row, age, 61);
        base.setValue(row, useful, true);

        row = base.appendRow();
        base.setValue(row, name, "Mary");
        base.setValue(row, age, 62);
        base.setValue(row, useful, false);

        assertEquals(3, sizeOfModel(base));
        assertEquals(1, numberThatAreUseful(base, useful));

        filter = new TreeModelFilter(base, null);
    }

    public final void testFiltering() {
        TreeIter row;

        filter.setVisibleCallback(new TreeModelFilter.Visible() {
            public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row) {
                return base.getValue(row, useful);
            }
        });

        /*
         * We had a bug where we were not setting the model field of the
         * TreeIter leading to a check failure which is why we wrote this test
         * case. Having the callback be called yielded the exception.
         */
        filter.refilter();

        assertEquals(1, sizeOfModel(filter));
        row = filter.getIterFirst();
        assertEquals("Paul", filter.getValue(row, name));
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

    private static int numberThatAreUseful(TreeModel model, DataColumnBoolean usefulColumn) {
        TreeIter pointerRow;
        int i;

        pointerRow = model.getIterFirst();
        assertNotNull(pointerRow);

        i = 0;
        do {
            if (model.getValue(pointerRow, usefulColumn) == true) {
                i++;
            }
        } while (pointerRow.iterNext());

        return i;
    }

    public final void testIterConversion() {
        TreeIter rowInFitler, rowInBase;
        TreePath pathInFitler, pathInBase;

        filter.setVisibleCallback(new TreeModelFilter.Visible() {
            public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row) {
                return (base.getValue(row, age) == 62);
            }
        });

        filter.refilter();

        assertEquals(1, sizeOfModel(filter));
        rowInFitler = filter.getIterFirst();
        assertEquals("Mary", filter.getValue(rowInFitler, name));

        /*
         * Ok. Now test TreeIter conversion functions
         */

        rowInBase = filter.convertIterFilterToBase(rowInFitler);
        assertEquals("Mary", base.getValue(rowInBase, name));

        rowInFitler = filter.convertIterBaseToFilter(rowInBase);
        assertEquals("Mary", filter.getValue(rowInFitler, name));

        /*
         * What happens when the row isn't present?
         */

        rowInBase = base.getIterFirst();
        assertEquals("Peter", base.getValue(rowInBase, name));

        rowInFitler = filter.convertIterBaseToFilter(rowInBase);
        assertNull(rowInFitler);

        /*
         * And somewhat more convoluted, the TreePath conversion functions
         */

        rowInFitler = filter.getIterFirst();
        pathInFitler = filter.getPath(rowInFitler);

        pathInBase = filter.convertPathFilterToBase(pathInFitler);
        assertEquals("0", pathInFitler.toString());
        assertEquals("2", pathInBase.toString());
        assertFalse(pathInBase.equals(pathInFitler));

        rowInBase = base.getIter(pathInBase);
        assertEquals("Mary", base.getValue(rowInBase, name));

        pathInFitler = filter.convertPathBaseToFilter(pathInBase);
        rowInFitler = filter.getIter(pathInFitler);
        assertEquals("Mary", filter.getValue(rowInFitler, name));

        /*
         * And again, what happens when the path isn't present?
         */

        rowInBase = base.getIterFirst();
        pathInBase = base.getPath(rowInBase);

        pathInFitler = filter.convertPathBaseToFilter(pathInBase);
        assertNull(pathInFitler);
    }
}
