/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Illustrate the EntryCompletion use on an Entry widget.
 * 
 * @author Guillaume Mazoyer
 */
public class SnapshotEntryCompletion extends Snapshot
{
    public SnapshotEntryCompletion() {
        super(EntryCompletion.class);

        final ListStore model;
        final DataColumnString column;
        final Entry entry;
        final EntryCompletion completion;
        final VBox vbox;
        final Label label;

        TreeIter row;

        window = new Window();
        window.setDecorated(false);

        vbox = new VBox(false, 3);
        window.add(vbox);

        label = new Label("Send a mail to:");
        vbox.add(label);

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString()
        });

        row = model.appendRow();
        model.setValue(row, column, "respawneral@gmail.com");

        entry = new Entry();
        completion = new EntryCompletion();

        completion.setModel(model);
        completion.setTextColumn(column);

        entry.setCompletion(completion);
        vbox.add(entry);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotEntryCompletion());
        Gtk.main();
    }
}
