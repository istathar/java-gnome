/*
 * ValidateTextViewProperties.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Verify default values we document and other related tests.
 * 
 * @author Andrew Cowie
 */
public class ValidateTextViewProperties extends GraphicalTestCase
{
    public final void testTabCharacter() {
        final TextView view;

        view = new TextView();

        assertEquals(true, GtkTextView.getAcceptsTab(view));

        view.setAcceptsTab(false);

        assertEquals(false, GtkTextView.getAcceptsTab(view));
    }

    public final void testMarginSetters() {
        final TextView view;

        view = new TextView();

        assertEquals(0, GtkTextView.getLeftMargin(view));

        try {
            view.setMarginLeft(-5);
            fail();
        } catch (IllegalArgumentException iae) {
            // good
        }

        view.setMarginLeft(5);
        assertEquals(5, GtkTextView.getLeftMargin(view));

        /*
         * Repeat for margin-right
         */

        assertEquals(0, GtkTextView.getRightMargin(view));

        try {
            view.setMarginRight(-11);
            fail();
        } catch (IllegalArgumentException iae) {
            // good
        }

        view.setMarginRight(11);
        assertEquals(11, GtkTextView.getRightMargin(view));
    }
}
