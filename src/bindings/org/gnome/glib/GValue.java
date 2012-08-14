/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.glib;

import org.freedesktop.bindings.Constant;
import org.freedesktop.bindings.Flag;
import org.gnome.gdk.Pixbuf;
import org.gnome.pango.FontDescription;

/*
 * Crafted: the creation of fundamentals is quite custom.
 */
final class GValue extends Plumbing
{
    // no instantiation
    private GValue() {}

    static final long createValue() {
        return g_value_new();
    }

    private static native final long g_value_new();

    static final long createValue(int i) {
        return g_value_init(i);
    }

    static final long createValue(boolean b) {
        return g_value_init(b);
    }

    static final long createValue(String str) {
        return g_value_init(str);
    }

    static final long createValue(Object obj) {
        return g_value_init_object(pointerOf(obj));
    }

    static final long createValue(Pixbuf pixbuf) {
        return g_value_init_pixbuf(pointerOf(pixbuf));
    }

    static final long createValue(FontDescription desc) {
        return g_value_init_font_description(pointerOf(desc));
    }

    static final long createValue(float f) {
        return g_value_init(f);
    }

    static final long createValue(double d) {
        return g_value_init(d);
    }

    static final long createValue(long j) {
        return g_value_init(j);
    }

    public static long createValue(Constant value) {
        final String typeName;

        typeName = typeOf(value.getClass());

        return g_value_init_enum(typeName, numOf(value));
    }

    /*
     * These ones does not match the exact prototype of g_value_init(); we do
     * the type system magic on the other side (where its all mostly macros in
     * any case) and carry out allocation using GSlice. A rare occasion when
     * we overload the native call.
     */

    private static native final long g_value_init(int i);

    private static native final long g_value_init(boolean b);

    private static native final long g_value_init(float f);

    private static native final long g_value_init(double d);

    private static native final long g_value_init(String str);

    /*
     * ... and boom, our naming conventon goes splat when we added
     * DataColumnLong.
     */
    private static native final long g_value_init(long j);

    /*
     * Meaning the original g_value_init(long) got in the way. Grr. What a
     * mess. Oh well. Rename it as follows to create a distinct function name,
     * and corresponding to the Value.<init>(long, boolean) bullshit
     * constructor. TODO If someone wants to clean up these overloads, go
     * ahead, but beware of the function prototypes on the JNI side.
     */
    private static native final long g_value_init_object(long obj);

    private static native final long g_value_init_pixbuf(long pixbuf);

    private static native final long g_value_init_font_description(long desc);

    private static native final long g_value_init_enum(String type, int num);

    static final int getInteger(Value value) {
        return g_value_get_int(pointerOf(value));
    }

    private static native final int g_value_get_int(long value);

    static final long getLong(Value value) {
        return g_value_get_long(pointerOf(value));
    }

    private static native final long g_value_get_long(long value);

    static final boolean getBoolean(Value value) {
        return g_value_get_boolean(pointerOf(value));
    }

    private static native final boolean g_value_get_boolean(long value);

    static final float getFloat(Value value) {
        return g_value_get_float(pointerOf(value));
    }

    private static native final float g_value_get_float(long value);

    static final double getDouble(Value value) {
        return g_value_get_double(pointerOf(value));
    }

    private static native final double g_value_get_double(long value);

    static final String getString(Value value) {
        return g_value_get_string(pointerOf(value));
    }

    private static native final String g_value_get_string(long value);

    static final Constant getEnum(Value value) {
        final long pointer;
        final int ordinal;
        final String name;
        final Class<?> k;

        pointer = pointerOf(value);

        ordinal = g_value_get_enum(pointer);

        name = typeName(pointer);
        k = lookupType(name);
        return enumFor(k, ordinal);
    }

    private static native final int g_value_get_enum(long value);

    static final Flag getFlags(Value value) {
        final long pointer;
        final int ordinal;
        final String name;
        final Class<?> k;

        pointer = pointerOf(value);

        ordinal = g_value_get_flags(pointer);

        name = typeName(pointer);
        k = lookupType(name);
        return flagFor(k, ordinal);
    }

    private static native final int g_value_get_flags(long value);

    static final Object getObject(Value value) {
        return objectFor(g_value_get_object(pointerOf(value)));
    }

    private static native final long g_value_get_object(long value);

    static final Pixbuf getPixbuf(Value value) {
        return (Pixbuf) objectFor(g_value_get_pixbuf(pointerOf(value)));
    }

    private static native final long g_value_get_pixbuf(long value);

    /**
     * Lookup the type name for a given Value. <i>When a GType such as a
     * primitive (fundamental) type or a class is registered in GObject, it is
     * given a name.
     * 
     * <p>
     * <i>We do not use or even provide a mechanism to retrieve the GType
     * itself. This value would be opaque and in any case changes from run to
     * run.</i>
     * 
     * @param value
     *            the pointer address of the <code>GValue</code> you are
     *            looking at
     * 
     * @return the name which is used to identify the <code>GType</code> in
     *         the underlying libraries.
     */
    /*
     * We don't really need this, but we'll leave it here for bindings hackers
     * to use if debugging.
     */
    static final String typeName(Value value) {
        return g_type_name(pointerOf(value)).intern();
    }

    private static final String typeName(long value) {
        return g_type_name(value).intern();
    }

    private static native final String g_type_name(long value);

    static final void free(Value reference) {
        g_value_free(pointerOf(reference));
    }

    /*
     * This was originally called g_slice_free, but since it's specifically to
     * release the GValue copies we make for Standard Types, give it a more
     * appropriate name instead.
     */
    private static native final void g_value_free(long value);
}
