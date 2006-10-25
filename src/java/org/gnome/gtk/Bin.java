/*
 * Bin.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
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
