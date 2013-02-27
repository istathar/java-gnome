/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2013 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gtk;

/**
 * Evaluate the uniquness behaviour of GtkApplication
 */
public class ValidateUniqueApplications extends GraphicalTestCase
{
    public final void testNameValidation() {
        @SuppressWarnings("unused")
        Application app;

        try {
            app = new Application("");
            fail("Should have thrown exception");
            return;
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            app = new Application("MyApplication");
            fail("Should have thrown exception");
            return;
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            app = new Application("org.gnome.Invalid$Application");
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

    private boolean hit;

    public final void testInstantiateApplicationObject() {
        final Application app;
        final String name;

        name = "test.java-gnome.InstantiateApplicationObject" + this.hashCode();
        app = new Application(name);

        assertEquals(name, app.getApplicationId());

        hit = false;

        app.connect(new Application.Startup() {
            public void onStartup(Application source) {
                assertFalse(app.isRemote());
                hit = true;
            }
        });

        app.connect(new Application.Activate() {
            public void onActivate(Application source) {
                source.quit();
            }
        });

        app.run(null);
        assertTrue(hit);
    }

    private int cookie;

    public final void testApplicationInhibition() {
        final Application app;
        final String name;

        name = "test.java-gnome.ApplicationInhibition" + this.hashCode();
        app = new Application(name);

        app.connect(new Application.Startup() {
            public void onStartup(Application source) {
                cookie = app.inhibit(null, ApplicationInhibitFlags.LOGOUT, "Inhibition test");

                assertFalse(app.isInhibited(ApplicationInhibitFlags.SWITCH));
                assertTrue(app.isInhibited(ApplicationInhibitFlags.LOGOUT));

                app.uninhibit(cookie);

                assertFalse(app.isInhibited(ApplicationInhibitFlags.LOGOUT));
            }
        });

        app.connect(new Application.Activate() {
            public void onActivate(Application source) {
                source.quit();
            }
        });

        app.run(null);
    }
}
