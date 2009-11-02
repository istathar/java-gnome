/*
 * ValidateTreeView.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
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
public class ValidateIconView extends GraphicalTestCase
{
    public final void testValidateAutomaticProperties() {
        final ListStore model;
        final IconView view;

        model = new ListStore(new DataColumn[] {
                new DataColumnPixbuf(), new DataColumnString()
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
}
