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

import org.gnome.gtk.GraphicalTestCase;

/**
 * Evaluate the behaviour of LibUnique
 */
public class ValidateUniqueApplications extends GraphicalTestCase
{
    public final void testNameValidation() {
        Application app;

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

        if (false) {
            app.getClass(); // avoid warning
        }
    }

    /*
     * This is tricky. If we didn't ensure a unique name, and you've managed
     * to get another one of these test cases running, and wedged, then this
     * would fail. So we do some minimal effort to come up with a more-or-less
     * likely-to-be-unique name.
     */
    public final void testInstantiateApplicationObject() {
        final Application app;
        final String name;

        name = "test.java-gnome.InstantiateApplicationObject" + this.hashCode();
        app = new Application(name, null);

        assertEquals(name, app.getName());
        assertFalse(app.isRunning());
    }

    /*
     * Here's an interesting one. Since we've long said that GNOME is a
     * prerequisite for java-gnome, and in any event we encourage people
     * developing GNOME applications to be actually using GNOME, we "know"
     * Nautilus is running. :)
     * 
     * FUTURE This won't work in headless build environment, and really our
     * tests should pass in such. But it's this or we'd have to fire off
     * another process. This will do nicely for now.
     */
    public final void testIsNautilusRunning() {
        final Application app;

        app = new Application("org.gnome.Nautilus", null);
        assertTrue(app.isRunning());
    }

    /*
     * We, of course, know nothing of Nautilus's responses to internally
     * designated commands. They're "internal". But sending it Command.OPEN
     * results in your home directory coming up (so awesome), and CLOSE
     * results in it restarting (!). ACTIVATE seems harmless, and in testing
     * this I got an OK back.
     */
    public final void testSendToNautilus() {
        final Application app;
        final Response result;

        app = new Application("org.gnome.Nautilus", null);
        assertTrue(app.isRunning());

        result = app.sendMessage(Command.ACTIVATE, null);
        assertSame(Response.OK, result);
    }
}
