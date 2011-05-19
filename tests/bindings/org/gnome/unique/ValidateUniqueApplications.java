/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.unique;

import org.gnome.gtk.GraphicalTestCase;

/**
 * Evaluate the behaviour of LibUnique
 */
public class ValidateUniqueApplications extends GraphicalTestCase
{
    public final void testNameValidation() {
        @SuppressWarnings("unused")
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
    public final void failsIsNautilusRunning() {
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
    public final void failsSendToNautilus() {
        final Application app;
        final Response result;

        app = new Application("org.gnome.Nautilus", null);
        assertTrue(app.isRunning());

        result = app.sendMessage(Command.ACTIVATE, null);
        assertSame(Response.OK, result);
    }
}
