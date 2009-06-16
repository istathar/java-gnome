/*
 * ValidateUniqueApplications.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.unique;

import org.gnome.gtk.TestCaseGtk;

/**
 * Evaluate the behaviour of LibUnique
 */
public class ValidateUniqueApplications extends TestCaseGtk
{
    public final void testInstantiateApplicationObject() throws InterruptedException {
        Application app;
        final String name;

        try {
            app = new Application("", null);
            fail("Should have thrown exception");
            return;
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            app = new Application("MyApplication", null);
            fail("Should have thrown exception");
            return;
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            app = new Application("org.gnome.Invalid$Application", null);
            fail("Should have thrown exception");
            return;
        } catch (IllegalArgumentException iae) {
            // good
        }

        /*
         * This is tricky. If we didn't ensure a unique name, and you've
         * managed to get another one of these test cases running, and wedged,
         * then this would fail. So we do some minimal effort to come up with
         * a more-or-less likely-to-be-unique name.
         */

        name = "test.java-gnome.InstantiateApplicationObject" + this.hashCode();
        app = new Application(name, null);

        assertEquals(name, app.getName());
        assertFalse(app.isRunning());
    }
}
