/*
 * ValidateNotebookBehaviour.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Evaluate the behaviour of Notebook Containers.
 */
public class ValidateNotebookBehaviour extends TestCaseGtk
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
