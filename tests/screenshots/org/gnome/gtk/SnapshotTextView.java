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
