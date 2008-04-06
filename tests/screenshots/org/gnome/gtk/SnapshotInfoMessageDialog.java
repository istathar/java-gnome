/*
 * ScreenshotInfoMessageDialog.java
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
public class SnapshotInfoMessageDialog extends SnapshotDialog
{
    public SnapshotInfoMessageDialog() {
        super(InfoMessageDialog.class);

        window = new InfoMessageDialog(null, "You should feel privileged", "He hardly lands for anyone.");
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotInfoMessageDialog());
        Gtk.main();
    }
}
