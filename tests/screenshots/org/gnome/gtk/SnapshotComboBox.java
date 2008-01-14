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
    private final ListStore model;

    private final DataColumnString cityColumn;

    private final DataColumnString codeColumn;

    public SnapshotComboBox() {
        super(ComboBox.class);
        TreeIter row;
        final ComboBox combo;
        CellRendererText text;

        window = new Window();
        window.setTitle("Airports");
        window.setDecorated(false);

        model = new ListStore(new DataColumn[] {
                cityColumn = new DataColumnString(), codeColumn = new DataColumnString(),
        });

        populate("Sydney", "Australia", "SYD");
        populate("Toronto", "Canada", "YYZ");
        populate("New York", "United States of America", "JFK");
        populate("London", "United Kingdom of Great Britain and Northern Ireland", "LHR");

        combo = new ComboBox(model);

        text = new CellRendererText(combo);
        text.setMarkup(cityColumn);

        text = new CellRendererText(combo);
        text.setMarkup(codeColumn);
        text.setAlignment(Alignment.RIGHT, Alignment.TOP);

        window.add(combo);

        window.showAll();
        window.move(100, 100);
        combo.setActive(2);
        combo.popup();
    }

    private void populate(String city, String country, String code) {
        TreeIter row;

        row = model.appendRow();
        model.setValue(row, cityColumn, city + "\n<span size='small'><i>" + country + "</i></span>");
        model.setValue(row, codeColumn, "<span font_desc='Mono'>" + code + "</span>");
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotComboBox());
        Gtk.main();
    }
}
