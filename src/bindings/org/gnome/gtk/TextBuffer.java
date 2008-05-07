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
     * function that calls {@link #getText(TextIter, TextIter, boolean)} from
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
     * Marks the content as changed. This is primarily used to <i>unset</i>
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
     */
    /*
     * TODO should we remove exposure of the mark_name? TODO (SP) I don't see
     * any advantage in using the the mark_names, unless you need to access
     * built-in names like "insert" or "selection-bound" (are there more?)
     */
    public TextMark createMark(String name, TextIter where, boolean leftGravity) {
        return GtkTextBuffer.createMark(this, name, where, leftGravity);
    }

    /**
     * Returns a TextMark with the given name.
     * 
     * @since 4.0.8
     */
    public TextMark getMark(String name) {
        return GtkTextBuffer.getMark(this, name);
    }

    /**
     * Insert a text at a given position. All {@link TextIter} behind the
     * position move accordingly, while marks keep their position.
     * 
     * @since 4.0.8
     */
    public void insert(TextIter position, String text) {
        GtkTextBuffer.insert(this, position, text, -1);
    }

    /**
     * Insert the text at the current cursor position.
     * 
     * @since 4.0.8
     */
    public void insertAtCursor(String text) {
        GtkTextBuffer.insertAtCursor(this, text, -1);
    }

    /**
     * Like {@link #insert(TextIter, String) insert(TextIter,String)}, but
     * the insertion will not occur if <code>pos</code> points to a
     * non-editable location in the buffer - meaning that it is enclosed in
     * TextTags that mark the area non-editable.<br/>
     * 
     * @param pos
     *            Position to insert at.
     * @param text
     *            Text to insert.
     * @param defaultEditable
     *            How shall the area be handled, if there are no tags
     *            affacting editability.
     * @since 4.0.8
     */
    public void insertInteractive(TextIter pos, String text, boolean defaultEditable) {
        GtkTextBuffer.insertInteractive(this, pos, text, -1, defaultEditable);
    }

    /**
     * Returns the current cursor position. Is also used at the start position
     * of a selected text.
     * 
     * You can call {@link #getIterAtMark(TextMark) getIterAtMark()} to
     * convert the TextMark to an TextIter.
     * 
     * @since 4.0.8
     */
    public TextMark getInsert() {
        return GtkTextBuffer.getInsert(this);
    }

    /**
     * Returns the end of the current selection. If you need the beginning of
     * the selection use {@link #getInsert() getInsert()}.
     * 
     * You can call {@link #getIterAtMark(TextMark) getIterAtMark()} to
     * convert the TextMark to an TextIter.
     * 
     * @since 4.0.8
     */
    /*
     * (SP) I tested around a bit and getSelectionBound() always returned the
     * same position as getInsert(). I checked the generated code - it looks
     * ok. A bug in GTK ? This even is true if I access the mark
     * "selection_bound" directly.
     */
    public TextMark getSelectionBound() {
        return GtkTextBuffer.getSelectionBound(this);
    }

    /**
     * Returns whether or not the TextBuffer has a selection
     */
    public boolean getHasSelection() {
        return GtkTextBuffer.getHasSelection(this);
    }

    /**
     * Return start and end of the current selection - if any.
     * 
     * @return A array of two TextIter (start,end) marking the selection or
     *         <code>null</code> if nothing was selected.
     * @since 4.0.8
     */
    public TextIter[] getSelectionBounds() {
        TextIter start = new TextIter(this);
        TextIter end = new TextIter(this);
        if (GtkTextBuffer.getSelectionBounds(this, start, end)) {
            return new TextIter[] {
                    start, end
            };
        } else {
            return null;
        }
    }

    /**
     * Converts a {@link TextMark TextMark} into a {@link TextIter TextIter}.
     * 
     * @since 4.0.8
     */
    public TextIter getIterAtMark(TextMark mark) {
        final TextIter iter;

        iter = new TextIter(this);

        GtkTextBuffer.getIterAtMark(this, iter, mark);

        return iter;
    }

    /**
     * Apply the selected tag on the area in the TextBuffer between the start
     * and end positions.
     * 
     * @since 4.0.8
     */
    public void applyTag(TextTag tag, TextIter start, TextIter end) {
        GtkTextBuffer.applyTag(this, tag, start, end);
    }

    /*
     * TODO (SP) When we don't expose mark names we also don't need to expose
     * tag names, don't we?
     */
    void applyTagByName(String name, TextIter start, TextIter end) {
        GtkTextBuffer.applyTagByName(this, name, start, end);
    }

    /**
     * Select a range of text. The <var>selection-bound</var> mark will be
     * placed at <code>start</code>, and the <var>insert</var> mark will
     * be placed at <code>end</code>.
     * 
     * <p>
     * Note that this should be used in preference to manually manipulating
     * the two marks with {@link #moveMark() moveMark()} calls; doing it that
     * way results in transient selections appearing which are different than
     * what you wish to see.
     * 
     * <p>
     * <i>The native GTK function has these arguments reversed but start and
     * end make more sense in consecuitive order.</i>
     * 
     * @since 4.0.8
     */
    public void selectRange(TextIter start, TextIter end) {
        GtkTextBuffer.selectRange(this, end, start);
    }
}
