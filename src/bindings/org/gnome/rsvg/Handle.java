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
package org.gnome.rsvg;

/*
 * This is essentially the same treatment as is found in org.gnome.gdk.Pixbuf
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.glib.GlibException;

/**
 * Handle to an SVG image in memory. This class allows you to load an SVG
 * image from a file on disk or from memory.
 * 
 * <p>
 * Once you have the Handle you can render it to Cairo directly with Context's
 * {@link org.freedesktop.cairo.Context#showHandle(Handle) showHandle()},
 * 
 * <pre>
 * Rsvg.init();
 * ...
 * 
 * graphic = new Handle(&quot;Tiger.svg&quot;);
 * cr.showHandle(graphic);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.18
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
     * @since 4.0.18
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
     * @since 4.0.18
     */
    public DimensionData getDimensionsSub(String id) {
        final DimensionData size;

        size = new DimensionData();

        RsvgHandle.getDimensionsSub(this, size, id);

        return size;
    }

    /**
     * Get the horizontal resolution of this graphic.
     * 
     * <p>
     * Resolution and SVG is a bit of a weird topic. The default unts in an
     * SVG file are "pixels", <code>px</code> as defined in the CSS
     * specification, and the default resolution turns out to be 90 pixels per
     * inch. So unless the program writing the graphic you are loading has
     * gone to extraordinary lengths, the value of this property will be
     * <code>90.0</code> <i>regardless of the user space units in use inside
     * the graphic</i>.
     * 
     * <p>
     * Under ordinary circumstances it will be the same as the vertical
     * resolution so you probably don't have to query both.
     * 
     * @since 4.0.18
     */
    public double getDPIX() {
        return super.getPropertyDouble("dpi-x");
    }

    /**
     * Get the vertical resolution of this graphic. See {@link #getDPIX()
     * getDPIX()} for details.
     * 
     * @since 4.0.18
     */
    public double getDPIY() {
        return super.getPropertyDouble("dpi-y");
    }
}
