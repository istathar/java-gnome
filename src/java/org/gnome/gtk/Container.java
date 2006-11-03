/*
 * Container.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
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
