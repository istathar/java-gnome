/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gtk;

/**
 * The MenuShell is the abstract super class of {@link Menu} and
 * {@link MenuBar}. It provides the common methods for adding and organizing
 * {@link MenuItem}s.
 * 
 * <p>
 * The following example creates one simple menu with a submenu and adds it to
 * a menu bar:
 * 
 * <pre>
 * Menu subMenu = new Menu();
 * subMenu.append(new MenuItem(&quot;Sub Item _1&quot;));
 * subMenu.append(new MenuItem(&quot;Sub Item _2&quot;));
 * MenuItem subMenuItem = new MenuItem(&quot;Sub menu ..&quot;);
 * subMenuItem.setSubmenu(subMenu);
 * 
 * Menu aMenu = new Menu();
 * aMenu.append(new MenuItem(&quot;Item _1&quot;));
 * aMenu.append(new MenuItem(&quot;Item _2&quot;));
 * aMenu.append(subMenuItem);
 * MenuItem aMenuItem = new MenuItem(&quot;_Other menu ..&quot;);
 * aMenuItem.setSubmenu(aMenu);
 * 
 * MenuBar menuBar = new MenuBar();
 * menuBar.append(aMenuItem);
 * // finally add menuBar to the Window's VBox
 * </pre>
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public abstract class MenuShell extends Container
{
    protected MenuShell(long pointer) {
        super(pointer);
    }

    /**
     * Append one Widget to the MenuShell.
     * 
     * @since 4.0.3
     */
    public void append(Widget child) {
        GtkMenuShell.append(this, child);
    }

    /**
     * Prepend one Widget to the MenuShell.
     * 
     * @since 4.0.3
     */
    public void prepend(Widget child) {
        GtkMenuShell.prepend(this, child);
    }

    /**
     * Insert one Widget to the MenuShell at the specified position.
     * 
     * @since 4.0.3
     */
    public void insert(Widget child, int position) {
        GtkMenuShell.insert(this, child, position);
    }

    /**
     * Deactivate the MenuShell.
     * 
     * <p>
     * <i>According to the GTK API documentation, this "typically" results in
     * the Menu being erased from the screen. TODO what other effect could it
     * have?</i>
     * 
     * @since 4.0.3
     */
    public void deactivate() {
        GtkMenuShell.deactivate(this);
    }
}
