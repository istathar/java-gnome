/*
 * GValue.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.glib;

// generated or crafted? Uncertain, probably crafted, given our munging of Value
// and Type
final class GValue {

    // no instantiation
    private GValue() {
    }

    // g_value_init(value, type)
    static native void init(Value value /*, Type type? */);
    
    // g_value_set_boolean(value, v_boolean)
    static native void setBoolean(Value value, boolean b);
}
