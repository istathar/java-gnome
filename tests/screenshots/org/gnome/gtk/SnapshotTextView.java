/*
 * SnapshotTextView.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.pango.Style;

import static org.gnome.gtk.PolicyType.ALWAYS;
import static org.gnome.gtk.PolicyType.NEVER;
import static org.gnome.gtk.WrapMode.WORD;
import static textview.LoremIpsum.text;

/**
 * @author Andrew Cowie
 */
public class SnapshotTextView extends Snapshot
{
    public SnapshotTextView() {
        super(TextView.class);
        final TextView view;
        final TextBuffer buffer;
        final ScrolledWindow scroll;
        final TextTag italic;
        TextIter start;

        buffer = new TextBuffer();
        view = new TextView(buffer);

        view.setWrapMode(WORD);
        scroll = new ScrolledWindow();
        scroll.setPolicy(NEVER, ALWAYS);
        scroll.add(view);

        italic = new TextTag();
        italic.setStyle(Style.ITALIC);

        start = buffer.getIterStart();
        buffer.insert(start, text, italic);

        view.setPaddingBelowParagraph(15);

        window = new Window();
        window.add(scroll);
        window.setDecorated(false);
        window.setBorderWidth(2);
        window.setDefaultSize(300, 600);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotTextView());
        Gtk.main();
    }
}
