/*
 * SnapshotButton.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * @author Andrew Cowie
 */
public class SnapshotButton extends Snapshot
{
    public SnapshotButton() {
        super(Button.class);

        final Button b;

        window = new Window();
        window.setDecorated(false);

        b = new Button(" Press Me ");

        window.add(b);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotButton());
        Gtk.main();
    }
}
