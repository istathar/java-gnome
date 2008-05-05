/*
 * TextIter.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Boxed;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public final class TextIter extends Boxed
{
    private TextBuffer buffer;

    protected TextIter(long pointer) {
        super(pointer);
    }

    /**
     * Allocate a blank TextIter structure. This is done by declaring one
     * locally, copying it, and returning the pointer to the copy.
     * 
     * <p>
     * <b>For use by bindings hackers only!</b>
     */
    TextIter(TextBuffer buffer) {
        super(GtkTextIterOverride.createTextIter());

        this.buffer = buffer;
    }

    void setBuffer(TextBuffer buffer) {
        this.buffer = buffer;
    }

    /**
     * Returns the {@link TextBuffer} this iterator is associated with.
     * @return
     */
    TextBuffer getBuffer() {
        if (buffer == null) {
            throw new IllegalStateException(
            "\nSorry, this TextIter doesn't have its internal reference to its parent TextBuffer set");
        }
        return buffer;
    }

    protected void release() {
        GtkTextIter.free(this);
    }


    /**
     * Creates an independent copy of the iterator. Basically the same
     * a clone() on normal Java Objects would do, but in this case passed
     * on to the native GTK layer.
     */
    public TextIter copy() {
        return GtkTextIter.copy(this);
    }


    /**
     * Returns the character offset of an iterator. Each character in a 
     * TextBuffer has an offset, starting with 0 for the first character in 
     * the buffer. Use {@link TextBuffer.getIterAtOffset(int) getIterAtOffset()} 
     * to convert an offset back into an iterator.
     * 
     * @return A character offset from the start of the TextBuffer.
     */
    public int getOffset() {
        return GtkTextIter.getOffset(this);
    }

    /**
     * Sets the iterator to a character offset within the entire TextBuffer,
     * starting with 0.
     * 
     * @param charOffset
     *          A character number within the buffer. 
     */
    public void setOffset(int charOffset) {
        GtkTextIter.setOffset(this, charOffset);
    }

    /**
     * Returns the line number containing the iterator. Lines in a TextBuffer
     * are numbered beginning with 0 for the first line in the buffer.
     * 
     * @return A line number
     */
    public int getLine() {
        return GtkTextIter.getLine(this);
    }

    /**
     * Moves the iterator to the start of the given line. If _lineNumber_ is
     * negative or larger than the number of lines in the TextBuffer, it
     * moves the iterator to the start of the last line in the TextBuffer.
     * 
     * @param lineNumber
     *          Line number (counted from 0)
     */
    public void setLine(int lineNumber) {
        GtkTextIter.setLine(this, lineNumber);
    }

    /**
     * Returns the character offset of the iterator, counting from the start
     * of a newline-terminated line. The first character on the line has 
     * offset 0. So basically this is a _getColumn()_ function.
     * 
     * @return Offset from start of line
     */
    public int getLineOffset() {
        return GtkTextIter.getLineOffset(this);        
    }

    //------------------------------------------------------
    /**
     * Moves the iterator within a line, to a new character (not byte) 
     * offset. The given character offset must be less than or equal to the 
     * number of characters in the line; if equal, the iterator moves to the 
     * start of the next line. 
     * 
     * @param column
     *          A character offset relative to the start of iter's current 
     *          line 
     */
    public void setLineOffset(int column) {
        GtkTextIter.setLineOffset(this, column);
    }
}
