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
 * "Claspath Exception"), the copyright holders of this library give you
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
 * An image as stored in the X server's memory.
 * 
 * <p>
 * This class is easily confused with {@link Pixbuf}, which is what you are
 * probably looking for. Pixbuf is used for representing images on the client
 * side in your application. Loading images and passing them for use in
 * Widgets such as Image and MenuItem is done there. Pixmap, on the other
 * hand, is a wrapper around a resource in the X server.
 * 
 * <p>
 * You should not generally need to use this class. When we do draw Widgets
 * with Cairo, we do so directly to a Window in the X server which it then
 * manages for getting on screen. If you really are looking to work directly
 * with a Pixmap, then see also {@link org.freedesktop.cairo.XlibSurface
 * XlibSurface}, which ultimately is how we get data into Drawables, though
 * there too we don't work with them directly but rather let Cairo and GDK do
 * the heavy lifting.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public class Pixmap extends Drawable
{
    protected Pixmap(long pointer) {
        super(pointer);
    }

    /**
     * Create a Pixmap with qualities matching that of an existing Drawable.
     * In other words, if you already have a GDK Window (ie, you're in a
     * Widget.ExposeEvent), you can create a Pixmap that will be compatible
     * with it by using this constructor.
     * 
     * @since 4.0.7
     */
    public Pixmap(Drawable example, int width, int height) {
        super(GdkPixmap.createPixmap(validateDrawable(example), width, height, -1));
    }

    /**
     * Create a new Pixmap with a given pixel depth.
     * 
     * @since 4.0.7
     */
    public Pixmap(int width, int height, int depth) {
        super(GdkPixmap.createPixmap(null, width, height, validateDepth(depth)));
        // GdkDrawable.setColormap(this,
        // GdkScreen.getDefault().getDefaultColormap());
        // GdkDrawable.setColormap(this,
        // GdkWindow.getDefaultRootWindow().getColormap());
    }

    static final Drawable validateDrawable(final Drawable pixmap) {
        if (pixmap == null) {
            throw new IllegalArgumentException("The reference drawable must be non-null");
        }
        return pixmap;
    }

    static final int validateDepth(final int depth) {
        if (depth < 1) {
            throw new IllegalArgumentException("depth has to be set");
        }
        return depth;
    }
}
