/*
 * TextBuffer.java
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

import org.gnome.glib.Object;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * TODO
 * 
 * @author Andrew Cowie
 * @author Stefan Prelle
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
     * 
     * @since 4.0.8
     */
    public TextBuffer(TextTagTable tags) {
        super(GtkTextBuffer.createTextBuffer(tags));
    }

    /**
     * Replace the entire current contents of the TextBuffer.
     * 
     * @since 4.0.8
     */
    public void setText(String text) {
        GtkTextBuffer.setText(this, text, text.length());
    }

    /**
     * Returns the text in the range start,end. Excludes undisplayed text
     * (text marked with tags that set the invisibility attribute) if
     * <code>includeHiddenChars</code> is <code>false</code>. Does not
     * include characters representing embedded images, so byte and character
     * indexes into the returned string do not correspond to byte and
     * character indexes into the buffer.
     * 
     * @param start
     *            Start of a range
     * @param end
     *            End of a range
     * @param includeHidden
     *            Whether to include invisible text
     * @return The text of (the range of) the buffer.
     * @since 4.0.8
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
     * @since 4.0.8
     */
    public String getText() {
        return getText(getStartIter(), getEndIter(), true);
    }

    /**
     * Marks the content as changed. This is primarly used to <i>unset</i>
     * this property, making it <code>false</code>. See
     * {@link #getModified() getModified()} for details.
     * 
     * @since 4.0.8
     */
    public void setModified(boolean modified) {
        GtkTextBuffer.setModified(this, modified);
    }

    /**
     * Find out if the TextBuffer's content has changed. This is actually
     * defined as "has the TextBuffer changed since the last call to
     * {@link #setModified(boolean) setModified(false)}"; operations which
     * change the TextBuffer will toggle this property, and you can of course
     * manually set it with {@link #setModified(boolean) setModified(true)}.
     * 
     * <p>
     * This can be used to record whether a file being edited has been
     * modified and is not yet saved to disk (although most applications would
     * probably track that state more robustly, this can at least feed
     * information into that process).
     * 
     * @since 4.0.8
     */
    public boolean getModified() {
        return GtkTextBuffer.getModified(this);
    }

    /**
     * Returns a pointer to the beginning of the TextBuffer.
     * 
     * @since 4.0.8
     */
    public TextIter getStartIter() {
        final TextIter iter;

        iter = new TextIter(this);

        GtkTextBuffer.getStartIter(this, iter);

        return iter;
    }

    /**
     * Returns a pointer to the end of the TextBuffer.
     * 
     * @since 4.0.8
     */
    public TextIter getEndIter() {
        final TextIter iter;

        iter = new TextIter(this);

        GtkTextBuffer.getEndIter(this, iter);

        return iter;
    }

    /**
     * Create a new TextMark at the position of the supplied TextIter.
     * 
     * <p>
     * The <code>leftGravity</code> parameter is interesting. FIXME
     * 
     * @since 4.0.8
     */
    /*
     * TODO should we remove exposure of the mark_name?
     */
    public TextMark createMark(String name, TextIter where, boolean leftGravity) {
        return GtkTextBuffer.createMark(this, name, where, leftGravity);
    }
}
