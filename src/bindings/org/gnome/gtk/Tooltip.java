/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2013 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.gdk.Pixbuf;
import org.gnome.glib.Object;

/**
 * Displaying a tooltip when a user hovers the mouse over the widget or when
 * the widget receives notification the cursor is at it when in keyboard mode.
 * 
 * <p>
 * By default you can set text to show as a tooltip by using
 * {@link Widget#setTooltipText(java.lang.String) } and
 * {@link Widget#setTooltipMarkup(java.lang.String) } . However that is for
 * basic text and only text. If you want more then that you can hookup your
 * own tooltip query handler which in turn will provide an object of this
 * class for you to use.
 * 
 * <p>
 * In order to get an object of this class you first have to connect a
 * queryTooltip Handler.
 * 
 * <pre>
 * // any widget will do
 * widget = new Button();
 * widget.connect(new Widget.queryTooltip() {
 *     &#064;Override
 *     public boolean onQueryTooltip(Widget source, int x, int y, boolean keyboardMode, Tooltip tooltip) {
 *         // setup your tooltip.
 *     }
 * });
 * </pre>
 * 
 * <p>
 * Inside the method you can create your tooltip. You also receive the X and Y
 * values at which you can determine whether to show the tooltip or not. Which
 * is needed for example a TreeView which has a method to help you with. See
 * {@link TreeView#hasTooltipContext(int, int, boolean) } for more information
 * on how a TreeView handles tooltips.
 * 
 * <p>
 * KeyboardMode indicates that the application is run by only the keyboard and
 * no mouse.
 * 
 * <p>
 * When you want to show your tooltip return true, otherwise false.
 * 
 * <p>
 * There are two modes of displaying your tooltip contents. The first is the
 * basic Icon and String. You can set this up in anyway you want. Please do
 * note that both are on the same line and that the icon will always be on the
 * left.
 * 
 * <p>
 * If you want something more fancy or display more information you set any
 * widget to show. Suggested is a container of course. Where you have full
 * freedom to place all your tooltip contents.
 * 
 * @author Sarah Leibbrand
 * @since 4.1.3
 */
public class Tooltip extends Object
{
    protected Tooltip(long pointer) {
        super(pointer);
    }

    /**
     * The text to display in the tooltip.
     * 
     * @since 4.1.3
     */
    public void setText(String text) {
        GtkTooltip.setText(this, text);
    }

    /**
     * The text in pango markup to display in the tooltip.
     * 
     * @since 4.1.3
     */
    public void setMarkup(String markup) {
        GtkTooltip.setMarkup(this, markup);
    }

    /**
     * The icon to display in your tooltip.
     * 
     * @since 4.1.3
     */
    public void setIcon(Pixbuf icon) {
        GtkTooltip.setIcon(this, icon);
    }

    /**
     * The stock icon with the requested size to display in the tooltip.
     * 
     * @since 4.1.3
     */
    public void setStockIcon(Stock stock, IconSize size) {
        GtkTooltip.setIcon(this, stock.getStockId(), size);
    }

    /**
     * The widget to display in the tooltip.
     * 
     * This can be any widget but containers such as {@link Grid} are
     * recommended.
     * 
     * @since 4.1.3
     */
    public void setCustomWidget(Widget widget) {
        GtkTooltip.setCustom(this, widget);
    }
}
