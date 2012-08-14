/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Takes an existing model and creates a new model of it sorted as specified.
 * The data is not copied, but the TreeModel that results from creating a
 * TreeModelSort can be used independently. It listens to and reacts to the
 * signals emitted by the underlying base TreeModel. The end result is that of
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
 * You need to be careful to use the correct TreeModel for TreeIter pointers
 * you receive in callbacks. The scenario that arises more often is this:
 * 
 * <pre>
 * ListStore model;
 * TreeModelSort sorted;
 * TreeView view;
 * TreeSelection selection;
 * 
 * // usual TreeModel setup
 * model = new ListStore(...);
 * 
 * // then create the sorted one, and use it
 * sorted = new TreeModelSort(model);
 * view = new TreeView(sorted);
 * ...
 * 
 * // then, later
 * selection.connect(new TreeSelection.Changed() {
 *     public void onChanged(TreeSelection source) {
 *         final TreeIter row;
 *         final String str;
 * 
 *         row = selection.getSelected();
 *         if (row == null) {
 *             return;
 *         }
 *         str = model.getValue(row, column);
 *     }
 * }
 * </pre>
 * 
 * the problem that arises is that the retrieved TreeIter <i>is not valid</i>
 * in <code>model</code>. It's a TreeIter in <code>sorted</code>. Your program
 * will crash if you get this wrong. The fix is simple; change it to use the
 * correct TreeModel:
 * 
 * <pre>
 * ...
 *         str = sorted.getValue(row, column);
 * ...
 * </pre>
 * 
 * and things will work fine.
 * 
 * <p>
 * You don't normally have need of this class. Both ListStore and TreeStore
 * implement TreeSortable already, and there are various sorting tools built
 * into the view side of the TreeView/TreeModel MVC framework, notably
 * TreeViewColumn's {@link TreeViewColumn#setSortColumn(DataColumn)
 * setSortColumn()}. If, however, you are using a {@link TreeModelFilter}, you
 * will need to wrap it in one of these to make sorting work normally again.
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

    /**
     * Given a TreeIter obtained from the underying TreeModel, return one that
     * represents the same row but that will be valid in this TreeModelSort.
     * 
     * @since 4.0.9
     */
    public TreeIter convertIterBaseToSort(TreeIter row) {
        final TreeIter result;

        result = new TreeIter(this);

        GtkTreeModelSort.convertChildIterToIter(this, result, row);

        return result;
    }

    /**
     * Given a TreeIter valid in this TreeModelSort, figure out the
     * correspnding row in the underlying TreeModel and return a TreeIter that
     * will be valid there.
     * 
     * @since 4.0.9
     */
    public TreeIter convertIterSortToBase(TreeIter row) {
        final TreeModel base;
        final TreeIter result;

        base = GtkTreeModelSort.getModel(this);
        result = new TreeIter(base);

        GtkTreeModelSort.convertIterToChildIter(this, result, row);

        return result;
    }

    /**
     * Convert a TreePath identifying a row in the underying TreeModel into
     * the corresponding locator in this TreeModelSort.
     * 
     * <p>
     * If the row location specified by <code>path</code> isn't resolvable in
     * the underlying TreeModel, <code>null</code> is returned.
     * 
     * @since 4.0.9
     */
    public TreePath convertPathBaseToSort(TreePath path) {
        return GtkTreeModelSort.convertChildPathToPath(this, path);
    }

    /**
     * Convert a TreePath identifying a row in this TreeModelSort into one
     * that points to the corresponding row in the underying TreeModel.
     * 
     * @since 4.0.9
     */
    public TreePath convertPathSortToBase(TreePath path) {
        return GtkTreeModelSort.convertPathToChildPath(this, path);
    }
}
