/*
 * SnapshotDialog.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * @author Andrew Cowie
 */
/*
 * Doesn't have to be abstract (we'll have a snapshot for Dialog sooner or
 * later, I imagine); this was originally put here as a way to hook up the
 * appropriate handlers to deal with closing Dialogs when running manually.
 */
abstract class SnapshotDialog extends Snapshot
{
    protected SnapshotDialog(Class<?> underTest) {
        super(underTest);
    }

    /*
     * Chain up to the runExample() in Snapshot, then hook up a generic
     * RESPONSE handler.
     */
    protected static void runExample(final Snapshot example) {
        final Dialog dialog;

        Snapshot.runExample(example);

        dialog = (Dialog) example.window;

        dialog.connect(new Dialog.Response() {
            public void onResponse(Dialog source, ResponseType response) {
                Gtk.mainQuit();
            }
        });
    }
}
