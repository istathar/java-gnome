/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.gtk.TextView;

/**
 * A text Widget for displaying and editing source code.
 * 
 * <p>
 * GtkSourceView extends GTK's {@link TextView} and adds features that are
 * useful for building text or source-code editors. It provides syntax
 * highlighting for various languages, undo/redo, control over indentation and
 * more.
 * 
 * <p>
 * SourceView is the view part of the GtkSourceView library and is always used
 * together with a {@link SourceBuffer} as its model.
 * 
 * <h2>Usage</h2>
 * 
 * To create a SourceView a previously instantiated SourceBuffer is needed,
 * that is passed to the constructor:
 * 
 * <pre>
 * SourceBuffer buffer;
 * SourceView view;
 * ...
 * 
 * view = new SourceView(buffer);
 * </pre>
 * 
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

    /**
     * The handler interface used for undo signals.
     * 
     * @author Stefan Schweizer
     * @since 4.0.12
     */
    public interface Undo extends GtkSourceView.UndoSignal
    {
        public void onUndo(SourceView source);
    }

    /**
     * Connect a handler to receive <code>SourceView.Undo</code> signals. It
     * is emitted whenever something is undone in the editor window, e.g. by
     * hitting Ctrl+Z.
     * 
     * @since 4.0.12
     */
    public void connect(SourceView.Undo handler) {
        GtkSourceView.connect(this, handler, false);
    }

    /**
     * The handler interface used for redo signals.
     * 
     * @author Stefan Schweizer
     * @since 4.0.12
     */
    public interface Redo extends GtkSourceView.RedoSignal
    {
        public void onRedo(SourceView source);
    }

    /**
     * Connect a handler to receive <code>SourceView.Redo</code> signals. It
     * is emitted whenever something is redone in the editor window, e.g. by
     * hitting <code><b>Shift+Ctrl+Z</b></code>.
     * 
     * @since 4.0.12
     */
    public void connect(SourceView.Redo handler) {
        GtkSourceView.connect(this, handler, false);
    }
}
