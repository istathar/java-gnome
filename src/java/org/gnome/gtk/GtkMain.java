/*
 * GtkMain.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

import org.gnome.glib.Plumbing;

// crafted
final class GtkMain extends Plumbing {

    static final void init(String[] args) {
        gtk_init(/* args */);
    }

    private static native final void gtk_init(/* String[] args? */);

    static final void main() {
        gtk_main();
    }

    private static native final void gtk_main();
}
