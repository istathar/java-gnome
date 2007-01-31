/*
 * Object.java
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

import org.freedesktop.bindings.Constant;
import org.freedesktop.bindings.Proxy;

/**
 * Base class of the object system used by GLib and libraries based on it,
 * such as GTK.
 * 
 * <p>
 * <i><b>This is the wrapper around <code>GObject</code>!</b></i>
 * 
 * <p>
 * <i>Methods here provide the mechanism to get and set "properties" on the
 * underlying <code>GObject</code>s. As a deliberate design decision to
 * ensure type safety, however, these are not exposed for public use. To offer
 * a getter or setter for a property, a java-gnome subclass must expose an
 * explicitly named method. For example, to set the</i> "<code>righteous</code>"
 * <i>property, (assuming that the <code>GObject</code> in question has such
 * a property, that it is writable, and that it takes a string), create a
 * method like this:</i>
 * 
 * <pre>
 * public void setRighteous(String value) {
 *     setPropertyString(&quot;righteous&quot;, value);
 * }
 * </pre>
 * 
 * <i>This is of course insanely type-unsafe. In general, luckily, this is not
 * necessary, as most <code>GObject</code>s provide convenience methods for
 * such things, and should be used in preference wherever available.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public abstract class Object extends Proxy
{

    protected Object(long pointer) {
        super(pointer);
    }

    /**
     * Take the actions necessary to release our reference to the underlying
     * GObject, then carry on to
     * {@link org.freedesktop.bindings.Proxy#finalize() Proxy's finalize()}.
     */
    /*
     * This is a placeholder to remind us of the cleanup actions that will be
     * necessary, irrespective of the finalizer technique used.
     */
    protected void finalize() {
        release();
        super.finalize();
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
        GObject.unref(this);
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
        GObject.setProperty(this, name, new Value(GValue.createValue(value)));
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
        GObject.setProperty(this, name, new Value(GValue.createValue(value)));
    }

    /**
     * Set a property that takes a <code>boolean</code> for its value.
     * 
     * @since 4.0.0
     */
    /*
     * Obviously a convenience method, but the developer doesn't need to know
     * that.
     */
    protected void setPropertyBoolean(String name, boolean value) {
        GObject.setProperty(this, name, new Value(GValue.createValue(value)));
    }

    /**
     * Set a property that takes an Object subclass for its value.
     * 
     * @since 4.0.2
     */
    protected void setPropertyObject(String name, Object value) {
        GObject.setProperty(this, name, new Value(GValue.createValue(value)));
    }

    protected String getPropertyString(String name) {
        Value value = GObject.getProperty(this, name);
        return GValue.getString(value);
    }

    protected Constant getPropertyEnum(String name) {
        Value value = GObject.getProperty(this, name);
        return GValue.getEnum(value);
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
        Value value = GObject.getProperty(this, name);
        return GValue.getObject(value);
    }
}
