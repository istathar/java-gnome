/*
 * TextIter.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Boxed;

/**
 * A TextIter defines a position inside a {@link TextBuffer} and allows you to
 * manipulate it.
 * 
 * <p>
 * TextIters are just used for immediate operations, and are invalidated any
 * time when the TextBuffer's contents change. If you need to hold a reference
 * to a specific position, convert this TextIter into a TextMark with
 * TextBuffer's {@link TextBuffer#createMark(TextIter, boolean) createMark()}.
 * 
 * @author Stefan Prelle
 * @author Andrew Cowie
 * @since 4.0.8
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

    TextBuffer getBuffer() {
        if (buffer == null) {
            throw new IllegalStateException(
                    "\n"
                            + "Sorry, this TextIter doesn't have its internal reference to its parent TextBuffer set");
        }
        return buffer;
    }

    protected void release() {
        GtkTextIter.free(this);
    }

    /**
     * Creates an independent copy of the iterator.
     * 
     * <p>
     * <i>This is basically the same as a clone() on normal Java object would
     * do, but in this case is passed on to the native GTK layer.</i>
     * 
     * @since 4.0.8
     */
    public TextIter copy() {
        return GtkTextIter.copy(this);
    }

    /**
     * Returns the number of characters into the TextBuffer that this TextIter
     * is pointing. Offset counts are <code>0</code> origin.
     * 
     * 
     * <p>
     * If you have a given offset, you can use the TextBuffer
     * {@link TextBuffer#getIter(int) getIter()} method taking an
     * <code>int</code> to convert it into an TreeIter. If you want to move
     * this TreeIter to a given offset, then call
     * {@link #setOffset(int) setOffset()}.
     * 
     * @return A character offset from the start of the TextBuffer.
     * @since 4.0.8
     */
    public int getOffset() {
        return GtkTextIter.getOffset(this);
    }

    /**
     * Change this TextIter so that it points to the given offset into the
     * underlying TextBuffer.
     * 
     * @since 4.0.8
     */
    public void setOffset(int offset) {
        GtkTextIter.setOffset(this, offset);
    }

    /**
     * Get the line number of the TextBuffer that this TextIter points at.
     * Lines in a TextBuffer are numbered from <code>0</code>.
     * 
     * <p>
     * Be aware that this is the count of <i>unwrapped</i> lines in the
     * TextBuffer, where lines are split by newline characters. Most of the
     * time TextViews are set to wrap lines and thus a single very long line
     * will be presented visually as a paragraph (there is no definition of
     * paragraph in TextBuffer terms). If you're wondering about where the
     * position (the cursor, say) is down into a TextView widget, then see
     * TextView's
     * {@link TextView#startsDisplayLine(TextIter) startsDisplayLine()} and
     * related "<code>display</code>" methods.
     * 
     * @since 4.0.8
     */
    public int getLine() {
        return GtkTextIter.getLine(this);
    }

    /**
     * Move this TextIter to the start of the given line.
     * 
     * <p>
     * If <code>num</code> is negative or larger than the number of lines in
     * the TextBuffer, it moves the iterator to the start of the last line.
     * Line numbers are <code>0</code> origin.
     * 
     * @since 4.0.8
     */
    public void setLine(int row) {
        GtkTextIter.setLine(this, row);
    }

    /**
     * Get the character offset of this TextIter, counting from the start of
     * the line. The first character on the line has line offset
     * <code>0</code>.
     * 
     * <p>
     * <i> This is, in essence, a <code>getColumn()</code> method, but
     * beware that the number of characters you are into a given line in a
     * TextBuffer will only correspond to the column position on screen if the
     * presenting TextView is not wrapping lines.</i>
     * 
     * @since 4.0.8
     */
    public int getLineOffset() {
        return GtkTextIter.getLineOffset(this);
    }

    /**
     * Move the iterator within the current TextBuffer line to the given
     * character offset.
     * 
     * @since 4.0.8
     */
    public void setLineOffset(int column) {
        GtkTextIter.setLineOffset(this, column);
    }

    /**
     * Like {@link #getLineOffset()} but not counting those characters that
     * are tagged "invisible".
     * 
     * @return Offset from start of line
     * @since 4.0.8
     */
    public int getVisibleLineOffset() {
        return GtkTextIter.getVisibleLineOffset(this);
    }

    /**
     * Move this TextIter into the current line at the given offset, but
     * skipping characters that have been marked
     * {@link TextTag#setInvisible(boolean) invisible}.
     * 
     * <p>
     * See {@link #setLineOffset(int) setLineOffset()} for the method that
     * treats all characters normally.
     * 
     * @since 4.0.8
     */
    public void setVisibleLineOffset(int column) {
        GtkTextIter.setVisibleLineOffset(this, column);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("TextIter(");
        buf.append(getLineOffset());
        buf.append(":");
        buf.append(getLine());
        buf.append(",");
        buf.append(getOffset());
        buf.append(")");
        return buf.toString();
    }

    /**
     * Get the character immediately following the position this TextIter is
     * pointing at.
     * 
     * @return A <code>char</code> value of <code>0xFFFC</code> indicates
     *         a non-character element (an embedded Pixbuf or Widget). You'll
     *         get <code>0</code> if this TextIter is already at the
     *         TextBuffer's end.
     * @since 4.0.8
     */
    public char getChar() {
        return GtkTextIter.getChar(this);
    }

    /**
     * Bump this TextIter forward to the start of the next line. As a special
     * case, if you're already pointing at the last line of the TextBuffer,
     * then you will be moved to the end of the line.
     * 
     * @since 4.0.8
     */
    /*
     * According to tests, we don't need the boolean return value.
     */
    public void forwardLine() {
        GtkTextIter.forwardLine(this);
    }
}
