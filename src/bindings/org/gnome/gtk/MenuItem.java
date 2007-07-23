/*
 * MenuItem.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A MenuItem is the basic item in a menu. It behaves like a Button and serves
 * as superclass for specialized MenuItems.
 * 
 * <p>
 * For a broader explanation of Menus see {@link MenuShell}.
 * 
 * <p>
 * While in theory a MenuItem, being a Bin, could hold any Widget as its
 * contained child, in practice only specialized MenuItem subclasses will work
 * properly as they are what support highlighting, alignment, submenus, etc.
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class MenuItem extends Item
{
    protected MenuItem(long pointer) {
        super(pointer);
    }

    /**
     * Construct a MenuItem
     */
    public MenuItem() {
        super(GtkMenuItem.createMenuItem());
    }

    /**
     * Construct a MenuItem with a given text label. The label may contain
     * underscores (<code>_<code>) which, if present, will indicate
     * the mnemonic which will activate that MenuItem directly if that key is pressed.
     */
    public MenuItem(String mnemonicLabel) {
        super(GtkMenuItem.createMenuItemWithMnemonic(mnemonicLabel));
    }

    /**
     * Sets or replaces the MenuItem's submenu, or removes it if a
     * <code>null</code> Menu is passed.
     */
    public void setSubmenu(Menu child) {
        GtkMenuItem.setSubmenu(this, child);
    }

    /**
     * Returns the submenu underneath this menu item, if any
     * 
     * @return submenu for this menu item, or <code>null</code> if none.
     */
    public Widget getSubmenu() {
        return GtkMenuItem.getSubmenu(this);
    }

    /**
     * The handler interface for an activation. An activation is triggered
     * either when the user clicks the MenuItem, or activates it with the
     * keyboard either by typing that MenuItem's menomic character (if it has
     * one) or selecting the MenuItem via the arrow keys and then pressing
     * <code>&lt;ENTER&gt;</code>.
     */
    public interface ACTIVATE extends GtkMenuItem.ACTIVATE
    {
        public void onActivate(MenuItem sourceObject);
    }

    /**
     * Connect an {@see ACTIVATE} handler to the widget.
     */
    public void connect(ACTIVATE handler) {
        GtkMenuItem.connect(this, handler);
    }
}
