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
     * 
     * @since 4.0.3
     */
    public Menu() {
        super(GtkMenu.createMenu());
    }

    /**
     * Popup a context menu. Use this when you create a Menu that will be used
     * for the popup context menu in response to a right-click somewhere. The
     * menu will appear where the mouse pointer is.
     * 
     * <p>
     * Unlike normal Menus that are part of an application's MenuBar, context
     * menus are not mapped automatically, so you need to have
     * {@link Widget#showAll() showAll()} called on them once you're done
     * building them to allocate their resources (otherwise you end up with a
     * 1x1 pixel sized menu with nothing in it).
     * 
     * @since 4.0.3
     */
    public void popup() {
        GtkMenuOverride.popup(this);
    }

    /**
     * Having constructed a Menu to be used as the context menu, present it at
     * the specified location. This is useful in conjunction with TextView's
     * {@link TextView#getLocation(TextIter) getLocation()} if you wish to
     * place the menu at the "cursor position" rather than where the mouse
     * pointer is.
     * 
     * @since 4.0.15
     */
    public void popup(int x, int y) {
        GtkMenuOverride.popupAtPosition(this, x, y);
    }

    /**
     * A special case for popping up the context menu associated with a
     * StatusIcon. Having constructed a Menu to be used as the context menu,
     * you then call this from the <code>StatusIcon.PopupMenu</code> signal
     * callback as follows:
     * 
     * <pre>
     * StatusIcon si;
     * Menu context;
     * ...
     * 
     * si.connect(new StatusIcon.PopupMenu() {
     *     public void onPopupMenu(StatusIcon source, int button, int activateTime) {
     *         context.popup(source);
     *     }
     * });
     * </pre>
     * 
     * Don't forget to call {@link Widget#showAll() showAll()} on the Menu
     * when you're done constructing it before trying to use it the first
     * time.
     * 
     * @since 4.0.3
     */
    public void popup(StatusIcon status) {
        GtkMenuOverride.popupStatusIcon(this, status);
    }

    /**
     * This method must be called in order to have the key bindings in
     * MenuItems work. It has to be the same AcceleratorGroup that was
     * specified for the enclosing top level Window that this Menu will be a
     * part of via Window's
     * {@link Window#addAcceleratorGroup(AcceleratorGroup)
     * addAcceleratorGroup()}.
     * 
     * @since 4.0.16
     */
    public void setAcceleratorGroup(AcceleratorGroup group) {
        GtkMenu.setAccelGroup(this, group);
    }
}
