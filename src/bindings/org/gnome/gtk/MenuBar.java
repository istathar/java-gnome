/*
 * MenuBar.java
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
 * A MenuBar ist the container for menus.
 *
 * <p>A MenuBar can be added to container {@link Window} as every other widget.
 *    To create the Application Menu, you can place the MenuBar in a {@link VBox} for example.
 * </p>
 *
 * <p>For a broader explanation of Menus see {@link MenuShell}.</p>
 *
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class MenuBar extends MenuShell
{
    protected MenuBar(long pointer) {
        super(pointer);
    }

    /**
     * Constructs the MenuBar
     */
    public MenuBar() {
        super(GtkMenuBar.createMenuBar());
    }
}
