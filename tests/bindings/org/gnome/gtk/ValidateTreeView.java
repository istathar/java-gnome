/*
 * ValidateTreeView.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
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

    public final void testDontAllowAddWithViewport() {
        final TreeView view;
        final ScrolledWindow scroll;

        view = new TreeView();
        scroll = new ScrolledWindow();

        try {
            scroll.addWithViewport(view);
            fail("Should have been prevented");
        } catch (IllegalArgumentException iae) {
            // good
        }
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
