/*
 * SourceView.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.sourceview;

import org.gnome.gtk.TextView;

/**
 * @author Stefan Schweizer
 * @since 4.0.12
 */
public class SourceView extends TextView
{
    protected SourceView(long pointer) {
        super(pointer);
    }

    /**
     * Create a new SourceView that displays the SourceBuffer.
     * 
     * @since 4.0.12
     */
    public SourceView(SourceBuffer buffer) {
        super(GtkSourceView.createSourceViewWithBuffer(buffer));
    }

    /**
     * Returns whether line numbers are shown next to the text or not.
     * 
     * @since 4.0.12
     */
    public boolean getShowLineNumbers() {
        return GtkSourceView.getShowLineNumbers(this);
    }

    /**
     * Enable or disable the display of line numbers next to the text. The
     * display is disabled by default.
     * 
     * @since 4.0.12
     */
    public void setShowLineNumbers(boolean show) {
        GtkSourceView.setShowLineNumbers(this, show);
    }

    /**
     * Returns whether the current line is highlighted or not. Disabled by
     * default.
     * 
     * @since 4.0.12
     */
    public boolean getHighlightCurrentLine() {
        return GtkSourceView.getHighlightCurrentLine(this);
    }

    /**
     * Enable or disable the highlighting of the current line. Disabled by
     * default.
     * 
     * @since 4.0.12
     */
    public void setHighlightCurrentLine(boolean highlight) {
        GtkSourceView.setHighlightCurrentLine(this, highlight);
    }

    /**
     * Returns whether the line indicating the right margin is shown or not.
     * 
     * @since 4.0.12
     */
    public boolean getShowRightMargin() {
        return GtkSourceView.getShowRightMargin(this);
    }

    /**
     * Show a line that indicates the right margin of the text. Disabled by
     * default.
     * 
     * @since 4.0.12
     */
    public void setShowRightMargin(boolean show) {
        GtkSourceView.setShowRightMargin(this, show);
    }

    /**
     * Return the position of the line that indicates the right margin of the
     * text.
     * 
     * @since 4.0.12
     */
    public int getRightMarginPosition() {
        return GtkSourceView.getRightMarginPosition(this);
    }

    /**
     * Set the position of the line that indicates the right margin of the
     * text. The default position is 80.
     * 
     * @since 4.0.12
     */
    public void setRightMarginPosition(int position) {
        GtkSourceView.setRightMarginPosition(this, position);
    }

    /**
     * Returns whether spaces are used instead of the tab character.
     * 
     * @since 4.0.12
     */
    public boolean getInsertSpacesInsteadOfTabs() {
        return GtkSourceView.getInsertSpacesInsteadOfTabs(this);
    }

    /**
     * Insert spaces instead of the tab character. Disabled by default.
     * 
     * @since 4.0.12
     */
    public void setInsertSpacesInsteadOfTabs(boolean enable) {
        GtkSourceView.setInsertSpacesInsteadOfTabs(this, enable);
    }

    /**
     * Return the number of spaces that are used for the tab character.
     * 
     * @since 4.0.12
     */
    public int getTabWidth() {
        return GtkSourceView.getTabWidth(this);
    }

    /**
     * Set the number of spaces that are used for the tab character.
     * 
     * @since 4.0.12
     */
    public void setTabWidth(int width) {
        GtkSourceView.setTabWidth(this, width);
    }

    /**
     * Return whether audo-indentation is enabled of not.
     * 
     * @since 4.0.12
     */
    public boolean getAutoIndent() {
        return GtkSourceView.getAutoIndent(this);
    }

    /**
     * Enable/disable auto-indentation. If enabled, a new line will have the
     * same indentation as the current line. Disabled by default.
     * 
     * @since 4.0.12
     */
    public void setAutoIndent(boolean enable) {
        GtkSourceView.setAutoIndent(this, enable);
    }
}
