/*
 * TreeStore.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
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
 * concrete TreeModel subclass where rows can also have other children rows.
 * This model is suitable for hierarchical data where each row has a parent
 * and a list of children. If you just want to store a list of rows, 
 * {@link ListStore ListStore} is a better alternative.
 * 
 * 
 * <p>
 * TreeStores are instantiated much like ListStores, as specified in the
 * {@link DataColumn} class. In summary, given
 * 
 * <pre>
 * final DataColumnString fileName;
 * final DataColumnPixbuf icon;
 * final TreeStore model;
 * ...
 * </pre>
 * 
 * you build a two column model as follows:
 * 
 * <pre>
 * model = new TreeStore(new DataColumn[] {
 *         fileName = new DataColumnString(),
 *         icon = new DataColumnPixbuf()
 * });
 * </pre>
 * 
 * <p>
 * You can then add new rows to the TreeStore. To add a new row at the top
 * level of the hierarchy, you can just use
 * 
 * <pre>
 *         row = model.appendRow();
 *         model.setValue(row, ...);
 * </pre>
 * 
 * <p>
 * However, if you want to add a new row as a child of an existing row, you
 * need to pass the parent to the appendRow() method:
 * 
 * <pre>
 *         row = model.appendRow(parent);
 *         model.setValue(row, ...);
 * </pre>
 * 
 * <p>
 * Although we talk about TreeModels all the time as the base superclass, it's
 * not a good idea to declare your model as a TreeModel when instantiating
 * because you'll need {@link #appendRow() appendRow()} method which is here,
 * on the concrete type TreeStore. In other words, do:
 * 
 * <pre>
 * TreeStore model = new TreeStore(...);
 * </pre>
 * 
 * as shown above, not:
 * 
 * <pre>
 * TreeStore model = new TreeStore(...);
 * </pre>
 * 
 * @author Vreixo Formoso
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
     */
    public TreeStore(DataColumn[] types) {
        super(GtkTreeModelOverride.createTreeStore(typesToClassNames(types)));
    }
    
    /**
     * @see TreeModel#dispatch(TreeIter, DataColumn, Value)
     */
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
     *      The row where the new child will be added. You can submit 
     *      <code>null</code> to add the new row at the top level. Note that
     *      in this case you can also use {@link #appendRow() appendRow()}.
     * @return
     *      An iterator that represents the row newly created.
     */
    public TreeIter appendRow(TreeIter parent) {
        final TreeIter iter;

        iter = new TreeIter(this);

        GtkTreeStore.append(this, iter, parent);

        return iter;
    }

    /**
     * Append a new row at the top level of this TreeStore. If what you want
     * is to add a row as a child of an already existing row, you should use
     * {@link #appendRow(TreeIter) appendRow(parent)} instead.
     * 
     * @return
     *      An iterator that represents the row newly created.
     */
    public TreeIter appendRow() {
        return appendRow(null);
    }

    /**
     * Remove all elements from this TreeStore.
     */
    public void clear() {
        GtkTreeStore.clear(this);
    }
}
