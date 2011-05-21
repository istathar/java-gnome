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

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.gnome.gdk.Pixbuf;
import org.gnome.pango.Scale;
import org.gnome.pango.Underline;
import org.gnome.pango.Weight;

/**
 * @author Andrew Cowie
 * @author Stefan Prelle
 */
public class ValidateTextBuffer extends GraphicalTestCase
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

        tag.setMarginLeft(42);
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

    public final void testTextMarkToTextIterConversion() {
        final TextBuffer buf;
        TextIter start, end;
        TextMark selectionBound, insert;

        buf = new TextBuffer(new TextTagTable());

        buf.setText("Hello World");
        start = buf.getIterStart();

        start.setOffset(5);
        end = start.copy();
        end.setOffset(8);

        buf.selectRange(start, end);

        selectionBound = buf.getSelectionBound();
        insert = buf.getInsert();

        assertEquals(5, selectionBound.getIter().getOffset());
        assertEquals(8, insert.getIter().getOffset());
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

        assertSame(TextBuffer.getDefaultTable(), GtkTextBuffer.getTagTable(buf));

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

    public void testTagsAtLocation() {
        final TextBuffer buf;
        final TextTag blue;
        TextIter pointer;

        buf = new TextBuffer();

        blue = new TextTag();
        blue.setForeground("blue");

        pointer = buf.getIterStart();

        buf.insert(pointer, "Hello ");
        buf.insert(pointer, "World", blue);

        pointer = buf.getIterStart();
        assertEquals(0, pointer.getTags().length);

        assertTrue(pointer.forwardChars(8));
        assertTrue(pointer.insideWord());
        assertEquals(1, pointer.getTags().length);
        assertSame(blue, pointer.getTags()[0]);
    }

    /*
     * Validate the values of various Pango constants, mostly to ensure our
     * documentation is accurate.
     */
    public final void testPangoWeight() {
        assertEquals(400, GtkTextTagOverride.valueOf(Weight.NORMAL));
        assertEquals(700, GtkTextTagOverride.valueOf(Weight.BOLD));
    }

    /**
     * Verify that the checkTag() method in TextBuffer works.
     */
    public final void testApplyTextTagCheckTable() {
        final TextTagTable table;
        final TextBuffer buffer;
        final TextTag noarg, legal;
        final TextIter pointer;

        table = new TextTagTable();
        buffer = new TextBuffer(table);

        noarg = new TextTag();
        legal = new TextTag(table);

        pointer = buffer.getIterStart();

        buffer.insert(pointer, "Hello");

        try {
            buffer.insert(pointer, " World", noarg);
            fail();
        } catch (IllegalArgumentException iae) {
            // good
        }

        buffer.insert(pointer, " World", legal);
        // good

        try {
            buffer.applyTag(noarg, buffer.getIterStart(), buffer.getIterEnd());
            fail();
        } catch (IllegalArgumentException iae) {
            // good
        }
        buffer.applyTag(legal, buffer.getIterStart(), buffer.getIterEnd());
        // good
    }

    /**
     * Verify that the checkTag() method in TextBuffer works, this time with
     * TextTags using our default table.
     */
    public final void testApplyTextTagCheckNoArg() {
        final TextTagTable table;
        final TextBuffer buffer;
        final TextTag noarg, illegal;
        final TextIter pointer;

        /*
         * Now the reverse - we construct without a TextTagTable.
         */

        buffer = new TextBuffer();

        noarg = new TextTag();

        table = new TextTagTable();
        illegal = new TextTag(table);

        pointer = buffer.getIterStart();

        buffer.insert(pointer, "Hello");

        buffer.insert(pointer, " World", noarg);
        try {
            buffer.insert(pointer, " World", illegal);
            fail();
        } catch (IllegalArgumentException iae) {
            // good
        }

        buffer.applyTag(noarg, buffer.getIterStart(), buffer.getIterEnd());
        try {
            buffer.applyTag(illegal, buffer.getIterStart(), buffer.getIterEnd());
            fail();
        } catch (IllegalArgumentException iae) {
            // good
        }
    }

    public final void testIterationOverCharacters() {
        final TextBuffer buffer;
        TextIter pointer;
        int i;
        StringBuilder str;

        /*
         * Put in 5 characters
         */

        buffer = new TextBuffer();
        pointer = buffer.getIterStart();
        buffer.insert(pointer, "Hello");

        /*
         * Iterate over it. Should reach a count of 5.
         */

        pointer = buffer.getIterStart();
        i = 0;
        str = new StringBuilder();

        do {
            i++;
            str = str.appendCodePoint(pointer.getChar());
        } while (pointer.forwardChar());

        assertEquals(5, i);
        assertEquals("Hello", str.toString());
    }

    public final void testInsertWithMultipleTags() {
        final TextBuffer buffer;
        final TextTag italic, bold, mono;
        TextIter start;

        buffer = new TextBuffer();

        italic = new TextTag();
        bold = new TextTag();
        mono = new TextTag();

        start = buffer.getIterStart();

        buffer.insert(start, "Hello", new TextTag[] {
            bold,
            italic
        });

        start = buffer.getIterStart();
        assertEquals(2, start.getTags().length);

        ArrayList<TextTag> list;
        list = new ArrayList<TextTag>(3);
        list.add(italic);
        list.add(bold);
        list.add(mono);

        buffer.insert(start, "World", list);

        start = buffer.getIterStart();
        assertEquals(3, start.getTags().length);
    }

    private int offset = -1;

    public final void testReactingToCursorPositionChanges() {
        final TextBuffer buffer;
        final TextIter pointer;

        buffer = new TextBuffer();

        assertEquals(-1, offset);
        offset = buffer.getCursorPosition();
        assertEquals(0, offset);

        buffer.connect(new TextBuffer.NotifyCursorPosition() {
            public void onNotifyCursorPosition(TextBuffer source) {
                offset = buffer.getCursorPosition();
            }
        });

        pointer = buffer.getIterStart();
        buffer.insert(pointer, "Hello World");

        assertEquals(11, offset);
    }

    /*
     * Not much of a test, but at least it exercises the code path to ensure
     * that Pango.SCALE is correctly accessed by TextTag.
     */
    public final void testCrossPackageConstantAccess() throws ClassNotFoundException, SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        final TextTag tag;
        Class<?> cls;
        Field fld;
        double scale1, scale2;

        tag = new TextTag();
        tag.setRise(4.5);

        cls = Class.forName("org.gnome.gtk.TextTag");
        fld = cls.getDeclaredField("SCALE");
        fld.setAccessible(true);
        scale1 = fld.getDouble(tag);

        cls = Class.forName("org.gnome.pango.Pango");
        fld = cls.getDeclaredField("SCALE");
        fld.setAccessible(true);
        scale2 = fld.getDouble(tag);

        assertEquals(scale2, scale1);
    }
}
