/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gdk;

import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.Window;

/**
 * @author Andrew Cowie
 */
public class ValidateScreensAndDisplays extends GraphicalTestCase
{
    /*
     * This tests the workaround employed in Gdk.c to manually increase the
     * ref count of the default GdkScreen; if that bug ever gets fixed and the
     * workaround removed, this test should continue to work.
     */
    public final void testGdkLackingRefCountToDefaultScreenBug() {
        final Window w;
        Screen s;

        w = new Window();

        cycleMainLoop();

        s = w.getScreen();
        assertTrue(s.getHeight() > 0);
        s = null;
        cycleMainLoop();

        cycleGarbageCollector();
        // at this point, if we haven't crashed, that's a good thing.
    }
}
