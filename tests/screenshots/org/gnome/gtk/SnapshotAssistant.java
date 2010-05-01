/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

/**
 * @author Stefan Prelle
 */
public class SnapshotAssistant extends Snapshot
{
    public SnapshotAssistant() {
        super(Assistant.class);

        final Assistant assi = new Assistant();
        final Entry id_tf;
        super.window = assi;

        final VBox page2 = new VBox(false, 3);
        id_tf = new Entry();
        page2.packStart(new Label("Enter any data"), false, false, 0);
        page2.packStart(new Label("The 'next' button will be enabled after\nyou hit return."), false,
                false, 0);
        page2.packEnd(id_tf, false, false, 0);
        assi.appendPage(page2);
        assi.setPageComplete(page2, true);
        assi.setPageType(page2, AssistantPageType.CONTENT);
        assi.setPageTitle(page2, "Enter data");

        // Page 3
        Label page3 = new Label("");
        assi.appendPage(page3);
        assi.setPageType(page3, AssistantPageType.CONFIRM);
        assi.setPageTitle(page3, "Done");
        assi.setPageComplete(page3, true);

    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotAssistant());
        Gtk.main();
    }
}
