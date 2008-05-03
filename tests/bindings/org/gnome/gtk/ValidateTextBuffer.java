/*
 * ValidateTextBuffer.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * @author Andrew Cowie
 */
public class ValidateTextBuffer extends TestCaseGtk
{
    public final void testAddingTagsToTextTagTable() {
        final TextTagTable table;
        final TextTag anon, bold1, italic, bold2;

        table = new TextTagTable();

        // shouldn't crash
        anon = new TextTag(null);

        // neither should this
        table.add(anon);

        bold1 = new TextTag("bold");
        table.add(bold1);

        // but this will without the extra defences we've added
        try {
            table.add(bold1);
            fail("Should have thrown");
        } catch (IllegalStateException ise) {
            // good
        }

        // adding another named tag is no problem
        italic = new TextTag("italics");
        table.add(italic);

        // but adding one with a name in use is bad.
        bold2 = new TextTag("bold");
        try {
            table.add(bold2);
            fail("Shouldn't be able to add a tag with a name already in this table");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }
}
