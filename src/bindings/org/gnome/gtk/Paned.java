/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2008 Vreixo Formoso
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
 * Base type for Containers that dispose child Widgets in two panes, arranged
 * either horizontally ({@link HPaned HPaned}) or vertically ({@link VPaned
 * VPaned}). A Paned will have two children, referred as child <code>1</code>
 * and <code>2</code>.
 * 
 * <p>
 * This Widget is mainly used to divide the Window area in two parts. The user
 * can adjust the size of the panes by dragging the separator that appears
 * between them. This way, the user can choose at any time how much size
 * allocate for each of the two child Widgets.
 * 
 * <p>
 * A typical usage of the Paned Container is to provide some kind of navigator
 * facilities to a application, generally with a list of bookmarks or an
 * index. In those cases, a HPaned is used to divide the application Window in
 * two columns, one at the left, with the index in a TreeView, and another,
 * wider, at the right, with the contents.
 * 
 * <p>
 * Note that Paned should only be used when we really need to allow users to
 * dynamically change the way size is distributed between the two children.
 * Otherwise, a {@link HBox HBox} or {@link VBox VBox} would be a better
 * choice.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public class Paned extends Container implements Orientable
{
    protected Paned(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Paned of the given orientation. See also the original
     * Widgets {@link HPaned} and {@link VPaned}.
     * 
     * @since 4.1.1
     */
    public Paned(Orientation orientation) {
        super(GtkPaned.createPaned(orientation));
    }

    /**
     * Adds a child Widget to the top (in case of VPaned) or to the left (in
     * case of HPaned) pane.
     * 
     * <p>
     * This call is equivalent to {@link #pack1(Widget, boolean, boolean)
     * pack1(child, false, true)}.
     * 
     * @since 4.0.7
     */
    public void add1(Widget child) {
        GtkPaned.add1(this, child);
    }

    /**
     * Adds a child Widget to the bottom (in case of VPaned) or to the right
     * (in case of HPaned) pane.
     * 
     * <p>
     * This call is equivalent to {@link #pack2(Widget, boolean, boolean)
     * pack2(child, false, true)}.
     * 
     * @since 4.0.7
     */
    public void add2(Widget child) {
        GtkPaned.add2(this, child);
    }

    /**
     * Adds a child Widget to the top (in case of VPaned) or to the left (in
     * case of HPaned) pane.
     * 
     * @param child
     *            the Widget to be added
     * @param resize
     *            Whether this pane (and thus its Widget) should be resized
     *            when the Paned is resize.
     * @param shrink
     *            Whether the user can make the pane smaller than the size
     *            requested by the child Widget. This can be useful to let the
     *            user hide one of the two panels, but depending of the child
     *            Widget type it can lead to poor visual results. Use with
     *            caution.
     * @since 4.0.7
     */
    public void pack1(Widget child, boolean resize, boolean shrink) {
        GtkPaned.pack1(this, child, resize, shrink);
    }

    /**
     * Adds a child Widget to the bottom (in case of a VPaned) or to the right
     * (in case of an HPaned) pane. See
     * {@link #pack1(Widget, boolean, boolean) pack1()} for details.
     * 
     * @since 4.0.7
     */
    public void pack2(Widget child, boolean resize, boolean shrink) {
        GtkPaned.pack2(this, child, resize, shrink);
    }

    /**
     * Get the child Widget in the top (in case of a VPaned) or in the left
     * (in case of an HPaned) pane.
     * 
     * @since 4.0.7
     */
    public Widget getChild1() {
        return GtkPaned.getChild1(this);
    }

    /**
     * Get the child Widget at the bottom (in case of VPaned) or at the right
     * (in case of HPaned) pane.
     * 
     * @since 4.0.7
     */
    public Widget getChild2() {
        return GtkPaned.getChild2(this);
    }

    /**
     * Sets the position of the divider between the two panes.
     * 
     * <p>
     * Note that the main utility of a Paned is to let user to set the
     * position, so in most cases you don't want to call this method. It can
     * be useful to set the initial position, if you feel that the default
     * value chosen by Gtk is not suitable for your application.
     * 
     * @param position
     *            The position of the divider, in pixels. A negative value
     *            means that the position is not being forced.
     * @since 4.0.7
     */
    public void setPosition(int position) {
        GtkPaned.setPosition(this, position);
    }

    /**
     * Obtains the position of the divider between the two panes.
     * 
     * @return the position in pixels.
     */
    public int getPosition() {
        return GtkPaned.getPosition(this);
    }

    public void setOrientation(Orientation orientation) {
        GtkOrientable.setOrientation(this, orientation);
    }

    public Orientation getOrientation() {
        return GtkOrientable.getOrientation(this);
    }
}
