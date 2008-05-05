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
     * Create an empty TextView without an associated TextBuffer.
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
     * Set the default editability of the TextView. You can override this
     * default setting with tags in the buffer, using the "editable" attribute
     * of tags.
     * 
     * @since 4.0.8
     */
    /*
     * FIXME link to the TextTag setter instead of saying "editiable"
     */
    public void setEditable(boolean editable) {
        GtkTextView.setEditable(this, editable);
    }

    /**
     * Returns the default editability of the TextView. Tags in the buffer may
     * override this setting for some ranges of text.
     * 
     * @return Whether text is editable by default
     */
    public boolean getEditable() {
        return GtkTextView.getEditable(this);
    }

    /**
     * Allows you to activate or deactivate the visible cursor. Usually used
     * in non-editable buffers to hide the cursor.
     * 
     * @param visible
     *            Whether to show the insertion cursor or not.
     */
    public void setCursorVisible(boolean visible) {
        GtkTextView.setCursorVisible(this, visible);
    }

    /**
     * Returns whether the cursor is currently visible or not.
     */
    public boolean getCursorVisible() {
        return GtkTextView.getCursorVisible(this);
    }
}
