/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2012 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.sourceview;

import org.gnome.gtk.TextBuffer;
import org.gnome.gtk.TextTagTable;

/**
 * SourceBuffer is the model used in a {@link SourceView}. It extends GTK's
 * {@link TextBuffer} and adds features typical for text editors. SourceBuffer
 * contains the actual text that is displayed in the view and allows its
 * manipulation.
 * 
 * <h2>Usage</h2>
 * 
 * The following example shows how a SourceBuffer is created and its initial
 * content is set:
 * 
 * <pre>
 * TextTagTable table;
 * SourceBuffer buffer;
 * ...
 * 
 * table = new TextTagTable();
 * buffer = new SourceBuffer();
 * buffer.setText(&quot;Insert text here...&quot;);
 * </pre>
 * 
 * The language used for syntax highlighting has to be obtained from the
 * LanguageManager:
 * 
 * <pre>
 * manager = LanguageManager.getDefault();
 * language = manager.getLanguage(&quot;java&quot;);
 * buffer.setLanguage(language);
 * </pre>
 * 
 * @author Stefan Schweizer
 * @since 4.0.12
 */
public class SourceBuffer extends TextBuffer
{
    protected SourceBuffer(long pointer) {
        super(pointer);
    }

    /**
     * Create a new SourceBuffer. This uses the same default TextTagTable as
     * used by the no-arg TextTags; see the no-arg TextBuffer
     * {@link org.gnome.gtk.TextBuffer#TextBuffer() &lt;init&gt;()} for
     * details.
     * 
     * @since 4.0.12
     */
    public SourceBuffer() {
        super(GtkSourceBuffer.createSourceBuffer(getDefaultTable()), true);
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
     * Set the {@link StyleScheme} that is used.
     * 
     * @since 4.1.2
     */
    public void setStyleScheme(StyleScheme scheme) {
        GtkSourceBuffer.setStyleScheme(this, scheme);
    }

    /**
     * Get the {@link StyleScheme} that is used.
     * 
     * @since 4.1.2
     */
    public StyleScheme getStyleScheme() {
        return GtkSourceBuffer.getStyleScheme(this);
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
     * Return <code>true</code> if an operation can be undone.
     * 
     * @since 4.0.12
     */
    public boolean canUndo() {
        return GtkSourceBuffer.canUndo(this);
    }

    /**
     * Return <code>true</code> if an operation can be redone.
     * 
     * @since 4.0.12
     */
    public boolean canRedo() {
        return GtkSourceBuffer.canRedo(this);
    }

    /**
     * Undo the last operation. You should only call this method if there is
     * an operation that can be undone. This can be checked with
     * <code>canUndo()</code>.
     * 
     * @since 4.0.12
     */
    public void undo() {
        GtkSourceBuffer.undo(this);
    }

    /**
     * Redo the last undone operation. You should only call this method if
     * there is an operation that can be redone. This can be checked with
     * <code>canRedo()</code>.
     * 
     * @since 4.0.12
     */
    public void redo() {
        GtkSourceBuffer.redo(this);
    }

    /**
     * Indicate the beginning of an action that cannot be undone. This is
     * especially useful when setting the initial content of the buffer.
     * 
     * @since 4.0.12
     */
    public void beginNotUndoableAction() {
        GtkSourceBuffer.beginNotUndoableAction(this);
    }

    /**
     * Indicate the end of an action that cannot be undone.
     * 
     * @since 4.0.12
     */
    public void endNotUndoableAction() {
        GtkSourceBuffer.endNotUndoableAction(this);
    }
}
