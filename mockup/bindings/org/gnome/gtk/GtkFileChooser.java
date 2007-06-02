/*
 * GtkFileChooser.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd and Others
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

import org.freedesktop.bindings.Proxy;
import org.gnome.glib.Plumbing;
import org.gnome.glib.Signal;

final class GtkFileChooser extends Plumbing
{
    private GtkFileChooser() {}

    static final String getFilename(FileChooser self) {
        synchronized (lock) {
            return gtk_file_chooser_get_filename(pointerOf((Proxy) self));
        }
    }

    private static native final String gtk_file_chooser_get_filename(long chooser);

    static final String getUri(FileChooser self) {
        synchronized (lock) {
            return gtk_file_chooser_get_uri(pointerOf((Proxy) self));
        }
    }

    private static native final String gtk_file_chooser_get_uri(long chooser);

    static final boolean setCurrentFolder(FileChooser self, String filename) {
        synchronized (lock) {
            return gtk_file_chooser_set_current_folder(pointerOf((Proxy) self), filename);
        }
    }

    private static native final boolean gtk_file_chooser_set_current_folder(long chooser, String filename);

    static final String getCurrentFolder(FileChooser self) {
        synchronized (lock) {
            return gtk_file_chooser_get_current_folder(pointerOf((Proxy) self));
        }
    }

    private static native final String gtk_file_chooser_get_current_folder(long chooser);

    interface FILE_ACTIVATED extends Signal
    {
        void onFileActivated(FileChooser source);
    }

    static final void connect(FileChooser self, GtkFileChooser.FILE_ACTIVATED handler) {
        connectSignal((Object) self, handler, GtkFileChooser.class, "file-activated");
    }

    protected static final void handleFileActivated(Signal handler, long source) {
        ((GtkFileChooser.FILE_ACTIVATED) handler).onFileActivated((FileChooser) objectFor(source));
    }

    interface SELECTION_CHANGED extends Signal
    {
        void onSelectionChanged(FileChooser source);
    }

    static final void connect(FileChooser self, GtkFileChooser.SELECTION_CHANGED handler) {
        connectSignal((Object) self, handler, GtkFileChooser.class, "selection-changed");
    }

    protected static final void handleSelectionChanged(Signal handler, long source) {
        ((GtkFileChooser.SELECTION_CHANGED) handler).onSelectionChanged((FileChooser) objectFor(source));
    }

}
