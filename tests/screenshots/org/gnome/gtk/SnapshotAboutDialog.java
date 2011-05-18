/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-20l1 Operational Dynamics Consulting, Pty Ltd
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
        dialog.setCopyright("Copyright \u00A9 2006-2011 Operational Dynamics Consulting, Pty Ltd and Others");
        dialog.setAuthors(new String[] {
            "Andrew Cowie <andrew@operationaldynamics.com>",
            "Srichand Pendyala <srichand.pendyala@gmail.com>",
            "Vreixo Formoso Lopes <metalpain2002@yahoo.es>",
            "Sebastian Mancke <s.mancke@tarent.de>"
        });
        dialog.setWebsite("http://java-gnome.sourceforge.net/");

        window = dialog;
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotAboutDialog());
        Gtk.main();
    }
}
