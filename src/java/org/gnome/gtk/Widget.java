/*
 * Widget.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

/**
 * TODO.
 * 
 * @author Andrew Cowie
 */
public abstract class Widget extends org.gnome.gtk.Object {
    
    protected Widget(long pointer) {
        super(pointer);
    }

    public void show() {
        GtkWidget.show(this);
    }

    public void showAll() {
        GtkWidget.showAll(this);
    }

}
