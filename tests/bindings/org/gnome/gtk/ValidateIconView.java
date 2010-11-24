/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * @author Guillaume Mazoyer
 */
public class ValidateIconView extends GraphicalTestCase
{
    public final void testValidateAutomaticProperties() {
        final ListStore model;
        final IconView view;

        model = new ListStore(new DataColumn[] {
            new DataColumnPixbuf(),
            new DataColumnString()
        });

        view = new IconView(model);

        try {
            view.setColumns(-2);
            fail("Should have thrown");
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            view.setColumns(0);
            fail("Should have thrown");
        } catch (IllegalArgumentException iae) {
            // good
        }

        view.setColumns(-1);
        view.setColumns(6);

        try {
            view.setItemWidth(-2);
            fail("Should have thrown");
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            view.setItemWidth(0);
            fail("Should have thrown");
        } catch (IllegalArgumentException iae) {
            // good
        }

        view.setItemWidth(-1);
        view.setItemWidth(100);
    }

    public final void testValidateSelection() {
        final ListStore model;
        final IconView view;
        final DataColumnPixbuf pixbuf;
        final DataColumnString text;

        TreeIter row;
        TreePath path;

        model = new ListStore(new DataColumn[] {
            pixbuf = new DataColumnPixbuf(),
            text = new DataColumnString()
        });

        view = new IconView(model);

        row = model.appendRow();
        model.setValue(row, pixbuf, null);
        model.setValue(row, text, "test_case");

        path = new TreePath("0");
        view.selectPath(path);

        assertEquals(1, view.getSelectedItems().length);
        assertTrue(view.isSelected(path));

        view.unselectPath(path);
        assertFalse(view.isSelected(path));
    }
}
