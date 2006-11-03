/*
 * GBoxed.java
 *
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.glib;

/*
 * Crafted: this is where we carry out the memory management of our GBoxed
 * proxies.
 */
final class GBoxed extends Plumbing {

    // no instantiation
    private GBoxed() {
    }

    static final void free(Boxed reference) {
        g_boxed_free(typeOf(reference), pointerOf(reference));
    }

    private static final native void g_boxed_free(long type, long boxed);
}
