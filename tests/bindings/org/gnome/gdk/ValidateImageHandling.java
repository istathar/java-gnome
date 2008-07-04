/*
 * ValidateImageHandling.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gdk;

import java.io.FileNotFoundException;

import org.gnome.gtk.TestCaseGtk;

/**
 * @author Andrew Cowie
 */
public class ValidateImageHandling extends TestCaseGtk
{
    public final void testPixbufFromFile() {
        try {
            new Pixbuf("foo.jpg");
            fail("Should have complained not being able to find file");
        } catch (FileNotFoundException fnfe) {
            // good
        }

        try {
            new Pixbuf("README");
            fail("Specified file not an image, should have thrown");
        } catch (RuntimeException re) {
            // good
        } catch (FileNotFoundException fnfe) {
            fail("Should have found non-image file");
        }

        try {
            new Pixbuf("src/bindings/java-gnome_Icon.png");
        } catch (FileNotFoundException fnfe) {
            fail("Target file should exist. Did someone refactor the logo image?");
        }
    }

    public final void testPixbufGetPixels() {
        final Pixbuf pixbuf;
        final byte[] data;
        final int width, height, num;

        try {
            pixbuf = new Pixbuf("src/bindings/java-gnome_Icon.png");
        } catch (FileNotFoundException fnfe) {
            fail("Target file should exist. Did someone refactor the logo image?");
            return;
        }

        width = pixbuf.getWidth();
        height = pixbuf.getHeight();
        num = pixbuf.getNumChannels();
        assertEquals("Expecting a 48x48 image", 48, width);
        assertEquals("Expecting a 48x48 image", 48, height);
        assertEquals("Where did the alpha channel go?", 4, num);

        data = pixbuf.getPixels();

        assertEquals(width * height * num, data.length);

        int sum;

        sum = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                for (int c = 0; c < num; c++) {
                    int pixel;

                    pixel = data[j * width + i * num + c] & 0xFF;

                    assertTrue((pixel >= 0) && (pixel <= 255));

                    sum += pixel;
                }
            }
        }

        /*
         * Ok, this isn't much of a test, but it sorta catches the flavour of
         * things, and is the sort of calculation we were doing during our
         * performance tests.
         */
        assertEquals(515687, sum);
    }
}
