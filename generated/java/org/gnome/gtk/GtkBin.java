/*
 * GtkBin.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

import org.gnome.glib.Plumbing;

final class GtkBin extends Plumbing {
    
    private GtkBin() { }

	static final Widget getChild(Bin self) {
        return null;
//        return objectFrom(gtk_bin_get_child(pointerOf(self)));
    }
    
//    private static native final long gtk_bin_get_child(long self);
}
