/*
 * GValue.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

import org.freedesktop.bindings.Constant;

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

    static final String getString(StringValue value) {
        return g_value_get_string(pointerOf(value));
    }

    private static native final String g_value_get_string(long value);

    static final Constant getEnum(EnumValue value) {
        int ordinal = g_value_get_enum(pointerOf(value));
        return constantFor(value.type, ordinal);
    }

    private static native final int g_value_get_enum(long value);

    /**
     * Lookup the name for a given type. <i>When a GType such as a primitive
     * (fundamental) type or a class is registered in GObject, it is given a
     * name.
     * 
     * <p>
     * <i>We do not use or even provide a mechanism to retrieve the GType
     * itself. This value would be opaque and in any case changes from run to
     * run.</i>
     * 
     * @param the
     *            pointer address of
     * 
     * @return an the name which is used to identify the <code>GType</code>
     *         in the underlying libraries.
     */
    /*
     * We don't really need this, but we'll leave it here for bindings hackers
     * to use if debugging.
     */
    static final String type(Value value) {
        return g_type_name(pointerOf(value));
    }

    /*
     * Atypically, this native method is package visible so that the crucial
     * instanceFor() in org.gnome.glib.Plumbing can see it. That method needs
     * to call this _before_ it can (and in order to) construct a Value. Yes,
     * this could well be in a GType class. Whatever.
     */
    static native final String g_type_name(long value);

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
