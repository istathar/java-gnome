/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
        e.overrideColor(StateFlags.NORMAL, RGBA.RED);
        e.selectRegion(0, 0);

        window.add(e);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotEntryRed());
        Gtk.main();
    }
}
