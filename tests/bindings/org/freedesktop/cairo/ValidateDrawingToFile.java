/*
 * ValidateDrawingToFile.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import java.io.File;
import java.io.IOException;

import org.gnome.gtk.Gtk;
import org.gnome.gtk.GraphicalTestCase;

/**
 * Started life as the Cairo drawing example; evolved to a unit test. This
 * TestCase is predicated on the assumption that PNGs are deterministic. I
 * don't know of anything that actually guarantees or requires this to be the
 * case, but it seems a reasonable guess, given that it is ultimately just a
 * pixel mapping. Perhaps compression will get in the way; if so, switch this
 * to a TIFF.
 * 
 * <p>
 * As you will see, the file being compared was (can be) generated with this
 * code, so if you need to redo this run it as an executable.
 * 
 * @author Andrew Cowie
 */
/*
 * TODO Actually do the comaprison!
 */
public class ValidateDrawingToFile extends GraphicalTestCase
{
    private static String OUTPUT_FILENAME = "tmp/tests/ValidateDrawingToFile.png";

    private static void draw() {
        final Context cr;
        final ImageSurface surface;

        surface = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(surface);

        cr.setSource(0.0, 0.0, 1.0, 0.8);
        cr.moveTo(10, 10);
        cr.lineTo(20, 5);
        cr.stroke();

        try {
            surface.writeToPNG(OUTPUT_FILENAME);
        } catch (IOException e) {
            fail("Unexpected file write error");
        }

        try {
            surface.writeToPNG("/an/inexistent/filename");
            fail("You should get an IOException");
        } catch (IOException e) {
            // ok
        }
    }

    public final void testImageSurfaceWriteToPNG() {
        final File target;

        target = new File(OUTPUT_FILENAME);

        if (target.exists()) {
            target.delete();
        }

        draw();

        assertTrue("PNG output not found", target.exists());

        /*
         * TODO compare output to some canonical example! How? Compare MD5
         * sums? Load the two images and compare pixels? And where are we
         * storing the other file in the tree?
         */
    }

    public static void main(String[] args) {
        Gtk.init(args);

        draw();
    }
}
