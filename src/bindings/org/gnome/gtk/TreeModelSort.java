/*
 * TreeModelSort.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Take a TreeModel and add sorting capability to it. This class takes an
 * existing model and creates a new model of it sorted as specified (the data
 * is not copied, but the TreeModel that results from creating a TreeModel
 * sort can be used independently). It listens to and reacts to the signals
 * emitted by the underlying base TreeModel. The end result is that of
 * allowing you to have two different TreeViews with their own sort of the
 * same underlying data set. This is potentially useful if you have a common
 * TreeModel backing a number of different presentations, although you should
 * be cognisant that a point will be reached where it is more efficient to
 * simply have separate models.
 * 
 * <p>
 * A TreeIter pointing into this TreeModelSort is <b>not</b> valid in the
 * underlying "child" TreeModel. If you need to change data in the base model,
 * use {@link #convertIterToChildIter(TreeIter) convertIterToChildIter()}.
 * 
 * <p>
 * You don't normally have need of this class. Both ListStore and TreeStore
 * implement TreeSortable already, and there are various sorting tools built
 * into the view side of the TreeView/TreeModel MVC framework, notably
 * TreeViewColumn's
 * {@link TreeViewColumn#setSortColumn(DataColumn) setSortColumn()}. If,
 * however, you are using a {@link TreeModelFilter}, you will need to wrap it
 * in one of these to make sorting work normally again.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class TreeModelSort extends TreeModel implements TreeDragSource, TreeSortable
{
    protected TreeModelSort(long pointer) {
        super(pointer);
    }

    /**
     * Create a new TreeModelSort, wrapping an existing model.
     * 
     * @since 4.0.6
     */
    public TreeModelSort(TreeModel base) {
        super(GtkTreeModelSort.createTreeModelSortWithModel(base));
    }

    /**
     * Convert a TreeIter pointing into this TreeModelSort into a TreeIter
     * valid in the underlying base TreeModel that is being proxied.
     * 
     * <p>
     * TODO <i>We need to test the limitations of this, as several people have
     * actually been getting away with not worrying about converting at all,
     * so clearly something isn't quite as expected.</i>
     * 
     * @since <span style="color:red;">Unstable</span>
     */
    public TreeIter convertIterToChildIter(TreeIter row) {
        final TreeIter result;
        final TreeModel child;

        child = GtkTreeModelSort.getModel(this);
        result = new TreeIter(child);

        GtkTreeModelSort.convertIterToChildIter(this, result, row);

        return result;
    }

    public void setSortColumn(DataColumn column, SortType ordering) {
        GtkTreeSortable.setSortColumnId(this, column.getOrdinal(), ordering);
    }
}
