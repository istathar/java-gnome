/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.glib.Object;

/**
 * Present data in CellLayouts (ie, the TreeViewColumns of TreeView, and
 * ComboBox).
 * 
 * <p>
 * Note that there is not one CellRenderer per individual cell in the
 * table-like presentation of a TreeView! Instead, a CellRenderer is an engine
 * called upon to draw <i>many</i> cells. The Widget employing them will, by
 * turn, set various properties (the actual data for the current cell in
 * question would be what changes, though most of the rest of the properties
 * would likely remain constant) and then ask the CellRenderer to return the
 * rendered result.
 * 
 * <p>
 * You will see two types of setters in CellRenderers. The most important ones
 * are those that take a DataColumn for an argument, and will adjust that
 * property of the CellRenderer for each row of the model based on the value
 * of the specified column.
 * 
 * <p>
 * While the whole point of the CellRenderers is to present the data from a
 * given column of your data model, CellRenderers also offer a considerable
 * number of properties that you <i>don't</i> need to vary row by row but want
 * to set for the TreeViewColumn as a whole. For these you will notice a a
 * setter which takes a conventional type as its argument; setting this
 * property will set it for all rows in that column. (Occasionally you will
 * see the setter for a property overloaded to offer you both a DataColumn
 * driven mode and a fixed value mode for the rare occasions where both styles
 * are useful, but in general it's fairly obvious that you use a property one
 * way or the other).
 * 
 * <p>
 * It's easy to be tempted into thinking that CellRenderers are Widgets,
 * especially as they get <code>pack()</code>ed into TreeViewColumns on their
 * way to being used in TreeViews. They are, however, merely utility elements
 * that are used to facilitate drawing, and <i>not</i> full power Widgets.
 * 
 * <p>
 * Do not try to reuse a CellRenderer between different TreeViewColumns.
 * 
 * @author Andrew Cowie
 * @author Stefan Prelle
 * @since 4.0.5
 */
/*
 * Injunction about reuse from Kristian Rietveld
 */
public abstract class CellRenderer extends Object
{
    /*
     * No <init>(long) only constructor implies that you can't get one of
     * these via objectFor() if it wasn't instantiated Java side. No problem;
     * it's not like LibGlade does that for you, although GtkBuilder might
     * someday (in which case you'd have to add logic to figure out which
     * TreeViewColumn had had this CellRenderer packed into it).
     */

    /**
     * The one (and only) TreeViewColumn this CellRenderer has been packed
     * into. Used by the set<Attribute>() methods.
     */
    protected CellLayout vertical;

    /**
     * Once you've constructed a CellRenderer and tied it to TreeViewColumn by
     * doing so (ie the way we have designed it), you can't reuse it, which is
     * why there's no <code>setVertical()</code>.
     * 
     * <p>
     * In case you hadn't noticed yet, TreeViewColumn is a CellLayout.
     */
    protected CellRenderer(long pointer, CellLayout vertical, boolean expand) {
        super(pointer);

        if (vertical == null) {
            throw new IllegalArgumentException(
                    "Must pass an instantiated TreeViewColumn to the CellRenderer constructor");
        }

        GtkCellLayout.packStart(vertical, this, expand);

        this.vertical = vertical;
    }

    /**
     * Set the alignment of this CellRenderer in the TreeViewColumn.
     */
    /*
     * Both at once matches the signature found in Button and Misc
     */
    public void setAlignment(float xalign, float yalign) {
        setPropertyFloat("xalign", xalign);
        setPropertyFloat("yalign", yalign);
    }

    /**
     * Indicate the DataColumn containing the name of the colour to use as
     * background for this CellRenderer. You can set the background
     * dynamically with TreeModel data via this method, or set it statically
     * once via the other {@link #setBackground(String) setBackground()}
     * overload.
     */
    /*
     * Note that we have exposed the "cell-background" property with the name
     * setBackground(), thereby eclipsing the use of CellRendererText's
     * "background" property as setBackground(). After extensive tests and
     * discussions with the GTK hackers, it would seem there is never an
     * occasion where you want to set the background of the text renderer but
     * not the cell as a whole. So combine them for the result of a cleaner
     * API.
     */
    public void setBackground(DataColumnString column) {
        GtkCellLayout.addAttribute(vertical, this, "cell-background", column.getOrdinal());
    }

    /**
     * Set the background colour to be used by this CellRenderer for all the
     * rows it draws.
     */
    public void setBackground(String colour) {
        setPropertyString("cell-background", colour);
    }

    /**
     * Indicate if the CellRenderer should be visible, depending on the
     * content of a DataColumnBoolean. For example, you could set use this
     * property to control whether or not a {@link CellRendererToggle} was
     * visible in a TreeView where you want each of the leaf rows to have such
     * a Button but you do not want the parent node rows to have one.
     * 
     * @since 4.0.9
     */
    public void setVisible(DataColumnBoolean column) {
        GtkCellLayout.addAttribute(vertical, this, "visible", column.getOrdinal());
    }

    /**
     * Fix the width of this CellRenderer, specified in pixels.
     * 
     * @since 4.0.20
     */
    public void setWidth(int width) {
        setPropertyInteger("width", width);
    }
}
