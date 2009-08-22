/*
 * SnapshotEntryRed.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Color;

/**
 * Illustrate using modifyText() on an Entry. FIXME, probably should save this
 * with a specific name for use on Widget.
 * 
 * @author Andrew Cowie
 */
public class SnapshotEntryRed extends Snapshot
{
    public SnapshotEntryRed() {
        super(Entry.class);

        final Entry e;

        window = new Window();
        window.setDecorated(false);

        e = new Entry();
        e.setText("Lots of money");
        e.modifyText(StateType.NORMAL, Color.RED);
        e.selectRegion(0, 0);

        window.add(e);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotEntryRed());
        Gtk.main();
    }
}
