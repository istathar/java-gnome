/*
 * ValidateEnvironment.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.bindings;

import org.gnome.gtk.TestCaseGtk;

/**
 * Make sure our reimplementaiton of fetching environment variables works.
 * 
 * @author Andrew Cowie
 */
public class ValidateEnvironment extends TestCaseGtk
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
}
