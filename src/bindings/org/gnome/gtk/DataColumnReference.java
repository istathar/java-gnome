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
 * A column containing references back to Java objects in a TreeModel.
 * 
 * <p>
 * If your TreeView is a GUI representation of a List of Account objects, say,
 * and someone selects one of the rows, you quite reasonably want to know
 * <i>which</i> Account was selected. While you could extract the account name
 * or some other text field displayed in the TextView and attempt to use that
 * to look up the Account object back on the Java side, a much better solution
 * is to merely store a reference to the object that was the source of the
 * data in that row in the first place. That's what DataColumnReference is
 * for.
 * 
 * <p>
 * Simply call {@link TreeModel#getValue(TreeIter, DataColumnReference)
 * getValue()} with the TreeIter the selection gave you and the
 * DataColumnReference indicating the column you stashed your object in, and
 * then ta-da you have the Account which was selected, allowing you to carry
 * on with your application logic in your application's domain model's terms.
 * 
 * <p>
 * Beyond this, see {@link DataColumn} for the full discussion of the role of
 * these classes and examples of how to employ them.
 * 
 * <p>
 * <b>Don't try to use this in an attempt to somehow magically render a Java
 * object in a TreeView!</b> That's not what this is for; 99.9% of the time
 * DataColumnString driving a TreeViewColumn with a CellRendererText, or a
 * DataColumnPixbuf driving a TreeViewColumn containing a CellRendererPixbuf
 * will more than handle the job for you.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public final class DataColumnReference<T> extends DataColumn
{
    public DataColumnReference() {
        super(java.lang.Object.class);
    }
}
