/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010-2013 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.glib;

import org.freedesktop.bindings.Environment;
import org.gnome.gtk.GraphicalTestCase;

/**
 * Investigate returns from various GLib utility functions.
 * 
 * @author Serkan Kaba
 * @author Andrew Cowie
 * @author Guillaume Mazoyer
 */
public class ValidateUtilityFunctions extends GraphicalTestCase
{
    public final void testGetUserName() {
        String str;
        final String userNameEnvironment, userNameGlib;

        str = Environment.getEnv("USERNAME");
        if (str == null) {
            str = Environment.getEnv("USER");
        }
        if (str == null) {
            str = Environment.getEnv("LOGNAME");
        }
        if (str == null) {
            fail("No USERNAME or USER in environment?");
        }

        userNameEnvironment = str;
        userNameGlib = Glib.getUserName();

        assertEquals(userNameEnvironment, userNameGlib);
    }

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

    public final void testFormatSizeForDisplay() {
        String result;

        result = Glib.formatSize(1000);
        assertEquals("1.0 kB", result);

        result = Glib.formatSize(1000 * 1000);
        assertEquals("1.0 MB", result);

        result = Glib.formatSize(1000 * 1000 * 1000);
        assertEquals("1.0 GB", result);

        result = Glib.formatSize(1024, FormatSizeFlags.IEC_UNITS);
        assertEquals("1.0 KiB", result);

        result = Glib.formatSize(1024 * 1024, FormatSizeFlags.IEC_UNITS);
        assertEquals("1.0 MiB", result);

        result = Glib.formatSize(1024 * 1024 * 1024, FormatSizeFlags.IEC_UNITS);
        assertEquals("1.0 GiB", result);
    }

    public final void testMarkupEscapeText() {
        String result;

        result = Glib.markupEscapeText("Hello");
        assertEquals("Hello", result);

        result = Glib.markupEscapeText("& World");
        assertEquals("&amp; World", result);

        result = Glib.markupEscapeText("Team > Me");
        assertEquals("Team &gt; Me", result);

        result = Glib.markupEscapeText("I < Team");
        assertEquals("I &lt; Team", result);
    }

    public final void testMarkupEscapeWhitespace() {
        String result;

        result = Glib.markupEscapeText("Goodbye\tCruel\nWorld");
        assertEquals("Goodbye\tCruel\nWorld", result);
    }
}
