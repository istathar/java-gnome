/*
 * SnapshotEntryIcon.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Illustrate using setIconFromStock() on an Entry.
 * 
 * @author Guillaume Mazoyer
 */
public class SnapshotEntryIcon extends Snapshot
{
    public SnapshotEntryIcon() {
        super(Entry.class);

        final Entry entry;

        window = new Window();
        window.setDecorated(false);

        entry = new Entry();
        entry.setIconFromStock(EntryIconPosition.SECONDARY, Stock.CLEAR);
        entry.setText("Search...");

        window.add(entry);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotEntryIcon());
        Gtk.main();
    }
}
