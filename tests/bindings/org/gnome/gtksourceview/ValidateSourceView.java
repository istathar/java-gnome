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
package org.gnome.gtksourceview;

import org.gnome.gtk.TestCaseGtk;

/**
 * TestCase for the GtkSourceView library.
 * 
 * @author Stefan Schweizer
 */
public class ValidateSourceView extends TestCaseGtk
{
    public void testGetLanguage() {
        final SourceLanguageManager manager;
        SourceLanguage lang;

        manager = SourceLanguageManager.getDefault();

        lang = manager.getLanguage("unknownID");
        assertNull(lang);

        lang = manager.getLanguage("java");
        assertEquals("java", lang.getID());
        assertEquals("Java", lang.getName());
    }
}
