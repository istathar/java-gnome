/*
 * TreeStore.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008      Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A TreeModel that stores its data in a hierarchical tree. TreeStore is a
 * concrete TreeModel subclass where rows can also have other rows that are
 * children. This model is suitable for hierarchical data where each row has a
 * parent and a list of children. If you just want to store a list of rows
 * (ie, plain tabular data), {@link ListStore ListStore} is a better
 * alternative.
 * 
 * <p>
 * TreeStores are instantiated much like ListStores, as specified in the
 * {@link DataColumn} class. In summary, given
 * 
 * <pre>
 * final DataColumnString filename;
 * final DataColumnPixbuf icon;
 * final TreeStore model;
 * ...
 * </pre>
 * 
 * you build a two column model as follows:
 * 
 * <pre>
 * model = new TreeStore(new DataColumn[] {
 *         filename = new DataColumnString(), icon = new DataColumnPixbuf()
 * });
 * </pre>
 * 
 * You can then add new rows to the TreeStore. To add a new row at the top
 * level of the hierarchy, you just use {@link #appendRow() appendRow()} as
 * normal:
 * 
 * <pre>
 *         row = model.appendRow();
 *         model.setValue(row, ...);
 * </pre>
 * 
 * If, however, you want to add a new row as a child of an existing row, you
 * need to pass the <i>parent</i> to the second
 * {@link #appendRow(TreeIter) appendRow()} method:
 * 
 * <pre>
 *         row = model.appendRow(parent);
 *         model.setValue(row, ...);
 * </pre>
 * 
 * <p>
 * The caveat described in ListStore applies here; don't declare your model as
 * type TreeModel because you'll need {@link #appendRow() appendRow()} method
 * which is here, on the concrete type TreeStore. In other words, do:
 * 
 * <pre>
 * TreeStore model = new TreeStore(...);
 * </pre>
 * 
 * as shown above, not:
 * 
 * <pre>
 * TreeModel model = new TreeStore(...);
 * </pre>
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.7
 */
public class TreeStore extends TreeModel implements TreeDragSource, TreeDragDest, TreeSortable
{
    protected TreeStore(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new TreeStore with the given column types. See
     * {@link DataColumn DataColumn} for details. You must include at least
     * one DataColumn in your <code>types</code> array (you can't have a
     * TreeStore with no columns).
     * 
     * @since 4.0.7
     */
    public TreeStore(DataColumn[] types) {
        super(GtkTreeModelOverride.createTreeStore(typesToClassNames(types)));
    }

    protected void dispatch(TreeIter row, DataColumn column, Value value) {
        GtkTreeStore.setValue(this, row, column.getOrdinal(), value);
    }

    /**
     * Append a new row after the last child of the given row. You'll need to
     * fill in the various columns with one of the various
     * {@link TreeModel#setValue(TreeIter, DataColumnString, String) setValue()}
     * methods, of course.
     * 
     * @param parent
     *            The row where the new child will be added. You can submit
     *            <code>null</code> to add the new row at the top level.
     *            Note that in this case you can also use
     *            {@link #appendRow() appendRow()}.
     * @return An iterator that represents the row newly created.
     * @since 4.0.7
     */
    public TreeIter appendRow(TreeIter parent) {
        final TreeIter iter;

        checkIter(parent);
        iter = new TreeIter(this);

        GtkTreeStore.append(this, iter, parent);

        return iter;
    }

    /**
     * Append a new row at the top level of this TreeStore. If what you want
     * is to add a row as a child of an already existing row, you should use
     * {@link #appendRow(TreeIter) appendRow(parent)} instead.
     * 
     * @return An iterator that represents the newly created row.
     * @since 4.0.7
     */
    public TreeIter appendRow() {
        return appendRow(null);
    }

    /**
     * Returns whether the given <code>row</code> has at least one child
     * row.
     * 
     * <p>
     * You can use {@link #iterChildren() iterChildren()} to get the actual
     * children.
     * 
     * @return <code>true</code> if the specified row has one or more
     *         children, <code>false</code> otherwise.
     * @since 4.0.7
     */
    public boolean iterHasChild(TreeIter row) {
        checkIter(row);
        return GtkTreeModel.iterHasChild(this, row);
    }

    /**
     * Get the children of the given <code>row</code>, if any.
     * 
     * <p>
     * You can use the returned TreeIter to iterate on children rows as
     * follows:
     * 
     * <pre>
     * child = model.iterChildren(row);
     * if (child == null) {
     *     return;
     * }
     * 
     * do {
     *     // do something with child row
     * } while (child.iterNext());
     * </pre>
     * 
     * which works because iterating with a TreeIter will only iterate over
     * the rows that are siblings, ie, are at the same level of the hierarchy.
     * 
     * @return A TreeIter pointing at the first child of this row, or
     *         <code>null</code> if the row has no children.
     * @since 4.0.7
     */
    public TreeIter iterChildren(TreeIter row) {
        final TreeIter child;

        checkIter(row);
        child = new TreeIter(this);

        if (GtkTreeModel.iterChildren(this, child, row)) {
            return child;
        } else {
            return null;
        }
    }

    /**
     * Get the parent of the given <code>row</code>, assuming there is one.
     * 
     * @return The parent row, or <code>null</code> if this row has no
     *         parent.
     * @since 4.0.7
     */
    public TreeIter iterParent(TreeIter row) {
        final TreeIter parent;

        checkIter(row);
        parent = new TreeIter(this);

        if (GtkTreeModel.iterParent(this, parent, row)) {
            return parent;
        } else {
            return null;
        }
    }

    /**
     * Remove all elements from this TreeStore.
     * 
     * @since 4.0.7
     */
    public void clear() {
        GtkTreeStore.clear(this);
    }

    public void setSortColumn(DataColumn column, SortType ordering) {
        GtkTreeSortable.setSortColumnId(this, column.getOrdinal(), ordering);
    }
}
