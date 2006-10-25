/*
 * Value.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.glib;

import org.freedesktop.bindings.Proxy;

/**
 * Above the object oriented system supplied by the Glib library is a set of
 * foundation elements, represented here by Type and Value. Value is "a
 * polymorphic type that can hold values of any other type".
 * 
 * @author Andrew Cowie
 */
/*
 * This is plumbing! It is NOT a one to one wrapper of the underlying GValue
 * system. Notably, classes are their own identity, so need for a Type class? and instances
 * have that identity and a value...
 * 
 */
public abstract class Value extends Proxy {

    Value() {
        super(0);
    }
    
    protected Value(long pointer) {
        super(pointer);
    }

    // private Type type;
    //    
    // /**
    // * Values have Types and the Value themselves.
    // * @return a representation of the underlying type system.
    // */
    // protected Type getType() {
    // return null;
    // }

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
        GValue.init(this /*, type?? */);
        GValue.setBoolean(this, b);
    }
}