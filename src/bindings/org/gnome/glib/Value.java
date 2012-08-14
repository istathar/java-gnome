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
import org.freedesktop.bindings.Debug;
import org.freedesktop.bindings.Pointer;
import org.gnome.gdk.Pixbuf;
import org.gnome.pango.FontDescription;

/**
 * A generic value that can be passed as a parameter to or returned from a
 * method or function on an underlying entity in the GLib library and those
 * built on it. Value is only used in setting or getting Object properties,
 * and in the TreeModel system.
 * 
 * <p>
 * <b>For use by bindings hackers only.</b><br>
 * As with other classes in <code>org.gnome.glib</code>, this is
 * implementation machinery and should not be needed by anyone developing
 * applications with java-gnome.
 * 
 * <p>
 * Ironically, Values are <i>not</i> actually type safe; if you happen to
 * create one to hold Strings, and then call the getEnum() method on it, your
 * program will explode (this is somewhat to the contrary of the spirit of
 * java-gnome). These are therefore only for use from within strongly typed
 * methods exposing a safe and sane public API. You've been warned.
 * 
 * <p>
 * <i>Complementing the object oriented system supplied by the GLib library is
 * a set of foundation elements, <code>GType</code> and <code>GValue</code>,
 * the latter being defined as "a polymorphic type that can hold values of any
 * other type", which isn't much help, really.</i>
 * 
 * <p>
 * <i>Since instances of Java classes are their own identity, we do not need
 * to directly represent <code>GType</code> and <code>GValue</code> as
 * separate classes. We implement <code>GType</code> as a characteristic that
 * any</i> <code>Value</code> <i>or</i> <code>Object</code> <i>has.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public class Value extends Pointer
{
    /*
     * The second argument is a hack to create a different overload of
     * <init>(long). What a pain in the ass, but whatever.
     */
    protected Value(long pointer, boolean proxy) {
        super(pointer);
        if (Debug.MEMORY_MANAGEMENT) {
            System.err.println("Value.<init>(long)\t\t" + this.toString());
            System.err.flush();
        }
    }

    /**
     * By design, we are the owner of all the GValues because we allocate the
     * memory for them in (for example) the native implementation of
     * {@link GValue#createValue(int)}. So, call <code>g_slice_free()</code>
     * on our pointer to free that memory.
     */
    protected void release() {
        if (Debug.MEMORY_MANAGEMENT) {
            System.err.println("Value.release()\t\t\t" + this.toString());
        }
        GValue.free(this);
    }

    /**
     * Create an empty Value without initializing it's type. For use in
     * methods like TreeModel's
     * {@link org.gnome.gtk.TreeModel#getValue(org.gnome.gtk.TreeIter, org.gnome.gtk.DataColumnString)
     * getValue()} family, which use a blank Value internally.
     * 
     * Not public API!
     */
    protected Value() {
        this(GValue.createValue(), true);
    }

    /**
     * Create a Value containing a String. Not public API!
     */
    protected Value(String value) {
        this(GValue.createValue(value), true);
    }

    protected String getString() {
        return GValue.getString(this);
    }

    /**
     * Create a Value containing an <code>int</code>. Not public API!
     */
    protected Value(int value) {
        this(GValue.createValue(value), true);
    }

    protected int getInteger() {
        return GValue.getInteger(this);
    }

    /**
     * Create a Value containing a <code>boolean</code>. Not public API!
     */
    protected Value(boolean value) {
        this(GValue.createValue(value), true);
    }

    protected boolean getBoolean() {
        return GValue.getBoolean(this);
    }

    /**
     * Create a Value containing a <code>float</code>. Not public API!
     */
    protected Value(float value) {
        this(GValue.createValue(value), true);
    }

    protected Value(double value) {
        this(GValue.createValue(value), true);
    }

    protected Value(Pixbuf pixbuf) {
        this(GValue.createValue(pixbuf), true);
    }

    protected Value(Object obj) {
        this(GValue.createValue(obj), true);
    }

    protected Value(FontDescription desc) {
        this(GValue.createValue(desc), true);
    }

    /*
     * Another one that's only really here for unit tests.
     */
    protected Object getObject() {
        return GValue.getObject(this);
    }

    protected Pixbuf getPixbuf() {
        return GValue.getPixbuf(this);
    }

    protected float getFloat() {
        return GValue.getFloat(this);
    }

    protected double getDouble() {
        return GValue.getDouble(this);
    }

    protected Value(long value) {
        this(GValue.createValue(value), true);
    }

    protected Value(Constant value) {
        this(GValue.createValue(value), true);
    }

    protected long getLong() {
        return GValue.getLong(this);
    }

    protected Constant getEnum() {
        return GValue.getEnum(this);
    }
}
