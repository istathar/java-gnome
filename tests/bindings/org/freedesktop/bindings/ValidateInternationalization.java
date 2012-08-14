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
package org.freedesktop.bindings;

import org.gnome.gtk.GraphicalTestCase;

import static org.freedesktop.bindings.Internationalization.N_;
import static org.freedesktop.bindings.Internationalization._;
import static org.freedesktop.bindings.Internationalization.translateCountryName;
import static org.freedesktop.bindings.Internationalization.translateLanguageName;

/**
 * Exercise the Internationalization setup code.
 * 
 * @author Andrew Cowie
 */
/*
 * Extends TestCaseGtk to ensure Gtk.init() has been called.
 */
public class ValidateInternationalization extends GraphicalTestCase
{
    private static String LC_ALL;

    private static String LANGUAGE;

    private static String LANG;

    public final void testInitialization() {
        LC_ALL = Environment.getEnv("LC_ALL");
        LANGUAGE = Environment.getEnv("LANGUAGE");
        LANG = Environment.getEnv("LANG");

        try {
            Internationalization.init(null, "/usr/share/locale");
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException iae) {
            // good
        }
        try {
            Internationalization.init("", "/usr/share/locale");
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException iae) {
            // good
        }
        try {
            Internationalization.init("tester", null);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException iae) {
            // good
        }
        try {
            Internationalization.init("tester", "/no/such/directory/");
            fail("Should have thrown FatalError");
        } catch (FatalError iae) {
            // good
        }

    }

    public final void setUp() {
        /*
         * The choice here of en_US is simply because _everyone_ is going to
         * have that installed, somewhere. We are not testing changing locales
         * here, so if an American is running this, no big deal. We just need
         * something not the C locale.
         */
        Environment.setEnv("LC_ALL", "en_US.UTF-8");
        Environment.setEnv("LANGUAGE", "en_US.UTF-8");
        Environment.setEnv("LANG", "en_US.UTF-8");
        Internationalization.init("unittest", "tmp/locale");
    }

    public final void testTranslation() {
        assertEquals("Hello", _("login"));
    }

    private static final String GOODBYE = N_("logoff");

    public final void testStaticWrapper() {
        assertEquals("logoff", N_(GOODBYE));
        assertSame(GOODBYE, N_(GOODBYE));
        assertEquals("Goodbye", _(GOODBYE));
    }

    /*
     * We have coded a) a check for empty Strings, and b) that in the case of
     * no translation being available, the supplied String reference being
     * returned as-is. This works around various gettext() limitations, and
     * this test verfies this behaviour.
     */
    public final void testAvoidGettextBugs() {
        final String insult;

        assertEquals("", _(""));
        assertSame("", _(""));

        // *NOT* marked with the N_ wrapper!
        insult = "Yo mamma";
        assertSame(insult, _(insult));
    }

    /*
     * Without changing to another locale, this doesn't test very much!
     */
    public final void testLanguageAndCountryTranslation() {
        String language, country;

        language = translateLanguageName("Spanish; Castilian");
        country = translateCountryName("Spain");

        assertEquals("Spanish; Castilian", language);
        assertEquals("Spain", country);
    }

    public final void testRestoreEnvironment() {
        Environment.setEnv("LC_ALL", LC_ALL);
        Environment.setEnv("LANGUAGE", LANGUAGE);
        Environment.setEnv("LANG", LANG);
    }
}
