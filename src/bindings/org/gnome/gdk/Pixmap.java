/*
 * Pixmap.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public class Pixmap extends Drawable
{
    protected Pixmap(long pointer) {
        super(pointer);
    }

    /**
     * Create a Pixmap with qualities matching that of an existing Drawable.
     * In other words, if you already have a GDK Window (ie, you're in an
     * EXPOSE_EVENT), you can create a Pixmap that will be compatible with it
     * by using this constructor.
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
