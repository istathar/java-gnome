/*
 * GtkProgressBar.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
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

final class GtkProgressBar extends Plumbing
{
    private GtkProgressBar() {}

    static final long createProgressBar() {
        return gtk_progress_bar_new();
    }

    private static native final long gtk_progress_bar_new();

    static final void setText(ProgressBar self, String text) {
        gtk_progress_bar_set_text(pointerOf(self), text);
    }

    private static final native void gtk_progress_bar_set_text(long label, String text);

    static void setFraction(ProgressBar self, double fraction) {
        gtk_progress_bar_set_fraction(pointerOf(self), fraction);
    }

    private static native void gtk_progress_bar_set_fraction(long self, double fraction);
}
