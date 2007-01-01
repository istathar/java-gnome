/*
 * Fundamental.java
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

/**
 * Helper class to map back and forth from Java primitives to GLib fundamental
 * types. As it happens, there are Fundamentals subclasses for each of Java
 * fundamental types. This is used internally when properties need to be set
 * on {@link Object}s; these various subclasses are hidden from public view
 * as there is no need to work in other than Java primitives.
 * <p>
 * <i>These are wrappers around allocated <code>GValue</code>s with
 * fundamental types, which we need in the case we want to use them as
 * parameters to property setting functions. As we create them (and allocate
 * them with <code>GSlice</code> JNI side, we own them are are responsible
 * for <code>free()</code>ing them.</i>
 * <p>
 * <i>This is not used for regular parameter passing. Outside of the property
 * setting mechanism, arguments are passed by Java primitive or by pointer.
 * Indeed, this is such a generalized mechanism that we should rarely need it.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
/*
 * Any reason for this to be public? Note that there is no GFundamental, and
 * nor are we fronting a GSlice.
 */
abstract class Fundamental extends Value
{
    protected Fundamental(long pointer) {
        super(pointer);
    }

    /**
     * By design, we are the owner of all Fundamentals. Call
     * <code>g_slice_free()</code> on our pointer, then carry on to
     * {@link org.freedesktop.bindings.Proxy#finalize() Proxy's finalize()}.
     */
    /*
     * This is a placeholder to remind us of the cleanup actions that will be
     * necessary, irrespective of the finalizer technique used.
     */
    protected void finalize() {
        System.out.println("Fundamental.finalize() called");
        release();
        super.finalize();
    }

    protected void release() {
        GValue.free(this);
    }
}

/**
 * @author Andrew Cowie
 * @since 4.0.0
 */
final class StringValue extends Fundamental
{
    protected StringValue(long pointer) {
        super(pointer);
    }

    StringValue(String str) {
        super(GValue.createValue(str));
    }
}

/**
 * @author Andrew Cowie
 * @since 4.0.0
 */
final class IntegerValue extends Fundamental
{
    protected IntegerValue(long pointer) {
        super(pointer);
    }

    IntegerValue(int i) {
        super(GValue.createValue(i));
    }
}

/**
 * @author Andrew Cowie
 * @since 4.0.0
 */
final class BooleanValue extends Fundamental
{
    protected BooleanValue(long pointer) {
        super(pointer);
    }

    BooleanValue(boolean b) {
        super(GValue.createValue(b));
    }
}

/**
 * <i>This is just a representation of the fact that Enums too can be wrapped
 * in <code>GValues</code>. We only need this when passing or retrieving a
 * property that happens to be an enum on the C side. For everything else we
 * use the top level {@link org.freedesktop.bindings.Constant Constant}
 * representation.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.1
 */
final class EnumValue extends Fundamental
{
    /**
     * Used by GValue.getEnum() to extract the Constant object that
     * corresponds to the enum contained in this EnumValue.
     */
    final Class type;

    protected EnumValue(long pointer, Class type) {
        super(pointer);

        assert (type != null);
        assert (Constant.class.isAssignableFrom(type));

        this.type = type;
    }
}
