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
}
