/*
 * TreePath.java
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

import org.gnome.glib.Boxed;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */

/**
 * A logical but abstract representation of a row in a TreeModel. TreePaths
 * can be expressed as Strings.
 * 
 * <p>
 * Paths in ListStores are quite simple. They are a simple number,
 * incrementing from zero, indicating the row number. For example,
 * <code>TreePath("8")</code> is the ninth row in the model.
 * 
 * <p>
 * Paths for TreeStores are more complex. They are of the form
 * "first:second:third:..." where each of first, second, and third denote the
 * number of steps in from the first at each level of the hierarchy. For
 * example, <code>TreePath("2:4:0")</code> represnts the first third level
 * element in the fifth second level element in the third row.
 * 
 * <p>
 * TreePaths are given to you as a row address in signals like
 * {@link TreeView.ROW_ACTIVATED ROW_ACTIVATED}. Usually you need the row
 * expressed as a TreeIter (ie to get a value out of the row);to do that call
 * the underlying TreeModel's {@link TreeModel#getIter(TreePath) getIter()}
 * method.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public final class TreePath extends Boxed
{
    protected TreePath(long pointer) {
        super(pointer);
    }

    /**
     * Create an empty TreePath object. <b>For use by bindings hackers only!</b>
     */
    TreePath() {
        super(GtkTreePath.createTreePath());
    }

    /**
     * Create a TreePath object from the String form. Note that TreePaths are
     * abstract and independant of a particular model; if you want to look up
     * a given path in a TreeModel use the resultant object with its
     * {@link TreeModel#getIter(TreePath) getIter()} method.
     * 
     * @throws IllegalArgumentException
     *             if the <code>path</code> fails to parse as a valid
     *             TreePath.
     */
    public TreePath(String path) {
        super(GtkTreePath.createTreePathFromString(path));
    }

    static long validationCheck(final long pointer) {
        if (pointer == 0L) {
            throw new IllegalArgumentException(
                    "The supplied path string failed to parse as a valid TreePath");
        }
        return pointer;
    }

    protected void release() {
        GtkTreePath.free(this);
    }

    /**
     * Return <code>true</code> of this TreePath represents the same logical
     * path as the <code>other</code> does.
     */
    public boolean equals(java.lang.Object other) {
        if (!(other instanceof TreePath)) {
            return false;
        }
        TreePath path = (TreePath) other;
        if (GtkTreePath.compare(this, path) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
