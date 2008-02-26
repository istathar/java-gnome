/*
 * Paned.java
 *
 * Copyright (c) 2007 Vreixo Formoso
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Base type for Containers that dispose child Widgets in two panes, arranged
 * either horizantally ({@link HPaned HPaned}) or vertically ({@link VPaned VPaned}).
 * A Paned will have two children, refered as child <code>1</code> and
 * <code>2</code>.
 * 
 * <p>
 * This Widget is mainly used to divide the Window area in two parts. The user
 * can adjust the size of the panes by dragging the separator that appears
 * between them. This way, the user can choose at any time how much size
 * allocate for each of the two children Widgets.
 * 
 * <p>
 * A typical usage of Paned Container is to provide some kind of navigator
 * facilities to a application, generally with a list of bookmarks or an
 * index. In those cases, a HPaned is used to divide the application Window in
 * two columns, one at the left, with the index in a TreeView, and another,
 * wider, at the right, with the contents.
 * 
 * <p>
 * Note that Paned is only useful when we really need to allow users to
 * dinamically change the way size is distributed between the two children.
 * Otherwise, a {@link HBox HBox} or {@link VBox VBox} is a better option to
 * distribute our Widgets.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public abstract class Paned extends Container
{
    protected Paned(long pointer) {
        super(pointer);
    }

    /**
     * Adds a child Widget to the top (in case of VPaned) or to the left (in
     * case of HPaned) pane.
     * 
     * <p>
     * This call is equilavent to
     * {@link #pack1(Widget, boolean, boolean) pack1(child, false, true)}.
     */
    public void add1(Widget child) {
        GtkPaned.add1(this, child);
    }

    /**
     * Adds a child Widget to the bottom (in case of VPaned) or to the right
     * (in case of HPaned) pane.
     * 
     * <p>
     * This call is equilavent to
     * {@link #pack2(Widget, boolean, boolean) pack2(child, false, true)}.
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
     */
    public void pack1(Widget child, boolean resize, boolean shrink) {
        GtkPaned.pack1(this, child, resize, shrink);
    }

    /**
     * Adds a child Widget to the bottom (in case of VPaned) or to the right
     * (in case of HPaned) pane.
     * 
     * @see #pack1(Widget, boolean, boolean)
     */
    public void pack2(Widget child, boolean resize, boolean shrink) {
        GtkPaned.pack2(this, child, resize, shrink);
    }

    /**
     * Get the child Widget at the top (in case of VPaned) or at the left (in
     * case of HPaned) pane.
     */
    public Widget getChild1() {
        return GtkPaned.getChild1(this);
    }

    /**
     * Get the child Widget at the bottom (in case of VPaned) or at the right
     * (in case of HPaned) pane.
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
     *            means that the position is unset.
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
}
