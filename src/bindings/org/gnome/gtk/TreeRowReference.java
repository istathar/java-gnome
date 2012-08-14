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

import org.gnome.glib.Boxed;

/**
 * A stable reference to a specific row in a TreeModel. A TreeRowReference
 * listens to all changes made to the model (be they insertions, deletions,
 * sorting being applied, etc) and adjusts itself internally so that the same
 * row is pointed at by the instance regardless.
 * 
 * <p>
 * This class is primarily necessary because a TreeIter instance is no longer
 * usable if the model changes. Neither are TreePaths for that matter; if you
 * change the sorting order then the row pointed at by TreePath "2" will
 * [likely] be different before and after the sort.
 * 
 * <p>
 * Typical usage of this is from a TreeIter as follows:
 * 
 * <pre>
 * TreeModel source;
 * TreePath path;
 * TreeIter row;
 * TreeRowReference ref;
 * ...
 * 
 * path = source.getPath(row);
 * ref = new TreeRowReference(source, path);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public final class TreeRowReference extends Boxed
{
    protected TreeRowReference(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new TreeRowReference for the given TreePath into the given
     * Model.
     * 
     * @since 4.0.6
     */
    public TreeRowReference(TreeModel model, TreePath path) {
        super(GtkTreeRowReference.createTreeRowReference(model, path));
    }

    protected void release() {
        GtkTreeRowReference.free(this);
    }

    /**
     * Get a TreePath representing the row that this TreeRowReference is
     * currently pointing at.
     * 
     * @return You'll get <code>null</code> back if the TreeRowReference is no
     *         longer valid, which would happen if the row has been deleted.
     * 
     * @since 4.0.6
     */
    public TreePath getPath() {
        return GtkTreeRowReference.getPath(this);
    }
}
