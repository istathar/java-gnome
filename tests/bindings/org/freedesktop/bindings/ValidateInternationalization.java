/*
 * ValidateInternationalization.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.bindings;

import static org.freedesktop.bindings.Internationalization.N_;
import static org.freedesktop.bindings.Internationalization._;

import org.gnome.gtk.GraphicalTestCase;

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

        /*
         * The choice here of en_US is simply because _everyone_ is going to
         * have that installed, somewhere. We are not testingchanging locales
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

    public final void testRestoreEnvironment() {
        Environment.setEnv("LC_ALL", LC_ALL);
        Environment.setEnv("LANGUAGE", LANGUAGE);
        Environment.setEnv("LANG", LANG);
    }
}
