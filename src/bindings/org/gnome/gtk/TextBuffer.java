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

import org.gnome.gdk.Pixbuf;
import org.gnome.glib.Object;

import static org.gnome.gtk.TextTagTable.getDefaultTable;

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
/*
 * Judging by the GTK documentation, almost everything in this class works
 * through the default signal handlers. Thus if we expose any signals we must
 * a) make sure that the documentation of the corresponding methods here
 * mentions that behaviour could change, b) warn people in the signal's
 * documentation that they could massively screw things up, and c) write test
 * coverage to guard against this sort of thing. Based on all that, I'm not
 * going to be in a rush to see any of those internal signals exposed in our
 * public API.
 */
public class TextBuffer extends Object
{
    /**
     * This constant is used to identify cells in the TextBuffer that are not
     * actually characters - in other words, Widgets and Pixbufs. The
     * placeholder is Unicode value <code>0xFFFC</code>, the "Object
     * Replacement Character" from the Special block. You might see it as
     * &#65532; in other contexts.
     * 
     * @since 4.0.8
     */
    public static final char OBJECT_REPLACEMENT_CHARACTER = 0xFFFC;

    protected TextBuffer(long pointer) {
        super(pointer);
    }

    /**
     * Create a new TextBuffer.
     * 
     * <p>
     * This will use the default built-in TextTagTable (shared by all
     * TextBuffers constructed with this call) which in turn will be populated
     * with tags created using the no-arg {@link TextTag#TextTag() TextTag}
     * constructor. This is a convenience; if you need to segregate TextTags
     * used by different TextBuffers then just use the other TextBuffer
     * constructor.
     * 
     * @since 4.0.8
     */
    public TextBuffer() {
        super(GtkTextBuffer.createTextBuffer(getDefaultTable()));
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
        GtkTextBuffer.setText(this, text, -1);
    }

    /**
     * Returns the text in the range <code>start</code> .. <code>end</code>.
     * Excludes undisplayed text (text marked with tags that set the
     * <var>invisibility</var> attribute) if <code>includeHidden</code> is
     * <code>false</code>. Does not include characters representing
     * embedded images, so indexes into the returned string do not correspond
     * to indexes into the TextBuffer.
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
     * buffer start to end with <code>includeHidden</code> being
     * <code>true</code>.
     * 
     * @return The complete text in the TextBuffer.
     * @since 4.0.8
     */
    public String getText() {
        return getText(getIterStart(), getIterEnd(), true);
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
    public TextIter getIterStart() {
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
    /*
     * The naming of this method is inverted so as to correspond with the
     * naming pattern established in TreeModel.
     */
    public TextIter getIterEnd() {
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
    public TextMark createMark(TextIter where, boolean leftGravity) {
        return GtkTextBuffer.createMark(this, null, where, leftGravity);
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
     * Insert text as for {@link #insert(TextIter, String) insert()} but
     * simultaneously apply the formatting described by <code>tag</code>.
     * 
     * @since 4.0.8
     */
    public void insert(TextIter position, String text, TextTag tag) {
        GtkTextBuffer.insertWithTags(this, position, text, -1, tag);
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
     * Like {@link #insert(TextIter, String) insert()}, but the insertion
     * will not occur if <code>pos</code> points to a non-editable location
     * in the buffer - meaning that it is enclosed in TextTags that mark the
     * area non-editable.<br/>
     * 
     * @param pos
     *            Position to insert at.
     * @param text
     *            Text to insert.
     * @param defaultEditability
     *            How shall the area be handled, if there are no tags
     *            affecting the <var>editable</var> property at the given
     *            location. You probably want to use <code>true</code>
     *            unless you used TextView's
     *            {@link TextView#setEditable(boolean) setEditable()} to
     *            change the default setting in the display Widget you're
     *            using.
     * @since 4.0.8
     */
    public void insertInteractive(TextIter pos, String text, boolean defaultEditability) {
        GtkTextBuffer.insertInteractive(this, pos, text, -1, defaultEditability);
    }

    /**
     * Inserts an image at the cursor position.
     * 
     * @since 4.0.8
     */
    public void insert(TextIter pos, Pixbuf image) {
        GtkTextBuffer.insertPixbuf(this, pos, image);
    }

    /**
     * Returns the current cursor position. Is also used at the start position
     * of a selected text.
     * 
     * <p>
     * You can call {@link #getIter(TextMark) getIter()} to convert the
     * TextMark to an TextIter.
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
     * <p>
     * Under ordinary circumstances you could think the <var>selection-bound</var>
     * TextMark as being the beginning of a selection, and the <var>insert</var>
     * mark as the end, but if the user (or you, programmatically) has
     * selected "backwards" then this TextMark will be further ahead in the
     * TextBuffer than the insertion one.
     * 
     * <p>
     * You can call {@link #getIter(TextMark) getIter()} to convert the
     * TextMark to an TextIter.
     * 
     * @since 4.0.8
     */
    public TextMark getSelectionBound() {
        return GtkTextBuffer.getSelectionBound(this);
    }

    /**
     * Returns whether or not the TextBuffer has a selection
     * 
     * @since 4.0.8
     */
    public boolean getHasSelection() {
        return GtkTextBuffer.getHasSelection(this);
    }

    /**
     * Converts a {@link TextMark TextMark} into a valid
     * {@link TextIter TextIter} that you can use to point into the TextBuffer
     * in its current state.
     * 
     * @since 4.0.8
     */
    /*
     * This method is more compactly named so as to correspond with the naming
     * pattern already established in TreeModel.
     */
    public TextIter getIter(TextMark mark) {
        final TextIter iter;

        iter = new TextIter(this);

        GtkTextBuffer.getIterAtMark(this, iter, mark);

        return iter;
    }

    /**
     * Get a TextIter pointing at the position <code>offset</code>
     * characters into the TextBuffer.
     * 
     * @since 4.0.8
     */
    public TextIter getIter(int offset) {
        final TextIter iter;

        iter = new TextIter(this);

        GtkTextBuffer.getIterAtOffset(this, iter, offset);

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
     * See {@link #placeCursor(TextIter) placeCursor()} if you just want to
     * move the two marks to the same location; and, assuming the TextBuffer
     * is showing in a TextView, this will move the cursor.
     * 
     * <p>
     * <i>The native GTK function has these arguments reversed but start and
     * end make more sense in consecutive order.</i>
     * 
     * @since 4.0.8
     */
    public void selectRange(TextIter start, TextIter end) {
        GtkTextBuffer.selectRange(this, end, start);
    }

    /**
     * Remove the effect of the supplied <code>tag</code> from across the
     * range between <code>start</code> and <code>end</code>. The order
     * of the two TextIters doesn't actually matter; they are just bounds.
     * 
     * @since 4.0.8
     */
    public void removeTag(TextTag tag, TextIter start, TextIter end) {
        GtkTextBuffer.removeTag(this, tag, start, end);
    }

    /**
     * Create a new TextChildAnchor at <code>location</code>. Use the
     * method in TextView to load a Widget into it.
     * 
     * @since 4.0.8
     */
    public TextChildAnchor createChildAnchor(TextIter location) {
        return GtkTextBuffer.createChildAnchor(this, location);
    }

    /**
     * Move the cursor (ie, the <var>selection-bound</var> and <var>insert</var>
     * marks) to the given location.
     * 
     * <p>
     * This is more than just a convenience function; like
     * {@link #selectRange(TextIter, TextIter) selectRange()} it carries out
     * the move without intermediate state of part of the text being selected
     * while the individual TextMarks are being moved.
     * 
     * <p>
     * See also TextView's
     * {@link TextView#placeCursorOnscreen() placeCursorOnscreen()} if you
     * just want to force the cursor into the currently showing section of the
     * text.
     * 
     * @since 4.0.8
     */
    public void placeCursor(TextIter location) {
        GtkTextBuffer.placeCursor(this, location);
    }

    /**
     * Returns the number of characters in this buffer. Since non-character
     * elements such as widgets use more than one byte, this may differ from
     * the number of bytes.
     * 
     * @since 4.0.8
     */
    public int getCharCount() {
        return GtkTextBuffer.getCharCount(this);
    }

    /**
     * Returns the number of text lines in this buffer.
     * 
     * @since 4.0.8
     */
    public int getLineCount() {
        return GtkTextBuffer.getLineCount(this);
    }

    /**
     * The signal emitted when the contents of the TextBuffer have changed.
     * 
     * @author Andrew Cowie
     * @since 4.0.8
     */
    public interface CHANGED extends GtkTextBuffer.CHANGED
    {
        public void onChanged(TextBuffer source);
    }

    /**
     * Hook up a handler for <code>CHANGED</code> signals.
     * 
     * @since 4.0.8
     */
    public void connect(CHANGED handler) {
        GtkTextBuffer.connect(this, handler, false);
    }

    /**
     * Signal emitted when text is inserted into the TextBuffer.
     * 
     * <p>
     * You must leave the TextIter <code>pointer</code> in a valid state;
     * that is, if you do something in your signal handler that changes the
     * TextBuffer, you must revalidate <code>pos</code> before returning.
     * 
     * <p>
     * <i>The default handler for this signal is where the mechanism to
     * actually insert text into the TextBuffer lives.</i>
     * 
     * @author Andrew Cowie
     * @since 4.0.8
     */
    /*
     * FIXME How do you do that?
     */
    public interface INSERT_TEXT
    {
        public void onInsertText(TextBuffer source, TextIter pointer, String text);
    }

    /**
     * Hook up a handler for <code>INSERT_TEXT</code> signals. This will be
     * invoked before the default handler is run, that is, before the new text
     * is actually inserted into the TextBuffer.
     * 
     * @since 4.0.8
     */
    public void connect(INSERT_TEXT handler) {
        GtkTextBuffer.connect(this, new InsertTextHandler(handler), false);
    }

    /**
     * Hook up a handler for <code>INSERT_TEXT</code> signals that will be
     * called <i>after</i> the default handler.
     * 
     * @since 4.0.8
     */
    public void connectAfter(INSERT_TEXT handler) {
        GtkTextBuffer.connect(this, new InsertTextHandler(handler), true);
    }

    /*
     * Trim off the length parameter which is unnecessary in Java.
     */
    private static class InsertTextHandler implements GtkTextBuffer.INSERT_TEXT
    {
        private final INSERT_TEXT handler;

        private InsertTextHandler(INSERT_TEXT handler) {
            this.handler = handler;
        }

        public void onInsertText(TextBuffer source, TextIter pos, String text, int length) {
            handler.onInsertText(source, pos, text);
        }
    }

    /**
     * Signal emitted when one or more characters are deleted.
     * 
     * <p>
     * You can get a String representing the the removed characters by
     * calling:
     * 
     * <pre>
     * deleted = buffer.getText(start, end, false);
     * </pre>
     * 
     * <p>
     * Note that in the case of a user action which attempts to delete text in
     * a TextView that is not <var>editable</var> or within a range of
     * characters affected by TextTags with the <var>editable</var> property
     * set to <code>false</code> then the action will be inhibited and this
     * signal will not be raised.
     * 
     * @author Andrew Cowie
     * @since 4.0.8
     */
    /*
     * TODO Can anyone explain how to stop a deletion from occurring in
     * response to the value of the text between start and end? The default
     * handler will nuke the text.
     */
    public interface DELETE_RANGE extends GtkTextBuffer.DELETE_RANGE
    {
        public void onDeleteRange(TextBuffer source, TextIter start, TextIter end);
    }

    /**
     * Hook up a handler for <code>DELETE_RANGE</code> signals on this
     * TextBuffer.
     * 
     * @since 4.0.8
     */
    public void connect(DELETE_RANGE handler) {
        GtkTextBuffer.connect(this, handler, false);
    }
}
