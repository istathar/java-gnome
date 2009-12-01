/*
 * Handle.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 * 
 * This is essentially the same treatment as is found in org.gnome.gdk.Pixbuf
 */
package org.gnome.rsvg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.glib.GlibException;

/**
 * Handle to an SVG image in memory.
 * 
 * @author Andrew Cowie
 * @since 4.0.15
 */
public class Handle extends org.gnome.glib.Object
{
    public Handle() {
        super(RsvgHandle.createHandle());
    }

    /**
     * Construct a handle to an SVG image sourced from the given filename.
     * 
     * @since 4.0.15
     */
    public Handle(String filename) throws FileNotFoundException {
        super(checkHandleFromFile(filename));
    }

    /*
     * First check the file exists first, allowing us to isolate the GError
     * representing image format problems. The RsvgError is not very
     * impressive.
     */
    private static long checkHandleFromFile(String filename) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(target + " not found");
        }
        try {
            return RsvgHandle.createHandleFromFile(filename);
        } catch (GlibException ge) {
            throw new RuntimeException(ge.getMessage());
        }
    }

    /**
     * Write data <i>into</i> this Handle.
     * 
     * @since <span style="color: red;">Unstable</span>
     * @throws IOException
     *             if there is a problem loading the data
     */
    /*
     * Except that it's not actually an IO problem; it'd be a data formatting
     * error. Still, it's a checked exception for sure.
     */
    public void write(byte[] data) throws IOException {
        try {
            // FIXME second argument needs work.
            RsvgHandle.write(this, null, data.length);
        } catch (Exception ge) {
            // FIXME actually GlibException.
            throw new IOException(ge.getMessage());
        }
    }

    /**
     * Indicate you have finished loading data into the Handle.
     * 
     * <p>
     * This method is the compliment of the {@link #write(byte[]) write()}
     * method, and should be called when you have passed in all the data for
     * the image you are loading.
     * 
     * @since <span style="color: red;">Unstable</span>
     */
    public void close() throws IOException {
        try {
            RsvgHandle.close(this);
        } catch (GlibException ge) {
            throw new RuntimeException(ge.getMessage());
        }
    }

    /**
     * Get the size of the SVG image represented by this Handle.
     * 
     * @since 4.0.15
     */
    public DimensionData getDimensions() {
        final DimensionData size;

        size = new DimensionData();

        RsvgHandle.getDimensions(this, size);

        return size;
    }

    /**
     * Get the size of a named sub-element of the SVG image represented by
     * this Handle.
     * 
     * <p>
     * You can pass <code>null<code> to get the size of the entire graphic.
     * 
     * @since 4.0.15
     */
    public DimensionData getDimensionsSub(String id) {
        final DimensionData size;

        size = new DimensionData();

        RsvgHandle.getDimensionsSub(this, size, id);

        return size;
    }
}
