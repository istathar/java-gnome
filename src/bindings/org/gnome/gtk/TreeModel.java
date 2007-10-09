/*
 * TreeModel.java
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

import org.gnome.gdk.Pixbuf;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * @author Andrew Cowie
 * @author Peter Miller
 * @since 4.0.5
 */
/*
 * This is a departure from a strict mapping of the underlying library; in GTK
 * TreeModel is an interface implemented by things like ListStore and
 * TreeStore; by using it as an abstract superclass instead we can avoid
 * duplicating the implementation of quite a lot of code.
 */
public abstract class TreeModel extends org.gnome.glib.Object
{
    protected TreeModel(long pointer) {
        super(pointer);
    }

    /**
     * Used by the constructors. Convert from public DataColumn entities to
     * the Class array we'll be passing into the translation layer, carrying
     * out the crucial step of setting the column number ordinals along the
     * way.
     */
    protected static final Class[] typesToClassNames(DataColumn[] types) {
        final Class[] names;

        if (types == null) {
            throw new IllegalArgumentException("Array passed to TreeModel constructor must not be null");
        }

        if (types.length == 0) {
            throw new IllegalArgumentException(
                    "Must specify at least one column when constructing a TreeModel");
        }

        names = new Class[types.length];

        for (int i = 0; i < types.length; i++) {
            names[i] = types[i].getType();
            types[i].setOrdinal(i);
        }

        return names;
    }

    /**
     * The concrete TreeModels have their own setValue() methods that take a
     * typed ListStore or TreeStore , so we concentrate the calls and delegate
     * from here so they can call their specific translation method
     * accordingly.
     * 
     * ... and putting it here avoids recursive overload problems in
     * TreeModel.
     */
    private void dispatch(TreeIter row, DataColumn column, Value value) {
        if (this instanceof ListStore) {
            GtkListStore.setValue((ListStore) this, row, column.getOrdinal(), value);
        } else if (this instanceof TreeStore) {
            GtkTreeStore.setValue((TreeStore) this, row, column.getOrdinal(), value);
        } else {
            throw new UnsupportedOperationException(
                    "You need to implement setValue() for your TreeModel subclass");
        }
    }

    /**
     * 
     */
    public void setValue(TreeIter row, DataColumnString column, String value) {
        dispatch(row, column, new Value(value));
    }

    /**
     * 
     */
    public String getValue(TreeIter row, DataColumnString column) {
        final Value result;

        result = new Value();

        GtkTreeModel.getValue(this, row, column.getOrdinal(), result);

        return result.getString();
    }

    /**
     * 
     */
    public int getValue(TreeIter row, DataColumnInteger column) {
        final Value result;

        result = new Value();

        GtkTreeModel.getValue(this, row, column.getOrdinal(), result);

        return result.getInteger();
    }

    /**
     * 
     */
    public void setValue(TreeIter row, DataColumnInteger column, int value) {
        dispatch(row, column, new Value(value));
    }

    public boolean getValue(TreeIter row, DataColumnBoolean column) {
        final Value result;

        result = new Value();

        GtkTreeModel.getValue(this, row, column.getOrdinal(), result);

        return result.getBoolean();
    }

    public void setValue(TreeIter row, DataColumnBoolean column, boolean value) {
        dispatch(row, column, new Value(value));
    }

    Pixbuf getValue(TreeIter row, DataColumnPixbuf column) {
        final Value result;

        result = new Value();

        GtkTreeModel.getValue(this, row, column.getOrdinal(), result);

        return (Pixbuf) result.getObject();
    }

    /**
     * You'll have to cast the return value to whatever type you put in there
     * in the first place. TODO would making this generic help?
     */
    public java.lang.Object getValue(TreeIter row, DataColumnReference column) {
        return GtkTreeModelOverride.getReference(this, row, column.getOrdinal());
    }

    /**
     * 
     */
    /*
     * Calls a custom override as we manually manage a global reference to the
     * passed object on the JNI side. This also avoids the ambiguiity
     * collision in the signatures of Value(org.gnome.glib.Object) and
     * Value(java.lang.Object) that otherwise arose and prevented compilation.
     */
    public void setValue(TreeIter row, DataColumnReference column, java.lang.Object value) {
        GtkTreeModelOverride.setReference(this, row, column.getOrdinal(), value);
    }

    public void setValue(TreeIter row, DataColumnPixbuf column, Pixbuf value) {
        dispatch(row, column, new Value(value));
    }

    /**
     * Initialize a new iterator at the beginning of the model. Since you
     * presumably want to iterate through the remaining rows, use the
     * {@link TreeIter#iterNext() iterNext()} method you'll find on TreeIter
     * as follows:
     * 
     * <pre>
     * TreeIter row;
     * 
     * row = model.getIterFirst();
     * do {
     *     // do something with row
     * } while (row.iterNext());
     * </pre>
     * 
     * @return <code>null</code> if the model is presently empty.
     */
    public TreeIter getIterFirst() {
        final TreeIter iter;

        iter = new TreeIter(this);

        if (GtkTreeModel.getIterFirst(this, iter)) {
            return iter;
        } else {
            return null;
        }
    }

    /**
     * Convert a TreePath to a TreeIter appropriate for this TreeModel.
     * 
     * @param path
     * @return <code>null</code> if it can't make the conversion.
     * @since 4.0.5
     */
    public TreeIter getIter(TreePath path) {
        final TreeIter iter;

        iter = new TreeIter(this);

        if (GtkTreeModel.getIter(this, iter, path)) {
            return iter;
        } else {
            return null;
        }
    }
}
