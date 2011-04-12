/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2011 Operational Dynamics Consulting, Pty Ltd
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

import org.gnome.gdk.RGBA;

/**
 * @author Andrew Cowie
 */
public class SnapshotStatusbar extends Snapshot
{
    public SnapshotStatusbar() {
        super(Statusbar.class);
        final VBox box;
        final Statusbar status;
        final Label spacer;

        window = new Window();
        window.setTitle("Statusbar");
        window.setDecorated(true);
        window.setDefaultSize(200, 100);

        box = new VBox(false, 0);
        // I wish Java had here-docs
        spacer = new Label("This <tt>Window</tt> has a <tt>Statusbar</tt> " + "at the bottom. "
                + "Notice the message \"Ready\" " + "and also the grab handle on the right-hand side.");
        spacer.setLineWrap(true);
        spacer.setUseMarkup(true);
        spacer.setAlignment(0.0f, 0.0f);
        spacer.setSizeRequest(200, 100);
        spacer.setPadding(2, 1);
        window.overrideBackground(StateFlags.NORMAL, RGBA.WHITE);
        box.packStart(spacer, false, false, 0);

        status = new Statusbar();
        status.setMessage("Ready");
        box.packEnd(status, false, false, 0);

        window.add(box);
        window.showAll();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotStatusbar());
        Gtk.main();
    }
}
