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

import org.gnome.gtk.GraphicalTestCase;

/**
 * Exercise the handling of the internals of our Plumbing infrastructure for
 * turning Cairo sub types into concrete Java objects.
 * 
 * @author Andrew Cowie
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

    public final void testUsingMimeData() throws IOException {
        final Surface s1;
        final Context cr;
        final byte[] data;
        final Surface s2;

        s1 = new SvgSurface("tmp/tests/org/freedesktop/cairo/MimeType.svg", 100, 100);
        cr = new Context(s1);

        s2 = s1.createSimilar(Content.COLOR_ALPHA, 100, 100);

        data = readFileIntoArray("tests/prototype/MapleSyrup.jpg");
        s2.setMimeData(MimeType.JPEG, data);

        cr.setSource(s2, 0, 0);
        cr.paint();

        s1.finish();
    }
}
