/*
 * TreeSelection.java
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

/**
 * Manipulate the selection state of a TreeView. Every TreeView has an
 * accompanying TreeSelection object which is used to manage whether or not
 * rows can be selected, and to return to the programmer the current state of
 * which rows are selected, if any.
 * 
 * <p>
 * <i>Mostly this is an API helper; the underlying documentation notes that
 * these could have all been methods on <code>GtkTreeView</code>.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class TreeSelection extends Object
{
    protected TreeSelection(long pointer) {
        super(pointer);
    }

    public void setMode(SelectionMode type) {
        GtkTreeSelection.setMode(this, type);
    }

    /**
     * Get the selected row from the TreeView. Note that this only works when
     * the selection mode is {@link SelectionMode#SINGLE SINGLE} or
     * {@link SelectionMode#BROWSE BROWSE}.
     * 
     * @return <code>null</code> if there is no currently selected row.
     */
    /*
     * Second parameter to native call is an out-parameter that gets filled
     * with a pointer to the GtkTreeModel. It's only for convenience, and is
     * unnecessary for us. Looking at the GtkTreeSelection C code it ignores
     * it if NULL. We'll skip it.
     */
    public TreeIter getSelected() {
        final TreeIter row;

        row = new TreeIter();

        if (GtkTreeSelection.getSelected(this, null, row)) {
            return row;
        } else {
            return null;
        }
    }

    /**
     * Emitted when the selection state of the TreeView changes.
     * 
     * <p>
     * Beware that you sometimes get false positives or false negatives
     * relative to how you are interpreting "change". You'll be calling
     * {@link TreeSelection#getSelected() getSelected()} anyway, but it's a
     * good idea to keep in mind that the state may not have changed in quite
     * the way you think it might have, so have a look at the return from that
     * method fairly closely to decide for yourself whether the selection has
     * "changed" or not.
     * 
     * @author Andrew Cowie
     * @since 4.0.5
     */
    public interface CHANGED extends GtkTreeSelection.CHANGED
    {
        void onChanged(TreeSelection source);
    }
}
