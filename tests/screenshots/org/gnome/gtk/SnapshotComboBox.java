/*
 * SnapshotComboBox.java
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
 * Wrote this before we had the text methods spliced off into TextComboBox,
 * but in any case this provides a good test of the ComboBox functionality.
 * FIXME Screenshot doesn't seem to capture the popup properly, even though it
 * does raise to screen when run manually. Help?
 * 
 * @author Andrew Cowie
 */
public class SnapshotComboBox extends Snapshot
{
    public SnapshotComboBox() {
        super(ComboBox.class);

        final ListStore model;
        final DataColumnString code;
        TreeIter row;
        final ComboBox combo;
        final CellRendererText text;

        window = new Window();
        window.setTitle("Airports");
        window.setDecorated(false);

        model = new ListStore(new DataColumn[] {
            code = new DataColumnString(),
        });

        row = model.appendRow();
        model.setValue(row, code, "SYD");
        row = model.appendRow();
        model.setValue(row, code, "YYZ");
        row = model.appendRow();
        model.setValue(row, code, "JFK");
        row = model.appendRow();
        model.setValue(row, code, "LHR");

        combo = new ComboBox(model);

        text = new CellRendererText(combo);
        text.setText(code);
        window.add(combo);

        window.showAll();
        window.move(100, 100);
        combo.setActive(2);
        combo.popup();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotComboBox());
        Gtk.main();
    }
}
