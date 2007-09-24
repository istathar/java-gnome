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
 * @author Andrew Cowie
 * @author Peter Miller
 * @since 4.0.5
 */
/*
 * This is a departure from a strict mapping of the underlying library; in GTK
 * TreeModel is an interface implemented by things like ListStore and
 * TreeStore; by using it as an abstract superclass instead we can avoid
 * duplicating the implemention of quite a lot of code.
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
     */
    private void setValue(TreeIter row, DataColumn column, Value value) {
        if (this instanceof ListStore) {
            GtkListStore.setValue((ListStore) this, row, column.getOrdinal(), value);
        } else if (this instanceof TreeStore) {
            GtkTreeStore.setValue((TreeStore) this, row, column.getOrdinal(), value);
        } else {
            throw new UnsupportedOperationException(
                    "You need to implement setValue() in your TreeModel subclass");
        }
    }

    /**
     * 
     */
    public void setValue(TreeIter row, DataColumnString column, String value) {
        setValue(row, column, new Value(value));
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
        setValue(row, column, new Value(value));
    }

    public boolean getValue(TreeIter row, DataColumnBoolean column) {
        final Value result;

        result = new Value();

        GtkTreeModel.getValue(this, row, column.getOrdinal(), result);

        return result.getBoolean();
    }

    public void setValue(TreeIter row, DataColumnBoolean column, boolean value) {
        setValue(row, column, new Value(value));
    }

    /**
     * 
     */
    public java.lang.Object getValue(TreeIter row, DataColumnReference column) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    public void setValue(TreeIter row, DataColumnReference column, java.lang.Object value) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    /**
     * Initialize a new iterator at the beginning of the model. Since you
     * presumably want to iterate through the remaining rows, use
     * {@link TreeModel#iterNext() iterNext()} as in the following:
     * 
     * <pre>
     * TreeIter row;
     * 
     * row = model.getIterFirst();
     * while (row != null) {
     *     // do something with row
     *     row = model.iterNext();
     * }
     * </pre>
     * 
     * @return <code>null</code> if the model is presently empty.
     */
    /*
     * While this is not exactly Java programmer friendly, the alternatives
     * aren't much good either. a) Could change this to java.util.Iterator
     * style, but there it is the Iterator that has the next() method, not
     * TreeModel as we have to deal with. Given how primative TreeIters are
     * that's probably not the best idea. b) Change to iterFirst(), but that
     * would leave getIter() as iter(). Basically this falls under gratuitous
     * changing of GTK for no real benefit. So we'll leave it alone for now.
     * At least we changed it from out parameter to returning something.
     * Further suggestions welcome.
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
