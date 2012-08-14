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

/**
 * Constants representing the image file formats that GDK is capable of
 * writing to. This is used by Pixbuf's
 * {@link Pixbuf#save(String, PixbufFormat) save()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
/*
 * This is a total hack at the moment. There is a GdkPixbufFormat, but we'll
 * need to clean things up so that these constants are actually properly
 * instantiated from the underlying structs. The only thing that will stay the
 * same is that the constants here will be for the writable formats only.
 */
public class PixbufFormat // FIXME is actually a Boxed
{
    // TODO drop this and fetch authentic data
    private String name;

    private PixbufFormat(String type) {
        this.name = type;
    }

    // TODO ok to make public
    String getName() {
        return name;
    }

    /*
     * This will all have to be redone when we get the real GdkPixbufFormat
     * hooked up.
     */

    /**
     * The Portable Network Graphic image format. The filename extension for
     * PNGs is <code>.png</code> and should be used when saving images of this
     * type.
     */
    public static PixbufFormat PNG = new PixbufFormat("png");

    /**
     * The Joint Photographic Group image format. Often used for digital
     * photographs, JPEG uses frequency compression to store enough of the
     * image as to be able to capture its essence, but does so using a
     * transform that is lossy. The common filename extension is
     * <code>.jpg</code>.
     */
    public static PixbufFormat JPEG = new PixbufFormat("jpeg");

    /**
     * The Tagged Image File Format, used as a container format for storing
     * images including photographs and line art. It is a lossless format, but
     * is incredibly space-inefficient. Images saved in this format should be
     * given the filename extension <code>.tiff</code>.
     * 
     * @see <a href="http://en.wikipedia.org/wiki/TIFF">TIFF entry at
     *      Wikipedia</a>
     */
    public static PixbufFormat TIFF = new PixbufFormat("tiff");

    public static PixbufFormat ICO = new PixbufFormat("ico");

    public static PixbufFormat BMP = new PixbufFormat("bmp");
}
