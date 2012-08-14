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

import java.util.ArrayList;

import org.freedesktop.bindings.Constant;
import org.freedesktop.bindings.Debug;
import org.freedesktop.bindings.Flag;
import org.freedesktop.bindings.Proxy;
import org.gnome.pango.FontDescription;

/**
 * Base class of the object system used by GLib and libraries based on it,
 * such as GTK.
 * 
 * <p>
 * <i><b>This is the wrapper around <code>GObject</code>!</b></i>
 * 
 * <p>
 * <i>Methods here provide the mechanism to get and set "properties" on the
 * underlying <code>GObject</code>s. As a deliberate design decision to ensure
 * type safety, however, these are not exposed for public use. To offer a
 * getter or setter for a property, a java-gnome subclass must expose an
 * explicitly named method. For example, to set the</i> <var>righteous</var>
 * <i>property, (assuming that the <code>GObject</code> in question has such a
 * property, that it is writable, and that it takes a string), create a method
 * like this:</i>
 * 
 * <pre>
 * public void setRighteous(String value) {
 *     setPropertyString(&quot;righteous&quot;, value);
 * }
 * </pre>
 * 
 * <i>The use of a String property name is, of course, insanely type-unsafe,
 * which is why we don't expose it in our public API. Luckily, though, in
 * general it is not necessary to use this at all, as most
 * <code>GObject</code>s provide convenience methods for such things, and
 * should be used in preference wherever available.</i>
 * 
 * <p>
 * <i>It is probably also worth noting that these code paths are less
 * efficient by about 8:1 as a <code>GValue</code> must be constructed as a
 * wrapper around the data for the property being set. While that's not too
 * expensive in C, from Java (and from all other language bindings of GTK) it
 * requires allocation and initialization of the wrapper structure which
 * really is just throw away.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public abstract class Object extends Proxy
{
    /**
     * When signals are collected, we maintain the strong Java reference here,
     * in the GObject Proxy so that circular (well, dangling) references to
     * the Signal instance can be detected by the garbage collector. There is
     * a weak Java reference from the BindingsJavaClosure on the JNI side. See
     * bindings_java_signal.c in src/jni.
     */
    private ArrayList<Signal> handlers;

    protected Object(long pointer) {
        super(pointer);
        if (Debug.MEMORY_MANAGEMENT) {
            System.err.println("Object.<init>(long)\t\t" + this.toString());
            System.err.flush();
        }
        GObject.addToggleRef(this);
    }

    /**
     * Drop our reference count to the underlying GObject.
     * 
     * <p>
     * <i>This should, incidentally, cause this GObject to be disposed by GLib
     * of if we're the only one still holding a reference count to it. On the
     * other hand, if our Proxy just happens to be going out of scope, and the
     * GtkWidget (say) is [still] packed into some GtkContainer, then it will
     * continue to exist quite happily.</i>
     */
    protected final void release() {
        if (Debug.MEMORY_MANAGEMENT) {
            System.err.println("Object.release()\t\t" + this.toString());
            System.err.flush();
        }
        GObject.removeToggleRef(this);
    }

    /**
     * Set a property that takes a <code>String</code> for its value.
     * 
     * @since 4.0.0
     */
    /*
     * Obviously a convenience method, but the developer doesn't need to know
     * that.
     */
    protected void setPropertyString(String name, String value) {
        GObject.setProperty(this, name, new Value(value));
    }

    /**
     * Set a property that takes an <code>int</code> for its value.
     * 
     * @since 4.0.0
     */
    /*
     * Obviously a convenience method, but the developer doesn't need to know
     * that.
     */
    protected void setPropertyInteger(String name, int value) {
        GObject.setProperty(this, name, new Value(value));
    }

    protected int getPropertyInteger(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getInteger(value);
    }

    /**
     * Set a property that takes a <code>boolean</code> for its value.
     * 
     * @since 4.0.0
     */
    protected void setPropertyBoolean(String name, boolean value) {
        GObject.setProperty(this, name, new Value(value));
    }

    protected boolean getPropertyBoolean(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getBoolean(value);
    }

    /**
     * Set a property that takes a <code>float</code> for its value.
     * 
     * @since 4.0.4
     */
    protected void setPropertyFloat(String name, float value) {
        GObject.setProperty(this, name, new Value(value));
    }

    protected float getPropertyFloat(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getFloat(value);
    }

    protected void setPropertyDouble(String name, double value) {
        GObject.setProperty(this, name, new Value(value));
    }

    protected double getPropertyDouble(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getDouble(value);
    }

    /**
     * Set a property that takes an Object subclass for its value.
     * 
     * @since 4.0.2
     */
    protected void setPropertyObject(String name, Object value) {
        GObject.setProperty(this, name, new Value(value));
    }

    protected String getPropertyString(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getString(value);
    }

    protected void setPropertyEnum(String name, Constant value) {
        GObject.setProperty(this, name, new Value(value));
    }

    protected Constant getPropertyEnum(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getEnum(value);
    }

    protected Flag getPropertyFlags(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getFlags(value);
    }

    /**
     * Get a property that takes a Value [subclass] such as an Object or Boxed
     * as its value. This isn't type safe at all, so if you call it and want
     * to downcast the result, you'd probably be careful about it.
     * 
     * @param name
     *            the property whose value you want to get.
     * @return the {@link org.gnome.glib.Value Value} of the property, or null
     *         if not set (?). FIXME what about no such property?
     * @since 4.0.1
     */
    protected Object getPropertyObject(String name) {
        final Value value;

        value = GObject.getProperty(this, name);
        return GValue.getObject(value);
    }

    protected void setPropertyBoxed(String name, FontDescription desc) {
        GObject.setProperty(this, name, new Value(desc));
    }

    /**
     * When a Signal handler is connected, we maintain a strong reference from
     * here. We don't use it for anything, just memory management. Only called
     * from Plumbing.connectSignal().
     */
    void addHandler(Signal handler) {
        synchronized (this) {
            if (handlers == null) {
                handlers = new ArrayList<Signal>(1);
            }
            handlers.add(handler);
        }
    }
}
