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
 */
public class SourceBuffer extends TextBuffer
{
    protected SourceBuffer(long pointer) {
        super(pointer);
    }

    /**
     * Create a new SourceBuffer using the given TextTagTable.
     */
    public SourceBuffer(TextTagTable tags) {
        super(GtkSourceBuffer.createSourceBuffer(tags));
    }

    /**
     * Enable/disable syntax higlighting. The SourceLangage is configured with
     * <code>setLanguage</code>. Highlighting is enabled by default.
     */
    public void setHighlightSyntax(boolean highlight) {
        GtkSourceBuffer.setHighlightSyntax(this, highlight);
    }

    /**
     * Return whether syntax highlighting is enabled or not.
     */
    public boolean getHighlightSyntax() {
        return GtkSourceBuffer.getHighlightSyntax(this);
    }

    /**
     * Set the SourceLanguage that is used for syntax highlighting.
     */
    public void setLanguage(SourceLanguage language) {
        GtkSourceBuffer.setLanguage(this, language);
    }

    /**
     * Get the SourceLanguage that is used for syntax highlighting.
     */
    public SourceLanguage getLanguage() {
        return GtkSourceBuffer.getLanguage(this);
    }
}
