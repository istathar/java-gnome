/*
 * CairoContextOverride.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.gdk.Drawable;

/**
 * Hack to allow us to get at gdk_cairo_create() as a constructor of Contexts
 * 
 * @author Andrew Cowie
 */
final class CairoContextOverride extends Plumbing
{
    private CairoContextOverride() {}

    static final long createContextFromDrawable(Drawable drawable) {
        if (drawable == null) {
            /*
             * This check is, unfortunately, particularly important. If you've
             * gotten this far with a null Drawable, that means that the state
             * you think you're in isn't what it should be - in otherwords,
             * you don't _really_ have a Drawable yet.
             */
            throw new IllegalArgumentException("drawable can't be null");
        }

        synchronized (lock) {
            return gdk_cairo_create(pointerOf(drawable));
        }
    }

    private static native final long gdk_cairo_create(long drawable);
}
