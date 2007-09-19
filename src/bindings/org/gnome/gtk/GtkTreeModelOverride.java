/*
 * GtkTreeModelOverride.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Manual code relating to TreeModel.
 * 
 * @author Andrew Cowie
 */
final class GtkTreeModelOverride extends Plumbing
{
    /*
     * FIXME REMOVE! Work around the fact that we don't have a working
     * ListStore constructor yet.
     */
    static final long createDummyListStore() {
        synchronized (lock) {
            return gtk_list_store_new();
        }
    }

    private static native final long gtk_list_store_new();
}
