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

import java.io.File;
import java.io.FileNotFoundException;

import org.gnome.gdk.Pixbuf;

/**
 * @author Andrew Cowie
 */
public class ValidateTreeModel extends GraphicalTestCase
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
            column,
            column
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

    public final void testSettingValueLongColumn() {
        final ListStore model;
        TreeIter row;
        DataColumnLong column;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnLong(),
        });

        row = model.appendRow();
        model.setValue(row, column, -600000000000L);

        assertEquals(-600000000000L, model.getValue(row, column));
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

    public final void testTreePathFromToString() {
        TreePath path;

        path = new TreePath("42");
        assertEquals("42", path.toString());
        /*
         * That this points to the 43rd row of something is irrelevent. These
         * are abstract until used with model.getIter()
         */
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

    public final void testTreePathFromTreeIter() {
        final ListStore model;
        final DataColumnBoolean column;
        TreeIter row;
        TreePath path;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnBoolean(),
        });

        row = model.appendRow();
        model.setValue(row, column, false);
        row = model.appendRow();
        model.setValue(row, column, true);

        path = model.getPath(row);
        assertEquals("1", path.toString());
    }

    /*
     * This could move to a TreeView test case class if we create one.
     */
    public final void testTreeIterFromTreeSelection() {
        final ListStore model;
        final DataColumnBoolean column;
        TreeIter row;
        TreePath path0, path1;
        final TreeView view;
        final TreeSelection helper;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnBoolean(),
        });

        row = model.appendRow();
        model.setValue(row, column, false);
        row = model.appendRow();
        model.setValue(row, column, true);

        /*
         * Same setup as previous one. Now create a TreeView and see what
         * happens if we manually select things.
         */

        view = new TreeView(model);
        helper = view.getSelection();

        /*
         * We have commented that the default is SINGLE. If this fails, change
         * the documentation of TreeSelection's setMode().
         */

        assertSame("GTK default behaviour changed. Adjust documentation", SelectionMode.SINGLE,
                helper.getMode());

        helper.setMode(SelectionMode.BROWSE);
        assertSame(SelectionMode.BROWSE, helper.getMode());

        path0 = new TreePath("0");
        helper.selectRow(path0);

        row = model.getIter(path0);
        assertEquals(false, model.getValue(row, column));

        row = helper.getSelected();
        assertEquals(false, model.getValue(row, column));

        path1 = new TreePath("1");
        helper.selectRow(path0);

        row = model.getIter(path1);
        assertEquals(true, model.getValue(row, column));

        // haven't moved selection yet
        row = helper.getSelected();
        assertEquals(false, model.getValue(row, column));

        // now move it
        helper.selectRow(path1);
        row = helper.getSelected();
        assertEquals(true, model.getValue(row, column));

        /*
         * And now test TreeIter form of selectRow()
         */

        row = model.getIter(path0);
        helper.selectRow(row);

        row = helper.getSelected();
        assertEquals(false, model.getValue(row, column));
    }

    public final void testPixbufColumns() throws FileNotFoundException {
        final ListStore model;
        final DataColumnPixbuf column;
        final TreeIter row;
        final Pixbuf image;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnPixbuf(),
        });

        row = model.appendRow();
        image = new Pixbuf("src/bindings/java-gnome_Icon.png");

        model.setValue(row, column, image);

        assertSame(image, model.getValue(row, column));
    }

    public final void testSettingValueReferenceColumn() {
        final ListStore model;
        final DataColumnReference<File> column;
        final TreeIter row;
        final File target;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnReference<File>(),
        });

        row = model.appendRow();
        target = new File("/etc/passwd");
        model.setValue(row, column, target);

        assertSame(target, model.getValue(row, column));
    }

    public final void testIterationOverModel() {
        final ListStore model;
        final DataColumnString column;
        TreeIter row;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString(),
        });

        for (int i = 0; i < 10; i++) {
            row = model.appendRow();
            model.setValue(row, column, "" + i);
        }

        /*
         * now exercise our loop
         */

        int j = 0;

        row = model.getIterFirst();
        do {
            assertEquals("" + j, model.getValue(row, column));
            j++;
        } while (row.iterNext());

        assertEquals(10, j);
    }

    public final void testTreeRowReferences() {
        final ListStore model;
        final DataColumnString text;
        final DataColumnInteger sort;
        TreeIter row;
        TreePath path;
        final TreeView view;
        TreeViewColumn vertical;
        CellRendererText renderer;
        TreeRowReference ref;

        model = new ListStore(new DataColumn[] {
            text = new DataColumnString(),
            sort = new DataColumnInteger(),
        });

        /*
         * Initial order: 1, 2, 3, ... 10
         */
        for (int i = 1; i <= 10; i++) {
            row = model.appendRow();
            model.setValue(row, text, Integer.toString(i));
            model.setValue(row, sort, i);
        }

        view = new TreeView(model);
        vertical = view.appendColumn();
        renderer = new CellRendererText(vertical);
        renderer.setText(text);

        path = new TreePath("1");
        row = model.getIter(path);
        assertEquals("2", model.getValue(row, text));

        ref = new TreeRowReference(model, path);

        path = ref.getPath();
        row = model.getIter(path);
        assertEquals("2", model.getValue(row, text));

        /*
         * Rearrange things: sorting will change order to 1, 10, 2, 3, ... 9
         */
        vertical.setSortColumn(text);
        vertical.emitClicked();

        path = new TreePath("1");
        row = model.getIter(path);
        assertEquals("10", model.getValue(row, text));

        /*
         * But is reference still stable?
         */
        path = ref.getPath();
        row = model.getIter(path);
        assertEquals("2", model.getValue(row, text)); // yup

        model.clear();
        path = ref.getPath();
        assertNull(path);
    }

    public final void testCheckIter() {
        final ListStore model1, model2;
        TreeIter row;
        DataColumnString column1, column2;

        model1 = new ListStore(new DataColumn[] {
            column1 = new DataColumnString(),
        });

        model2 = new ListStore(new DataColumn[] {
            column2 = new DataColumnString(),
        });

        row = model1.appendRow();
        model1.setValue(row, column1, "Hello");

        assertEquals("Hello", model1.getValue(row, column1));

        try {
            model2.setValue(row, column2, "Hello");
            fail("model2 has accepted a model1 TreeIter");
        } catch (IllegalArgumentException iae) {
            // ok
        }
    }

    public final void testDeleteRow() {
        final ListStore model;
        final DataColumnInteger column;
        TreeIter row;
        int i, sum;
        boolean result;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnInteger(),
        });

        for (i = 1; i <= 5; i++) {
            row = model.appendRow();
            model.setValue(row, column, i);
        }

        row = model.getIterFirst();
        sum = 0;
        do {
            sum += model.getValue(row, column);
        } while (row.iterNext());
        assertEquals(15, sum);

        row = model.getIterFirst();

        result = model.removeRow(row);
        assertTrue(result);

        row = model.getIterFirst();
        sum = 0;
        do {
            sum += model.getValue(row, column);
        } while (row.iterNext());
        assertEquals(14, sum);

        row = model.getIterFirst();
        result = model.removeRow(row);
        assertTrue(result);
        result = model.removeRow(row);
        assertTrue(result);
        result = model.removeRow(row);
        assertTrue(result);
        result = model.removeRow(row);
        assertFalse(result);
    }

    public final void testInsertRow() {
        final ListStore model;
        final DataColumnInteger column;
        TreeIter row, sibbling;
        int i;
        String str;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnInteger(),
        });

        for (i = 0; i < 10; i += 3) {
            row = model.appendRow();
            model.setValue(row, column, i);
        }

        try {
            row = model.insertRow(-1);
            fail("Should have thrown");
        } catch (IllegalArgumentException iae) {
            // good
        }

        str = "";
        row = model.getIterFirst();
        do {
            str += model.getValue(row, column);
        } while (row.iterNext());
        assertEquals("0369", str);

        row = model.insertRow(1);
        model.setValue(row, column, 2);

        str = "";
        row = model.getIterFirst();
        do {
            str += model.getValue(row, column);
        } while (row.iterNext());
        assertEquals("02369", str);

        row = model.insertRow(50);
        model.setValue(row, column, 8);

        str = "";
        row = model.getIterFirst();
        do {
            str += model.getValue(row, column);
        } while (row.iterNext());
        assertEquals("023698", str);

        sibbling = model.getIter(new TreePath("3"));
        row = model.insertRow(sibbling);
        model.setValue(row, column, 7);

        str = "";
        row = model.getIterFirst();
        do {
            str += model.getValue(row, column);
        } while (row.iterNext());
        assertEquals("0237698", str);
    }
}
