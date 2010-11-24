/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * @author Vreixo Formoso
 * @author Stefan Prelle
 */
/*
 * Some are copy/adapted from ValidateTreeModel
 */
public class ValidateTreeStore extends GraphicalTestCase
{
    public final void testTreeStoreConstructorArguments() {
        try {
            new TreeStore(null);
            fail("Underlying library doesn't allow null arguments to TreeStore contstructor");
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            new TreeStore(new DataColumn[] {});
            fail("Underlying library doesn't allow zero columns in TreeStores");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }

    public final void testDataColumnTreeStoreConstructorInteraction() {
        final DataColumnString name;
        final DataColumnInteger age;
        final DataColumnBoolean useful;
        final DataColumn[] types;
        final TreeStore model;

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

        model = new TreeStore(types);

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
            column,
            column
        };

        try {
            new TreeStore(types);
            fail("Shouldn't be allowed to construct a TreeStore with the same DataColumn used twice");
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
        final TreeStore model;
        TreeIter iter;

        model = new TreeStore(new DataColumn[] {
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

    public void testAppendRow() {
        final TreeStore model;
        TreeIter iter1, iter2, iter3;

        model = new TreeStore(new DataColumn[] {
            new DataColumnString(),
        });

        iter1 = model.getIterFirst();
        assertNull(iter1);

        iter1 = model.appendRow();
        assertNotNull(iter1);

        iter1 = model.getIterFirst();
        assertNotNull(iter1);

        assertFalse(iter1.iterNext());

        iter1 = model.appendRow();
        assertNotNull(iter1);

        iter1 = model.getIterFirst();
        assertNotNull(iter1);
        assertTrue(iter1.iterNext());

        iter2 = model.appendChild(iter1);
        iter3 = iter2.copy();

        assertFalse(iter3.iterNext());

        iter3 = model.appendChild(iter1);
        assertTrue(iter2.iterNext());
        assertFalse(iter3.iterNext());

        iter1 = model.getIterFirst();
        assertNotNull(iter1);
        assertTrue(iter1.iterNext());
        assertFalse(iter1.iterNext());

        iter1 = iter2 = null;
        cycleGarbageCollector();
    }

    public void testIterHasChild() {
        final TreeStore model;
        TreeIter iter1, iter2;

        model = new TreeStore(new DataColumn[] {
            new DataColumnString(),
        });

        iter1 = model.appendRow();
        assertNotNull(iter1);
        assertFalse(model.iterHasChild(iter1));

        iter2 = model.appendChild(iter1);
        assertNotNull(iter2);

        assertFalse(model.iterHasChild(iter2));
        assertTrue(model.iterHasChild(iter1));

        iter1 = iter2 = null;
        cycleGarbageCollector();
    }

    public void testIterChildren() {
        final TreeStore model;
        final DataColumnString column;
        TreeIter iter1, iter2;

        model = new TreeStore(new DataColumn[] {
            column = new DataColumnString()
        });

        iter1 = model.appendRow();
        assertNotNull(iter1);
        assertFalse(model.iterHasChild(iter1));
        assertNull(model.iterChildren(iter1));

        iter2 = model.appendChild(iter1);
        assertNotNull(iter2);
        assertNull(model.iterChildren(iter2));
        assertNotNull(model.iterChildren(iter1));

        model.setValue(iter2, column, "1st child");
        iter2 = model.iterChildren(iter1);
        assertNotNull(iter2);
        assertEquals("1st child", model.getValue(iter2, column));
        assertNull(model.iterChildren(iter2));
        assertFalse(iter2.iterNext());

        iter2 = model.appendChild(iter1);
        model.setValue(iter2, column, "2nd child");

        iter2 = model.iterChildren(iter1);
        assertNotNull(iter2);
        assertEquals("1st child", model.getValue(iter2, column));
        assertNull(model.iterChildren(iter2));
        assertTrue(iter2.iterNext());
        assertEquals("2nd child", model.getValue(iter2, column));
        assertFalse(iter2.iterNext());

        iter1 = iter2 = null;
        cycleGarbageCollector();
    }

    public void testIterParent() {
        final TreeStore model;
        final DataColumnString column;
        TreeIter p1, p2, iter;

        model = new TreeStore(new DataColumn[] {
            column = new DataColumnString()
        });

        p1 = model.appendRow();
        assertNotNull(p1);
        assertNull(model.iterParent(p1));
        model.setValue(p1, column, "parent1");

        p2 = model.appendRow();
        assertNotNull(p2);
        assertNull(model.iterParent(p2));
        model.setValue(p2, column, "parent2");

        iter = model.appendChild(p1);
        assertNotNull(iter);
        iter = model.iterParent(iter);
        assertEquals("parent1", model.getValue(iter, column));

        iter = model.appendChild(p1);
        assertNotNull(iter);
        iter = model.iterParent(iter);
        assertEquals("parent1", model.getValue(iter, column));

        iter = model.appendChild(p2);
        assertNotNull(iter);
        iter = model.iterParent(iter);
        assertEquals("parent2", model.getValue(iter, column));
    }

    public final void testTreeIterFromTreePath() {
        final TreeStore model;
        final DataColumnInteger column;
        TreeIter row, child;
        TreePath path1, path2, path3;

        model = new TreeStore(new DataColumn[] {
            column = new DataColumnInteger(),
        });

        row = model.appendRow();
        model.setValue(row, column, 1);
        child = model.appendChild(row);
        model.setValue(child, column, 11);
        row = model.appendRow();
        model.setValue(row, column, 2);
        child = model.appendChild(row);
        model.setValue(child, column, 21);
        child = model.appendChild(row);
        model.setValue(child, column, 22);

        path1 = new TreePath("0");
        row = model.getIter(path1);
        assertEquals(1, model.getValue(row, column));

        path2 = new TreePath("1");
        row = model.getIter(path2);
        assertEquals(2, model.getValue(row, column));

        assertNotSame(path1, path2);
        assertFalse(path1.equals(path2));

        path3 = new TreePath("1");
        assertNotSame(path2, path3);
        assertTrue(path2.equals(path3));
        assertTrue(path3.equals(path2));

        path1 = new TreePath("0:0");
        row = model.getIter(path1);
        assertEquals(11, model.getValue(row, column));

        path1 = new TreePath("1:0");
        row = model.getIter(path1);
        assertEquals(21, model.getValue(row, column));

        path1 = new TreePath("1:1");
        row = model.getIter(path1);
        assertEquals(22, model.getValue(row, column));

        path1 = new TreePath("1:2");
        assertNull(model.getIter(path1));

        path1 = new TreePath("1:1:0");
        assertNull(model.getIter(path1));

        path1 = new TreePath("2");
        assertNull(model.getIter(path1));
    }

    public final void testTreePathFromTreeIter() {
        final TreeStore model;
        final DataColumnBoolean column;
        TreeIter row, child;
        TreePath path;

        model = new TreeStore(new DataColumn[] {
            column = new DataColumnBoolean(),
        });

        row = model.appendRow();
        model.setValue(row, column, false);
        row = model.appendRow();
        model.setValue(row, column, true);

        path = model.getPath(row);
        assertEquals("1", path.toString());

        child = model.appendChild(row);
        path = model.getPath(child);
        assertEquals("1:0", path.toString());

        child = model.appendChild(row);
        path = model.getPath(child);
        assertEquals("1:1", path.toString());

        child = model.appendChild(child);
        path = model.getPath(child);
        assertEquals("1:1:0", path.toString());
    }

    public final void testInsertRow() {
        final TreeStore model;
        final DataColumnString column;
        TreeIter row, row2, row3;

        model = new TreeStore(new DataColumn[] {
            column = new DataColumnString(),
        });

        // base data to play with
        row = model.appendRow();
        model.setValue(row, column, "a");
        row2 = model.appendChild(row);
        model.setValue(row2, column, "aa");
        row3 = model.appendChild(row2);
        model.setValue(row3, column, "aaa");
        row3 = model.appendChild(row2);
        model.setValue(row3, column, "aab");
        row = model.appendRow();
        model.setValue(row, column, "b");

        // Insert
        row = model.insertRow(null, 1);
        assertNotNull(row);
        model.setValue(row, column, "a-b");

        // Insert negative is not allowed
        try {
            model.insertRow(null, -1);
            fail("Negative insertion index not recognized");
        } catch (RuntimeException e) {
        }

        // Ensure that element is inserted between 1 and 2
        row = model.getIterFirst();
        assertEquals("a", model.getValue(row, column));
        assertTrue(row.iterNext());
        assertEquals("a-b", model.getValue(row, column));
        assertTrue(row.iterNext());
        assertEquals("b", model.getValue(row, column));
        assertFalse(row.iterNext());

        // Now test with depth of 3
        row2 = model.getIter(new TreePath("0:0"));
        row3 = model.insertRow(row2, 1);
        assertNotNull(row3);
        model.setValue(row3, column, "aaa-b");

        // Ensure that element is inserted between 1 and 2
        row3 = model.getIter(new TreePath("0:0:0"));
        assertEquals("aaa", model.getValue(row3, column));
        assertTrue(row3.iterNext());
        assertEquals("aaa-b", model.getValue(row3, column));
        assertTrue(row3.iterNext());
        assertEquals("aab", model.getValue(row3, column));
        assertFalse(row3.iterNext());
    }
}
