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
     * These ones does not match the exact prototype of g_value_init() [which
     * is (GValue*, GType)]; we do the type system magic on the other side
     * (where its all mostly macros in any case) and carry out allocation
     * using GSlice. A rare occasion when we overload the native call.
     */

    private static native final long g_value_init(int i);

    private static native final long g_value_init(boolean b);

    private static native final long g_value_init(String str);

    static final String getString(Value value) {
        return g_value_get_string(pointerOf(value));
    }

    private static native final String g_value_get_string(long value);

    /**
     * <i>This is an opaque representation of the GType value code used by
     * GLib. Any interpretation that the Java language might assign to a long
     * (ie, that it's signed) is meaningless and incorrect! This is package
     * public so that Plumbing can see it, and final so that once constructed
     * its immutable. GTypes are gulong.</i>
     * 
     * @return an opaque value which can be passed as a <code>GType</code>
     *         to the underlying libraries.
     */
    /*
     * Atypically, this is package visible so that org.gnome.glib.Plumbing can
     * see it.
     */
    static native final long g_value_type(long value);

    static final void free(Fundamental reference) {
        g_value_free(pointerOf(reference));
    }

    /*
     * This was originally called g_slice_free, but since it's specifically to
     * release the GValue copies we make for Standard Types, give it a more
     * appropriate name instead.
     */
    private static native final void g_value_free(long value);
}
