/*
 * TreeView.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Display the data from a TreeModel in a tabular form. TreeViews are
 * ubiquitous in most applications, being used to both output data in list
 * form, as well as allowing the user to select one or more items from a list.
 * TreeView is the view part of GTK's model-view-controller pattern list
 * Widget, with one of the TreeModel subclasses supplying the underlying data
 * model.
 * 
 * <p>
 * The TreeView API is very powerful, but with that power comes considerable
 * complexity. To build a working TreeModel backed TreeView you will need to
 * follow the instructions presented in the documentation quite carefully. The
 * remainder of this page discusses the presentation side of the API; see
 * {@link DataColumn DataColumn} for a detailed overview of the contents side.
 * 
 * <p>
 * A TreeView is composed of one or more vertical columns called
 * {@link TreeViewColumn TreeViewColumn}s. Into these are packed
 * {@link CellRenderer CellRenderer}s. A CellRenderer does the job of taking
 * data from the underlying TreeModel and rendering it in the TreeViewColumn.
 * There is a family of CellRenderers for different underlying data types, but
 * you'll use CellRendererText almost exclusively.
 * 
 * <p>
 * Let's assume we have a ListStore with a String DataColumn in it, ie
 * 
 * <pre>
 * final ListStore model;
 * final DataColumnString countryNameColumn;
 * final TreeView view;
 * final TreeSelection selection;
 * TreeViewColumn vertical;
 * CellRendererText text;
 * 
 * ...
 * model = new ListStore(new DataColumn[] {
 *     countryNameColumn,
 *     ...
 * }
 * </pre>
 * 
 * Note that there is nothing that requires you to <i>populate</i> your model
 * before building your TreeView. You can do that later - indeed, that might
 * be the whole point of your application.
 * 
 * <p>
 * You start creating your view by instantiating a TreeView and then using it
 * to get TreeViewColumn instances:
 * 
 * <pre>
 * view = new TreeView(model);
 * vertical = view.appendColumn();
 * vertical.setTitle(&quot;Country&quot;);
 * </pre>
 * 
 * Now you construct a CellRenderer, specifying what TreeViewColumn it's going
 * to be a part of, and then the most important part, specifying where its
 * data is going to come from. This is the step that binds TreeView and
 * TreeModel.
 * 
 * <pre>
 * text = new CellRendererText(vertical);
 * text.setText(countryNameColumn);
 * </pre>
 * 
 * along with setting any other properties on the CellRenderer as necessary.
 * And that's it! You will of course need to do this for each TreeViewColumn
 * of information you wish to have showing in your TreeView. (We tend to find
 * it easier if you reuse the TreeViewColumn and CellRenderer variable names;
 * there is usually no real reason to keep a reference to them individually;
 * otherwise you've got to come up with unique names for everything and that
 * tends to make for ugly code).
 * 
 * <p>
 * Dealing with the events generated on the TreeView is either straight
 * forward or quite complicated, depending on what you are trying to
 * accomplish. If you just need a callback when the user activates a row in
 * the display, then the {@link TreeView.ROW_ACTIVATED ROW_ACTIVATED} signal
 * will do the trick fairly simply; see its documentation for an example. For
 * anything else, you will need to use the {@link TreeSelection TreeSelection}
 * helper class (every TreeView automatically has one). It has a
 * {@link TreeSelection.CHANGED CHANGED} signal which you hook up to which
 * will tell you what row(s) are currently selected.
 * 
 * <pre>
 * selection = view.getSelection();
 * </pre>
 * 
 * <p>
 * The design of the TreeView API is such that you can have more than one view
 * for a given TreeModel, but we tend to only create TreeModels as the place
 * to push the text that we wish displayed, so in general you'll have one
 * TreeModel per TreeView.
 * 
 * <p>
 * <i>We have departed a fair way from the method call sequence used in the
 * underlying GTK library, in particular by assuming default behaviour and
 * combining calls where possible. This is in an effort to make the TreeView
 * API somewhat easier to learn, more appropriate in a Java context, and
 * easier to use for the common cases which dominate its usage.</i>
 * 
 * @author Andrew Cowie
 * @author Srichand Pendyala
 * @since 4.0.5
 */
public class TreeView extends Container
{
    protected TreeView(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new TreeView with an already established TreeModel as its
     * data.
     */
    public TreeView(TreeModel store) {
        super(GtkTreeView.createTreeViewWithModel(store));
    }

    /**
     * Construct a new TreeView. If you use this constructor, you will need to
     * call {@link #setModel(TreeModel) setModel()} before any data will be
     * displayable!
     */
    public TreeView() {
        super(GtkTreeView.createTreeView());
    }

    /**
     * Set the TreeModel being used to source data for this TreeView. If a
     * model has already been set, calling this will replace it.
     * 
     * @param store
     *            a value of <code>null</code> will remove the data model
     *            underlying this TreeView, leaving it unset for the present.
     */
    public void setModel(TreeModel store) {
        GtkTreeView.setModel(this, store);
    }

    /**
     * Get the TreeModel currently providing the data powering this TreeView
     * Widget, or <code>null</code> if not yet set.
     */
    public TreeModel getModel() {
        return GtkTreeView.getModel(this);
    }

    /**
     * Create a new TreeViewColumn and add it to right-hand edge of this
     * TreeView.
     */
    /*
     * It is quite easy to screw up by creating a TreeViewColumn, configuring
     * it and its CellRenderer, only to forget to add it to the TreeView. We
     * get around this by making appendColumn() return a new TreeViewColumn.
     * Nicely complements appendRow() returning a TreeIter in the TreeModels()
     * too.
     */
    public TreeViewColumn appendColumn() {
        final TreeViewColumn vertical;

        vertical = new TreeViewColumn();

        GtkTreeView.appendColumn(this, vertical);

        return vertical;
    }

    /**
     * Set whether this TreeView has a header row at the top of the Widget
     * showing the titles of each of the TreeViewColumns packed into it. The
     * default is <code>true</code>, for headers to be visible.
     */
    public void setHeadersVisible(boolean setting) {
        GtkTreeView.setHeadersVisible(this, setting);
    }

    /**
     * Set whether the column titles in the header row can be clicked to
     * change the sorting of the displayed data. While the default is
     * <code>false</code> (since you frequently have the rows ordered the
     * way they are for a reason and don't want to let the user be reordering
     * the display and getting lost in the process), calling TreeViewColumn's
     * {@link TreeViewColumn#setSortColumn(DataColumn) setSortColumn()} will
     * make the headers clickable. Use this method after your column setup to
     * turn it off [again].
     */
    public void setHeadersClickable(boolean setting) {
        GtkTreeView.setHeadersClickable(this, setting);
    }

    /**
     * Emitted when a row in the TreeView has been activated. Activation
     * occurs when a row in the view is double-clicked, or when
     * <code>Space</code> or <code>Enter</code> is pressed while a row is
     * selected.
     * 
     * <p>
     * In general, you've got the TreeModel and especially its DataColumns
     * visible, so to use <code>ROW_ACTIVATED</code> you can just:
     * 
     * <pre>
     * final TreeModel model;
     * final DataColumnString column;
     * 
     * view.connect(new TreeView.ROW_ACTIVATED() {
     *     public void onRowActivated(TreeView source, TreePath path, TreeViewColumn vertical) {
     *         final TreeIter row;
     * 
     *         row = model.getIter(path);
     * 
     *         ... = model.getValue(row, column);
     *     }
     * });
     * </pre>
     * 
     * Remember that TreeIters and TreePaths are not stable over changes to
     * the model, so get on with using <code>path</code> right away.
     * 
     * <p>
     * <code>ROW_ACTIVATED</code> is perfectly sufficient for basic
     * situations, but you may need to see TreeSelection's
     * {@link TreeSelection.CHANGED CHANGED} to for more complicated selection
     * and activation expressions. In practise you'll use both.
     * 
     * @author Andrew Cowie
     * @since 4.0.5
     */
    public interface ROW_ACTIVATED extends GtkTreeView.ROW_ACTIVATED
    {
        /**
         * The useful parameter is usually <code>path</code> which can be
         * converted into a TreeIter with your TreeModel's
         * {@link TreeModel#getIter() getIter()} allowing you to then lookup a
         * particular value from the data model. You rarely need
         * <code>vertical</code> but it can give you some indication in
         * which column the click happened.
         */
        public void onRowActivated(TreeView source, TreePath path, TreeViewColumn vertical);
    }

    /**
     * Hook up a <code>ROW_ACTIVATED</code> handler.
     */
    public void connect(ROW_ACTIVATED handler) {
        GtkTreeView.connect(this, handler);
    }

    /**
     * Get the TreeSelection object corresponding to this TreeView. Every
     * TreeView has a TreeSelection which is a utility instance allowing you
     * to manipulate the state of the selected row(s) in the TreeView. This
     * method gives you access to it.
     */
    public TreeSelection getSelection() {
        return GtkTreeView.getSelection(this);
    }

    /**
     * Set whether or not the built-in quick search capability will be enabled
     * for this TreeView. If the user focuses the TreeView and starts typing
     * characters a small popup Entry will appear and the characters entered
     * will be used to interactively search through the list and will
     * progressively select the row which is the closest match.
     * 
     * <p>
     * Use {@link #setSearchColumn(DataColumn) setSearchColumn()} to indicate
     * which data source in your TreeModel is actually what the interactive
     * text search will seek through.
     * 
     * <p>
     * The default is <code>true</code>, so this method is only called when
     * you wish to disable type-ahead find.
     */
    public void setEnableSearch(boolean setting) {
        GtkTreeView.setEnableSearch(this, setting);
    }

    /**
     * Check if the built-in quick search capability is enabled for this
     * TreeView. The default is <code>true</code>.
     * 
     * <p>
     * Use {@link #setEnableSearch(boolean) setEnableSearch()} to disable or
     * enable the search feature.
     * 
     * @return <code>true</code> if the quick search capability is enabled;
     *         <code>false</code> otherwise.
     */

    public boolean getEnableSearch() {
        return GtkTreeView.getEnableSearch(this);
    }

    /**
     * Set the column in your TreeModel which will searched through if the
     * user starts an interactive search. Ordinarily this can just be the
     * DataColumnString of whichever column you want as the index, but if you
     * have applied extensive formatting then you may need to supply an
     * auxiliary column with the data in cleaner form (the use of
     * DataColumnIntegers to provide sort order for verticals that are
     * displaying formatting via
     * {@link TreeViewColumn#setSortColumn(DataColumn) setSortColumn()} is
     * analogous).
     */
    public void setSearchColumn(DataColumn column) {
        GtkTreeView.setSearchColumn(this, column.getOrdinal());
    }

    /**
     * Get the current Entry widget being used for the interactive search
     * feature for this TreeView. If the built-in widget is being used for
     * search, then <code>null</code> is returned.
     */
    public Entry getSearchEntry() {
        return GtkTreeView.getSearchEntry(this);
    }

    /**
     * Set an Entry to be used as an alternative to the default built-in popup
     * used by the the interactive search. This is useful for occasions when
     * you want to put the search Entry at some fixed location elsewhere in
     * your UI.
     * 
     * <p>
     * To reset the TreeView to use the built-in popup Entry, pass in
     * <code>null</code>.
     */
    public void setSearchEntry(Entry entry) {
        GtkTreeView.setSearchEntry(this, entry);
    }

    /**
     * Set whether rubber banding is enabled in this TreeView.
     * 
     * <p>
     * Rubber banding affects how selections work when the selection mode is
     * set to {@link SelectionMode#MULTIPLE MULTIPLE}. When set to
     * <code>true</code> then rubber banding will allow the user to select
     * multiple rows with the mouse. Rubber banding is off by default.
     */
    public void setRubberBanding(boolean enable) {
        GtkTreeView.setRubberBanding(this, enable);
    }

    /**
     * Get the current status of the rubber banding property of the TreeView.
     * See {@link #setRubberBanding(boolean) setRubberBanding()} for a
     * detailed description of how rubber banding works.
     */
    public boolean getRubberBanding() {
        return GtkTreeView.getRubberBanding(this);
    }

    /**
     * Get the Adjustment currently being used for the horizontal aspect of
     * this TreeView. If no horizontal adjustment is being used, then a
     * <code>null</code> is returned. To set this value, see
     * {@link #setHAdjustment(Adjustment) setHAdjustment()}.
     */
    public Adjustment getHAdjustment() {
        return GtkTreeView.getHadjustment(this);
    }

    /**
     * Set the Adjustment for the horizontal aspect of this TreeView. To fetch
     * the current value of the horizontal adjustment aspect of this TreeView,
     * use {@link #getHAdjustment() getHAdjustment()}.
     */
    public void setHAdjustment(Adjustment adjustment) {
        GtkTreeView.setHadjustment(this, adjustment);
    }

    /**
     * Get the Adjustment for the vertical aspect of this TreeView. If the
     * vertical Adjustment has not been previously set, this value is
     * <code>null</code>.
     */
    public Adjustment getVAdjustment() {
        return GtkTreeView.getVadjustment(this);
    }

    /**
     * Set the Adjustment for the vertical aspect of this TreeView. To fetch
     * the current vertical adjustment aspect of this TreeView, see
     * {@link #getVAdjustment() getVAdjustment()}.
     */
    public void setVAdjustment(Adjustment adjustment) {
        GtkTreeView.setVadjustment(this, adjustment);
    }

    /**
     * Set the fixed height mode for the TreeView. When set to true, all
     * displayed rows in the TreeView are displayed with the same height. This
     * can have the effect of speeding up the TreeView, although you will have
     * to evaluate this in the specific circumstances particular to your
     * application.
     * 
     * <p>
     * To fetch the current height mode, see
     * {@link #getFixedHeightMode() getFixedHeightMode()}.
     * 
     * @param enable
     *            <code>true</code> if all rows in the TreeView are to be of
     *            the same height; <code>false</code> otherwise. The default
     *            is <code>false</code>.
     */
    public void setFixedHeightMode(boolean enable) {
        GtkTreeView.setFixedHeightMode(this, enable);
    }

    /**
     * Get the current fixed height mode for the TreeView. When set to true,
     * all displayed rows in the TreeView are displayed with the same height.
     * 
     * <p>
     * To set the current height mode, see
     * {@link #setFixedHeightMode(boolean) setFixedHeightMode()}
     * 
     * @return <code>true</code> if all rows are to be of the same height;
     *         <code>false</code> otherwise.
     */
    public boolean getFixedHeightMode() {
        return GtkTreeView.getFixedHeightMode(this);
    }

    /**
     * This signal is emitted when the user selects all the rows in the
     * TreeView. This usually occurs, when the user presses the
     * <code>Ctrl+A</code> key combination.
     * 
     * <p>
     * This signal is particularly useful, when you wish to be able to offer
     * the user an option to do some manipulation on the data, when all data
     * is selected. For instance, upon selecting all the rows of the TreeView,
     * in say, an email client, where each row represents an email, an option
     * to mark all emails as read can be made to pop up.
     * 
     * <p>
     * This signal should also be used with much care. The "Principle of Least
     * Surprise" is rather easy to violate by misusing this signal.
     * 
     * @author Srichand Pendyala
     * 
     */
    public interface SELECT_ALL extends GtkTreeView.SELECT_ALL
    {
        public boolean onSelectAll(TreeView source);
    }

    /**
     * Hook up a <code>SELECT_ALL</code> signal handler.
     */
    public void connect(SELECT_ALL handler) {
        GtkTreeView.connect(this, handler);
    }
}
