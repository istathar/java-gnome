/*
 * GtkWindow.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

import org.gnome.glib.Plumbing;

// generated
final class GtkWindow extends Plumbing {

    private GtkWindow() {
    }

    static final long createWindow(WindowType type) {
        return gtk_window_new(numOf(type));
    }

    private static native final long gtk_window_new(int type);

    static final void setTitle(Window self, String title) {
        gtk_window_set_title(pointerOf(self), title);
    }

    private static native final void gtk_window_set_title(long window,
            String title);
}
