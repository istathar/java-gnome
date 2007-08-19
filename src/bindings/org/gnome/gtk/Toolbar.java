/*
 * Toolbar.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A Toolbar is a bar with several controls (usually little graphical
 * buttons), used to provide a fast and convenient access to operations
 * commonly used in an application.
 *  
 * <p>
 * In most cases, you will want to add some {@link ToolButton}s to the
 * Toolbar, but you can also add other kind of items, after adding then to a
 * {@link ToolItem}. While you can use {@link Container} methods to add any
 * Widget to the Toolbar, you should only add ToolItems (or any of its
 * subclasses).
 * 
 * <p>
 * You can also group related items together, adding {@link SeparatorToolItem}
 * to the Toolbar to separate between them.
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class Toolbar extends Container
{
    protected Toolbar(long pointer) {
        super(pointer);
    }

    /**
     * Create a new, empty, Toolbar.
     */
    public Toolbar() {
        super(GtkToolbar.createToolbar());
    }

    /**
     * Insert a ToolItem in the Toolbar at a given position.
     * 
     * @param item
     *      The new item to add to the Toolbar.
     * @param pos
     *      The position where the new item will be inserted. You can use
     *      <code>0</code> to prepend the item at the beginning of the Toolbar,
     *      or a negative value to append the item at the end.
     */
    public void insert(ToolItem item, int pos) {
        GtkToolbar.insert(this, item, pos);
    }

    /**
     * Sets the orientation of the Toolbar on screen.
     * 
     * <p>
     * Horizontal Toolbars are commonly used. Usually you shouldn't use a
     * Vertical Toolbar, as for the user it is more difficult to search for an
     * specific control. However, when your application has several Toolbars,
     * a Vertical orientation can be useful to make a better usage of the
     * screen space.
     */
    public void setOrientation(Orientation orientation) {
        GtkToolbar.setOrientation(this, orientation);
    }

    /**
     * Set if the Tooltips of the Toolbar should be enabled or not. When
     * enabled, a little help message will be shown when user puts the mouse
     * cursor over a toolbar item (if that item has a tooltip, of course).
     */
    public void setTooltips(boolean enable) {
        GtkToolbar.setTooltips(this, enable);
    }

}
