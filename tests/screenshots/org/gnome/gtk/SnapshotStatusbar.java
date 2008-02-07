/*
 * SnapshotStatusbar.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Color;

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
        window.modifyBackground(StateType.NORMAL, Color.WHITE);
        box.packStart(spacer);

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
