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

import static org.gnome.gtk.PolicyType.ALWAYS;
import static org.gnome.gtk.TextWindowType.BOTTOM;
import static org.gnome.gtk.TextWindowType.LEFT;
import static org.gnome.gtk.TextWindowType.RIGHT;
import static org.gnome.gtk.TextWindowType.TOP;
import static org.gnome.gtk.WrapMode.WORD;
import static textview.LoremIpsum.text;

/**
 * @author Andrew Cowie
 */
public class SnapshotTextViewBorderWindows extends Snapshot
{
    public SnapshotTextViewBorderWindows() {
        super(TextView.class, "BorderWindows");
        final TextTagTable table;
        final TextView view;
        final TextBuffer buffer;
        final Label left2, right, top, bottom;
        final Box left;
        final ScrolledWindow scroll;

        table = new TextTagTable();
        buffer = new TextBuffer(table);
        view = new TextView(buffer);

        left2 = new Label("LEFT");
        left = new HBox(true, 0);
        left.packStart(left2, false, false, 0);

        right = new Label("RIGHT");
        top = new Label("TOP");
        bottom = new Label("BOTTOM");

        view.setBorderWindowSize(LEFT, 50);
        view.add(left, LEFT, 0, 0);

        view.setBorderWindowSize(RIGHT, 50);
        view.add(right, RIGHT, 0, 0);

        view.setBorderWindowSize(TOP, 50);
        view.add(top, TOP, 0, 0);

        view.setBorderWindowSize(BOTTOM, 50);
        view.add(bottom, BOTTOM, 0, 0);

        view.setWrapMode(WORD);
        scroll = new ScrolledWindow();
        scroll.setPolicy(ALWAYS, ALWAYS);
        scroll.add(view);

        buffer.insertAtCursor(text);

        window = new Window();
        window.add(scroll);
        window.setDecorated(false);
        window.setBorderWidth(2);
        window.setDefaultSize(400, 300);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotTextViewBorderWindows());
        Gtk.main();
    }
}
