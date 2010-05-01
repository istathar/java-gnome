/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
