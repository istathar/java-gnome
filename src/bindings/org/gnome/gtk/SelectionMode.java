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

import org.freedesktop.bindings.Constant;

/**
 * What kinds of selections are possible on a TreeView? These are used by
 * {@link TreeSelection#setMode(SelectionMode) setMode()} on TreeSelection,
 * which in turn you get by calling TreeView's {@link TreeView#getSelection()
 * getSelection()}.
 * 
 * <p>
 * The default is {@link #SINGLE SINGLE}.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public final class SelectionMode extends Constant
{
    private SelectionMode(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Rows may <b>not</b> be selected.
     */
    public static final SelectionMode NONE = new SelectionMode(GtkSelectionMode.NONE, "NONE");

    /**
     * One element may be selected; zero selected rows is also permitted. This
     * it the default.
     */
    public static final SelectionMode SINGLE = new SelectionMode(GtkSelectionMode.SINGLE, "SINGLE");

    /**
     * Normally exactly one row will be selected; the user cannot deselect
     * this row without selecting another. This is not all encompassing,
     * however, as no row selected is possible as an initial state and also
     * during interactive searches.
     */
    public static final SelectionMode BROWSE = new SelectionMode(GtkSelectionMode.BROWSE, "BROWSE");

    /**
     * Multiple rows may be selected. The behaviour is the same as with
     * multiple selections elsewhere in the GNOME desktop (file browsing,
     * etc). Mouse clicks toggle the selected row to the location of the
     * click. The <code>Shift</code> key will cause the selection to go from
     * the focus to the click location, and the <code>Ctrl</code> key will
     * enlarge the selection by adding the clicked row to the other rows
     * already selected.
     */
    public static final SelectionMode MULTIPLE = new SelectionMode(GtkSelectionMode.MULTIPLE, "MULTIPLE");
}
