/*
 * EntryCompletion.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Object;

/**
 * An object to use in conjunction with {@link Entry} to provide completion functionality.
 *
 * <p>
 * To add completion functionality to an entry, use the Entry's
 * {@link Entry.setCompletion(EntryCompletion) setCompletion} method.
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
     * Retrieve the <code>Entry</code> that the entry completion is attached to.
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
     *            underlying this EntryCompletion, leaving it unset for the present.
     * @since 4.0.12
     */
    public void setModel(TreeModel store) {
        GtkEntryCompletion.setModel(this, store);
    }

    /**
     * Get the TreeModel currently providing the data powering this EntryCompletion,
     * or null if not yet set.
     * 
     * @since 4.0.12
     */
    public TreeModel getModel() {
        return GtkEntryCompletion.getModel(this);
    }

    /**
     * Set the minimum length of the search key to the value specified by <code>length<code>.
     * This means that the key string (which is in the Entry) must be at least length characters
     * before a completion list will be displayed.
     * 
     * <p>
     * This is useful for long lists, where completing using a small key will take too much
     * time and will return a too large dataset.
     * 
     * @param length
     *            the minimum length of the key string in order to start completing.
     * @since 4.0.12
     */
    public void setMinimumKeyLength(int length) {
        GtkEntryCompletion.setMinimumKeyLength(this, length);
    }

    /**
     * Return the minimum key length set for the entry completion.
     * See the {@link setMinimumKeyLength(int) setMinimumKeyLength()} method for more information.
     * 
     * @since 4.0.12
     */
    public int getMinimumKeyLength() {
        return GtkEntryCompletion.getMinimumKeyLength(this);
    }

    /**
     * Insert an action in the action item list of the EntryCompletion at the position
     * specified by <code>index</code> with the text specified by <code>text</code>.
     * 
     * <p>
     * If you want the action item to have markup, use the
     * {@link insertActionMarkup(int, String) insertActionMarkup()} method.
     * 
     * @since 4.0.12
     */
    public void insertActionText(int index, String text) {
        GtkEntryCompletion.insertActionText(this, index, text);
    }

    /**
     * Insert an action item in the action item list of the EntryCompletion at the position
     * specified by <code>index</code> with the markup specified by <code>markup</code>.
     * 
     * @since 4.0.12
     */
    public void insertActionMarkup(int index, String markup) {
        GtkEntryCompletion.insertActionMarkup(this, index, markup);
    }

    /**
     * Delete the item at the position in the action item list specified by <code>index</code>.
     * 
     * @since 4.0.12
     */
    public void deleteAction(int index) {
        GtkEntryCompletion.deleteAction(this, index);
    }

    /**
     * Create and adds a {@link CellRendererText} using the column specified by <code>column</code>
     * as the source for completion strings
     * 
     * @since 4.0.12
     */
    public void setTextColumn(int column) {
        GtkEntryCompletion.setTextColumn(this, column);
    }

    /**
     * Return the index of the column in the completion model to get strings from.
     * 
     * <p>
     * See the {@link setTextColumn(int) setTextColumn()} method for more information.
     * 
     * @since 4.0.12
     */
    public int getTextColumn() {
        return GtkEntryCompletion.getTextColumn(this);
    }

    /**
     * Request a prefix insertion.
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
     * If <code>setting</code> is <code>true</code>, the common prefix of the possible
     * completions should be automatically inserted in the Entry.
     * 
     * @since 4.0.12
     */
    public void setInlineCompletion(boolean setting) {
        GtkEntryCompletion.setInlineCompletion(this, setting);
    }

    /**
     * Return <code>true</code> if automatic inline completion is enabled.
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
     * If <code>setting</code> is <code>true</code> the completions should
     * be presented in a popup window.
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
     * If <code>setting</code> is <code>true</code> the popup will appear
     * even if there is a single match.
     * 
     * @since 4.0.12
     */
    public void setPopupSingleMatch(boolean setting) {
        GtkEntryCompletion.setPopupSingleMatch(this, setting);
    }

    /**
     * Return <code>true</code> if completion popup appears when there is
     * a single match.
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
     * Enable or disable the possibility of cycle through the possible completions
     * inside the entry.
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
            handler.onMatchSelected(source, model, iter);

            return true;
        }
    }

    /**
     * Emitted when a completion string was selected from the completion list.
     * 
     * <p>
     * Generally, this signal is enough to handle a completion selection and it just
     * used to complete the entry according to the selected completion string.
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
     *
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
         * The useful parameter are <code>model</code> and <code>iter</code> which will
         * make you able to get the value of the selected completion using the TreeModel's
         * {@link TreeModel#getValue(TreeIter, DataColumnString) getValue() method.
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
     * In this way, you can do something different from the {@link MatchSelected} signal.
     * 
     * <pre>
     * final EntryCompletion completion;
     * 
     * ...
     * 
     * completion.insertActionText(0, "complete");
     * completion.insertActionText(1, "another");
     * 
     * completion.connect(new EntryCompletion.ActionActivated() {
     *     public void onActionActivated(EntryCompletion source, int index) {
     *         System.out.println("Action " + index + " activated.");
     *     }
     * });
     * </pre>
     * 
     * @since 4.0.12
     */
    public interface ActionActivated extends GtkEntryCompletion.ActionActivatedSignal
    {
        /**
         * The only two parameters give references to the EntryCompletion <code>source</code>
         * which has emitted the signal and the activated action <code>index</code>.
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
     * Emitted when the inline auto-completion is triggered. The default behavior
     * is to make the entry display the whole prefix and select the newly inserted part.
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
         * The only two parameters give references to the EntryCompletion <code>source</code>
         * which has emitted the signal and the selected <code>prefix</code>.
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
}
