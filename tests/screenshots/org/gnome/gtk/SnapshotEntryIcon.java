/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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
