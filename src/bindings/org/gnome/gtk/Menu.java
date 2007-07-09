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
 * The Menu is the holder of {@link MenuItem}s. Normaly it
 * is part of a {@link MenuBar}.
 * 
 * <p>For a broader explanation of Menus see {@link MenuShell}.</p>
 *
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class Menu extends MenuShell
{
    protected Menu(long pointer) {
        super(pointer);
    }

    /**
     * Contructs a new Menu
     */
    public Menu() {
        super(GtkMenu.createMenu());
    }
}
