/*
 * Container.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

public abstract class Container extends Widget {
    
    protected Container(long pointer) {
        super(pointer);
    }

    public void add(Widget child) {
        GtkContainer.add(this, child);
    }
}
