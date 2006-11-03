/*
 * Bin.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

/**
 * A Container with only one child Widget.
 * 
 * @author Andrew Cowie
 */
public abstract class Bin extends Container {

    protected Bin(long pointer) {
        super(pointer);
    }

    public Widget getChild() {
        return GtkBin.getChild(this);
    }
}
