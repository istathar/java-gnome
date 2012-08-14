/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
package org.freedesktop.cairo;

/**
 * A Surface which is an image in memory and can be written to disk. If
 * drawing to an image you intend to write to a PNG, you would end up doing
 * something like:
 * 
 * <pre>
 * surface = new ImageSurface(Format.ARGB32, 100, 100);
 * cr = new Context(surface);
 * 
 * // do drawing
 * 
 * surface.writeToPNG(filename);
 * </pre>
 * 
 * <p>
 * While ImageSurfaces are good for writing images out to disk, they are not
 * optimized per se to be efficient as a back end, nor are they accelerated by
 * your graphics card. So they are not an appropriate intermediate in drawing
 * operations; don't be calling <code>setSource()</code> on one of these.
 * 
 * <p>
 * More importantly, ImageSurface is <b>not</b> an image loader! Remember that
 * Surfaces are what Cairo draws <i>to</i>. If what you are doing is building
 * up images for display to the screen, then load your images into
 * {@link XlibSurface}s and use those as sources.
 * 
 * <p>
 * <i>Deep in the guts, Cairo's ImageSurface is like GDK's Pixbuf, a format
 * that C programmers can directly address directly in memory via pointers.
 * That's useful for very low level programming, but not needed for
 * application development. If you're drawing, use Cairo's higher level
 * drawing primitives; if you need to introspect an image, then load it with
 * Pixbuf and use Pixbuf's</i> {@link org.gnome.gdk.Pixbuf#getPixels()
 * getPixels()} <i>to peek around in its data.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public class ImageSurface extends Surface
{
    protected ImageSurface(long pointer) {
        super(pointer);
    }

    /**
     * Construct an ImageSurface of the specified visual depth and size.
     * 
     * @since 4.0.7
     */
    public ImageSurface(Format format, int width, int height) {
        super(CairoSurface.createSurface(format, width, height));
        checkStatus();
    }
}
