/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
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
