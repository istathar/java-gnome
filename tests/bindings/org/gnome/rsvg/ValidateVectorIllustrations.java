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
package org.gnome.rsvg;

/*
 * Inspired by org.gnome.gdk.ValidateImageHandling
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.freedesktop.cairo.Surface;
import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.GraphicalTestCase;

/**
 * @author Andrew Cowie
 */
public class ValidateVectorIllustrations extends GraphicalTestCase
{
    /*
     * Is this actually necessary?
     */
    public final void testInitializing() {
        Rsvg.init();
    }

    public final void testLoadingFromFile() {
        try {
            new Handle("Linux_Tux.svg");
            fail("Should have complained not being able to find file");
        } catch (FileNotFoundException fnfe) {
            // good
        }

        try {
            new Handle("README.markdown");
            fail("Specified file not an image, should have thrown");
        } catch (RuntimeException re) {
            // good
        } catch (FileNotFoundException fnfe) {
            fail("Should have found non-image file");
        }

        try {
            new Handle("tests/bindings/org/gnome/rsvg/Linux_Tux.svg");
        } catch (FileNotFoundException fnfe) {
            fail("Target file should exist. Did someone move the test image?");
        }
    }

    public final void testHandleMethods() throws IOException {
        final Handle graphic;
        final DimensionData dim;

        graphic = new Handle("tests/bindings/org/gnome/rsvg/Linux_Tux.svg");
        dim = graphic.getDimensions();
        assertEquals(334, dim.getWidth());
        assertEquals(393, dim.getHeight());

        assertEquals(90.0, graphic.getDPIX());
        assertEquals(90.0, graphic.getDPIY());
    }

    public final void testHandleRendering() throws IOException {
        final Handle graphic;
        final Surface surface;
        final Context cr;
        final File target;
        final int width;

        surface = new ImageSurface(Format.ARGB32, 400, 500);
        cr = new Context(surface);

        graphic = new Handle("tests/bindings/org/gnome/rsvg/Linux_Tux.svg");
        cr.showHandle(graphic);

        /*
         * Ok, so we've exercised the code paths. How can we actually validate
         * it? TODO This isn't much of a test; as written it just traverses
         * the Cairo ImageSurface logic which isn't anything to do with
         * whether or not our graphic got rendered to it.
         */

        target = new File("tmp/tests/org/gnome/rsvg/Linux_Tux.png");
        if (target.exists()) {
            target.delete();
        }

        surface.writeToPNG("tmp/tests/org/gnome/rsvg/Linux_Tux.png");
        surface.finish();

        assertTrue(target.exists());
        width = Pixbuf.getFileInfoWidth("tmp/tests/org/gnome/rsvg/Linux_Tux.png");
        assertEquals(400, width);
    }
}
