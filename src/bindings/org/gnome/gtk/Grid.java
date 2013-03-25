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

/*
 * Created from Table.java
 */

/**
 * Layout Widgets in a row (horizontally or vertically), or in a table.
 * 
 * <p>
 * <i>This replaces GTK 2's VBox, HBox, and Table.</i>
 * 
 * @author Andrew Cowie
 * @since 4.1.1
 */
public class Grid extends Container implements Orientable
{
    protected Grid(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Grid.
     * 
     * @since 4.1.1
     */
    public Grid() {
        super(GtkGrid.createGrid());
    }

    /**
     * Add a child Widget to this Grid.
     * 
     * @since 4.1.1
     */
    public void attach(Widget child, int left, int top, int width, int height) {
        GtkGrid.attach(this, child, left, top, width, height);
    }

    /**
     * Set the (extra) spacing between all columns.
     * 
     * @since 4.1.1
     */
    public void setColumnSpacing(int spacing) {
        GtkGrid.setColumnSpacing(this, spacing);
    }

    /**
     * Set the (extra) spacing between all rows.
     * 
     * @since 4.1.1
     */
    public void setRowSpacing(int spacing) {
        GtkGrid.setRowSpacing(this, spacing);
    }

    public Orientation getOrientation() {
        return GtkOrientable.getOrientation(this);
    }

    public void setOrientation(Orientation orientation) {
        GtkOrientable.setOrientation(this, orientation);
    }

    /**
     * Retrieve a widget at a specific cell or null if there isn't any.
     * 
     * @since 4.1.3
     */
    public Widget getChildAt(int left, int top) {
        return GtkGrid.getChildAt(this, left, top);
    }
}
