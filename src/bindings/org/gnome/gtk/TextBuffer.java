/*
 * TextBuffer.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Object;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 * 
 * @since 4.0.8
 */
public class TextBuffer extends Object
{
    protected TextBuffer(long pointer) {
        super(pointer);
    }

    /**
     * Create a new TextBuffer. Uses (or reuses) the supplied TextTagTable;
     * you can add more TextTags to it later.
     */
    public TextBuffer(TextTagTable tags) {
        super(GtkTextBuffer.createTextBuffer(tags));
    }

    /**
     * Deletes current contents of buffer, and inserts text  instead.
     *
     * @param text
     *            The text to use as the new content of the buffer.
     */
    public void setText(String text) {
        GtkTextBuffer.setText(this, text, text.length());
    }

    /**
     * Returns the text in the range start,end. Excludes undisplayed text 
     * (text marked with tags that set the invisibility attribute) if 
     * <code>includeHiddenChars</code> is <code>false</code>. Does not 
     * include characters representing embedded images, so byte and 
     * character indexes into the returned string do not correspond to byte 
     * and character indexes into the buffer.
     *
     * @param start 
     *            Start of a range
     * @param end 
     *            End of a range
     * @param includeHidden
     *            Whether to include invisible text
     * @return The text of (the range of) the buffer.
     */
    public String getText(TextIter start, TextIter end, boolean includeHidden) {
        return GtkTextBuffer.getText(this, start, end, includeHidden);
    }

    /**
     * Returns the text in the TextBuffer. This is merely a convenience 
     * function that calls {@link getText(TextIter, TextIter, boolean)} from
     * buffer start to end with <code>includeHiddenChars</code> being
     * <code>true</code>.
     * 
     * @return The complete text in the TextBuffer.
     */
    public String getText() {
        return getText(getStartIter(), getEndIter(), true);
    }

    /**
     * Marks the content as changed.
     *
     * @param modified
     *            If TRUE, the buffer is considered changed
     * @since 4.0.8
     */
    public void setModified(boolean modified) {
        GtkTextBuffer.setModified(this, modified);
    }

    /**
     * Returns TRUE if the TextBuffer content has changed since the last
     * call to <code>setModified(FALSE)</code>.
     *
     * @return TRUE, if the buffer has changed
     * @since 4.0.8
     */
    public boolean getModified() {
        return GtkTextBuffer.getModified(this);
    }

    /**
     * Returns a pointer to the beginning of the TextBuffer.
     */
    public TextIter getStartIter() {
        final TextIter iter;

        iter = new TextIter(this);

        GtkTextBuffer.getStartIter(this, iter);
        return iter;
    }

    /**
     * Returns a pointer to the end of the TextBuffer.
     */
    public TextIter getEndIter() {
        final TextIter iter;

        iter = new TextIter(this);

        GtkTextBuffer.getEndIter(this, iter);
        return iter;
    }
}
