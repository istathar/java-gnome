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
package org.freedesktop.cairo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.GraphicalTestCase;

/**
 * Exercise the handling of the internals of our Plumbing infrastructure for
 * turning Cairo sub types into concrete Java objects.
 * 
 * @author Andrew Cowie
 * @author Adrian Johnson
 */
public class ValidateCairoInternals extends GraphicalTestCase
{
    public final void testPatternProxyCreation() {
        final Context cr;
        final ImageSurface s;
        final Pattern p;

        s = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(s);

        /*
         * Do something arbitrary.
         */

        cr.setSource(0.0, 0.0, 1.0, 0.8);

        /*
         * Call a method that returns an abstract type, in this case a
         * Pattern.
         */

        p = cr.getSource();
        assertNotNull(p);
        assertTrue(p instanceof SolidPattern);

        /*
         * Note: The preceeding assertion doesn't have to be a SolidPattern.
         * It just needs to be something concrete, and more to the point
         * something that we didn't already have a Proxy for. Obviously we
         * can't have instantiated a non-concrete class, but this makes the
         * point clearly that we got something out of it that we didn't have
         * on the Java side before.
         */
    }

    private static byte[] readFileIntoArray(String filename) throws IOException {
        final File source;
        final int length;
        final FileInputStream fis;
        final byte[] data, overflow;
        int actual;

        source = new File(filename);
        length = (int) source.length();

        data = new byte[length];

        /*
         * Read the entire file in one go.
         */

        fis = new FileInputStream(source);
        actual = fis.read(data, 0, length);

        /*
         * Make sure we really read the whole file; if there are any bytes
         * left that's bad.
         */

        if (actual != length) {
            fail();
        }

        overflow = new byte[2];
        actual = fis.read(overflow, 0, 2);
        if (actual != -1) {
            fail();
        }

        /*
         * Return file contents
         */

        return data;
    }

    private static final void assertFileContains(String target, String filename) throws IOException {
        final byte[] data;
        final String text;

        data = readFileIntoArray(filename);

        text = new String(data);

        assertTrue("Output file does not contain \"" + target + "\"", text.contains(target));
    }

    public final void testUsingMimeDataExplicit() throws IOException {
        final Surface s1, s2;
        final Context c1, c2;
        final Pixbuf pixbuf;
        final byte[] data;

        /*
         * Desired output target.
         */

        s1 = new SvgSurface("tmp/tests/org/freedesktop/cairo/MimeType1.svg", 100, 100);
        c1 = new Context(s1);

        /*
         * Get our image into a GDK Pixbuf, but instead of directly loading as
         * usual load it into Java memory first so we can reuse it in a
         * minute.
         */

        data = readFileIntoArray("tests/prototype/MapleSyrup.jpg");
        pixbuf = new Pixbuf(data);

        /*
         * Intermediate surface. What a bother.
         */

        s2 = new ImageSurface(Format.ARGB32, 100, 100);
        c2 = new Context(s2);

        c2.setSource(pixbuf, 0, 0);
        c2.paint();

        s2.setMimeData(MimeType.JPEG, data);

        /*
         * Paint the image onto our real surface.
         */

        c1.setSource(s2, 0, 0);
        c1.paint();

        s1.finish();

        /*
         * Now test
         */

        assertFileContains("image/jpeg", "tmp/tests/org/freedesktop/cairo/MimeType1.svg");
    }

    /*
     * Try and excercise the cairo_destroy_func_t callback. No promises, of
     * course, but with this we were getting crashes until we fixed it.
     */
    public final void testImageDataCleanup() {
        cycleMainLoop();
        cycleGarbageCollector();
    }

    /**
     * Thanks for the help of Adrian Johnson. In trying this pattern.
     */
    public final void testUsingMimeDataViaPattern() throws IOException {
        final Surface surface, implicit;
        final Context cr;
        final Pattern pattern;
        final byte[] data;
        final Pixbuf pixbuf;

        /*
         * Desired output target.
         */

        surface = new SvgSurface("tmp/tests/org/freedesktop/cairo/MimeType2.svg", 100, 100);
        cr = new Context(surface);

        data = readFileIntoArray("tests/prototype/MapleSyrup.jpg");
        pixbuf = new Pixbuf(data);

        /*
         * Set the source using the gdk_cairo_set_source_pixbuf() utility, but
         * do NOT draw it yet.
         */

        cr.setSource(pixbuf, 0, 0);

        /*
         * Get the implicitly created Surface, and set the mime data.
         */

        pattern = cr.getSource();
        implicit = pattern.getSurface();
        implicit.setMimeData(MimeType.JPEG, data);

        /*
         * Now you can paint.
         */

        cr.paint();

        surface.finish();

        /*
         * Now test
         */

        assertFileContains("image/jpeg", "tmp/tests/org/freedesktop/cairo/MimeType2.svg");
    }
}
