/*
 * ValidateSourceView.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.sourceview;

import org.gnome.gtk.TestCaseGtk;
import org.gnome.gtk.TextTagTable;

/**
 * TestCase for the GtkSourceView library.
 * 
 * @author Stefan Schweizer
 */
public class ValidateSourceView extends TestCaseGtk
{
    public void testGetLanguage() {
        final LanguageManager manager;
        Language lang;

        manager = LanguageManager.getDefault();

        lang = manager.getLanguage("unknownID");
        assertNull(lang);

        lang = manager.getLanguage("java");
        assertEquals("java", lang.getID());
        assertEquals("Java", lang.getName());
    }

    public void testCreateSourceViewWithHighlight() {
        final SourceView view;
        final SourceBuffer buffer;
        final TextTagTable tagTable;

        tagTable = new TextTagTable();
        buffer = new SourceBuffer(tagTable);
        buffer.setLanguage(LanguageManager.getDefault().getLanguage("java"));
        view = new SourceView(buffer);

        assertNotNull(view);
        assertTrue(buffer.getHighlightSyntax());
    }
}
