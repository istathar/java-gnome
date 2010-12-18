/*
 * RsvgDimensionDataOverride.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.rsvg;

/**
 * Yet another struct allocator. I'm getting tired of writing these.
 * 
 * @author Andrew Cowie
 */
final class RsvgDimensionDataOverride extends Plumbing
{
    static final long createDimensionData() {
        synchronized (lock) {
            return rsvg_dimension_data_new();
        }
    }

    private static native final long rsvg_dimension_data_new();

    static final void free(DimensionData self) {
        synchronized (lock) {
            rsvg_dimension_data_free(pointerOf(self));
        }
    }

    private static native final void rsvg_dimension_data_free(long self);

}
