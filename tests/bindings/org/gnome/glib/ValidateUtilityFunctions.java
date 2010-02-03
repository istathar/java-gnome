/*
 * ValidateUtilityMethods.java
 *
 * Copyright (c) 20099 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.glib;

import org.gnome.gtk.GraphicalTestCase;

/**
 * Investigate returns from XDG utility functions
 * 
 * @author Andrew Cowie
 */
public class ValidateUtilityFunctions extends GraphicalTestCase
{
    /*
     * This is probably fragile; after all, the idea is that this is variable
     * and from the environment. But it's actually one of those
     * "you know what it is anyway" things. So be it.
     */
    public final void testGetUserConfigDir() {
        final String home, conf;

        home = System.getProperty("user.home");

        conf = Glib.getUserConfigDir();

        assertNotNull(conf);
        assertEquals(home + "/.config", conf);
    }
}
