/*
 * TreeIter.java
 *
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

import org.gnome.glib.Boxed;

/**
 * FIXME placeholder first concrete Boxed subclass.
 * 
 * <p>
 * To obtain a new TreeIter, use one of the following:
 * <ul>
 * <li>{@link org.gnome.gtk.TreeModel#getFirstIter() TreeModel's getFirstIter()}.
 * </ul>
 * 
 * @author Andrew Cowie
 */
public class TreeIter extends Boxed
{

    protected TreeIter(long pointer) {
        super(pointer);
    }

    protected void release() {
        GtkTreeIter.free(this);
    }
}
