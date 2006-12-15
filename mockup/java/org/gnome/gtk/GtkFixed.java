/*
 * GtkWindow.java
 *
 * Copyright (c) 2006 Srichand Pendyala
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

final class GtkFixed extends Plumbing
{
    private GtkFixed() {}

    static final long createFixed() {
        return (gtk_fixed_new());
    }

    private static native final long gtk_fixed_new();

    static final void putWidget(Fixed self, Widget widget, int x, int y) {
        gtk_fixed_put(pointerOf(self), pointerOf(widget), x, y);
    }

    private static native final void gtk_fixed_put(long fixed, long widget, int x, int y);

    static final void moveWidget(Fixed self, Widget widget, int x, int y) {
        gtk_fixed_move(pointerOf(self), pointerOf(widget), x, y);
    }

    private static native final void gtk_fixed_move(long fixed, long widget, int x, int y);
}
