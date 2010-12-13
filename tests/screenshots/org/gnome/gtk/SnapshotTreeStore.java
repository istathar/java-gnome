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
