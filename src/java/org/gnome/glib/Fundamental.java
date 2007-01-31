/*
 * Fundamental.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

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
 * @deprecated
 */
/*
 * Any reason for this to be public? Note that there is no GFundamental, and
 * nor are we fronting a GSlice.
 */
abstract class Fundamental
{
}
