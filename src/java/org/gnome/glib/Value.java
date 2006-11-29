/*
 * Value.java
 *
 * Copyright (c) 2006 Operational Dynamic
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.glib;

import org.freedesktop.bindings.Proxy;

/**
 * A generic value that can be passed as a parameter to or returned from a
 * method or function on an underlying entitiy in the GLib library and those
 * built on it.
 * 
 * <p>
 * <i>Above the object oriented system supplied by the GLib library is a set of
 * foundation elements, <code>GType</code> and <code>GValue</code>, the
 * latter being defined as "a polymorphic type that can hold values of any other
 * type", which isn't much help, really.</i>
 * 
 * <p>
 * <i>Since instances of Java classes are their own identity, we do not need to
 * directly represent <code>GType</code> and <code>GValue</code> as separate
 * classes. We implement <code>GType</code> as a characteristic that any</i>
 * <code>Value</code> <i>has. <b>This class is NOT a one-to-one wrapper of the
 * underlying <code>GValue</code> system.</i>
 * 
 * @author Andrew Cowie
 */
/*
 * This is plumbing!
 */
public abstract class Value extends Proxy
{
    /*
     * This is an opaque representation of the GType value code used by GLib.
     * Any interpretation that the Java language might assign to a long (ie,
     * that it's signed) is meaningless and incorrect! This is package public so
     * that Plumbing can see it, and final so that once constructed its
     * immutable.
     */
    final long type = 0; // FIXME REMOVE?

    protected Value(long pointer) {
        super(pointer);
    }

    /**
     * Parent release function. Will be called by the GC when it invokes the
     * finalizer, so this is the time to release references and free memory on
     * the C side.
     */
    protected abstract void release();
}
