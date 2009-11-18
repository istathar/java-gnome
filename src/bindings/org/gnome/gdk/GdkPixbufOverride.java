/*
 * GdkPixbufOverride.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

import org.gnome.glib.GlibException;

final class GdkPixbufOverride extends Plumbing
{
    private GdkPixbufOverride() {}

    static final byte[] getPixels(Pixbuf self) {
        synchronized (lock) {
            return gdk_pixbuf_get_pixels(pointerOf(self));
        }
    }

    private static native final byte[] gdk_pixbuf_get_pixels(long self);

    static final long createPixbufFromArray(byte[] data, int width, int height,
            boolean preserveAspectRatio, boolean scale) throws GlibException {
        if (data == null) {
            throw new IllegalArgumentException("byte array can't be null");
        }

        synchronized (lock) {
            return gdk_pixbuf_new_from_stream(data, width, height, preserveAspectRatio, scale);
        }
    }

    private static native final long gdk_pixbuf_new_from_stream(byte[] data, int width, int height,
            boolean preserveAspectRatio, boolean scale) throws GlibException;

    static final int getFileInfoX(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("filename can't be null");
        }

        synchronized (lock) {
            return gdk_pixbuf_get_file_info_X(filename);
        }
    }

    private static native final int gdk_pixbuf_get_file_info_X(String filename);
}
