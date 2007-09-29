/*
 * ValidateTreeModel.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.gtk.TestCaseGtk;

/**
 * @author Andrew Cowie
 */
public class ValidateTreeModel extends TestCaseGtk
{
    public final void testListStoreConstructorArguments() {
        try {
            new ListStore(null);
            fail("Underlying library doesn't allow null arguments to ListStore contstructor");
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            new ListStore(new DataColumn[] {});
            fail("Underlying library doesn't allow zero columns in ListStores");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }

    public final void testDataColumnTreeModelConstructorInteraction() {
        final DataColumnString name;
        final DataColumnInteger age;
        final DataColumnBoolean useful;
        final DataColumn[] types;
        final ListStore model;

        types = new DataColumn[] {
                name = new DataColumnString(),
                age = new DataColumnInteger(),
                useful = new DataColumnBoolean()
        };

        try {
            name.getOrdinal();
            fail("Should have failed check to see that this DataColumn is already added to a ListStore");
        } catch (IllegalStateException ise) {
            // good
        }

        assertSame(String.class, name.getType());
        assertSame(Integer.class, age.getType());
        assertSame(Boolean.class, useful.getType());

        model = new ListStore(types);

        assertEquals(0, name.getOrdinal());
        assertEquals(1, age.getOrdinal());
        assertEquals(2, useful.getOrdinal());

        // supress warning
        model.getClass();
    }

    public final void testDontUseDataColumnTwice() {
        final DataColumn[] types;
        final DataColumnString column;

        column = new DataColumnString();

        types = new DataColumn[] {
                column, column
        };

        try {
            new ListStore(types);
            fail("Shouldn't be allowed to construct a ListStore with the same DataColumn used twice");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }

    /*
     * Check the manually written code in GtkTreeIterOverride which allocates
     * and returns us a TreeIter. Both append() and getIterFirst() call
     * TreeIter.<init>() which invokes this code.
     */
    public final void testAllocateTreeIter() {
        final ListStore model;
        TreeIter iter;

        model = new ListStore(new DataColumn[] {
            new DataColumnString(),
        });

        iter = model.getIterFirst();

        assertNull(iter);

        iter = model.appendRow();
        assertNotNull(iter);

        iter = model.getIterFirst();
        assertNotNull(iter);

        iter = null;
        cycleGarbageCollector();
        // at this point, if we haven't crashed, that's a good thing.
    }

    public final void testSettingValueStringColumn() {
        final ListStore model;
        TreeIter row;
        DataColumnString column;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString(),
        });

        row = model.appendRow();
        model.setValue(row, column, "Hello");

        assertEquals("Hello", model.getValue(row, column));
    }

    public final void testSettingValueIntegerColumn() {
        final ListStore model;
        TreeIter row;
        DataColumnInteger column;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnInteger(),
        });

        row = model.appendRow();
        model.setValue(row, column, 42);

        assertEquals(42, model.getValue(row, column));
    }

    public final void testSettingValueBooleanColumn() {
        final ListStore model;
        TreeIter row;
        DataColumnBoolean column;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnBoolean(),
        });

        row = model.appendRow();
        model.setValue(row, column, true);
        assertEquals(true, model.getValue(row, column));

        model.setValue(row, column, false);
        assertEquals(false, model.getValue(row, column));

        model.setValue(row, column, true);
        assertEquals(true, model.getValue(row, column));
    }

    public final void testTreePathParseCheck() {
        try {
            // path = new TreePath("-1");
            TreePath.validationCheck(0L);
            fail("TreePath constructor should have caught null condition");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }

    public final void testTreeIterFromTreePath() {
        final ListStore model;
        final DataColumnBoolean column;
        TreeIter row;
        TreePath path1, path2, path3;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnBoolean(),
        });

        row = model.appendRow();
        model.setValue(row, column, false);
        row = model.appendRow();
        model.setValue(row, column, true);

        path1 = new TreePath("0");
        row = model.getIter(path1);
        assertEquals(false, model.getValue(row, column));

        path2 = new TreePath("1");
        row = model.getIter(path2);
        assertEquals(true, model.getValue(row, column));

        assertNotSame(path1, path2);
        assertFalse(path1.equals(path2));

        path3 = new TreePath("1");
        assertNotSame(path2, path3);
        assertTrue(path2.equals(path3));
        assertTrue(path3.equals(path2));
    }
}
