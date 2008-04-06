/*
 * ValidateTreeModelFilter.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
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
public class ValidateTreeModelFilter extends TestCaseGtk
{
    public final void testFiltering() {
        final DataColumnString name;
        final DataColumnInteger age;
        final DataColumnBoolean useful;
        final ListStore model;
        TreeIter row;
        final TreeModelFilter filter;

        model = new ListStore(new DataColumn[] {
                name = new DataColumnString(),
                age = new DataColumnInteger(),
                useful = new DataColumnBoolean()
        });

        row = model.appendRow();
        model.setValue(row, name, "Peter");
        model.setValue(row, age, 60);
        model.setValue(row, useful, false);

        row = model.appendRow();
        model.setValue(row, name, "Paul");
        model.setValue(row, age, 61);
        model.setValue(row, useful, true);

        row = model.appendRow();
        model.setValue(row, name, "Mary");
        model.setValue(row, age, 62);
        model.setValue(row, useful, false);

        assertEquals(3, sizeOfModel(model));
        assertEquals(1, numberThatAreUseful(model, useful));

        filter = new TreeModelFilter(model, null);

        filter.setVisibleCallback(new TreeModelFilter.VISIBLE() {
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
}
