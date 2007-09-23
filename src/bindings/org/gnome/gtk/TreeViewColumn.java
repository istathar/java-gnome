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
}
