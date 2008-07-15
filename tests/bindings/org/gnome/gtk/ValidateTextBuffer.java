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

import java.io.FileNotFoundException;

import org.gnome.gdk.Pixbuf;
import org.gnome.pango.Scale;
import org.gnome.pango.Underline;
import org.gnome.pango.Weight;

/**
 * @author Andrew Cowie
 * @author Stefan Prelle
 */
public class ValidateTextBuffer extends TestCaseGtk
{
    public final void testTagsAddedToTextTagTableAutomatically() {
        final TextTagTable table;
        final TextTag anon, bold, italic;

        table = new TextTagTable();

        // shouldn't crash
        anon = new TextTag(table);

        bold = new TextTag(table);

        // adding another named tag is no problem
        italic = new TextTag(table);

        assertEquals(3, GtkTextTagTable.getSize(table));

        // avoid warnings
        anon.getClass();
        bold.getClass();
        italic.getClass();
    }

    /*
     * This is here so that we can test the code paths through GValue for
     * properties of type double. Repeating the setter and/or getter here for
     * the scale property armours us against public API changes to TextTag.
     */
    private static class LocalTextTag extends TextTag
    {
        LocalTextTag() {
            super(new TextTagTable());
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
        start = buf.getIterStart();
        end = buf.getIterEnd();
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
        pointer = buf.getIterStart();

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
        buf.selectRange(buf.getIterStart(), buf.getIterEnd());

        selectionBound = buf.getSelectionBound();
        insert = buf.getInsert();
        assertEquals(0, buf.getIter(selectionBound).getOffset());
        assertEquals("This is a test of the ".length(), buf.getIter(insert).getOffset());

        pointer = buf.getIterEnd();
        buf.insert(pointer, "Emergency Broadcast System");
        assertEquals(buf.getText(), "This is a test of the " + "Emergency Broadcast System");
    }

    public void testIterFromOffset() {
        final TextBuffer buf;
        TextIter pointer, other;

        buf = new TextBuffer(new TextTagTable());

        pointer = buf.getIterEnd();
        assertEquals(0, pointer.getOffset());

        buf.insertAtCursor("I hate spam");
        pointer = buf.getIter(7);
        other = pointer.copy();
        other.forwardLine();
        assertEquals(11, other.getOffset());
    }

    public void testEndOfBuffer() {
        final TextBuffer buf;
        TextIter pointer;

        buf = new TextBuffer(new TextTagTable());

        pointer = buf.getIterStart();

        buf.insertAtCursor("I need a bunch of lines\n");
        buf.insertAtCursor("This is the second line\n");
        buf.insertAtCursor("Three should be enough"); // note lack of \n

        pointer = buf.getIterStart();

        assertEquals(0, pointer.getLine());
        pointer.forwardLine();
        assertEquals(1, pointer.getLine());
        pointer.forwardLine();
        assertEquals(2, pointer.getLine());

        /*
         * Last line, should be at beginning of line according to docs. Then,
         * calling forwardLine() should bump us to the end of the last line.
         */

        assertEquals(0, pointer.getLineOffset());
        pointer.forwardLine();
        assertEquals(22, pointer.getLineOffset());

        assertEquals(buf.getIterEnd().getOffset(), pointer.getOffset());

        /*
         * Now, the question of the return value. At present we've made the
         * signature void, because we can't figure out what we'd need it for.
         * Does this error or invalidate the TreeIter?
         */

        pointer.forwardLine();
        assertEquals(22, pointer.getLineOffset());

        /*
         * Apparently not. void it is.
         */
    }

    public void testNonPrintable() {
        final TextBuffer buf;
        TextIter pointer;

        buf = new TextBuffer(new TextTagTable());

        pointer = buf.getIterStart();

        buf.insertAtCursor("I need a bunch of lines\n");
        buf.insertAtCursor("This is the second line\n");
        buf.insertAtCursor("and some non-ASCII: \u00e4\u00f6\u00fc\u00df");

        pointer = buf.getIterStart();
        pointer.forwardLine();

        Pixbuf pixbuf = null;
        try {
            pixbuf = new Pixbuf("src/bindings/java-gnome_Icon.png");
            buf.insert(pointer, pixbuf);
        } catch (FileNotFoundException e) {
            fail("Could not open image");
        }

        /*
         * Assert that getChar recognizes non-character values and that moving
         * forward and backward over such elements works
         */
        assertEquals('T', pointer.getChar());
        pointer.backwardChar();
        assertEquals(TextBuffer.OBJECT_REPLACEMENT_CHARACTER, pointer.getChar());

        /*
         * The image returned by getPixbuf() should be identical.
         */
        Pixbuf check = pointer.getPixbuf();
        assertNotNull(check);
        assertEquals(pixbuf, check);
        assertSame(pixbuf, check);

        // And where no image is, it should be null
        pointer.forwardChar();
        assertNull(pointer.getPixbuf());
    }

    public void testUnicodeChars() {
        final TextBuffer buf;
        TextIter pointer;

        buf = new TextBuffer(new TextTagTable());

        /*
         * ASCII characters only. In UTF-8 each character uses 1 byte (but we
         * are no longer assessing bytes vs characters)
         */
        buf.setText("abcd");
        pointer = buf.getIterStart();
        assertEquals(4, pointer.getCharsInLine());
        assertEquals(4, buf.getCharCount());

        /*
         * Now some more complicated Unicode. This represents äöüß.
         */
        final String COMPLEX = "Some umlauts: \u00e4\u00f6\u00fc\u00df";

        buf.setText(COMPLEX);
        assertEquals(COMPLEX, buf.getText());

        pointer = buf.getIterStart();
        assertEquals(18, COMPLEX.length());
        assertEquals(18, pointer.getCharsInLine());
        assertEquals(18, buf.getCharCount());
    }

    public void testEditability() {
        final TextBuffer buf;
        final TextTagTable table;
        TextIter start, end;
        TextTag nonEdit;

        table = new TextTagTable();
        nonEdit = new TextTag(table);
        nonEdit.setEditable(false);

        buf = new TextBuffer(table);
        buf.insertAtCursor("I need a bunch of lines\n");
        buf.insertAtCursor("This is the second line\n");

        start = buf.getIter(2);
        end = buf.getIter(6);
        buf.applyTag(nonEdit, start, end);

        // start points to begin of a non-editable block
        assertEquals(false, start.isEditable(true));
        // end points to end of non-editable block, which means that
        // it is editable
        assertEquals(true, end.isEditable(true));

        // Now the other way around
        nonEdit.setEditable(true);
        // start points to begin of an editable block
        assertEquals(true, start.isEditable(false));
        // end points to end of an editable block, which means that
        // it is non-editable
        assertEquals(false, end.isEditable(false));
    }

    public void testWordBoundaries() {
        final TextBuffer buf;
        TextIter pos;

        buf = new TextBuffer(new TextTagTable());
        buf.insertAtCursor("  Give.  123 \n");

        pos = buf.getIter(0);
        assertEquals(false, pos.startsWord());
        assertEquals(false, pos.insideWord());
        assertEquals(false, pos.endsWord());

        pos = buf.getIter(2);
        assertEquals('G', pos.getChar());
        assertEquals(true, pos.startsWord());
        assertEquals(true, pos.insideWord());
        assertEquals(false, pos.endsWord());
        assertEquals(true, pos.startsSentence());

        pos = buf.getIter(3);
        assertEquals('i', pos.getChar());
        assertEquals(false, pos.startsWord());
        assertEquals(true, pos.insideWord());
        assertEquals(false, pos.endsWord());
        assertEquals(false, pos.startsSentence());
        assertEquals(false, pos.endsSentence());

        pos = buf.getIter(5);
        assertEquals('e', pos.getChar());
        assertEquals(false, pos.startsWord());
        assertEquals(true, pos.insideWord());
        assertEquals(false, pos.endsWord());
        assertEquals(false, pos.startsSentence());
        assertEquals(false, pos.endsSentence());

        pos = buf.getIter(6);
        assertEquals('.', pos.getChar());
        assertEquals(false, pos.startsWord());
        assertEquals(false, pos.insideWord());
        assertEquals(true, pos.endsWord());
        assertEquals(false, pos.endsSentence());

        pos = buf.getIter(7);
        assertEquals(true, pos.endsSentence());
    }

    public void testCharCounts() {
        final TextBuffer buf;
        TextIter pointer;

        buf = new TextBuffer(new TextTagTable());

        buf.setText("hello");
        pointer = buf.getIterStart();
        assertEquals(5, pointer.getCharsInLine());

        buf.setText("hello\n");
        pointer = buf.getIterStart();
        assertEquals(6, pointer.getCharsInLine());

        pointer = buf.getIter(2);
        Pixbuf pixbuf = null;
        try {
            pixbuf = new Pixbuf("src/bindings/java-gnome_Icon.png");
            buf.insert(pointer, pixbuf);
        } catch (FileNotFoundException e) {
            fail("Could not open image");
        }
        assertEquals(7, pointer.getCharsInLine());
    }

    public final void testImplicitTextTagTable() {
        final TextBuffer buf;
        final TextTag bold;
        TextIter pointer;
        final TextTag[] tags;

        /*
         * Does no-arg TextBuffer constructor exist and work? This will
         * initialize default text tag table...
         */

        buf = new TextBuffer();

        /*
         * Well, did it?
         */

        assertSame(TextTagTable.getDefaultTable(), GtkTextBuffer.getTagTable(buf));

        /*
         * Does no-arg TextTag constructor exist and work?
         */

        bold = new TextTag();
        bold.setWeight(Weight.BOLD);

        pointer = buf.getIterStart();
        buf.insert(pointer, "Be bold!", bold);

        pointer = buf.getIter(4);
        tags = pointer.getTags();

        assertEquals(1, tags.length);
        assertSame(bold, tags[0]);
    }
}
