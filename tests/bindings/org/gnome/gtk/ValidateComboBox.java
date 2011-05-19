/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
 * ComboBox is full of insane nastiness. Test our binding of it here.
 * 
 * @author Andrew Cowie
 */
public class ValidateComboBox extends GraphicalTestCase
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

    public final void testComboTextSubclass() {
        final ComboBoxText combo;
        final TreeModel model;

        combo = new ComboBoxText();
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

    public final void skipExtractEntry() {
        final ComboBoxText combo1;
        final Widget w;
        final Entry e;

        combo1 = new ComboBoxText();

        combo1.appendText("espresso");

        w = combo1.getChild();
        assertTrue(w instanceof Entry);

        e = (Entry) w;
        assertEquals("", e.getText());

        combo1.setActive(0);
        assertEquals("espresso", e.getText());
    }

    public final void skipModelBackingEntry() {
        final ListStore model;
        final DataColumnString column;
        TreeIter row;
        final ComboBox combo;
        final Entry entry;

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

        assertEquals(3, sizeOfModel(model));

        entry = (Entry) combo.getChild();
        assertEquals("", entry.getText());

        combo.setActiveIter(row);
        assertEquals(2, combo.getActive());
        assertEquals("Three", entry.getText());
    }
}
