/*
 * Pixbuf.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

import java.io.File;
import java.io.FileNotFoundException;

import org.gnome.glib.GlibException;

/**
 * An image in memory.
 * 
 * <p>
 * <i>Image data in a Pixbuf is stored in memory in uncompressed, packed
 * format. Rows in the image are stored top to bottom, and in each row pixels
 * are stored from left to right. There may be padding at the end of a row.
 * The "rowstride" value of a pixbuf, as returned by
 * {@link #getRowstride() getRowstride()}, indicates the number of bytes
 * between rows.</i>
 * 
 * @author Andrew Cowie
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
            throw new FileNotFoundException(target + "not found");
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
     * <li>width of <code>-1</code> will cause the image to be scaled to
     * the exact given height
     * <li>height of <code>-1</code> will cause the image to be scaled to
     * the exact given width.
     * </ul>
     * When not preserving aspect ratio,
     * <ul>
     * <li>a width or height of <code>-1</code> means to not scale the
     * image at all in that dimension.
     * </ul>
     * 
     * @since 4.0.5
     */
    public Pixbuf(String filename, int width, int height, boolean preserveAspectRatio)
            throws FileNotFoundException {
        super(checkPixbufFromFileAtScale(filename, width, height, preserveAspectRatio));
    }

    private static long checkPixbufFromFileAtScale(String filename, int width, int height,
            boolean preserveAspectRatio) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(target + "not found");
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
}
