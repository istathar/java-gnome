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
package org.freedesktop.bindings;

import org.gnome.gtk.GraphicalTestCase;

/**
 * Make sure our reimplementaiton of fetching environment variables works.
 * 
 * @author Andrew Cowie
 */
public class ValidateEnvironment extends GraphicalTestCase
{
    public final void testGetEnvironmentVariable() {
        final String home;
        String username;

        home = Environment.getEnv("HOME");

        assertNotNull(home);

        username = Environment.getEnv("LOGNAME");
        if (username == null) {
            username = Environment.getEnv("USER");
        }

        if (username != null) {
            assertTrue("How come your user name is not in your home directory location?",
                    (home.indexOf(username) != -1));
        }
    }

    public final void testSetEnvironmentVariable() {
        String blah;
        final String SOMETHING_UNIQUE = "SOMETHING_UNIQUE";

        /*
         * Ensure it's empty
         */
        blah = Environment.getEnv(SOMETHING_UNIQUE);
        assertNull(blah);

        Environment.setEnv(SOMETHING_UNIQUE, "Absolutely");

        blah = Environment.getEnv(SOMETHING_UNIQUE);
        assertNotNull(blah);
        assertEquals("Absolutely", blah);

        /*
         * And test that it will overwrite
         */
        Environment.setEnv(SOMETHING_UNIQUE, "Fantastic");

        blah = Environment.getEnv(SOMETHING_UNIQUE);
        assertNotNull(blah);
        assertEquals("Fantastic", blah);

        /*
         * Finally, test deleting
         */
        Environment.setEnv(SOMETHING_UNIQUE, null);
        blah = Environment.getEnv(SOMETHING_UNIQUE);
        assertNull(blah);
    }

    public final void testProcessID() {
        int pid;

        pid = Environment.getProcessID();

        assertTrue(pid > 1);
    }
}
