/*
 * ValidateTreeModel.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * ComboBox is full of insane nastiness. Test our binding of it here.
 * 
 * @author Andrew Cowie
 */
public class ValidateComboBox extends TestCaseGtk
{
    public final void testConstructor() {
        final ListStore model;
        final DataColumnString column;
        final ComboBox combo;
        TreeIter row;

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString(),
        });

        combo = new ComboBox(model);

        row = model.appendRow();
        model.setValue(row, column, "One");
        row = model.appendRow();
        model.setValue(row, column, "Two");
        row = model.appendRow();
        model.setValue(row, column, "Three");

        combo.setActive(2);
        assertEquals(2, combo.getActive());

        row = combo.getActiveIter();

        assertEquals("Three", model.getValue(row, column));
    }

    public void testTextSubclass() {
        final TextComboBox combo;
        final TreeModel model;

        combo = new TextComboBox();
        model = combo.getModel();

        assertEquals(0, sizeOfModel(model));

        combo.appendText("World");
        assertEquals(1, sizeOfModel(model));

        combo.prependText("Hello");
        assertEquals(2, sizeOfModel(model));

        combo.setActive(0);
        assertEquals("Hello", combo.getActiveText());
        combo.setActive(1);
        assertEquals("World", combo.getActiveText());

        combo.insertText(1, "big");
        assertEquals("World", combo.getActiveText());

        assertEquals(3, sizeOfModel(model));
        combo.setActive(1);
        assertEquals("big", combo.getActiveText());
    }

    private static int sizeOfModel(final TreeModel model) {
        int size;
        TreeIter row;

        size = 0;

        row = model.getIterFirst();
        if (row == null) {
            return 0;
        }

        do {
            size++;
        } while (row.iterNext());

        return size;
    }
}
