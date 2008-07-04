/*
 * SnapshotRadioButton.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
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
public class SnapshotRadioButton extends Snapshot
{
    public SnapshotRadioButton() {
        super(RadioButton.class);

        final RadioButtonGroup muppets;
        final RadioButton kermit, animal, gonzo, fozzie;
        final VBox x;

        window = new Window();
        window.setDecorated(false);

        x = new VBox(false, 3);

        muppets = new RadioButtonGroup();

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
