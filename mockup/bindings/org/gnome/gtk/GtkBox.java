/*
 * GtkBox.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
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

final class GtkBox extends Plumbing
{
    private GtkBox() {}

    static final void packStart(Box self, Widget child, boolean expand, boolean fill, int padding) {
        synchronized (lock) {
            gtk_box_pack_start(pointerOf(self), pointerOf(child), expand, fill, padding);
        }
    }

    private static native final void gtk_box_pack_start(long self, long child, boolean expand,
            boolean fill, int padding);
}
