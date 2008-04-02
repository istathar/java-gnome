/*
 * ValidateInternationalization.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.bindings;

import static org.freedesktop.bindings.Internationalization.N_;
import static org.freedesktop.bindings.Internationalization._;

import org.gnome.gtk.TestCaseGtk;

/**
 * Exercise the Internationalization setup code.
 * 
 * @author Andrew Cowie
 */
/*
 * Extends TestCaseGtk to ensure Gtk.init() has been called.
 */
public class ValidateInternationalization extends TestCaseGtk
{
    public final void testInternationalizationInitialization() {
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

        Environment.setEnv("LC_ALL", "fr_CA.UTF-8");
        Internationalization.init("ValidateInternals", "tmp/tests/locale");
    }

    public final void testTranslation() {
        assertEquals("Bonjour", _("Hello"));
    }

    private static final String GOODBYE = N_("Goodbye");

    public final void testStaticWrapper() {
        assertEquals("Goodbye", N_(GOODBYE));
        assertSame(GOODBYE, N_(GOODBYE));
        assertEquals("Au revoir", _(GOODBYE));
    }
}
