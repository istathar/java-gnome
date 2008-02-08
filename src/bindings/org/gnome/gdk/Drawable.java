/*
 * Drawable.java
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

import org.gnome.glib.Object;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * Drawable is notable for being the parent class of both [<code>org.gnome.gdk</code>]
 * Window (representing the native server-side on-screen resources behind a
 * Widget) and [<code>org.gnome.gdk</code>] Pixmap (a general server-side
 * but off-screen area you can draw to).
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public abstract class Drawable extends Object
{
    protected Drawable(long pointer) {
        super(pointer);
    }

    /**
     * Get the bits per pixel of the data being used to back this Drawable.
     * 
     * @since 4.0.7
     */
    public int getDepth() {
        return GdkDrawable.getDepth(this);
    }
}
