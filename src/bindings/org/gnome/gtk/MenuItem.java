/*
 * MenuItem.java
 *
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
 * A MenuItem is the basic item of a menu. It behaves like a button 
 * and serves as superclass for specialized MenuItems.
 *
 * <p>For a broader explanation of Menus see {@link MenuShell}.</p>
 *
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class MenuItem extends Item
{
    protected MenuItem(long pointer) {
        super(pointer);
    }

    /**
     * Constructs the MenuItem
     */
    public MenuItem() {
        super(GtkMenuItem.createMenuItem());
    }

    /**
     * Constructs a MenuItem with a label.
     * The label may contain underscores (<code>_<code>) to indicate
     * the mnemonic for the menu item.
     *
     * @param mnemonicLabel The text of the item, with an underscore 
     *        in front of the mnemonic character
     */
    public MenuItem(String mnemonicLabel) {
        super(GtkMenuItem.createMenuItemWithMnemonic(mnemonicLabel));
    }

    /**
     * Sets or replaces the menu item's submenu, or removes it when a 
     * NULL submenu is passed.   
     */
    // TODO: Should this better be a Menu, instead of a generic Widget?
    public void setSubmenu(Widget child) {
        GtkMenuItem.setSubmenu(this, child);
    }

    /**
     * Returns the submenu underneath this menu item, if any
     *
     * @return submenu for this menu item, or NULL if none.
     */
    public Widget getSubmenu() {
        return GtkMenuItem.getSubmenu(this);
    }
    
    /** 
     * The handler interface for an activation.
     * An activation is usual triggered, when the user clickes the MenuItem
     * or activates it with the keyboard.
     */
    public interface ACTIVATE extends GtkMenuItem.ACTIVATE {
        // TODO: should we repeat the interface here?
        // public void onActivate(MenuItem sourceObject);        
    }
    
    /** 
     * Connect an {@see ACTIVATE} handler to the widget.
     */
    public void connect(ACTIVATE handler) {
        GtkMenuItem.connect(this, handler);
    }

}
