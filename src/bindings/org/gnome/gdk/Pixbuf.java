/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.glib.GlibException;

/**
 * An image in memory.
 * 
 * <p>
 * <i> Pixbuf is just here to be efficient at handling images that are going
 * to placed in your GTK user interfaces. If you want to draw on an image, use
 * Cairo's ImageSurface. If you want to otherwise manipulate the image, use a
 * dedicated image processing library to load the data as it will doubtless
 * provide for more efficient storage anticipating the processing tasks it
 * will facilitate.</i>
 * 
 * @author Andrew Cowie
 * @author Serkan Kaba
 * @since 4.0.0
 */
public class Pixbuf extends org.gnome.glib.Object
{
    protected Pixbuf(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Pixbuf object from the image found in
     * <code>filename</code>.
     * 
     * @since 4.0.5
     */
    /*
     * Trapping an exception in a constructor is tricky; you have to use an
     * auxiliary helper method.
     */
    public Pixbuf(String filename) throws FileNotFoundException {
        super(checkPixbufFromFile(filename));
    }

    /*
     * First check the file exists first, allowing us to isolate the GError
     * representing image format problems. We'll need a more efficient GError
     */
    private static long checkPixbufFromFile(String filename) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(target + " not found");
        }
        try {
            return GdkPixbuf.createPixbufFromFile(filename);
        } catch (GlibException ge) {
            // FIXME change to something more image related.
            throw new RuntimeException(ge.getMessage());
        }
    }

    /**
     * Construct a new Pixbuf object by loading an image from a given
     * filename, but scaling it. The image will be scaled to fit the supplied
     * width and height, preserving the aspect ratio or not based on the value
     * of the fourth parameter.
     * 
     * <p>
     * If preserving the aspect ratio,
     * <ul>
     * <li>width of <code>-1</code> will cause the image to be scaled to the
     * exact given height
     * <li>height of <code>-1</code> will cause the image to be scaled to the
     * exact given width.
     * </ul>
     * When not preserving aspect ratio,
     * <ul>
     * <li>a width or height of <code>-1</code> means to not scale the image
     * at all in that dimension.
     * </ul>
     * 
     * @since 4.0.5
     */
    public Pixbuf(String filename, int width, int height, boolean preserveAspectRatio)
            throws FileNotFoundException {
        super(checkPixbufFromFileAtScale(filename, width, height, preserveAspectRatio));
    }

    public Pixbuf(int width, int height) {
        super(GdkPixbuf.createPixbuf(null, true, 8, width, height));
        GdkPixbuf.fill(this, 0);
    }

    /**
     * Construct a new Pixbuf object from image data already in memory. The
     * data needs to be a complete image in a format that will be recognized
     * by one of the gdk-pixbuf library's loaders (PNG, JPEG, etc).
     * 
     * @since 4.0.10
     */
    public Pixbuf(byte[] data) throws IOException {
        super(checkPixbufFromArray(data));
    }

    private static long checkPixbufFromArray(byte[] data) throws IOException {
        try {
            // Parameters 2-4 have no meaning when we're not scaling
            return GdkPixbufOverride.createPixbufFromArray(data, 0, 0, true, false);
        } catch (GlibException ge) {
            /*
             * FIXME this will need to be more specific when our GError
             * handling is better! IOException is the usual in stream-ish
             * cases, but is it really appropriate here?
             */
            throw new IOException(ge.getMessage());
        }
    }

    /**
     * Construct a new Pixbuf from in-memory data and scale it.
     * <p>
     * See {@link #Pixbuf(byte[])} for info on in-memory data.<br>
     * See {@link #Pixbuf(String, int, int, boolean)} for info on scaling.
     * 
     * @since 4.0.12
     */
    public Pixbuf(byte[] data, int width, int height, boolean preserveAspectRatio) throws IOException {
        super(checkPixbufFromArrayAtScale(data, width, height, preserveAspectRatio));
    }

    private static long checkPixbufFromArrayAtScale(byte[] data, int width, int height,
            boolean preserveAspectRatio) throws IOException {
        try {
            return GdkPixbufOverride.createPixbufFromArray(data, width, height, preserveAspectRatio,
                    true);
        } catch (GlibException ge) {
            /*
             * FIXME this will need to be more specific when our GError
             * handling is better! IOException is the usual in stream-ish
             * cases, but is it really appropriate here?
             */
            throw new IOException(ge.getMessage());
        }
    }

    /**
     * Create an identical copy of this Pixbuf.
     * 
     * <p>
     * Since you can happily reuse a Pixbuf instance in multiple places, you
     * generally won't need this unless doing mutating operations like
     * rescaling.
     * 
     * @since 4.0.10
     */
    public Pixbuf copy() {
        return GdkPixbuf.copy(this);
    }

    private static long checkPixbufFromFileAtScale(String filename, int width, int height,
            boolean preserveAspectRatio) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(target + " not found");
        }
        try {
            return GdkPixbuf.createPixbufFromFileAtScale(filename, width, height, preserveAspectRatio);
        } catch (GlibException ge) {
            // FIXME change to something more image related.
            throw new RuntimeException(ge.getMessage());
        }
    }

    /**
     * Write this Pixbuf to a file.
     * 
     * <p>
     * The various file formats that GDK is actually capable of writing to are
     * specified by the constants on PixbufFormat. For example, you can save a
     * screenshot as a PNG using:
     * 
     * <pre>
     * Pixbuf p;
     * ...
     * 
     * p.save(&quot;Screenshot.png&quot;, PixbufFormat.PNG);
     * </pre>
     * 
     * @since 4.0.5
     */
    /*
     * TODO there are a wide range of GError states that emanate from this
     * call; we need some appropriate code to interpret the more "common" ones
     * and turn them into more strongly typed Exceptions.
     */
    public void save(String filename, PixbufFormat type) {
        try {
            GdkPixbuf.savev(this, filename, type.getName(), null, null);
        } catch (GlibException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    /**
     * Get the width of this Pixbuf, in pixels
     * 
     * @since 4.0.8
     */
    public int getWidth() {
        return GdkPixbuf.getWidth(this);
    }

    /**
     * Get the height of this Pixbuf, in pixels
     * 
     * @since 4.0.8
     */
    public int getHeight() {
        return GdkPixbuf.getHeight(this);
    }

    /**
     * Get a the individual pixel values comprising this Pixbuf.
     * 
     * <p>
     * Image data in a Pixbuf is stored in memory in an uncompressed but
     * packed format. Rows in the image are stored top to bottom, and in each
     * row pixels are stored from left to right. The returned array is, as you
     * would expect, the bytes comprising each single pixel in a sequential
     * series. There will be either three (RGB) or four (RGBA) bytes per
     * pixel, see {@link #getNumChannels() getNumChannels()}.
     * 
     * <p>
     * The return array is of type of is <code>byte</code> but you can expect
     * unsigned values in the range <code>0</code> to <code>255</code>. If you
     * need the actual values you'll have to <code>&amp;</code> with
     * <code>0xFF</code> to get yourself to the correct unsigned integer.
     * 
     * <p>
     * You should not need to call this. See the caveats at the top of this
     * class; changing the values of this array will <b>not</b> change the
     * underlying image.
     * 
     * <p>
     * <i>The underlying library stores Pixbufs in a format which is efficient
     * for internal representation and memory operations, though not
     * necessarily efficient for size and frequently extra bits are added as
     * alignment padding. We do some minor copying so that the array returned
     * is one byte per channel and Cartesian. </i>
     * 
     * <p>
     * <i>In the current GDK Pixbuf library implementation, the red, green,
     * blue and optional alpha are fixed at 8 bits per channel.</i>
     * 
     * @since 4.0.8
     */
    public byte[] getPixels() {
        return GdkPixbufOverride.getPixels(this);
    }

    /**
     * Get the number of colour channels in this Pixbuf. This will be either
     * <code>3</code> for RGB or <code>4</code> for an RGBA image.
     * 
     * <p>
     * You don't actually need this; if you're working with the pixel data
     * from {@link #getPixels() getPixels()} just divide the length of the
     * returned array by (<var>width</var> <code>*</code> <var>height</var>).
     * 
     * @since 4.0.8
     */
    public int getNumChannels() {
        return GdkPixbuf.getNChannels(this);
    }

    /**
     * Create a new Pixbuf, applying the scaling factors inherent in the
     * ratios between the size of this Pixbuf and the new image size specified
     * by <code>width</code> and <code>height</code>.
     * 
     * <p>
     * In general you probably want to use the {@link InterpType#BILINEAR
     * BILINEAR} interpolation algorithm.
     * 
     * @since 4.0.10
     */
    public Pixbuf scale(int width, int height, InterpType algorithm) {
        return GdkPixbuf.scaleSimple(this, width, height, algorithm);
    }

    /*
     * TODO we'll want to expose the full gdk_pixbuf_scale() method too. That
     * will require a (probably package private) no-arg Pixbuf constructor to
     * pass as the second argument out-parameter of GdkPixbuf.scale(), which
     * we can then return. The signature and stub here should otherwise be
     * correct. Good luck explaining all this stuff.
     */
    Pixbuf scale(int x, int y, int width, int height, double offsetX, double offsetY, double scaleX,
            double scaleY, InterpType algorithm) {
        final Pixbuf result;

        result = null; // FIXME

        GdkPixbuf.scale(this, result, x, y, width, height, offsetX, offsetY, scaleX, scaleY, algorithm);

        return result;
    }

    /**
     * @param filename
     */
    /*
     * TODO to expose the gdk_pixbuf_get_file_info() function properly, we'll
     * have to fix the engineering of PixbufFormat which is presently a dogs
     * breakfast. Luckily, given the two out parameter accessors below, we
     * don't seem to need this.
     */
    PixbufFormat getFileInfo(String filename) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Query an image on disk for its width.
     * 
     * <p>
     * This is a utility function where the minimum amount is read in order to
     * determine metadata about the file in question. You'll get an
     * IOException if the gdk-pixbuf library's image loaders can't figure out
     * the format of the file.
     * 
     * @since 4.0.14
     */
    public static int getFileInfoWidth(String filename) throws IOException {
        int result;

        result = GdkPixbufOverride.getFileInfoX(filename);

        if (result == -1) {
            throw new IOException("Image format not recognized");
        }

        return result;
    }

    /**
     * Query an image on disk for its height.
     * 
     * <p>
     * This function is the compliment of {@link #getFileInfoWidth(String)
     * getFileInfoWidth()}; see there.
     * 
     * @since 4.0.14
     */
    public static int getFileInfoHeight(String filename) throws IOException {
        int result;

        result = GdkPixbufOverride.getFileInfoY(filename);

        if (result == -1) {
            throw new IOException("Image format not recognized");
        }

        return result;
    }
}
