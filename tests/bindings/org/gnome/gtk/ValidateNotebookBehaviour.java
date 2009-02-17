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

    public void testSwitchPage() {
        final Notebook notebook;

        notebook = new Notebook();

        notebook.connect(new Notebook.SwitchPage() {
            public void onSwitchPage(Notebook source, int pageNum) {}
        });

        /*
         * TODO incomplete. The above connection not failing is a good start,
         * but we need to add some Widgets to form 2 or more pages, then
         * programmatically switch between them to verify that the signal
         * connection worked.
         */
    }
}
