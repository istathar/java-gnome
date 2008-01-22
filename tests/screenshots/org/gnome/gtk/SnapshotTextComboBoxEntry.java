/*
 * SnapshotTextComboBoxEntry.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * @author Andrew Cowie
 */
public class SnapshotTextComboBoxEntry extends Snapshot
{
    public SnapshotTextComboBoxEntry() {
        super(TextComboBoxEntry.class);

        final TextComboBoxEntry combo;

        window = new Window();
        window.setDecorated(false);

        combo = new TextComboBoxEntry();
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
        runExample(new SnapshotTextComboBoxEntry());
        Gtk.main();
    }
}
