/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.gdk.Keyval;
import org.gnome.gdk.ModifierType;

/**
 * MenuItems are the basic elements that form a Menu.
 * 
 * While MenuItems are Containers and are thus capable of containing other
 * Widgets; in practise only the specialized MenuItem classes will work
 * properly as they are what support highlighting, alignment, submenus, etc.
 * 
 * <p>
 * MenuItems can be either left justified or right justified, but in general
 * you should just leave this alone and it will do the right thing for your
 * locale (left aligned for western languages, right aligned for right-to-left
 * languages like Arabic).
 * 
 * <p>
 * <i>Right justification was initially designed to be used for "Help" Menus,
 * but this is now considered a bad idea and is no longer used in GNOME.</i>
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @author Srichand Pendyala
 * @author Vreixo Formoso
 * @since 4.0.3
 */
public class MenuItem extends Bin implements Activatable
{
    protected MenuItem(long pointer) {
        super(pointer);
    }

    /**
     * Construct a MenuItem
     * 
     * @since 4.0.3
     */
    public MenuItem() {
        super(GtkMenuItem.createMenuItem());
    }

    /**
     * Construct a MenuItem with a given text label. The label may contain
     * underscores (the <code>_</code> character) which, if present, will
     * indicate the mnemonic which will activate that MenuItem directly if
     * that key is pressed (whilst the Menu is showing, obviously).
     * 
     * @since 4.0.3
     */
    public MenuItem(String mnemonicLabel) {
        super(GtkMenuItem.createMenuItemWithMnemonic(mnemonicLabel));
    }

    /**
     * Construct a MenuItem with a given text label, and additionally connect
     * a handler to its <code>MenuItem.Activate</code> signal.
     * 
     * <p>
     * This is equivalent to:
     * 
     * <pre>
     * item = new MenuItem(&quot;_My menu item&quot;);
     * item.connect(handler);
     * </pre>
     * 
     * and affords you the convenience of being able to add a MenuItem fairly
     * compactly:
     * 
     * <pre>
     * Menu editMenu;
     *    
     * editMenu.append(new MenuItem(&quot;_Paste&quot;, new MenuItem.Activate() {
     *     public void onActivate(MenuItem source) {
     *         ...
     *     }
     * }));
     * </pre>
     * 
     * @since 4.0.4
     */
    public MenuItem(String mnemonicLabel, MenuItem.Activate handler) {
        super(GtkMenuItem.createMenuItemWithMnemonic(mnemonicLabel));
        connect(handler);
    }

    /**
     * Sets or replaces the MenuItem's submenu, or removes it if a
     * <code>null</code> Menu is passed.
     * 
     * @since 4.0.3
     */
    public void setSubmenu(Menu child) {
        GtkMenuItem.setSubmenu(this, child);
    }

    /**
     * Returns the submenu underneath this menu item, if any
     * 
     * @return submenu for this menu item, or <code>null</code> if none.
     * @since 4.0.3
     */
    public Widget getSubmenu() {
        return GtkMenuItem.getSubmenu(this);
    }

    /**
     * The handler interface for an activation. An activation is triggered
     * either when the user clicks the MenuItem, or activates it with the
     * keyboard either by typing that MenuItem's mnemonic character (if it has
     * one) or selecting the MenuItem via the arrow keys and then pressing <b>
     * <code>Enter</code></b>.
     * 
     * @since 4.0.3
     */
    public interface Activate extends GtkMenuItem.ActivateSignal
    {
        public void onActivate(MenuItem source);
    }

    /**
     * Connect an <code>MenuItem.Activate</code> handler to the widget.
     * 
     * @since 4.0.3
     */
    public void connect(MenuItem.Activate handler) {
        GtkMenuItem.connect(this, handler, false);
    }

    public void setRelatedAction(Action action) {
        GtkActivatable.setRelatedAction(this, action);
    }

    public Action getRelatedAction() {
        return GtkActivatable.getRelatedAction(this);
    }

    /**
     * Set a key binding for this MenuItem.
     * 
     * @since 4.0.16
     */
    public void setAccelerator(AcceleratorGroup group, Keyval keyval, ModifierType modifier) {
        String path;
        boolean exists, result;

        /*
         * Check whether it has already has a path and whether it is known, if
         * so then change it. If not, generate a path, set it and add it the
         * map to be registered.
         */

        path = GtkMenuItem.getAccelPath(this);

        if (path == null) {
            exists = false;
            path = group.generateRandomPath();
            GtkMenuItem.setAccelPath(this, path);
        } else {
            exists = GtkAccelMap.lookupEntry(path, null);
        }

        if (exists) {
            result = GtkAccelMap.changeEntry(path, keyval, modifier, true);

            if (!result) {
                throw new IllegalStateException("Can't change exising accelerator");
            }
        } else {
            GtkAccelMap.addEntry(path, keyval, modifier);
        }
    }
}
