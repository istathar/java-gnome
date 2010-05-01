/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.gtk;

import org.gnome.gdk.Bitmap;
import org.gnome.gdk.Pixbuf;
import org.gnome.gdk.Pixmap;

/**
 * A Widget that displays an image.
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class Image extends Misc
{
    protected Image(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Image Widget from the image located at the specified
     * path. This always results in a new Image; if the file is not found the
     * Image will be populated with the "broken image" icon.
     */
    /*
     * I'm not entirely convinced about this - I am inclined instead to have
     * this throw FileNotFoundException; FUTURE revisit this question when we
     * bind GdkPixbuf.createPixbufFromFile().
     */
    public Image(String filename) {
        super(GtkImage.createImageFromFile(filename));
    }

    /**
     * Create a new, but empty, Image Widget. You can use the setters on this
     * class to emplace image data later.
     * 
     * <p>
     * Although this can be used as a blank slate upon which you can do
     * arbitrary Cairo drawing in an <code>Widget.ExposeEvent</code> handler,
     * you are better off using {@link DrawingArea} for that purpose.
     * 
     * @since 4.0.7
     */
    public Image() {
        super(GtkImage.createImage());
    }

    /**
     * Construct a new Image Widget from an image already loaded into a
     * Pixbuf.
     * 
     * @since 4.0.5
     */
    public Image(Pixbuf pixbuf) {
        super(GtkImage.createImageFromPixbuf(pixbuf));
    }

    /**
     * Construct a new Image based on one of the Stock icons. As each one
     * actually comes in various sizes, you have to say which variant you
     * want.
     * 
     * <p>
     * In most cases, you shouldn't need this; most of the special purpose
     * Widgets have constructors which directly use a Stock item (see
     * {@link ImageMenuItem#ImageMenuItem(Stock) ImageMenuItem} or
     * {@link ToolButton#ToolButton(Stock) ToolButton} for example) and which
     * will take care of the sizing issues for you.
     * 
     * @since 4.0.6
     */
    public Image(Stock stock, IconSize size) {
        super(GtkImage.createImageFromStock(stock.getStockId(), size));
    }

    /**
     * Construct a new Image based on an existing Pixmap. Though passing a
     * mask is supported via the <code>mask</code> parameter, you generally
     * just want to mass <code>null</code>.
     * 
     * @since 4.0.7
     */
    public Image(Pixmap pixmap, Bitmap mask) {
        super(GtkImage.createImageFromPixmap(pixmap, mask));
    }

    /**
     * Specify the Pixbuf to be presented by this Image.
     * 
     * @since 4.0.8
     */
    /*
     * The first of numerous overloads, one each matching the constructors we
     * support. Bit of a toss-up as to what the name of these should be;
     * clearly setFrom() won't do and setFromPixbuf() etc precludes the use of
     * an overload which is far better Java form.
     */
    public void setImage(Pixbuf pixbuf) {
        GtkImage.setFromPixbuf(this, pixbuf);
    }

    /**
     * Specify a stock icon to be displayed by this Image. See the
     * {@link #Image(Stock, IconSize) constructor} taking a Stock instance.
     * 
     * @since 4.0.8
     */
    public void setImage(Stock stock, IconSize size) {
        GtkImage.setFromStock(this, stock.getStockId(), size);
    }

    /**
     * Specify the filename to load and parse as image data to be displayed in
     * this Image. See the filename {@link #Image(String) constructor}.
     * 
     * @since 4.0.8
     */
    public void setImage(String filename) {
        GtkImage.setFromFile(this, filename);
    }

    /**
     * Reset this Image to be empty.
     * 
     * @since 4.0.8
     */
    public void clear() {
        GtkImage.clear(this);
    }
}
