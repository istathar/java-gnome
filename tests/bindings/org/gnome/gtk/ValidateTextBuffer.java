/*
 * ValidateTextBuffer.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.pango.Scale;
import org.gnome.pango.Underline;

/**
 * @author Andrew Cowie
 */
public class ValidateTextBuffer extends TestCaseGtk
{
    public final void testAddingTagsToTextTagTable() {
        final TextTagTable table;
        final TextTag anon, bold1, italic;

        table = new TextTagTable();

        // shouldn't crash
        anon = new TextTag();

        // neither should this
        table.add(anon);

        bold1 = new TextTag();
        table.add(bold1);

        // but this will without the extra defences we've added
        try {
            table.add(bold1);
            fail("Should have thrown");
        } catch (IllegalStateException ise) {
            // good
        }

        // adding another named tag is no problem
        italic = new TextTag();
        table.add(italic);
    }

    /*
     * This is here so that we can test the code paths through GValue for
     * properties of type double. Repeating the setter and/or getter here for
     * the scale property armours us against public API changes to TextTag.
     */
    private static class LocalTextTag extends TextTag
    {
        LocalTextTag() {
            super();
        }

        public void setScale(double scale) {
            setPropertyDouble("scale", scale);
        }

        public double getScale() {
            return getPropertyDouble("scale");
        }

        public int getLeftMargin() {
            return getPropertyInteger("left-margin");
        }

        public Underline getUnderline() {
            return (Underline) getPropertyEnum("underline");
        }

    }

    public final void testScalePropertyInternalCodePaths() {
        final LocalTextTag tag;

        tag = new LocalTextTag();

        tag.setScale(10.2d);
        assertEquals(10.2d, tag.getScale(), 0.0001);
    }

    public final void testStronglyTypedScaleProperty() {
        final LocalTextTag tag;

        tag = new LocalTextTag();

        tag.setScale(Scale.LARGE);

        /*
         * At time of writing there is no Plumbing infrastructure to go from
         * Class,double to DoubleConstant, so we use the getter from the
         * LocalTextTag hack again to extract the property for testing.
         */
        assertEquals(GtkTextTagOverride.valueOf(Scale.LARGE), tag.getScale(), 0.0001);
    }

    public final void testUnderlineProperty() {
        final LocalTextTag tag;

        tag = new LocalTextTag();

        tag.setLeftMargin(42);
        assertEquals(42, tag.getLeftMargin());

        tag.setUnderline(Underline.SINGLE);
        assertEquals(Underline.SINGLE, tag.getUnderline());
    }

    public final void testSimpleGetAndSet() {
        final TextBuffer buf;
        final TextIter start, end;

        buf = new TextBuffer(new TextTagTable());

        buf.setText("Hello\nWorld!");
        start = buf.getStartIter();
        end = buf.getEndIter();
        assertEquals(0, start.getLine());
        assertEquals(0, start.getLineOffset());
        assertEquals(1, end.getLine());
        assertEquals(6, end.getLineOffset());

        assertEquals("Hello\nWorld!", buf.getText(start, end, true));

        start.setLine(1);
        assertEquals("World!", buf.getText(start, end, true));
    }

    public final void testBasicTextInsertion() {
        final TextBuffer buf;
        TextIter pointer;
        TextMark selectionBound, insert;

        buf = new TextBuffer(new TextTagTable());

        buf.setText("This test");
        pointer = buf.getStartIter();

        pointer.setOffset(5);
        buf.insert(pointer, "is a ");
        assertEquals(buf.getText(), "This is a test");

        /*
         * Test the assumption that the cursor is at the end of the TextBuffer
         * by default.
         */

        buf.insertAtCursor(" of the ");
        assertEquals(buf.getText(), "This is a test of the ");

        /*
         * And, test the getSelectionBounds() bug.
         */
        buf.selectRange(buf.getStartIter(), buf.getEndIter());

        selectionBound = buf.getSelectionBound();
        insert = buf.getInsert();
        assertEquals(0, buf.getIterAtMark(selectionBound).getOffset());
        assertEquals("This is a test of the ".length(), buf.getIterAtMark(insert).getOffset());

        pointer = buf.getEndIter();
        buf.insert(pointer, "Emergency Broadcast System");
        assertEquals(buf.getText(), "This is a test of the " + "Emergency Broadcast System");
    }
}
