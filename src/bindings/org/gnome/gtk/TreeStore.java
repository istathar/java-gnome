/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2008      Vreixo Formoso
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

/**
 * A TreeModel that stores its data in a hierarchical tree. TreeStore is a
 * concrete TreeModel subclass where rows can also have other rows that are
 * children. This model is suitable for hierarchical data where each row has a
 * parent and a list of children. If you just want to store a list of rows
 * (ie, plain tabular data), {@link ListStore ListStore} is a more appropriate
 * choice.
 * 
 * <p>
 * Instantiating a TreeStore is done identically as with a ListStore, by
 * specifying an array of {@link DataColumn}s. For example, given:
 * 
 * <pre>
 * final TreeStore model;
 * final DataColumnString place;
 * ...
 * </pre>
 * 
 * you would build a model as follows:
 * 
 * <pre>
 * model = new TreeStore(new DataColumn[] {
 *         place = new DataColumnString(),
 *         ...
 * });
 * </pre>
 * 
 * The caveat described in ListStore applies here; don't declare your model as
 * abstract type TreeModel; keep them as concrete type TreeStore so you can
 * get to the TreeStore specific methods for adding rows and navigating the
 * hierarchy. <img src="TreeStore.png" class="snapshot">
 * 
 * <p>
 * To add a new row at the top level of the hierarchy, you just use
 * {@link #appendRow() appendRow()} as you have seen with ListStore:
 * 
 * <pre>
 * parent = model.appendRow();
 * model.setValue(parent, place, &quot;Europe&quot;);
 * </pre>
 * 
 * If, however, you want to add a new row as a child of an existing row, you
 * need to use the {@link #appendChild(TreeIter) appendChild()} method
 * instead:
 * 
 * <pre>
 * child = model.appendRow(parent);
 * model.setValue(child, place, &quot;London&quot;);
 * child = model.appendRow(parent);
 * model.setValue(child, place, &quot;Paris&quot;);
 * ...
 * </pre>
 * 
 * passing the TreeIter representing the parent you wish to create a child
 * under.
 * 
 * <p>
 * You may also want to read the discussion at {@link TreePath} to understand
 * how to address a particular row in a given TreeStore. You will also
 * probably want to be aware of TreeView's
 * {@link TreeView#expandRow(TreePath, boolean) expandRow()},
 * {@link TreeView#expandAll() expandAll()}, and corresponding
 * <code>collapse</code> methods.
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
     * {@link TreeModel#setValue(TreeIter, DataColumnString, String)
     * setValue()} methods, of course.
     * 
     * <p>
     * To add a top level row, use {@link #appendRow() appendRow()}.
     * 
     * @param parent
     *            The row to which a new child will be added.
     * @return A pointer to the newly created child row.
     * @since 4.0.7
     */
    public TreeIter appendChild(TreeIter parent) {
        final TreeIter child;

        if (parent == null) {
            throw new IllegalArgumentException("Use appendRow() to add a top level row");
        }
        checkIter(parent);

        child = new TreeIter(this);

        GtkTreeStore.append(this, child, parent);

        return child;
    }

    /**
     * Append a new row at the top level of this TreeStore. If what you want
     * is to add a row as a child of an already existing row, you use
     * {@link #appendChild(TreeIter) appendChild(parent)}.
     * 
     * @return A pointer to the newly created top level row.
     * @since 4.0.7
     */
    public TreeIter appendRow() {
        final TreeIter iter;

        iter = new TreeIter(this);

        GtkTreeStore.append(this, iter, null);

        return iter;
    }

    /**
     * Returns whether the given <code>row</code> has at least one child row.
     * 
     * <p>
     * You can use {@link #iterChildren(TreeIter) iterChildren()} to get the
     * actual children.
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
     * @return The parent row, or <code>null</code> if this row has no parent.
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

    /**
     * Delete a row from the TreeStore. If there is another row after this
     * <i>at this level</i> then <code>true</code> will be returned and the
     * TreeIter will still be valid. Otherwise, <code>false</code> is returned
     * and <code>row</code> is invalidated.
     * 
     * @since 4.0.7
     */
    public boolean removeRow(TreeIter row) {
        return GtkTreeStore.remove(this, row);
    }

    /**
     * Insert a new row in the TreeStore. The row will be placed as a child of
     * <code>parent</code> at the <code>position</code> specified. If
     * <code>parent</code> is <code>null</code> then the new row will be
     * inserted at the top level of the TreeStore.
     * 
     * @since 4.0.7
     */
    public TreeIter insertRow(TreeIter parent, int position) {
        final TreeIter iter;

        if (position < 0) {
            throw new IllegalArgumentException("position can't be negative");
        }

        iter = new TreeIter(this);

        GtkTreeStore.insert(this, iter, parent, position);

        return iter;
    }

    /**
     * Insert a new row in the TreeStore. The empty row will be a child of
     * <code>parent</code> and will be placed in front of the supplied
     * <code>sibling</code>.
     * 
     * @since 4.0.7
     */
    public TreeIter insertRow(TreeIter parent, TreeIter sibling) {
        final TreeIter iter;

        iter = new TreeIter(this);

        GtkTreeStore.insertBefore(this, iter, parent, sibling);

        return iter;
    }
}
