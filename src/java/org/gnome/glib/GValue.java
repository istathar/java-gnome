/*
 * GValue.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
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
