/*
 * SnapshotTextComboBox.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * FIXME Same problem as SnapshotComboBox, which is where this code originally
 * lived.
 * 
 * @author Andrew Cowie
 */
public class SnapshotTextComboBox extends Snapshot
{
    public SnapshotTextComboBox() {
        super(TextComboBox.class);

        final TextComboBox combo;

        window = new Window();
        window.setDecorated(false);

        combo = new TextComboBox();
        combo.appendText("SYD");
        combo.appendText("YYZ");
        combo.appendText("JFK");
        combo.appendText("LHR");
        window.add(combo);

        window.showAll();
        window.move(100, 100);
        combo.setActive(2);
        // combo.popup();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotTextComboBox());
        Gtk.main();
    }
}
