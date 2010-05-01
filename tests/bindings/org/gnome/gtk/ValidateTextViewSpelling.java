/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Check that getting the GtkSpell helper object functions correctly.
 * 
 * @author Andrew Cowie
 */
public class ValidateTextViewSpelling extends GraphicalTestCase
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
        } catch (RuntimeException iae) {
            // good
        }
    }
}
