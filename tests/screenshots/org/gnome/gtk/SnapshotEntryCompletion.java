/*
 * SnapshotEntryCompletion.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
