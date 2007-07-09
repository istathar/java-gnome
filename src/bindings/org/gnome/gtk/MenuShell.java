/*
 * MenuShell.java
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
 * The MenuShell is the abstract super class of {@link Menu} and {@link MenuBar}.
 * It provides the common methods for adding and organizing {@link MenuItems}.
 *
 * <p>The following example creates one simple menu with a submenu and 
 *   adds it to a menu bar:
 *   <pre>      
 *        Menu subMenu = new Menu();
 *        subMenu.append(new MenuItem("Sub Item _1"));
 *        subMenu.append(new MenuItem("Sub Item _2"));
 *        MenuItem subMenuItem = new MenuItem("Sub menu ..");
 *        subMenuItem.setSubmenu(subMenu);
 *
 *        Menu aMenu = new Menu();
 *        aMenu.append(new MenuItem("Item _1"));
 *        aMenu.append(new MenuItem("Item _2"));
 *        aMenu.append(subMenuItem);
 *        MenuItem aMenuItem = new MenuItem("_Other menu ..");
 *        aMenuItem.setSubmenu(aMenu);
 *
 *        MenuBar menuBar = new MenuBar();
 *        menuBar.append(aMenuItem);
 *        // e.g. add the menuBar to the Window's VBox
 *   </pre>
 * </p>
 *
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public abstract class MenuShell extends Container
{

    protected MenuShell(long pointer) {
        super(pointer);
    }

    /**
     * Append one widget to the shell.
     */
    public void append(Widget child) {
        GtkMenuShell.append(this, child);
    }

    /**
     * Prepend one widget to the shell
     */
    public void prepend(Widget child) {
        GtkMenuShell.prepend(this, child);
    }

    /**
     * Insert one widget to the shell, at the supplied position.
     */
    public void insert(Widget child, int position) {
        GtkMenuShell.insert(this, child, position);
    }

    /**
     * Deactivate the Shell
     */
    public void deactivate() {
        GtkMenuShell.deactivate(this);
    }   
}
