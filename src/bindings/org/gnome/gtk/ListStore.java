/*
 * ListStore.java
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

import org.gnome.glib.Object;
import org.gnome.glib.Value;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * ... is the
 */
public class ListStore extends Object implements TreeModel, TreeDragSource, TreeDragDest, TreeSortable
{
    protected ListStore(long pointer) {
        super(pointer);
    }

    /**
     * 
     */
    public ListStore(Class[] types) {
        super(GtkTreeModelOverride.createListStore(types));
    }

    /**
     * Add a new row to the ListStore. You'll need to fill in the various
     * columns with {@link #setValueString(TreeIter, int, String) setValue()}
     * of course.
     */
    public TreeIter append() {
        final TreeIter iter;

        iter = new TreeIter();

        GtkListStore.append(this, iter);
        return iter;
    }

    /**
     * 
     */
    public void setValueString(TreeIter row, int column, String value) {
        GtkListStore.setValue(this, row, column, new Value(value));
    }

    /**
     * 
     */
    public String getValueString(TreeIter row, int column) {
        final Value result;

        result = new Value();

        GtkTreeModel.getValue(this, row, column, result);
        return result.getString();
    }

    /**
     * 
     */
    public TreeIter getIterFirst() {
        final TreeIter iter;

        iter = new TreeIter();

        if (GtkTreeModel.getIterFirst(this, iter)) {
            return iter;
        } else {
            return null;
        }
    }
}
