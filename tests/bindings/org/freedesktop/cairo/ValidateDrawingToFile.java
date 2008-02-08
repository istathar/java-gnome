/*
 * ValidateDrawingToFile.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.gtk.Gtk;
import org.gnome.gtk.TestCaseGtk;

/**
 * Started life as the Cairo drawing example; evolved to a unit test. This
 * TestCase is predicated on the assumption that PNGs are deterministic. I
 * don't know of anything that actually guaruntees or requires this to be the
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
public class ValidateDrawingToFile extends TestCaseGtk
{
    private static void draw() {
        final Context cr;
        final ImageSurface surface;

        surface = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(surface);

        cr.setSourceRGBA(0.0, 0.0, 1.0, 0.8);
        cr.moveTo(10, 10);
        cr.lineTo(20, 5);
        cr.stroke();

        surface.writeToPNG("tmp/tests/ValidateDrawingToFile.png");
    }

    public final void testImageSurfaceWriteToPNG() {
        draw();
    }

    public static void main(String[] args) {
        Gtk.init(args);

        draw();
    }
}
