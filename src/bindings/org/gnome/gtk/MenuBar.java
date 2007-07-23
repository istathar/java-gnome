/*
 * MenuBar.java
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
 * A MenuBar is the container for menus.
 * 
 * <p>
 * A MenuBar can be added to container {@link Window} just like any other
 * Widget; in almost every situation you should be adding the menu to the top
 * of the Window by making it the first Widget packed into a VBox in that
 * Window.
 * </p>
 * 
 * <p>
 * For a broader explanation of how to use the Menus APIs, see
 * {@link MenuShell}.
 * </p>
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class MenuBar extends MenuShell
{
    protected MenuBar(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a MenuBar
     */
    public MenuBar() {
        super(GtkMenuBar.createMenuBar());
    }
}
