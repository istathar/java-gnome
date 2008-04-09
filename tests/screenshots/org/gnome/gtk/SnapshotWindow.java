/*
 * SnapshotWindow.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
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
public class SnapshotWindow extends Snapshot
{
    public SnapshotWindow() {
        super(Window.class);

        window = new Window();
        window.setKeepAbove(true);
        window.setTitle("Window");
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotWindow());
        Gtk.main();
    }
}
