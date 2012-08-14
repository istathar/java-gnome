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

/**
 * Base class for Containers which organize a variable number of Widgets into
 * a rectangular area. This is either a single row of child Widgets (in the
 * case of {@link HBox HBox}), or a single column (for the case of
 * {@link VBox VBox}). All the children of a Box are allocated one dimension
 * in common, being the height of a row, or the width of a column,
 * respectively.
 * 
 * <p>
 * Nested combinations of VBoxes and HBoxes are the cornerstone layout
 * technique used in GTK. A top level VBox to organize a Window is followed by
 * a sequence Widgets, many of which will end up being HBoxes, and each of
 * those in turn might contain further Containers. This sort of thing gives
 * you great flexibility when laying out your user interface.
 * 
 * <p>
 * Boxes are also used to enable grouping of Widgets at very small scales as
 * well. For example, the ok Button you see in every GNOME dialog is actually
 * an Image holding the icon and a Label with the text "OK" packed into an
 * HBox; the HBox is what was actually added to the Button!
 * 
 * @author Andrew Cowie
 * @since 4.0.1
 */
public class Box extends Container implements Orientable
{
    protected Box(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Box of the given orientation and specifying a default
     * spacing. See also the original Widgets {@link HBox} and {@link VBox}.
     * 
     * @since 4.1.1
     */
    public Box(Orientation orientation, int spacing) {
        super(GtkBox.createBox(orientation, spacing));
    }

    /**
     * Add a Widget to the beginning of the Box. Widget child will be ordered
     * after any other Widgets that have already been packed with respect to
     * the start of the Box, but before any Widgets that are packed at the end
     * of the Box with {@link #packEnd(Widget) packEnd()}.
     * 
     * @param child
     *            the Widget to be added
     * @param expand
     *            Whether the new <code>child</code> is to be given extra
     *            space allocated to Box. The extra space will be divided
     *            evenly between all children of this Box that were added with
     *            <code>expand</code> set to <code>true</code>.
     * @param fill
     *            Whether space given to <code>child</code> by the
     *            <code>expand</code> option is actually allocated to child.
     *            If you specify <code>false</code> here, then any extra space
     *            will pad the Widget, rather than causing it to grow larger.
     * @param padding
     *            extra space (in pixels) to put between this child and its
     *            neighbours. This is over and above the global amount of
     *            padding that was specified by the <code>spacing</code>
     *            parameter when the Box was constructed. If
     *            <code>child</code> is the Widget at one of the start of the
     *            Box, then <code>padding</code> pixels are also put between
     *            the Widget and the leading edge.
     * @since 4.0.1
     */
    public void packStart(Widget child, boolean expand, boolean fill, int padding) {
        GtkBox.packStart(this, child, expand, fill, padding);
    }

    /**
     * Add a Widget to the end of the Box. The parameters work the same as for
     * {@link #packStart(Widget, boolean, boolean, int) packStart()}, but note
     * that Widgets packed with respect to the end will pack <i>inwards</i>
     * from the end, closer to the middle than Widgets already added with
     * <code>packEnd()</code>.
     * 
     * @since 4.0.6
     */
    public void packEnd(Widget child, boolean expand, boolean fill, int padding) {
        GtkBox.packEnd(this, child, expand, fill, padding);
    }

    /**
     * Change the position of a Widget in the Box. The child will still be
     * packed with respect to the beginning (if
     * {@link #packStart(Widget, boolean, boolean, int) packStart()} was used)
     * or end (if {@link #packEnd(Widget, boolean, boolean, int) packEnd()}
     * was used) of the Box, and will be placed just after the Widget at
     * <code>position</code>.
     * 
     * @since 4.0.8
     */
    public void reorderChild(Widget child, int position) {
        GtkBox.reorderChild(this, child, position);
    }

    /**
     * Set the amount of spacing between the Widgets in the Box. The spacing
     * is measured in pixels. By default this is <code>0</code>, but is
     * actually whatever was set in the {@link HBox#HBox(boolean, int) HBox}
     * or {@link VBox#VBox(boolean, int) VBox} constructor.
     * 
     * <p>
     * Allowed values are <code>0</code> or greater.
     * 
     * @since 4.0.11
     */
    public void setSpacing(int spacing) {
        if (spacing < 0) {
            throw new IllegalArgumentException("spacing must be >= 0");
        }
        GtkBox.setSpacing(this, spacing);
    }

    /**
     * Returns the amount of spacing that is set to be places between the
     * Widgets that are in the Box. The amount is measured in pixels.
     * 
     * @since 4.0.11
     */
    public int getSpacing() {
        return GtkBox.getSpacing(this);
    }

    public void setOrientation(Orientation orientation) {
        GtkOrientable.setOrientation(this, orientation);
    }

    public Orientation getOrientation() {
        return GtkOrientable.getOrientation(this);
    }
}
