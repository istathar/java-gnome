/*
 * GtkWindow.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
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

final class GtkWindow extends Plumbing
{
    private GtkWindow() {}

    static final long createWindow(WindowType type) {
        return gtk_window_new(numOf(type));
    }

    private static native final long gtk_window_new(int type);

    static final void setTitle(Window self, String title) {
        gtk_window_set_title(pointerOf(self), title);
    }

    private static native final void gtk_window_set_title(long window, String title);

    static final void setDecorated(Window self, boolean decorated) {
        gtk_window_set_decorated(pointerOf(self), decorated);

    }

    private static native final void gtk_window_set_decorated(long window, boolean decorated);

    static final void setDefaultSize(Window self, int width, int height) {
        gtk_window_set_default_size(pointerOf(self), width, height);
    }

    private static native final void gtk_window_set_default_size(long window, int width, int height);
}
