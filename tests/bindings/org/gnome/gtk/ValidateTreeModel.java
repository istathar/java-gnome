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
    /*
     * Check the manually written code in GtkTreeIterOverride which allocates
     * and returns us a TreeIter. Both append() and getIterFirst() call
     * TreeIter.<init>() which invokes this code.
     */
    public final void testAllocateTreeIter() {
        final ListStore model;
        TreeIter iter;

        // FIXME
        model = new ListStore(new Class[] {});

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

        model = new ListStore(new Class[] {
            String.class
        });

        row = model.append();
        model.setValueString(row, 0, "Hello");

        assertEquals("Hello", model.getValueString(row, 0));
    }
}
