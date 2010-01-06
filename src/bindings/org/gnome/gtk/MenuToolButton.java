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
 * "Claspath Exception"), the copyright holders of this library give you
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
 * A MenuToolButton is an special kind of ToolButton that has an additional
 * drop-down Menu.
 * 
 * <p>
 * Next to the ToolButton itself, a MenuToolButton shows another little Button
 * with an arrow. When the user clicks this additional Button, a drop-down
 * Menu pops up.
 * 
 * <p>
 * The main usage of a MenuToolButton is to provide access to several related
 * actions in a Toolbar without wasting too much screen space. For example,
 * your application can have a MenuToolButton for the "New Document" action,
 * using the attached Menu to let users choose what kind of new document they
 * want to create.
 * 
 * <p>
 * A MenuToolButton has a default action, to be executed when user clicks the
 * main Button itself and not the the arrow Button. You can capture that
 * default event with the {@link ToolButton.Clicked} signal. User Menu
 * selections are captured with the usual {@link MenuItem.Activate} signal of
 * each <code>MenuItem</code>.
 * 
 * @see Toolbar
 * @see Menu
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class MenuToolButton extends ToolButton
{
    protected MenuToolButton(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new MenuToolButton using the given icon and Label.
     * 
     * @param iconWidget
     *            The Widget to be used as the icon for the MenuToolButton.
     *            Usually you will want to use a Widget display an image, such
     *            as {@link Image}. Use <code>null</code> if you do not want
     *            an icon.
     * @param label
     *            The Label for the MenuToolButton, or <code>null</code> to
     *            provide not Label.
     */
    public MenuToolButton(Widget iconWidget, String label) {
        super(GtkMenuToolButton.createMenuToolButton(iconWidget, label));
    }

    /**
     * Creates a new MenuToolButton from the specific stock item. Both the
     * Label and icon will be set properly from the stock item. By using a
     * system stock item, the newly created MenuToolButton with use the same
     * Label and Image as other GNOME applications. To ensure consistent look
     * and feel between applications, it is highly recommended that you use
     * provided stock items whenever possible.
     * 
     * @param stock
     *            The StockId that will determine the Label and icon of the
     *            MenuToolButton.
     */
    public MenuToolButton(Stock stock) {
        super(GtkMenuToolButton.createMenuToolButtonFromStock(stock.getStockId()));
    }

    /**
     * Sets the Menu to be popped up when the user clicks the arrow Button.
     * 
     * <p>
     * You can pass <code>null</code> to make arrow insensitive.
     */
    public void setMenu(Menu menu) {
        GtkMenuToolButton.setMenu(this, menu);
    }

    /**
     * Get the Menu associated with the MenuToolButton.
     * 
     * @return The associated Menu or <code>null</code> if no Menu has been
     *         set.
     */
    public Menu getMenu() {
        return (Menu) GtkMenuToolButton.getMenu(this);
    }
}
