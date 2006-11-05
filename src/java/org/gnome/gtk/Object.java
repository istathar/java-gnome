/*
 * GtkObject.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

import org.gnome.gdk.Pixbuf;

/**
 * <i><b>This is the wrapper around <code>GtkObject</code>!</b></i>
 */
public abstract class Object extends org.gnome.glib.Object {
    protected Object(long pointer) {
        super(pointer);
    }

    protected void setPropertyPixbuf(String name, Pixbuf pixbuf) {
        setProperty(name, pixbuf); 
    }
}
