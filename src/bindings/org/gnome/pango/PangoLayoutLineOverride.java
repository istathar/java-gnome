/*
 * PangoLayoutLineOverride.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

/**
 * @author Andrew Cowie
 */
final class PangoLayoutLineOverride extends Plumbing
{
    private PangoLayoutLineOverride() {}

    static final int getStartIndex(LayoutLine self) {
        int result;

        if (self == null) {
            throw new IllegalArgumentException("self can't be null");
        }

        synchronized (lock) {
            result = pango_layout_line_get_start_index(pointerOf(self));

            return result;
        }
    }

    private static native final int pango_layout_line_get_start_index(long self);

    static final int getLength(LayoutLine self) {
        int result;

        if (self == null) {
            throw new IllegalArgumentException("self can't be null");
        }

        synchronized (lock) {
            result = pango_layout_line_get_length(pointerOf(self));

            return result;
        }
    }

    private static native final int pango_layout_line_get_length(long self);
}
