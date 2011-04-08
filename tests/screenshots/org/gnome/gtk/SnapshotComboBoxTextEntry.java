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

/**
 * @author Andrew Cowie
 */
public class SnapshotComboBoxTextEntry extends Snapshot
{
    public SnapshotComboBoxTextEntry() {
        super(ComboBoxText.class, "-Entry");

        final ComboBoxText combo;

        window = new Window();
        window.setDecorated(false);

        combo = new ComboBoxText();
        combo.appendText("Sydney");
        combo.appendText("Toronto");
        combo.appendText("New York");
        combo.appendText("London");
        window.add(combo);

        window.showAll();
        window.move(100, 100);
        combo.setActive(2);
        // combo.popup();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotComboBoxText());
        Gtk.main();
    }
}
