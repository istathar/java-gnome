/*
 * ScreenshotInfoMessageDialog.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import java.io.File;
import java.io.IOException;

/**
 * @author Andrew Cowie
 */
public class SnapshotFileChooserDialog extends SnapshotDialog
{
    public SnapshotFileChooserDialog() {
        super(FileChooserDialog.class);

        final FileChooserDialog dialog;

        dialog = new FileChooserDialog("Pick a file", null, FileChooserAction.OPEN);
        try {
            dialog.setFilename(new File("/home/andrew/src/andrew/java-gnome/mainline", "AUTHORS").getCanonicalPath());
        } catch (IOException ie) {
            System.err.println("Couldn't extract the canonical path. Go figure");
            ie.printStackTrace();
            /*
             * And carry on. Not great, but if you're not the person
             * publishing the website, whatever.
             */
        }

        window = dialog;
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotFileChooserDialog());
        Gtk.main();
    }
}
