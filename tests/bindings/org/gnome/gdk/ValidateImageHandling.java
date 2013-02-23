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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.gtk.Button;
import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Stock;

/**
 * @author Andrew Cowie
 */
public class ValidateImageHandling extends GraphicalTestCase
{
    public final void testPixbufFromFile() {
        try {
            new Pixbuf("foo.jpg");
            fail("Should have complained not being able to find file");
        } catch (FileNotFoundException fnfe) {
            // good
        }

        try {
            new Pixbuf("README.markdown");
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

    public final void testPixbufCopying() {
        final Pixbuf original;
        final Pixbuf copy;
        byte[] data;
        int O, C;

        original = Gtk.renderIcon(new Button(), Stock.OK, IconSize.BUTTON);
        data = original.getPixels();
        O = 0;
        for (int i = 0; i < data.length; i++) {
            O += data[i];
        }

        copy = original.copy();

        assertNotSame(copy, original);

        data = copy.getPixels();
        C = 0;
        for (int i = 0; i < data.length; i++) {
            C += data[i];
        }

        assertEquals(C, O);
    }

    /*
     * Obviously we can't assess quality of transformed image, but we can at
     * least exercise the code path.
     */
    public final void testPixbufScaling() {
        final Pixbuf original;
        final Pixbuf result;

        original = Gtk.renderIcon(new Button(), Stock.HELP, IconSize.MENU);

        assertTrue(original.getWidth() < 100);
        assertTrue(original.getHeight() < 100);

        result = original.scale(300, 500, InterpType.NEAREST);

        assertEquals(300, result.getWidth());
        assertEquals(500, result.getHeight());
    }

    public final void testPixbufFromData() throws IOException {
        Pixbuf target;
        byte[] data;
        final FileInputStream png;

        target = null;
        data = null;

        try {
            target = new Pixbuf(data);
            fail("Shouldn't be able to pass a null byte[]");
        } catch (IllegalArgumentException iae) {
            // good
        }

        data = new byte[0];
        try {
            target = new Pixbuf(data);
            fail("Something should break if you pass in an empty byte[]!");
        } catch (IOException iae) {
            // good
        }

        try {
            target = new Pixbuf(data);
            fail("The byte[] isn't an image.");
        } catch (IOException iae) {
            // good
        }

        data = new byte[4017];
        png = new FileInputStream("src/bindings/java-gnome_Icon.png");
        png.read(data);

        target = new Pixbuf(data);

        assertEquals(48, target.getWidth());
        assertEquals(48, target.getHeight());
    }

    public final void testPixbufInfoFromDisk() throws IOException {
        final Pixbuf pixbuf;
        final int loadedWidth, loadedHeight;
        final int infoWidth, infoHeight;

        /*
         * This file specifically picked to be a non-square image.
         */

        pixbuf = new Pixbuf("tests/prototype/MapleSyrup.jpg");

        loadedWidth = pixbuf.getWidth();
        loadedHeight = pixbuf.getHeight();

        assertEquals("Expecting a 200x398 image", 200, loadedWidth);
        assertEquals("Expecting a 200x398 image", 398, loadedHeight);

        /*
         * Now test the static functions used for probing a file on disk. It's
         * hard to imagine that this would come up with different answers than
         * loading an image, but the point is to exercise these code paths,
         * not validate gdk-pixbuf as such.
         */

        infoWidth = Pixbuf.getFileInfoWidth("tests/prototype/MapleSyrup.jpg");
        infoHeight = Pixbuf.getFileInfoHeight("tests/prototype/MapleSyrup.jpg");

        assertEquals("File info width should be 200", 200, infoWidth);
        assertEquals("File info height should be 398", 398, infoHeight);
    }
}
