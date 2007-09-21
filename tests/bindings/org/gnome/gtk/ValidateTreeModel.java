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

        iter = model.append();
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

        row = model.append();
        model.setValue(row, column, "Hello");

        assertEquals("Hello", model.getValueString(row, column));
    }
}
