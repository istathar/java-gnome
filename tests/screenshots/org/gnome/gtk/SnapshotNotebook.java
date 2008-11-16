/*
 * SnapshotNotebook.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
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
public class SnapshotNotebook extends Snapshot
{
    public SnapshotNotebook() {
        super(Notebook.class);
        final Notebook book;
        final Label firstPage, firstTab;
        final Label secondPage, secondTab;
        final Label thirdPage, thirdTab;

        book = new Notebook();

        firstPage = new Label("This is the first page");
        firstTab = new Label("One");
        book.appendPage(firstPage, firstTab);

        secondPage = new Label("");
        secondTab = new Label("Two");
        book.appendPage(secondPage, secondTab);

        thirdPage = new Label("");
        thirdTab = new Label("Three");
        book.appendPage(thirdPage, thirdTab);

        window = new Window();
        window.setDecorated(true);
        window.setDefaultSize(200, 200);
        window.add(book);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotNotebook());
        Gtk.main();
    }
}
