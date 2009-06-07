/*
 * ValidateTextViewSpelling.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Check that getting the GtkSpell helper object functions correctly.
 * 
 * @author Andrew Cowie
 */
public class ValidateTextViewSpelling extends TestCaseGtk
{
    public final void testAttachingDefault() {
        final TextView view;

        view = new TextView();

        try {
            view.getSpell();
            fail("Can't get without attaching!");
        } catch (IllegalStateException ise) {
            // good
        }

        view.attachSpell();
        assertNotNull(view.getSpell());

        try {
            view.attachSpell();
            fail("Can't attach twice");
        } catch (IllegalStateException ise) {
            // good
        }
    }

    public final void testAttachingSpecific() {
        final TextView view;
        final Spell helper;

        view = new TextView();
        view.attachSpell("en");

        try {
            view.attachSpell();
            fail("Can't attach twice");
        } catch (IllegalStateException ise) {
            // good
        }

        try {
            view.attachSpell("en");
            fail("Can't attach twice");
        } catch (IllegalStateException ise) {
            // good
        }

        helper = view.getSpell();
        assertNotNull(helper);

        try {
            helper.setLanguage("zu_LU");
            fail("Enchant should have failed for an obviously unknown language");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }
}
