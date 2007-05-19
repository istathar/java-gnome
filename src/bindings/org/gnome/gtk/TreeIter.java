/*
 * TreeIter.java
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
