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
 * A TreeModel which can present a subset of its backing model as determined
 * by a filter function. TreeModelFilter acts to wrap an underlying TreeModel.
 * You store your data in this underlying model; the TreeModelFilter just adds
 * the functionality to selectively determine which rows should be visible.
 * 
 * <p>
 * Usage is straight forward. Given the following declarations:
 * 
 * <pre>
 * final ListStore model;
 * final TreeModelFilter filter;
 * final DataColumnInteger elevation;
 * ...
 * </pre>
 * 
 * you initialize and populate your ListStore as usual. To add the filtering
 * functionality, you wrap your ListStore in a TreeModelFilter:
 * 
 * <pre>
 * filter = new TreeModelFilter(model, null);
 * </pre>
 * 
 * then instruct the TreeModelFilter how to select the rows from the concrete
 * TreeModel it is proxying to be included in the virtual model it presents
 * via the {@link #setVisibleCallback(org.gnome.gtk.TreeModelFilter.Visible)
 * setVisibleCallback()}. For instance, if you have a list of all mountains
 * and only want to present peaks higher than 8000 meters, you might do:
 * 
 * <pre>
 * filter.setVisibleCallback(new TreeModelFilter.Visible() {
 *     public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row) {
 *         if (base.getValue(row, elevation) &gt; 8000) {
 *             return true;
 *         } else {
 *             return false;
 *         }
 *     }
 * }
 * </pre>
 * 
 * Assuming you are using this data to back a display Widget such as a
 * TreeView, and you only want to present this filtered list of rows, then you
 * use the TreeModelFilter, not the ListStore, as the model backing the
 * TreeView:
 * 
 * <pre>
 * view = new TreeView(filter);
 * </pre>
 * 
 * <p>
 * <b>Note:</b><br/>
 * For some reason, TreeModelFilter does <b>not</b> implement TreeSortable. If
 * you plan to sort the filtered model (ie via TreeViewColumn's
 * {@link TreeViewColumn#setSortColumn(DataColumn) setSortColumn()}) make sure
 * you wrap your TreeModelFilter in a {@link TreeModelSort} and add that to
 * the TreeView instead:
 * 
 * <pre>
 * store = new ListStore(...);
 * filtered = new TreeModelFilter(store, null);
 * sorted = new TreeModelSort(filtered);
 * 
 * view = new TreeView(sorted);
 * 
 * vertical = view.appendColumn();
 * vertical.setSortColumn(...);
 * </pre>
 * 
 * otherwise GTK will object vociferously.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class TreeModelFilter extends TreeModel implements TreeDragSource
{
    protected TreeModelFilter(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new TreeModelFilter.
     * 
     * @param base
     *            The underlying model that you are filtering
     * @param root
     *            You can give a TreePath to be used as a virtual root so that
     *            the TreeModelFilter only presents and operates on a
     *            subsection of the base TreeModel. This is rarely necessary,
     *            so specify <code>null</code>.
     */
    public TreeModelFilter(TreeModel base, TreePath root) {
        super(GtkTreeModelFilter.createTreeModelFilter(base, root));
    }

    /**
     * The callback invoked when a TreeModelFilter wants to ask if a given row
     * in its child TreeModel should be considered visible in the
     * TreeModelFilter.
     * 
     * <p>
     * Typically when you receive this callback you will reach into the
     * underlying model and query a column by which you will determine whether
     * or not to include this row. This grants the opportunity to put some
     * very complex logic into the <code>TextModelFilter.Visible</code>
     * callback. We tend to prefer this approach, but if you're rather
     * pre-calculate such states, then you can always add a DataColumnBoolean
     * to the model and simply return the state of that column as the return
     * value from this interface when it is invoked.
     * 
     * <p>
     * <i>If you are researching the GTK API documentation, see
     * <code>(*GtkTreeModelFilterVisibleFunc)</code>. Creating and invoking
     * this "visible" signal is how java-gnome has implemented the function
     * pointer expected by
     * <code>gtk_tree_model_filter_set_visible_func()</code>.</i>
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    /*
     * This is not a real GTK signal! This is a custom hack so we can get the
     * callback using the existing Signal machinery. Note that there is no
     * connect() method.
     */
    public interface Visible
    {
        /**
         * Answer the question "is this row to be visible?" Return
         * <code>true</code> for the row to be included in the model, or
         * <code>false</code> for the row to be filtered out.
         * 
         * <p>
         * <b>Warning!</b><br/>
         * <code>row</code> is a valid TreeIter in <code>base</code>, not
         * <code>source</code>. This makes sense if you consider that you will
         * need to ask the underlying proxied TreeModel for information about
         * a row; the only rows you can see in the <code>source</code>
         * TreeModelFilter are, of course, the ones that have passed this
         * test.
         * 
         * @since 4.0.6
         */
        public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row);
    }

    private static class VisibleHandler implements GtkTreeModelFilter.VisibleSignal
    {
        private final Visible handler;

        private VisibleHandler(Visible handler) {
            this.handler = handler;
        }

        public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row) {
            row.setModel(base);
            return handler.onVisible(source, base, row);
        }
    }

    /**
     * Hookup the <code>Visible</code> callback that will be used to determine
     * whether rows from the underlying TreeModel are to be included in the
     * set presented by this TreeModelFilter.
     * 
     * @since 4.0.6
     */
    /*
     * From the developer's point of view this works like a signal handler,
     * although the underlying function in GTK it actually requires a function
     * pointer. Our implementation registers 'visible' as a custom signal
     * which essentially has the same signature as
     * (GtkTreeModelFilterVisibleFunc)</code>.
     */
    public void setVisibleCallback(TreeModelFilter.Visible handler) {
        GtkTreeModelFilterOverride.setVisibleFunc(this);
        GtkTreeModelFilter.connect(this, new VisibleHandler(handler), false);
    }

    /**
     * Cause the TreeModelFilter to re-calculate whether rows are visible.
     * This will cause your <code>Visible</code> callback to be hit for each
     * row.
     * 
     * @since 4.0.6
     */
    public void refilter() {
        GtkTreeModelFilter.refilter(this);
    }

    /**
     * Convert a TreeIter valid in the underying TreeModel to one usable with
     * this TreeModelFilter.
     * 
     * @return <code>null</code> if the row is not (currently) present in the
     *         TreeModelFilter.
     * 
     * @since 4.0.9
     */
    public TreeIter convertIterBaseToFilter(TreeIter row) {
        final TreeIter result;
        final boolean valid;

        result = new TreeIter(this);

        valid = GtkTreeModelFilter.convertChildIterToIter(this, result, row);

        if (valid) {
            return result;
        } else {
            return null;
        }
    }

    /**
     * Convert a TreeIter valid in this TreeModelFilter into one usable with
     * the underying TreeModel.
     * 
     * @since 4.0.9
     */
    public TreeIter convertIterFilterToBase(TreeIter row) {
        final TreeModel base;
        final TreeIter result;

        base = GtkTreeModelFilter.getModel(this);
        result = new TreeIter(base);

        GtkTreeModelFilter.convertIterToChildIter(this, result, row);

        return result;
    }

    /**
     * Convert a TreePath representing a row in the underying TreeModel into
     * the corresponding locator in this TreeModelFilter.
     * 
     * <p>
     * If the row represented by <code>path</code> isn't (currently) present
     * in the narrowed representation provided by this TreeModelFilter, then
     * <code>null</code> is returned.
     * 
     * @since 4.0.9
     */
    public TreePath convertPathBaseToFilter(TreePath path) {
        return GtkTreeModelFilter.convertChildPathToPath(this, path);
    }

    /**
     * Convert a TreePath representing a row in this TreeModelFilter into one
     * that points to the corresponding row in the underying TreeModel.
     * 
     * @since 4.0.9
     */
    public TreePath convertPathFilterToBase(TreePath path) {
        return GtkTreeModelFilter.convertPathToChildPath(this, path);
    }
}
