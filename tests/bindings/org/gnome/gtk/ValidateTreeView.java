/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
public class ValidateTreeView extends GraphicalTestCase
{
    /*
     * This is only here to validate the comments we've made in our API
     * documentation...
     */
    public final void testDefaultSelection() {
        final TreeView view;
        final TreeSelection selection;

        view = new TreeView();
        selection = view.getSelection();

        assertEquals(SelectionMode.SINGLE, selection.getMode());
    }

    public final void testCellRendererToggleProperties() {
        final TreeView view;
        final TreeViewColumn vertical;
        final CellRendererToggle renderer;

        view = new TreeView();
        vertical = view.appendColumn();
        renderer = new CellRendererToggle(vertical);

        /*
         * Check default
         */
        assertEquals(false, GtkCellRendererToggle.getRadio(renderer));

        /*
         * Check setter
         */

        renderer.setRadio(true);
        assertEquals(true, GtkCellRendererToggle.getRadio(renderer));

        /*
         * Observe bug in setter
         */

        renderer.setRadio(false);
        assertEquals(false, GtkCellRendererToggle.getRadio(renderer));

        // and fixed.

        /*
         * Now check the fixed active property. Same sequenece as above.
         */

        assertEquals(false, renderer.getActive());
        renderer.setActive(true);
        assertEquals(true, renderer.getActive());
        renderer.setActive(false);
        assertEquals(false, renderer.getActive());
    }

    /*
     * Test if getting a column by index works as expected
     */
    public final void testGetColumn() {
        final TreeView view;
        final TreeViewColumn vertical, out;
        final String testName = "Test";

        view = new TreeView();
        vertical = view.appendColumn();
        vertical.setTitle(testName);

        out = view.getColumn(0);

        assertNotNull(out);
        assertEquals(testName, out.getTitle());
        assertSame(vertical, out);
    }
}
