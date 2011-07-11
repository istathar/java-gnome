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

/*
 * Inspired by SnapshotTextView.
 */

import static org.gnome.gtk.PolicyType.ALWAYS;
import static org.gnome.gtk.PolicyType.NEVER;
import static org.gnome.gtk.WrapMode.WORD;

/**
 * Illustrate spelling mistakes being underlined in red
 * 
 * @author Andrew Cowie
 * @author Serkan Kaba
 */
public class SnapshotTextViewSpelling extends Snapshot
{
    public SnapshotTextViewSpelling() throws UnsupportedOperationException {
        super(TextView.class, "Spelling");
        final TextView view;
        final TextBuffer buffer;
        final ScrolledWindow scroll;

        buffer = new TextBuffer();
        buffer.setText("Thiz text is fulll of misspelings.");

        view = new TextView(buffer);

        view.setWrapMode(WORD);
        view.attachSpell("en");
        scroll = new ScrolledWindow();
        scroll.setPolicy(NEVER, ALWAYS);
        scroll.add(view);

        view.setPaddingBelowParagraph(15);

        window = new Window();
        window.add(scroll);
        window.setDecorated(false);
        window.setBorderWidth(2);
        window.setDefaultSize(300, 10);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotTextViewSpelling());
        Gtk.main();
    }
}
