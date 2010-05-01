/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.sourceview;

import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.TextTagTable;

/**
 * TestCase for the GtkSourceView library.
 * 
 * @author Stefan Schweizer
 */
public class ValidateSourceView extends GraphicalTestCase
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
