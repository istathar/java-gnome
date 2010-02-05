/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
            dialog.setFilename(new File("AUTHORS").getCanonicalPath());
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
