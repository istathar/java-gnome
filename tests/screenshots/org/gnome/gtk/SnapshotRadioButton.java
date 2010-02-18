/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
public class SnapshotRadioButton extends Snapshot
{
    public SnapshotRadioButton() {
        super(RadioButton.class);

        final RadioGroup muppets;
        final RadioButton kermit, animal, gonzo, fozzie;
        final VBox x;

        window = new Window();
        window.setDecorated(false);

        x = new VBox(false, 3);

        muppets = new RadioGroup();

        kermit = new RadioButton(muppets, "Kermit");
        x.add(kermit);

        animal = new RadioButton(muppets, "Animal");
        x.add(animal);

        gonzo = new RadioButton(muppets, "Gonzo");
        x.add(gonzo);

        fozzie = new RadioButton(muppets, "Fozzie");
        x.add(fozzie);

        gonzo.setActive(true);

        window.add(x);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotRadioButton());
        Gtk.main();
    }
}
