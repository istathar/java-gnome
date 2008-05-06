/*
 * TextView.java
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

/**
 * @author Stefan Prelle
 * @since 4.0.8
 */
public class TextView extends Container
{
    protected TextView(long pointer) {
        super(pointer);
    }

    /**
     * Create an empty TextView without (yet) having an associated TextBuffer.
     * Use {@link #setBuffer(TextBuffer) setBuffer()} to indicate later which
     * TextBuffer to use.
     * 
     * @since 4.0.8
     */
    public TextView() {
        super(GtkTextView.createTextView());
    }

    /**
     * Create a TextView and display the contents of the TextBuffer.
     * 
     * @param buf
     *            TextBuffer to associated with this TextView.
     * @since 4.0.8
     */
    public TextView(TextBuffer buf) {
        super(GtkTextView.createTextViewWithBuffer(buf));
    }

    /**
     * Set or replace the TextBuffer that is currently being displayed by this
     * TextView.
     * 
     * @since 4.0.8
     */
    public void setBuffer(TextBuffer buffer) {
        GtkTextView.setBuffer(this, buffer);
    }

    /**
     * Set the line wrapping for the view.
     * 
     * @since 4.0.8
     */
    public void setWrapMode(WrapMode wrapMode) {
        GtkTextView.setWrapMode(this, wrapMode);
    }

    /**
     * Get the line wrapping for the view.
     * 
     * @since 4.0.8
     */
    public WrapMode getWrapMode() {
        return GtkTextView.getWrapMode(this);
    }

    /**
     * Set whether the normal state of this TextView is to allow editing or
     * not. The default for the <var>editability</var> property is
     * <code>true</code>.
     * 
     * <p>
     * Regardless of the default setting here, you can override this for
     * specific regions of text with TextTag's
     * {@link TextTag#setEditable(boolean) setEditable()}. of tags.
     * 
     * @since 4.0.8
     */
    public void setEditable(boolean editable) {
        GtkTextView.setEditable(this, editable);
    }

    /**
     * Get whether the default editability of the TextView. Tags in the buffer
     * may override this setting for some ranges of text.
     * 
     * @since 4.0.8
     */
    public boolean getEditable() {
        return GtkTextView.getEditable(this);
    }

    /**
     * Allows you to activate or deactivate the visible cursor. Usually used
     * to hide the cursor when displaying text that is non-editable. The
     * default is <code>true</code>, indicating the cursor will be shown.
     * 
     * @since 4.0.8
     */
    public void setCursorVisible(boolean visible) {
        GtkTextView.setCursorVisible(this, visible);
    }

    /**
     * Returns whether the cursor is currently visible or not.
     * 
     * @since 4.0.8
     */
    public boolean getCursorVisible() {
        return GtkTextView.getCursorVisible(this);
    }
}
