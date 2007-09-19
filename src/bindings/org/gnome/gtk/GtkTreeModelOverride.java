/*
 * GtkTreeModelOverride.java
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
    static final long createListStore(Class[] columns) {
        final String[] names;

        if (columns == null) {
            throw new IllegalArgumentException("Array passed to ListStore constructor must not be null");
        }

        if (columns.length == 0) {
            throw new IllegalArgumentException(
                    "Must specify at least one column when constructing ListStore");
        }

        names = new String[columns.length];

        for (int i = 0; i < columns.length; i++) {
            names[i] = columns[i].getName();
        }

        synchronized (lock) {
            return gtk_list_store_new(names);
        }
    }

    private static native final long gtk_list_store_new(String[] columns);
}
