/*
 * GObject.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.glib;

/**
 * This is the wrapper around GObject!
 */
public abstract class Object extends Value {

    protected Object(long pointer) {
        super(pointer);
    }

    /*
     * Obviously a convenience method, but the developer doesn't need to know that.
     */
    public void setProperty(String name, String value) {
        GObject.setProperty(this, name, new StringValue(value));
    }

    /*
     * Obviously a convenience method, but the developer doesn't need to know that.
     */
    public void setProperty(String name, int value) {
        GObject.setProperty(this, name, new IntegerValue(value));
    }

    /**
     * Set a property that takes a boolean for its value.
     * @param name
     * @param value
     */
    /*
     * Obviously a convenience method, but the developer doesn't need to know that.
     */
    public void setProperty(String name, boolean value) {
        GObject.setProperty(this, name, new BooleanValue(value));
    }

    /**
     * Set a property that takes a GObject [subclass] as its value. Ideally, you
     * will never need to call this method; by design java-gnome subclasses
     * provide convenience methods for almost all properties.
     * 
     * @param name
     *            the property you want to set.
     * @param value
     *            a org.gnome.glib.Object subclass which is the value you wish
     *            to set.
     */
    public void setProperty(String name, Object value) {
        GObject.setProperty(this, name, value);
    }
}