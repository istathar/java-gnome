/*
 * GtkObject.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

import org.gnome.gdk.Pixbuf;

/**
 * This is the wrapper around GtkObject!
 */
public abstract class Object extends org.gnome.glib.Object {
    protected Object(long pointer) {
        super(pointer);
    }

    public void setProperty(String name, Pixbuf pixbuf) {
        setProperty(name, (org.gnome.glib.Object) pixbuf); 
    }
}
