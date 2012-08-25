/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.icons;

import junit.framework.TestCase;

/**
 * Validate the handling of Icon items.
 * 
 * @author Guillaume Mazoyer
 */
public class ValidateIconItems extends TestCase
{
    /*
     * Force classloader to [re-run (sic)] static initializers by referencing
     * the constants we're going to check against. I'm not really convinced
     * that this is intuitive; these tests used to work without this.
     */
    public final void setUp() {
        assertNotNull(PlaceIcon.USER_TRASH);
        assertNotNull(ActionIcon.DOCUMENT_SAVE);
    }

    public final void testIconNames() {
        String name;

        name = Helper.getName(PlaceIcon.USER_TRASH);
        assertEquals("user-trash", name);

        name = Helper.getName(ActionIcon.DOCUMENT_SAVE);
        assertEquals("document-save", name);

        assertFalse(PlaceIcon.USER_TRASH == ActionIcon.DOCUMENT_SAVE);
    }

    public final void testIconRegistry() {
        Icon icon;

        icon = Helper.instanceFor("user-trash");
        assertSame(PlaceIcon.USER_TRASH, icon);

        icon = Helper.instanceFor("document-save");
        assertSame(ActionIcon.DOCUMENT_SAVE, icon);
    }

    public final void testIconEquals() {
        assertTrue(ActionIcon.APPLICATION_EXIT.equals(ActionIcon.APPLICATION_EXIT));
        assertFalse(ActionIcon.APPLICATION_EXIT.equals(ActionIcon.ZOOM_ORIGINAL));
    }
}
