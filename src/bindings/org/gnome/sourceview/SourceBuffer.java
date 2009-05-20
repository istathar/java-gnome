/*
 * SourceBuffer.java
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

import org.gnome.gtk.TextBuffer;
import org.gnome.gtk.TextTagTable;

/**
 * @author Stefan Schweizer
 * @since 4.0.12
 */
public class SourceBuffer extends TextBuffer
{
    protected SourceBuffer(long pointer) {
        super(pointer);
    }

    /**
     * Create a new SourceBuffer using the given TextTagTable.
     * 
     * @since 4.0.12
     */
    public SourceBuffer(TextTagTable tags) {
        super(GtkSourceBuffer.createSourceBuffer(tags));
    }

    /**
     * Enable/disable syntax higlighting. The SourceLangage is configured with
     * <code>setLanguage</code>. Highlighting is enabled by default.
     * 
     * @since 4.0.12
     */
    public void setHighlightSyntax(boolean highlight) {
        GtkSourceBuffer.setHighlightSyntax(this, highlight);
    }

    /**
     * Return whether syntax highlighting is enabled or not.
     * 
     * @since 4.0.12
     */
    public boolean getHighlightSyntax() {
        return GtkSourceBuffer.getHighlightSyntax(this);
    }

    /**
     * Set the Language that is used for syntax highlighting.
     * 
     * @since 4.0.12
     */
    public void setLanguage(Language language) {
        GtkSourceBuffer.setLanguage(this, language);
    }

    /**
     * Get the Language that is used for syntax highlighting.
     * 
     * @since 4.0.12
     */
    public Language getLanguage() {
        return GtkSourceBuffer.getLanguage(this);
    }

    /**
     * Enable or disable highlighting of matching brackets. Enabled by
     * default.
     * 
     * @since 4.0.12
     */
    public void setHighlightMatchingBrackets(boolean highlight) {
        GtkSourceBuffer.setHighlightMatchingBrackets(this, highlight);
    }

    /**
     * Return whether matching brackets are highlighted.
     * 
     * @since 4.0.12
     */
    public boolean getHighlightMatchingBrackets() {
        return GtkSourceBuffer.getHighlightMatchingBrackets(this);
    }

    /**
     * Return true if an operation can be undone.
     * 
     * @since 4.0.12
     */
    public boolean canUndo() {
        return GtkSourceBuffer.canUndo(this);
    }

    /**
     * Return true if an operation can be redone.
     * 
     * @since 4.0.12
     */
    public boolean canRedo() {
        return GtkSourceBuffer.canRedo(this);
    }

    /**
     * Undo the last operation.
     * 
     * @since 4.0.12
     */
    public void undo() {
        GtkSourceBuffer.undo(this);
    }

    /**
     * Redo the last undone operation.
     * 
     * @since 4.0.12
     */
    public void redo() {
        GtkSourceBuffer.redo(this);
    }
}
