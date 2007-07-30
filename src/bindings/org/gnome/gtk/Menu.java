/*
 * Menu.java
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
 * A drop-down set of Widgets creating a menu. Menus consist of other
 * {@link MenuItem}s. Menus are either placed inside a {@link MenuBar} or
 * another MenuItem, thereby forming a sub-menu. An entire hierarchy of Menu
 * structures can thus be created by appropriately placing Menus inside
 * MenuBars or MenuItems.
 * 
 * <p>
 * A "context menu" (a stand-alone menu which pops-up) can also be created;
 * this is commonly used to create a context sensitive popup menu in response
 * to a right-click (or left-click as the case may be); see the popup()
 * method.
 * 
 * <p>
 * For a broader explanation of the Menu API see {@link MenuShell}.
 * 
 * <p>
 * <i>GTK does have the ability to "tear" Menus off from the parent Container.
 * After consistently poor results in usability testing, however, tear-off
 * menus are now highly discouraged in the GNOME community. They are therefore
 * not presented in the java-gnome bindings.</i>
 * 
 * @author Sebastian Mancke
 * @author Srichand Pendyala
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class Menu extends MenuShell
{
    protected Menu(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Menu.
     */
    public Menu() {
        super(GtkMenu.createMenu());
    }
}
