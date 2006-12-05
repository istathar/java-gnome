/*
 * GValue.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

/*
 * Crafted: the creation of fundamentals is quite custom.
 */
final class GValue extends Plumbing
{

    // no instantiation
    private GValue() {}

    static final long createValue(int i) {
        return g_value_init(i);
    }

    static final long createValue(boolean b) {
        return g_value_init(b);
    }

    static final long createValue(String str) {
        return g_value_init(str);
    }

    /*
     * These ones does not match the exact prototype of g_value_init() [which is
     * (GValue*, GType)]; we do the type system magic on the other side (where
     * its all mostly macros in any case) and carry out allocation using GSlice.
     * A rare occasion when we overload the native call.
     */

    private static native final long g_value_init(int i);

    private static native final long g_value_init(boolean b);

    private static native final long g_value_init(String str);

    static final void free(Fundamental reference) {
        g_slice_free(pointerOf(reference));
    }

    /*
     * This could live in GSlice.java, I suppose, but no real reason. We're
     * below the level of clean abstraction here, and it just needs to go
     * somewhere.
     */
    private static native final long g_slice_free(long value);
}
