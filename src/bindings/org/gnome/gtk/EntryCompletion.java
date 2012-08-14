/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.gtk;

import org.gnome.glib.Object;

/**
 * An object to use in conjunction with {@link Entry} to provide completion
 * functionality. <img src="EntryCompletion.png" class="snapshot" />
 * 
 * <p>
 * To add completion functionality to an Entry, use its
 * {@link Entry#setCompletion(EntryCompletion) setCompletion()} method.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.12
 */
public class EntryCompletion extends Object implements CellLayout
{
    protected EntryCompletion(long pointer) {
        super(pointer);
    }

    /**
     * Create a new EntryCompletion.
     * 
     * @since 4.0.12
     */
    public EntryCompletion() {
        this(GtkEntryCompletion.createEntryCompletion());
    }

    /**
     * Retrieve the Entry that this EntryCompletion is attached to.
     * 
     * @since 4.0.12
     */
    public Entry getEntry() {
        return (Entry) GtkEntryCompletion.getEntry(this);
    }

    /**
     * Set the TreeModel being used to source data for this EntryCompletion.
     * If a model has already been set, calling this will replace it.
     * 
     * @param store
     *            a value of <code>null</code> will remove the data model
     *            underlying this EntryCompletion, leaving it unset for the
     *            present.
     * @since 4.0.12
     */
    public void setModel(TreeModel store) {
        GtkEntryCompletion.setModel(this, store);
    }

    /**
     * Get the TreeModel currently providing the data powering this
     * EntryCompletion, or <code>null</code> if not yet set.
     * 
     * @since 4.0.12
     */
    public TreeModel getModel() {
        return GtkEntryCompletion.getModel(this);
    }

    /**
     * Set the minimum length of the search key to the value specified by
     * <code>length</code>. This means that the key string (which is in the
     * Entry) must be at least length characters before a completion list will
     * be displayed.
     * 
     * <p>
     * This is useful for long lists, where completing using a small key will
     * take too much time and will return a too large dataset.
     * 
     * @since 4.0.12
     */
    public void setMinimumKeyLength(int length) {
        GtkEntryCompletion.setMinimumKeyLength(this, length);
    }

    /**
     * Return the minimum key length set for the entry completion. See
     * {@link #setMinimumKeyLength(int) setMinimumKeyLength()} for more
     * information.
     * 
     * @since 4.0.12
     */
    public int getMinimumKeyLength() {
        return GtkEntryCompletion.getMinimumKeyLength(this);
    }

    /**
     * Refilter the current list of completions based on the current key. Be
     * aware that this method will <i>not</i> put the completion in the Entry;
     * this is just a command to update completion popup.
     * 
     * <p>
     * If you are looking to duplicate the effect of a line in the completion
     * list being selected by the user see
     * {@link #emitMatchSelected(TreeIter) emitMatchSelected()} instead.
     * 
     * @since 4.0.12
     */
    public void complete() {
        GtkEntryCompletion.complete(this);
    }

    /**
     * Force the {@link EntryCompletion.MatchSelected} signal to be emitted.
     * This action will result in the completion of the associated Entry using
     * the completion string pointed at by the given TreeIter.
     * 
     * @since 4.0.12
     */
    public boolean emitMatchSelected(TreeIter row) {
        if (row == null) {
            throw new IllegalArgumentException(
                    "TreeIter passed to EntryCompletion emitMatchSelected(TreeIter) must not be null");
        }

        return GtkEntryCompletionOverride.emitMatchSelected(this, row);
    }

    /**
     * Insert an action in the action item list of the EntryCompletion at the
     * position specified by <code>index</code> with the text specified by
     * <code>text</code>.
     * 
     * <p>
     * If you want the action item to have markup, use
     * {@link #insertActionMarkup(int, String) insertActionMarkup()} instead.
     * 
     * @since 4.0.12
     */
    public void insertActionText(int index, String text) {
        GtkEntryCompletion.insertActionText(this, index, text);
    }

    /**
     * Insert an action item in the action item list of the EntryCompletion at
     * the position specified by <code>index</code> with the markup specified
     * by <code>markup</code>.
     * 
     * @since 4.0.12
     */
    public void insertActionMarkup(int index, String markup) {
        GtkEntryCompletion.insertActionMarkup(this, index, markup);
    }

    /**
     * Delete the item at the position in the action item list specified by
     * <code>index</code>.
     * 
     * @since 4.0.12
     */
    public void deleteAction(int index) {
        GtkEntryCompletion.deleteAction(this, index);
    }

    /**
     * Create and adds a {@link CellRendererText} using the specified
     * DataColumn in your TreeModel as the source for completion strings
     * 
     * @since 4.0.12
     */
    public void setTextColumn(DataColumn column) {
        GtkEntryCompletion.setTextColumn(this, column.getOrdinal());
    }

    /**
     * Request a prefix insertion. The prefix is the string that will trigger
     * the completion. For example, the prefix for &quot;Alice&quot; and
     * &quot;Albert&quot; is &quot;Al&quot;.
     * 
     * @since 4.0.12
     */
    public void insertPrefix() {
        GtkEntryCompletion.insertPrefix(this);
    }

    /**
     * Enable or disable the automatic inline completion.
     * 
     * <p>
     * If <code>setting</code> is <code>true</code>, the common prefix of the
     * possible completions will be automatically inserted in the Entry.
     * 
     * <p>
     * The default is <code>false</code>.
     * 
     * @since 4.0.12
     */
    public void setInlineCompletion(boolean setting) {
        GtkEntryCompletion.setInlineCompletion(this, setting);
    }

    /**
     * Has automatic inline completion been enabled?
     * 
     * @since 4.0.12
     */
    public boolean getInlineCompletion() {
        return GtkEntryCompletion.getInlineCompletion(this);
    }

    /**
     * Enable or disable completion popup.
     * 
     * <p>
     * The default is <code>true</code>, which is that completions will be
     * presented in a popup window.
     * 
     * @since 4.0.12
     */
    public void setPopupCompletion(boolean setting) {
        GtkEntryCompletion.setPopupCompletion(this, setting);
    }

    /**
     * Return <code>true</code> if automatic completion popup is enabled.
     * 
     * @since 4.0.12
     */
    public boolean getPopupCompletion() {
        return GtkEntryCompletion.getPopupCompletion(this);
    }

    /**
     * Enable or disable the completion popup resize.
     * 
     * <p>
     * If <code>setting</code> is <code>true</code> the popup window will be
     * resized according to the completion width.
     * 
     * @since 4.0.12
     */
    public void setPopupSetWidth(boolean setting) {
        GtkEntryCompletion.setPopupSetWidth(this, setting);
    }

    /**
     * Return <code>true</code> if completion popup resize is enabled.
     * 
     * @since 4.0.12
     */
    public boolean getPopupSetWidth() {
        return GtkEntryCompletion.getPopupSetWidth(this);
    }

    /**
     * Enable or disable the popup when there is only a single match.
     * 
     * <p>
     * If <code>setting</code> is <code>true</code> the popup will appear even
     * if there is a single match. The default is that this is the case.
     * 
     * @since 4.0.12
     */
    public void setPopupSingleMatch(boolean setting) {
        GtkEntryCompletion.setPopupSingleMatch(this, setting);
    }

    /**
     * Return <code>true</code> if completion popup appears when there is a
     * single match.
     * 
     * @since 4.0.12
     */
    public boolean getPopupSingleMatch() {
        return GtkEntryCompletion.getPopupSingleMatch(this);
    }

    /**
     * Get the original text entered that triggered the completion or
     * <code>null</code> if there is no completion.
     * 
     * @since 4.0.12
     */
    public String getCompletionPrefix() {
        return GtkEntryCompletion.getCompletionPrefix(this);
    }

    /**
     * Return <code>true</code> if inline-selection mode is turned on.
     * 
     * @since 4.0.12
     */
    public boolean getInlineSelection() {
        return GtkEntryCompletion.getInlineSelection(this);
    }

    /**
     * Enable or disable the possibility of cycle through the possible
     * completions inside the entry.
     * 
     * <p>
     * This is ordinarily unset.
     * 
     * @since 4.0.12
     */
    public void setInlineSelection(boolean setting) {
        GtkEntryCompletion.setInlineSelection(this, setting);
    }

    /*
     * This internal class is needed because the TreeIter passed to the
     * handler does not have the model field properly set, so we need to set
     * it before passing the TreeIter to the user.
     */
    private static class MatchSelectedHandler implements GtkEntryCompletion.MatchSelectedSignal
    {
        private final EntryCompletion.MatchSelected handler;

        private MatchSelectedHandler(EntryCompletion.MatchSelected handler) {
            super();
            this.handler = handler;
        }

        public boolean onMatchSelected(EntryCompletion source, TreeModel model, TreeIter iter) {
            iter.setModel(model);
            return handler.onMatchSelected(source, model, iter);
        }
    }

    /**
     * Emitted when a completion string was selected from the completion list.
     * 
     * <p>
     * Generally, this signal is enough to handle a completion selection and
     * it just used to complete the entry according to the selected completion
     * string.
     * 
     * <pre>
     * final DataColumnString column;
     * final EntryCompletion completion;
     * 
     * completion.connect(new EntryCompletion.MatchSelected() {
     *     final String text;
     *     final Entry entry;
     * 
     *     entry = source.getEntry();
     *     text = model.getValue(iter, column);
     * 
     *     entry.setText(text);
     *     ...
     * });
     * </pre>
     * 
     * @author Guillaume Mazoyer
     * @since 4.0.12
     */
    public interface MatchSelected extends GtkEntryCompletion.MatchSelectedSignal
    {
        /**
         * The useful parameter are <code>model</code> and <code>iter</code>
         * which will make you able to get the value of the selected
         * completion using the TreeModel's
         * {@link TreeModel#getValue(TreeIter, DataColumnString) getValue()}
         * method.
         * 
         * @since 4.0.12
         */
        public boolean onMatchSelected(EntryCompletion source, TreeModel model, TreeIter iter);
    }

    /**
     * Hook up the <code>EntryCompletion.MatchSelected</code> handler.
     * 
     * @since 4.0.12
     */
    public void connect(EntryCompletion.MatchSelected handler) {
        GtkEntryCompletion.connect(this, new MatchSelectedHandler(handler), false);
    }

    /**
     * Emitted when an action item is selected from the popup action list.
     * 
     * <p>
     * In this way, you can do something different from the
     * {@link MatchSelected} signal.
     * 
     * <pre>
     * final EntryCompletion completion;
     * 
     * ...
     * 
     * completion.insertActionText(0, &quot;complete&quot;);
     * completion.insertActionText(1, &quot;another&quot;);
     * 
     * completion.connect(new EntryCompletion.ActionActivated() {
     *     public void onActionActivated(EntryCompletion source, int index) {
     *         System.out.println(&quot;Action &quot; + index + &quot; activated.&quot;);
     *     }
     * });
     * </pre>
     * 
     * @since 4.0.12
     */
    public interface ActionActivated extends GtkEntryCompletion.ActionActivatedSignal
    {
        /**
         * The only two parameters give references to the EntryCompletion
         * <code>source</code> which has emitted the signal and the activated
         * action <code>index</code>.
         * 
         * @since 4.0.12
         */
        public void onActionActivated(EntryCompletion source, int index);
    }

    /**
     * Hook up the <code>EntryCompletion.ActionActivated</code> handler.
     * 
     * @since 4.0.12
     */
    public void connect(EntryCompletion.ActionActivated handler) {
        GtkEntryCompletion.connect(this, handler, false);
    }

    /**
     * Emitted when the inline auto-completion is triggered. The default
     * behavior is to make the entry display the whole prefix and select the
     * newly inserted part.
     * 
     * <pre>
     * final EntryCompletion completion;
     * 
     * ...
     * 
     * completion.setInlineCompletion(true);
     * completion.connect(new EntryCompletion.InsertPrefix() {
     *     public boolean onInsertPrefix(EntryCompletion source, String prefix) {
     *         final Entry entry;
     * 
     *         entry = source.getEntry();
     *         entry.setText(prefix);
     *         entry.selectRegion(0, prefix.length());
     *     }
     * });
     * </pre>
     * 
     * @since 4.0.12
     */
    public interface InsertPrefix extends GtkEntryCompletion.InsertPrefixSignal
    {
        /**
         * The only two parameters give references to the EntryCompletion
         * <code>source</code> which has emitted the signal and the selected
         * <code>prefix</code>.
         * 
         * @since 4.0.12
         */
        public boolean onInsertPrefix(EntryCompletion source, String prefix);
    }

    /**
     * Hook up the <code>EntryCompletion.InsertPrefix</code> handler.
     * 
     * @since 4.0.12
     */
    public void connect(EntryCompletion.InsertPrefix handler) {
        GtkEntryCompletion.connect(this, handler, false);
    }

    /*
     * This internal class is needed because the TreeIter passed to the
     * handler does not have the model field properly set, so we need to set
     * it before passing the TreeIter to the user.
     */
    private static class CursorOnMatchHandler implements GtkEntryCompletion.CursorOnMatchSignal
    {
        private final EntryCompletion.CursorOnMatch handler;

        private CursorOnMatchHandler(EntryCompletion.CursorOnMatch handler) {
            super();
            this.handler = handler;
        }

        public boolean onCursorOnMatch(EntryCompletion source, TreeModel model, TreeIter iter) {
            iter.setModel(model);
            return handler.onCursorOnMatch(source, model, iter);
        }
    }

    /**
     * Emitted when the cursor is on a completion string without select it.
     * The default behavior is to make the entry display the contents of the
     * row column pointed by <code>iter</code>.
     * 
     * <pre>
     * final DataColumnString column;
     * final EntryCompletion completion;
     * 
     * ...
     * 
     * completion.connect(new CursorOnMatch.InsertPrefix() {
     *     public boolean onCursorOnMatch(EntryCompletion source, TreeModel model, TreeIter iter) {
     *         final Entry entry;
     *         final String content;
     * 
     *         entry = source.getEntry();
     *         content = model.getValue(iter, column);
     * 
     *         entry.setText(content);
     * 
     *         return true;
     *     }
     * });
     * </pre>
     * 
     * @since 4.0.12
     */
    public interface CursorOnMatch extends GtkEntryCompletion.CursorOnMatchSignal
    {
        /**
         * The useful parameters are <code>model</code> and <code>iter</code>
         * which will make you able to get the value of the selected
         * completion using the TreeModel's
         * {@link TreeModel#getValue(TreeIter, DataColumnString) getValue()}
         * method.
         * 
         * @since 4.0.12
         */
        public boolean onCursorOnMatch(EntryCompletion source, TreeModel model, TreeIter iter);
    }

    /**
     * Hook up the <code>EntryCompletion.CursorOnMatch</code> handler.
     * 
     * @since 4.0.12
     */
    public void connect(EntryCompletion.CursorOnMatch handler) {
        GtkEntryCompletion.connect(this, new CursorOnMatchHandler(handler), false);
    }

    /*
     * This internal class is needed because the TreeIter passed to the
     * handler does not have the model field properly set, so we need to set
     * it before passing the TreeIter to the user.
     */
    private static class MatchHandler implements GtkEntryCompletion.MatchSignal
    {
        private final Match handler;

        private MatchHandler(Match handler) {
            this.handler = handler;
        }

        public boolean onMatch(EntryCompletion source, String key, TreeIter iter) {
            iter.setModel(source.getModel());
            return handler.onMatch(source, key, iter);
        }
    }

    /**
     * The callback invoked when a EntryCompletion wants to ask if a given row
     * in the TreeModel should be in the completion list.
     * 
     * Generally, when you will receive the callback you will reach into the
     * source's {@link #getModel() model} and query a column by which you will
     * determine whether or not to include this row. It allows you to a create
     * a more complex completion behavior into the
     * <code>EntryCompletion.Match</code> callback. The behavior of a default
     * EntryCompletion can be written like that:
     * 
     * <pre>
     * final DataColumnString column;
     * final EntryCompletion completion;
     * 
     * ...
     * 
     * completion.setMatchCallback(new EntryCompletion.Match() {
     *     public boolean onMatch(EntryCompletion source, String key, TreeIter iter) {
     *         final TreeModel model;
     *         final String text;
     * 
     *         model = source.getModel();
     *         text = model.getValue(iter, column);
     * 
     *         return text.startsWith(key);
     *     }
     * });
     * </pre>
     * 
     * <p>
     * <i>If you are researching the GTK API documentation, see
     * <code>(*GtkEntryCompletionMatchFunc)</code>. Creating and invoking this
     * "match" signal is how java-gnome has implemented the function pointer
     * expected by <code>gtk_entry_completion_set_match_func()</code>.</i>
     * 
     * @since 4.0.12
     */
    public interface Match
    {
        /**
         * Tell if a row should be in the completion list or not. Return
         * <code>true</code> for the row to be included in the list, or
         * <code>false</code> for the row to be excluded.
         * 
         * @since 4.0.12
         */
        public boolean onMatch(EntryCompletion source, String key, TreeIter iter);
    }

    /**
     * Hookup the <code>EntryCompletion.Match</code> callback that will be
     * used to determine if a row of the associated TreeModel should be in the
     * completion list.
     * 
     * @since 4.0.12
     */
    public void setMatchCallback(EntryCompletion.Match callback) {
        GtkEntryCompletionOverride.setMatchFunc(this);
        GtkEntryCompletion.connect(this, new MatchHandler(callback), false);
    }
}
