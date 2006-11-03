/*
 * GtkContainer.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

import org.gnome.glib.Plumbing;

// generated
final class GtkContainer extends Plumbing {

    private GtkContainer() {
    }

    /**
     * Wraps
     * <code><pre>
     * gtk_container_add(GtkContainer container, GtkWidget child)
     * </pre></code>
     */
    static final void add(Container self, Widget child) {
        gtk_container_add(pointerOf(self), pointerOf(child));
    }

    private static native final void gtk_container_add(long self, long child);
}
