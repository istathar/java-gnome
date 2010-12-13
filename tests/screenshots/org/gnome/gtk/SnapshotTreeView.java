/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
public class SnapshotTreeView extends Snapshot
{
    public SnapshotTreeView() {
        super(TreeView.class);

        final ListStore model;
        final DataColumnString countryName;
        final DataColumnString monarchName;
        final DataColumnString ascendedYear;
        TreeIter row;
        final TreeView view;
        TreeViewColumn vertical;
        CellRendererText text;

        window = new Window();
        window.setTitle("Sovereigns of Canada");

        model = new ListStore(new DataColumn[] {
            countryName = new DataColumnString(),
            monarchName = new DataColumnString(),
            ascendedYear = new DataColumnString(),
        });

        row = model.appendRow();
        model.setValue(row, countryName, "Great Britain");
        model.setValue(row, monarchName, "King George III");
        model.setValue(row, ascendedYear, "1760");

        row = model.appendRow();
        model.setValue(row, countryName, "United Kingdom");
        model.setValue(row, monarchName, "King George IV");
        model.setValue(row, ascendedYear, "1820");

        row = model.appendRow();
        model.setValue(row, countryName, "United Kingdom");
        model.setValue(row, monarchName, "King William IV");
        model.setValue(row, ascendedYear, "1830");

        row = model.appendRow();
        model.setValue(row, countryName, "United Kingdom");
        model.setValue(row, monarchName, "Queen Victoria");
        model.setValue(row, ascendedYear, "1837");

        row = model.appendRow();
        model.setValue(row, countryName, "United Kingdom");
        model.setValue(row, monarchName, "King Edward VII");
        model.setValue(row, ascendedYear, "1901");

        row = model.appendRow();
        model.setValue(row, countryName, "United Kingdom");
        model.setValue(row, monarchName, "King George V");
        model.setValue(row, ascendedYear, "1910");

        row = model.appendRow();
        model.setValue(row, countryName, "United Kingdom");
        model.setValue(row, monarchName, "King Edward VIII");
        model.setValue(row, ascendedYear, "1936");

        row = model.appendRow();
        model.setValue(row, countryName, "Canada");
        model.setValue(row, monarchName, "King George VI");
        model.setValue(row, ascendedYear, "1936");

        row = model.appendRow();
        model.setValue(row, countryName, "Canada");
        model.setValue(row, monarchName, "Queen Elizabeth II");
        model.setValue(row, ascendedYear, "1952");

        view = new TreeView(model);

        vertical = view.appendColumn();
        vertical.setTitle("Name");
        text = new CellRendererText(vertical);
        text.setText(monarchName);

        vertical = view.appendColumn();
        vertical.setTitle("Country");
        text = new CellRendererText(vertical);
        text.setText(countryName);

        vertical = view.appendColumn();
        vertical.setTitle("Ascended");
        text = new CellRendererText(vertical);
        text.setText(ascendedYear);
        text.setAlignment(Alignment.RIGHT, Alignment.TOP);

        view.setRulesHint(true);
        view.getSelection().selectRow(new TreePath("7"));

        window.add(view);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotTreeView());
        Gtk.main();
    }
}
