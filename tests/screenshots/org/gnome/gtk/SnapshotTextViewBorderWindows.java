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
        left.packStart(left2);

        right = new Label("RIGHT");
        top = new Label("TOP");
        bottom = new Label("BOTTOM");

        view.setBorderWindowSize(LEFT, 50);
        view.addChildInWindow(left, TextWindowType.LEFT, 0, 0);

        view.setBorderWindowSize(RIGHT, 50);
        view.addChildInWindow(right, TextWindowType.RIGHT, 0, 0);

        view.setBorderWindowSize(TOP, 50);
        view.addChildInWindow(top, TextWindowType.TOP, 0, 0);

        view.setBorderWindowSize(BOTTOM, 50);
        view.addChildInWindow(bottom, TextWindowType.BOTTOM, 0, 0);

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
