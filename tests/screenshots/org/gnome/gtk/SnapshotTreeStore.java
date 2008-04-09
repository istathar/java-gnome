/*
 * SnapshotTreeStore.java
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
public class SnapshotTreeStore extends Snapshot
{
    public SnapshotTreeStore() {
        super(TreeStore.class);

        final TreeStore model;
        final DataColumnString placeName;
        TreeIter parent, child;
        final TreeView view;
        TreeViewColumn vertical;
        CellRendererText text;

        window = new Window();
        window.setTitle("Cities");

        model = new TreeStore(new DataColumn[] {
            placeName = new DataColumnString(),
        });

        parent = model.appendRow();
        model.setValue(parent, placeName, "Europe");

        child = model.appendChild(parent);
        model.setValue(child, placeName, "London");

        child = model.appendChild(parent);
        model.setValue(child, placeName, "Paris");

        child = model.appendChild(parent);
        model.setValue(child, placeName, "Berlin");

        parent = model.appendRow();
        model.setValue(parent, placeName, "Asia");

        child = model.appendChild(parent);
        model.setValue(child, placeName, "Singapore");

        child = model.appendChild(parent);
        model.setValue(child, placeName, "Calcutta");

        child = model.appendChild(parent);
        model.setValue(child, placeName, "...");

        view = new TreeView(model);

        vertical = view.appendColumn();
        text = new CellRendererText(vertical);
        text.setText(placeName);

        view.setHeadersVisible(false);
        view.expandAll();
        view.getSelection().setMode(SelectionMode.NONE);
        view.setCanFocus(false);
        view.setRulesHint(false);

        window.add(view);
        window.setDecorated(false);
        window.setBorderWidth(2);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotTreeStore());
        Gtk.main();
    }
}
