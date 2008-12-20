/*
 * SnapshotAssistant.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
        page2.packStart(new Label("Enter any data"));
        page2.packStart(new Label("The 'next' button will be enabled after\nyou hit return."));
        page2.packEnd(id_tf);
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
