/*
 * ScreenshotAboutDialog.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Version;

/**
 * @author Andrew Cowie
 */
public class SnapshotAboutDialog extends SnapshotDialog
{
    public SnapshotAboutDialog() {
        super(AboutDialog.class);
        final AboutDialog dialog;

        dialog = new AboutDialog();

        dialog.setProgramName("java-gnome");
        dialog.setVersion(Version.getVersion());
        dialog.setComments("The library allowing you to write outstanding GNOME applications with the GTK widget toolkit, from Java!");
        dialog.setCopyright("Copyright \u00A9 2006-2008 Operational Dynamics Consulting Pty Ltd, and Others");
        dialog.setAuthors(new String[] {
                "Andrew Cowie <andrew@operationaldynamics.com>",
                "Srichand Pendyala <srichand.pendyala@gmail.com>",
                "Vreixo Formoso Lopes <metalpain2002@yahoo.es>",
                "Sebastian Mancke <s.mancke@tarent.de>"
        });

        window = dialog;
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotAboutDialog());
        Gtk.main();
    }
}
