/*
 * GtkContainer.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE WILL BE GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override
 * for this class.
 */
package org.gnome.gtk;

import org.gnome.glib.Plumbing;

final class GtkContainer extends Plumbing
{
    private GtkContainer() {}

    /**
     * Wraps <code><pre>
     *     gtk_container_add(GtkContainer container, GtkWidget child)
     * </pre></code>
     */
    static final void add(Container self, Widget child) {
        gtk_container_add(pointerOf(self), pointerOf(child));
    }

    private static native final void gtk_container_add(long self, long child);

    static final void remove(Container self, Widget child) {
        gtk_container_remove(pointerOf(self), pointerOf(child));
    }
    
    private static native final void gtk_container_remove(long self, long child);
}
