/*
 * TreeView.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Display the data from a TreeModel in a tabular form. You can select and
 * activate rows, ...
 * 
 * This is the view part of GTK's TreeView/TreeModel model-view-controller
 * pattern based list Widget. TreeView is very powerful, but with that power
 * comes considerable complexity...
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
     * This is perfectly sufficient for basic situations, but you may need to
     * see {@link TreeSelection TreeSelection} to for more complicated
     * selection and activation expressions.
     * 
     * @author Andrew Cowie
     * @since 4.0.5
     */
    public interface ROW_ACTIVATED extends GtkTreeView.ROW_ACTIVATED
    {
        /**
         * The useful parameter is usually<code>path</code> which can be
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
     * Check if the built-in quick search capability is enabled for this TreeView.
     * The default is <code>true</code>. 
     * 
     * <p>
     * Use {@link #setEnableSearch(boolean) setEnableSearch()} to disable or enable
     * the search feature.
     * @return <code>true</code> if the quick search capability is enabled; <code>false</code>
     * otherwise.
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
     * search, then a <code>null</code> is returned. Thus, check for a null
     * value, before attempting to use the object that is returned.
     * 
     * @return Entry being used for interactive search; null if built-in widget
     * is in use.
     */
    
    public Entry getSearchEntry(){
        return GtkTreeView.getSearchEntry(this);
    }
    
    /**
     * Set the Entry widget to be used for the interactive search. The default
     * is a built-in widget that appears when the user starts to type the 
     * search string. You should use this method if you want to use your own
     * Entry widget for the search string.
     * 
     * If the given Entry is <code>null</code> then no Exception is thrown at this
     * point. But any attempt to search by the user will result in a
     * NullPointerException. 
     * 
     * @param entry
     *              The Entry widget to be used for the interactive search.
     */
    
    public void setSearchEntry(Entry entry){
        GtkTreeView.setSearchEntry(this, entry);
    }
    
    /**
     * Set the rubber banding property of the TreeView. If the TreeView's
     * selection mode is set to <code>GTK_SELECTION_MULTIPLE</code>, then
     * rubber banding will allow the user to select multiple rows with the
     * mouse. This property is set to <code>false</code> by default.
     * 
     * @param enable
     *              Set the rubber banding property of the TreeView
     */
    
    public void setRubberBanding(boolean enable){
        GtkTreeView.setRubberBanding(this, enable);
    }
    
    /**
     * Get the current status of the rubber banding property of the TreeView.
     * This property is <code>false</code> by default. To set this property,
     * use setRubberBanding(). See {@link #setRubberBanding(boolean) setRubberBanding()}.
     * 
     * @return <code>true</code> if the rubber banding property is enabled.
     * <code>false</code> otherwise.
     */
    
    public boolean getRubberBanding(){
        return GtkTreeView.getRubberBanding(this);
    }
    
    
    /**
     * Get the Adjustment currently being used for the horizontal aspect of this
     * TreeView. If no horizontal adjustment is being used, then a null is returned.
     * To set this value, see {@link #getHAdjustment() getHAdjustment()}
     */
    
    public Adjustment getHAdjustment(){
        return GtkTreeView.getHadjustment(this);
    }
    
    /**
     * Set the Adjustment for the horizontal aspect of this TreeView. To 
     * fetch the current value of the horizontal adjustment aspect of this
     * TreeView, see {@link #setHAdjustment(Adjustment) setHAdjustment()}
     */
    
    public void setHAdjustment(Adjustment adjustment){
        GtkTreeView.setHadjustment(this, adjustment);
    }
    
}