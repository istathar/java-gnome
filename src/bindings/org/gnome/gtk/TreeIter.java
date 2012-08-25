/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.glib.Boxed;

/**
 * A temporary pointer to a row in a TreeModel. TreeIters are used to indicate
 * a row in a TreeModel, either the "current" row if you are iterating over
 * the data, or as an indication of which row a given event occurred on.
 * 
 * <p>
 * To obtain a new TreeIter, use one of the following:
 * <ul>
 * <li>ListStore's {@link ListStore#appendRow() appendRow()} (to add a new
 * record to the end of the data set in the model);
 * <li>TreeModel's {@link TreeModel#getIterFirst() getIterFirst()} (to start
 * iterating through the rows in the model); or
 * <li>TreeSelection's {@link TreeSelection#getSelected() getSelected()}
 * (allowing you to identify the selected row and subsequently read data from
 * it, usually with TreeModel's
 * {@link TreeModel#getValue(TreeIter, DataColumnReference) getValue()}.
 * </ul>
 * 
 * <p>
 * Like other iterators in Java, a TreeIter becomes invalid the moment the
 * underlying model changes. If you need a persistent pointer to a particular
 * row, create a {@link TreeRowReference TreeRowReference}.
 * 
 * <p>
 * <i>Note that although one use case for TreeIter is to iterate through all
 * the rows in a model, more often they are used to point to a single row (the
 * one you are presently adding data to, or the one that was selected by the
 * user); these aren't <code>java.util.Iterator</code>s.</i>
 * 
 * @author Andrew Cowie
 * @author Srichand Pendyala
 * @author Vreixo Formoso
 * @since 4.0.5
 */
public final class TreeIter extends Boxed
{
    private TreeModel model;

    /*
     * Standard no-arg constructor. In general, our TreeIters are instantiated
     * by us, and so the model is set. If this constructor is used (ie by a
     * signal handler delegate), then, you will need to call setModel() as
     * well.
     */
    protected TreeIter(long pointer) {
        super(pointer);

        model = null;
    }

    /**
     * Allocate a blank TreeIter structure. This is done by declaring one
     * locally, copying it, and returning the pointer to the copy.
     * 
     * <p>
     * <b>For use by bindings hackers only!</b>
     */
    TreeIter(TreeModel model) {
        super(GtkTreeIterOverride.createTreeIter());

        this.model = model;
    }

    void setModel(TreeModel model) {
        this.model = model;
    }

    TreeModel getModel() {
        if (model == null) {
            throw new IllegalStateException(
                    "\nSorry, this TreeIter doesn't have its internal reference to its parent TreeModel set");
        }
        return model;
    }

    protected void release() {
        model = null;
        GtkTreeIter.free(this);
    }

    /**
     * Change this TreeIter to point to the row following the current one. In
     * a ListStore, this is simply the next row in the model, and what you use
     * in conjunction with {@link TreeModel#getIterFirst() getIterFirst()} to
     * iterate through the entire model. In a TreeStore, however, it will
     * return the next row <i>at this level</i>.
     * 
     * <p>
     * This will return <code>true</code> if it was able to change this
     * TreeIter to the next row.
     * 
     * <p>
     * Be aware that when this returns <code>false</code> the TreeIter is no
     * longer valid.
     * 
     * @since 4.0.5
     */
    public boolean iterNext() {
        if (model == null) {
            return false;
        }
        return GtkTreeModel.iterNext(model, this);
    }

    /**
     * Create a copy of this TreeIter.
     * 
     * @since 4.0.14
     */
    public TreeIter copy() {
        return GtkTreeIter.copy(this);
    }
}
