/*
 * Object.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.glib;

/**
 * Base class of the object system used by GLib and libraries based on it, such
 * as GTK.
 * 
 * <p>
 * <i><b>This is the wrapper around <code>GObject</code>!</b></i>
 * 
 * <p>
 * <i>Methods here provide the mechanism to get and set "properties" on the
 * underlying <code>GObject</code>s. As a deliberate design decision to
 * ensure type safety, however, these are not exposed for public use. To offer a
 * getter or setter for a property, a java-gnome subclass must expose an
 * explicitly named method. For example, to set the</i> "<code>righteous</code>"
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
 * <i>This is of course insanely type-unsafe. In general, luckily, this is not
 * necessary, as most <code>GObject</code>s provide convenience methods for
 * such things, and should be used in preference wherever available.</i>
 */
public abstract class Object extends Value
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

    /*
     * Obviously a convenience method, but the developer doesn't need to know
     * that.
     */
    protected void setPropertyString(String name, String value) {
        GObject.setProperty(this, name, new StringValue(value));
    }

    /*
     * Obviously a convenience method, but the developer doesn't need to know
     * that.
     */
    protected void setPropertyInteger(String name, int value) {
        GObject.setProperty(this, name, new IntegerValue(value));
    }

    /**
     * Set a property that takes a boolean for its value.
     */
    /*
     * Obviously a convenience method, but the developer doesn't need to know
     * that.
     */
    protected void setPropertyBoolean(String name, boolean value) {
        GObject.setProperty(this, name, new BooleanValue(value));
    }

    /**
     * Set a property that takes a Value [subclass] such as an Object or Boxed
     * as its value.
     * 
     * @param name
     *            the property you want to set.
     * @param value
     *            a Value subclass such as {@link org.gnome.glib.Object Object},
     *            {@link org.gnome.glib.Boxed Boxed}, or Fundamental which is
     *            the value you wish to set.
     */
    protected void setProperty(String name, Value value) {
        GObject.setProperty(this, name, value);
    }

    /**
     * Set a property that takes a Value [subclass] such as an Object or Boxed
     * as its value. This isn't type safe at all, so if you call it and want to
     * downcast the result, you'd probably be careful about it.
     * 
     * @param name
     *            the property you want to set.
     * @return the {@link org.gnome.glib.Value Value} of the property, or null
     *         if not set (?). FIXME what about no such property?
     */
    protected Value getProperty(String name) {
        return GObject.getProperty(this, name);
    }
}
