/*
 * Object.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Pixbuf;

/**
 * Base class for Widgets and various other elements in GTK.
 * 
 * <p>
 * <i><b>This is the wrapper around <code>GtkObject</code>!</b></i>
 * 
 * <p>
 * <i>Since the GObject type system was abstracted out from GTK some time
 * after GTK was first written, <code>GtkObject</code> predates
 * <code>GObject</code>; almost all of the functionality originally
 * resident in <code>GtkObject</code> was moved to <code>GObject</code>
 * long ago. Its presence in the type hierarchy is largely for backwards
 * compatibility, although it does add a few additional property accessors
 * that are specific to GTK. Only people hacking on java-gnome itself will
 * have any need to interact with this class, and then only rarely.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public abstract class Object extends org.gnome.glib.Object
{
    protected Object(long pointer) {
        super(pointer);
    }

    /**
     * Set a property that takes a Pixbuf for its value.
     * 
     * @since 4.0.0
     */
    protected void setPropertyPixbuf(String name, Pixbuf pixbuf) {
        setProperty(name, pixbuf);
    }
}
