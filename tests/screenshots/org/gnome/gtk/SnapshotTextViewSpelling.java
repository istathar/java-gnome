/*
 * SnapshotTextViewSpelling.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import static org.gnome.gtk.PolicyType.ALWAYS;
import static org.gnome.gtk.PolicyType.NEVER;
import static org.gnome.gtk.WrapMode.WORD;

/**
 * Based on SnapshotTextView by Andrew Cowie
 * 
 * @author Andrew Cowie
 * @author Serkan Kaba
 */
public class SnapshotTextViewSpelling extends Snapshot
{
    public SnapshotTextViewSpelling() {
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
