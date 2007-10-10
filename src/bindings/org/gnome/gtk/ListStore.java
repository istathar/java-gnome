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
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class ListStore extends TreeModel implements TreeDragSource, TreeDragDest, TreeSortable
{
    protected ListStore(long pointer) {
        super(pointer);
    }

    /**
     * 
     */
    public ListStore(DataColumn[] types) {
        super(GtkTreeModelOverride.createListStore(typesToClassNames(types)));
    }

    /**
     * Add a new row to the ListStore. You'll need to fill in the various
     * columns with
     * {@link #setValue(TreeIter, DataColumnString, String) setValue()} of
     * course.
     */
    public TreeIter appendRow() {
        final TreeIter iter;

        iter = new TreeIter(this);

        GtkListStore.append(this, iter);

        return iter;
    }
}
