/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
