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
 * Evaluate the behaviour of Notebook Containers.
 */
public class ValidateNotebookBehaviour extends GraphicalTestCase
{
    public final void testNotebookDecorations() {
        final Notebook book;

        book = new Notebook();

        /*
         * show tabs
         */
        assertEquals(true, GtkNotebook.getShowTabs(book));

        book.setShowTabs(false);
        assertEquals(false, GtkNotebook.getShowTabs(book));

        book.setShowTabs(true);
        assertEquals(true, GtkNotebook.getShowTabs(book));

        /*
         * show border
         */

        assertEquals(true, GtkNotebook.getShowBorder(book));

        book.setShowBorder(false);
        assertEquals(false, GtkNotebook.getShowBorder(book));

        book.setShowBorder(true);
        assertEquals(true, GtkNotebook.getShowBorder(book));
    }

    private int page = -1;

    public void testSwitchPage() {
        final Notebook notebook;

        notebook = new Notebook();

        notebook.appendPage(new Button(), new Label("Label 1"));
        notebook.appendPage(new Button(), new Label("Label 2"));

        assertEquals(2, notebook.getPageCount());

        notebook.connect(new Notebook.SwitchPage() {
            public void onSwitchPage(Notebook source, int pageNum) {
                page = pageNum;
            }
        });

        notebook.setCurrentPage(0);
        assertEquals(0, page);
        notebook.setCurrentPage(1);
        assertEquals(1, page);
    }
}
