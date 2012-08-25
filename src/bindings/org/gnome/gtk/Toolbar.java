/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007-2009 Vreixo Formoso
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
 * A Toolbar is a broad horizontal bar with several controls (usually largish
 * graphical buttons) intended to provide a fast and convenient access to
 * operations commonly used in an application.
 * 
 * <p>
 * In most cases, you will want to add some {@link ToolButton}s to the
 * Toolbar, but you can also add other elements as well by creating a
 * {@link ToolItem} and adding your own customized elements to it.
 * 
 * <p>
 * You can also group related items together by using a
 * {@link SeparatorToolItem} to the Toolbar to create a separation between
 * them. Don't overdo that, however - too many separators result in a
 * cluttered appearance.
 * 
 * <p>
 * Note that the actual on screen appearance of the Toolbar is governed by the
 * user's theme and how they have configured Toolbars to appear. The choices
 * of "<var>Text below Icons</var>" (the usual default), "<var>Text beside
 * Icons</var>", "<var>Icons only</var>", and "<var>Text only</var>" are
 * available from the GNOME panel menu at <b>System <code>&gt;</code> Menus
 * &amp; Toolbars</b> which runs the <code>gnome-ui-properties</code> program.
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.4
 */
public class Toolbar extends Container implements Orientable
{
    protected Toolbar(long pointer) {
        super(pointer);
    }

    /**
     * Create a new, empty, Toolbar.
     */
    public Toolbar() {
        super(GtkToolbar.createToolbar());
    }

    public void add(Widget w) {
        if (!(w instanceof ToolItem)) {
            throw new IllegalArgumentException("You can only add ToolItems to a Toolbar");
        }
        GtkToolbar.insert(this, (ToolItem) w, -1);
    }

    /**
     * Insert a ToolItem in the Toolbar at a given position.
     * 
     * @param item
     *            The new item to add to the Toolbar.
     * @param pos
     *            The position where the new item will be inserted. You can
     *            use <code>0</code> to prepend the item at the beginning of
     *            the Toolbar, or a negative value to append the item at the
     *            end.
     */
    public void insert(ToolItem item, int pos) {
        GtkToolbar.insert(this, item, pos);
    }

    /**
     * Sets the orientation of the Toolbar on screen.
     * 
     * <p>
     * Horizontal Toolbars are commonly used. Usually you shouldn't use a
     * vertical Toolbar it is more difficult to search for the user to find a
     * specific control. When your application has several Toolbars, however,
     * a vertical orientation can become useful as a technique to make a
     * better usage of the available screen real estate.
     * 
     * @since 4.1.1
     */
    public void setOrientation(Orientation orientation) {
        GtkOrientable.setOrientation(this, orientation);
    }

    public Orientation getOrientation() {
        return GtkOrientable.getOrientation(this);
    }

    /**
     * Set the appearance of a Toolbar.
     * 
     * <p>
     * Toolbars buttons can be customized to display either an icon, a text
     * label, or both. As explained above, in the documentation for this
     * class, the appearance of Toolbars is configured by the user as a
     * desktop setting. This method will override those settings, so in most
     * cases <b>you should not use this method</b>, and just let the user
     * choose the appearance (s)he prefers.
     * 
     * <p>
     * However, some really complex applications might need several Toolbars
     * and many buttons on each one, so configuring a compact Toolbar style
     * can help to save screen space. In such cases this method may be useful,
     * but the general advice is to use this with care, and avoid overriding
     * of user settings unless you have a strong reason.
     * 
     * <p>
     * Finally, note that if you choose for example an <code>ICONS</code> only
     * appearance, and a given ToolButton has no icon, its Label will be shown
     * instead, so GTK engine will do its best to keep Toolbar usable.
     * 
     * @since 4.0.12
     */
    public void setStyle(ToolbarStyle style) {
        GtkToolbar.setStyle(this, style);
    }
}
