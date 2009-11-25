/*
 * ValidateUnicode.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test high range unicode characters through java-gnome layers, in this case
 * using TextBuffer [something we know uses g_utf8_validate()].
 * 
 * @author Andrew Cowie
 */
/*
 * DANGER: Eclipse seems to be buggy at editing lines with supplemental
 * characters in them. This file is UTF-8, so the content is correct. And the
 * display in Eclipse is correct. But if you try editing a line with the
 * multi-cell n character, Bad Things™ happen. Presumably Eclipse's editor is
 * making the assumption that String.length() == number of displayed
 * characters.
 */
public class ValidateUnicode extends GraphicalTestCase
{
    public final void testLatin1Supplement() {
        final TextBuffer buffer;
        TextIter pointer;

        buffer = new TextBuffer();

        /*
         * This will work, since µ is < 0xFFFF.
         */

        pointer = buffer.getIterStart();
        buffer.insert(pointer, "This character 'µ' is a micro symbol");
        assertEquals("This character '\u00b5' is a micro symbol", buffer.getText());
        buffer.setText("");
        assertEquals(0, buffer.getCharCount());
    }

    public final void testMathematicalAlphanumericSymbols() {
        final TextBuffer buffer;
        TextIter pointer;

        buffer = new TextBuffer();
        pointer = buffer.getIterStart();

        /*
         * The 𝑛 character, however, is a three-byte unicode character in
         * UTF-8, and needs to be represented as 2 characters in UTF-16.
         */

        pointer = buffer.getIterStart();
        buffer.insert(pointer, "This character '\ud835\udc5b' is an italic lower case n symbol");
        assertEquals("This character '\ud835\udc5b' is an italic lower case n symbol", buffer.getText());
        buffer.setText("");

        buffer.insertAtCursor("This character '𝑛' is an italic lower case n symbol");
        assertEquals("This character '𝑛' is an italic lower case n symbol", buffer.getText());
        buffer.setText("");

        pointer = buffer.getIterStart();
        buffer.insert(pointer, "This character '𝑛' is an italic lower case n symbol", (TextTag) null);
        assertEquals("This character '\ud835\udc5b' is an italic lower case n symbol", buffer.getText());
        buffer.setText("");

        buffer.insertAtCursor("This character '\ud835\udc5b' is an italic lower case n symbol");
        assertEquals("This character '𝑛' is an italic lower case n symbol", buffer.getText());
    }
}
