/*
 * ValidateScreensAndDisplays.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
