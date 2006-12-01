/*
 * GtkTreeIter.java
 *
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

import org.gnome.glib.Plumbing;

final class GtkTreeIter extends Plumbing
{
    private GtkTreeIter() {
    // no instatiation
    }

    static final void free(TreeIter self) {
        gtk_tree_iter_free(pointerOf(self));
    }

    private static native final void gtk_tree_iter_free(long self);
}
