/*
 * TextBuffer.java
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

import org.gnome.glib.Object;

/** 
 * A TextBuffer serves as model for text widgets.
 *
 * <p>TODO: Most methods are untested, because of the lacking
 *       completeness of TextIter</p>
 * 
 * {@see TextView}
 * 
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class TextBuffer extends Object
{
    protected TextBuffer(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a new TextBuffer with a default TagTable
     */
    public TextBuffer() {
        super(GtkTextBuffer.createTextBuffer(null));
    }

    /**
     * Sets the complete text of the TextBuffer
     */
    public void setText(String text) {
        GtkTextBuffer.setText(this, text, -1);
    }

    /**
     * Returns a TextIter positioned at the start of the TextBuffer
     */
    public TextIter getStartIter() {
        TextIter startIter = new TextIter();
        GtkTextBuffer.getStartIter(this, startIter);
        return startIter;
    }

    /**
     * Returns a TextIter positioned at the start of the TextBuffer
     */
    public TextIter getEndIter() {
        TextIter endIter = new TextIter();
        GtkTextBuffer.getEndIter(this, endIter);
        return endIter;
    }

    /**
     * Returns the text between [startIter,endIter).
     *
     * @param startIter The iterator specifying the begin (inclusive) of the text to return
     * @param endIter The iterator specifying the end (exclusive) of the text to return
     * @param includeHiddenChars <code>true</code>, if text marked as invisible should not be included, false otherwise
     */
    public String getText(TextIter startIter, TextIter endIter, boolean includeHiddenChars) {
        return GtkTextBuffer.getText(this, startIter, endIter, includeHiddenChars);
    }

    /**
     * Returns the text complete text of the TextBuffer.
     *
     */
    public String getText() {
        return GtkTextBuffer.getText(this, getStartIter(), getEndIter(), false);
    }

    /**
     * Deletes the text between the iterators.
     * As a side affect, startIter and endIter will point to the location
     * where the text was deleted, after calling the method.
     * 
     * <p>All other iterators will be invalid after calling this method</p>
     *
     * @param startIter The iterator specifying the begin (inclusive) of the text
     * @param endIter The iterator specifying the end (exclusive) of the text
     */
    public void delete(TextIter startIter, TextIter endIter) {
        GtkTextBuffer.delete(this, startIter, endIter);
    }

    /**
     * Insert the supplied text at the iterator position.
     * As a side affect, <code>iter</code> will point to the
     * end of the inserted text, after calling the method.
     * 
     * <p>All other iterators will be invalid after calling this method</p>
     *
     * @param iter position to insert the text
     * @param text the text to insert
     */
    public void insert(TextIter iter, String text) {
        GtkTextBuffer.insert(this, iter, text, -1);
    }
}
