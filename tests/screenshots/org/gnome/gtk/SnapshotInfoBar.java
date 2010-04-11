/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd
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
 * @author Guillaume Mazoyer
 */
public class SnapshotInfoBar extends Snapshot
{
    public SnapshotInfoBar() {
        super(InfoBar.class);

        final InfoBar bar;
        final Label label;

        window = new Window();
        window.setDecorated(false);
        window.setBorderWidth(6);

        bar = new InfoBar();

        label = new Label("You have been disconnected.\nClick on the reconnect button to go online.");
        bar.add(label);

        bar.addButton("Reconnect", ResponseType.OK);
        bar.addButton(Stock.QUIT, ResponseType.DELETE_EVENT);

        bar.setMessageType(MessageType.WARNING);

        window.add(bar);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotInfoBar());
        Gtk.main();
    }
}
