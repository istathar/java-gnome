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
public class ValidateTextViewProperties extends TestCaseGtk
{
    public final void testTabCharacter() {
        final TextView view;
        view = new TextView();

        assertEquals(true, GtkTextView.getAcceptsTab(view));

        view.setAcceptsTab(false);

        assertEquals(false, GtkTextView.getAcceptsTab(view));
    }
}
