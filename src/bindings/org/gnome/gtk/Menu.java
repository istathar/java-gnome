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

/**<p>
 * The Menu is a drop-down menu that consists of other {@link MenuItem}s. Menus are
 * either placed inside a {@link MenuBar} or another MenuItem as a sub menu. Thus an
 * entire hirearchy of Menu structures can be created, by appropriately placing Menus
 * inside MenuBars or MenuItems.
 * Context Menus can also be made to pop up, for instance, in response to a right-click
 * (or left-click as the case may be). This is achieved via the popup() method.
 * 
 * <p>
 * Menus can also be torn off from the parent Container. A torn off Menu's title is made
 * visible. In addition, it is also possible to tear off (detach) a Menu from one widget
 * and attach it to another.
 * 
 * <p>For a broader explanation of the Menu API see {@link MenuShell}.
 *
 * @author Sebastian Mancke
 * @author Srichand Pendyala
 * @since 4.0.3
 */
public class Menu extends MenuShell
{
    protected Menu(long pointer) {
        super(pointer);
    }

    /**
     * Contructs a new Menu.
     */
    public Menu() {
        super(GtkMenu.createMenu());
    }
    
}
