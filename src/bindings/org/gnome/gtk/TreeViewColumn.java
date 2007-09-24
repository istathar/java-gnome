/*
 * TreeViewColumn.java
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
 * A vertical visible column as presented in a TreeView Widget. A
 * TreeViewColumn's primary role is to connect one or more CellRenderers to a
 * vertical position in the tabular layout of a TreeView and then to assign
 * which attributes of that CellRenderer are powered by what rows from the
 * data TreeModel that will underlie the TreeView.
 * 
 * <p>
 * You get a TreeViewColumn by calling TreeView's
 * {@link TreeView#appendColumn() appendColumn()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
/*
 * At considerable odds with the underlying library, we have moved the logic
 * relating to packing CellRenderers and mapping attributes to TreeModel
 * columns over to the CellRenderer classes.
 */
public class TreeViewColumn extends Object implements CellLayout
{
    protected TreeViewColumn(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new TreeViewColumn. This doesn't need to be publicly
     * visible; you create one by appending a column to a TreeView with
     * appendColumn()
     */
    protected TreeViewColumn() {
        super(GtkTreeViewColumn.createTreeViewColumn());
    }

    /**
     * Set the title to be used for this TreeViewColumn in the TreeView's
     * header row. In addition to being descriptive, the title are what you
     * click to cause this column to be the one that is sorting the TreeView
     * (assuming you've enabled the columns to be sortable with TreeView's
     * {@link TreeView#setHeadersClickable(boolean) setHeadersClickable(true)}
     * and FIXME, and that the titles are visible in the first place, ie the
     * header row hasn't been turned off via TreeView's
     * {@link TreeView#setHeadersVisible(boolean) setHeadersVisible(false)}).
     */
    public void setTitle(String title) {
        GtkTreeViewColumn.setTitle(this, title);
    }

    /**
     * Set whether the column header allows itself to be clicked. The default
     * is <code>false</code>, not to be clickable, also meaning that header
     * won't take the keyboard focus either. Since calling
     * {@link #setSortColumn(DataColumn) setSortColumn()} will set
     * <var>clickable</var> to <code>true</code>, so you only need this
     * for the case where you assign a sort order to a column, but don't want
     * users to be able to change the sorting column or its order.
     */
    public void setClickable(boolean setting) {
        GtkTreeViewColumn.setClickable(this, setting);
    }

    /**
     * Set the DataColumn that will be used to sort this TreeViewColumn should
     * sorting be enabled.
     * 
     * <p>
     * Calling this also makes the TreeViewColumn <var>clickable</var>, but
     * it does not automatically engage the sorting (maybe you only want
     * sorting to be optional and only active if the user clicks a header,
     * certainly if you've set sort information for many columns you will have
     * to indicate explicitly which one is to be active first, etc). Use
     * {@link #clicked()} to activate this column as the one doing the
     * sorting.
     */
    public void setSortColumn(DataColumn column) {
        GtkTreeViewColumn.setSortColumnId(this, column.getOrdinal());
    }

    /**
     * Activate this column's sorting by sending a click the TreeViewColumn
     * header. Causes <code>CLICKED</code> to fire, but more importantly
     * this causes this vertical to become the active column dictating the
     * ordering of the TreeView as a whole (assuming the vertical is
     * {@link #setClickable(boolean) setClickable(true)} and assuming a
     * DataColumn has been set with
     * {@link #setSortColumn(DataColumn) setSortColumn()} to indicate the
     * ordering).
     */
    public void clicked() {
        GtkTreeViewColumn.clicked(this);
    }

    /**
     * Set whether this TreeViewColumn will share in additional space
     * available to the parent TreeView. Like Widgets packed into Containers,
     * ordinarily a TreeViewColumn will be allocated the space it needs but no
     * more, and the TreeView will be the size of the sum of these requests.
     * If the TreeView is allocated space over and above this, however, then
     * this additional space will be given to those TreeViewColumns with this
     * property set to <code>true</code>.
     * 
     * <p>
     * The default for new TreeViewColumns is <code>false</code>. The last
     * (right-most) column is treated specially, however. Extra space given to
     * the TreeView as a whole will automatically go to the last vertical
     * column (ie, ignoring this being set <code>false</code>) unless one
     * or more other columns have had <var>expand</var> set to
     * <code>true</code>.
     */
    public void setExpand(boolean setting) {
        GtkTreeViewColumn.setExpand(this, setting);
    }
}
