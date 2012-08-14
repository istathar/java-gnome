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
 * Manual code relating to TreeModel. We have to customize the constructor to
 * deal with the (deliberate) lack of a Java type to GType mapping.
 * 
 * @author Andrew Cowie
 */
final class GtkTreeModelOverride extends Plumbing
{
    /**
     * Hand written a) to enalbe our custom signature, b) to do manually do
     * the mapping from Class objects to fully qualified class names.
     */
    static final long createListStore(Class<?>[] columns) {
        final String[] names;

        assert (columns != null) : "Array passed to createListStore() must not be null";
        assert (columns.length > 0) : "Minimum one column when constructing ListStore";

        names = new String[columns.length];

        for (int i = 0; i < columns.length; i++) {
            names[i] = columns[i].getName();
        }

        synchronized (lock) {
            return gtk_list_store_new(names);
        }
    }

    private static native final long gtk_list_store_new(String[] columns);

    static final long createTreeStore(Class<?>[] columns) {
        final String[] names;

        assert (columns != null) : "Array passed to createTreeStore() must not be null";
        assert (columns.length > 0) : "Minimum one column when constructing ListStore";

        names = new String[columns.length];

        for (int i = 0; i < columns.length; i++) {
            names[i] = columns[i].getName();
        }

        synchronized (lock) {
            return gtk_tree_store_new(names);
        }
    }

    private static native final long gtk_tree_store_new(String[] columns);

    /**
     * Hand written because of the necessary custom C code to deal with our
     * BindingsJavaReference wrapping.
     */
    static final void setReference(TreeModel self, TreeIter row, int column, java.lang.Object reference) {
        if (self instanceof ListStore) {
            synchronized (lock) {
                gtk_list_store_set_reference(pointerOf(self), pointerOf(row), column, reference);
            }
        } else if (self instanceof TreeStore) {
            synchronized (lock) {
                gtk_tree_store_set_reference(pointerOf(self), pointerOf(row), column, reference);
            }
        } else {
            throw new UnsupportedOperationException(
                    "You need to implement setReference() for your TreeModel subclass");
        }
    }

    private static native final void gtk_list_store_set_reference(long self, long row, int column,
            java.lang.Object reference);

    private static native final void gtk_tree_store_set_reference(long self, long row, int column,
            java.lang.Object reference);

    /**
     * Hand written because of the necessary custom C code to deal with our
     * BindingsJavaReference wrapping, especially the reference corruption
     * prevention.
     */
    static final java.lang.Object getReference(TreeModel self, TreeIter row, int column) {
        java.lang.Object result;

        synchronized (lock) {
            result = gtk_tree_model_get_reference(pointerOf(self), pointerOf(row), column);

            return result;
        }
    }

    private static final native java.lang.Object gtk_tree_model_get_reference(long self, long row,
            int column);
}
