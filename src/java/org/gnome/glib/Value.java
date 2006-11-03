/*
 * Value.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
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
 * This is plumbing! It , so need for a Type class? and instances have that
 * identity and a value...
 * 
 */
public abstract class Value extends Proxy {
    /*
     * This is an opaque representation of a memory address. It's a Java long,
     * which means it's 64 bits wide which in turn means it can hold an address
     * on a 64 bit system, but any interpretation that the Java language might
     * asign to a long (ie, that it's signed) is meaningless and incorrect! This
     * is package public so that Plumbing can see it, and final so that once
     * constructed its immutable.
     */
    final long type;

    Value() {
        this(0);
    }

    protected Value(long pointer) {
        super(pointer);
        type = 0;
    }
}

/*
 * TODO Do something to create each of these things with the appropriate
 * GType... can we avoid a JNI round trip?
 */

final class StringValue extends Value {

    StringValue(String str) {

    }
}

final class IntegerValue extends Value {

    IntegerValue(int i) {

    }
}

final class BooleanValue extends Value {

    BooleanValue(boolean b) {
        GValue.init(this /* , type?? */);
        GValue.setBoolean(this, b);
    }
}