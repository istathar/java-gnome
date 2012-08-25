/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007      Vreixo Formoso
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
 * ToolItems are the items that can be added to a {@link Toolbar}.
 * 
 * <p>
 * Usually you will prefer to use a subtype of this class, such as
 * {@link ToolButton}, in your Toolbars. However, if you want to add another
 * kind of Widget, you have to create a new ToolItem and {@link #add(Widget)
 * add()} the desired Widget to it.
 * 
 * @see Toolbar
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class ToolItem extends Bin implements Activatable
{
    protected ToolItem(long pointer) {
        super(pointer);
    }

    /**
     * Create a new ToolItem.
     */
    public ToolItem() {
        super(GtkToolItem.createToolItem());
    }

    /**
     * Set whether this ToolItem will be expanded when there is available
     * space on the Toolbar.
     */
    public void setExpand(boolean expand) {
        GtkToolItem.setExpand(this, expand);
    }

    /**
     * Get if this ToolItem will be [is] expanded in the presence of extra
     * available space on the Toolbar.
     */
    public boolean getExpand() {
        return GtkToolItem.getExpand(this);
    }

    public void setRelatedAction(Action action) {
        GtkActivatable.setRelatedAction(this, action);
    }

    public Action getRelatedAction() {
        return GtkActivatable.getRelatedAction(this);
    }

    /**
     * Indicate that this ToolItem is to be considered to have
     * "priority text". When the ToolbarStyle is
     * {@link ToolbarStyle#BOTH_HORIZ BOTH_HORIZ} (which is the default in
     * GNOME these days) only ToolItems with this property set will have their
     * labels showing. For example, browsers often have the label "Back" but
     * not "Forward" or "Up" marked important and thus showing.
     * 
     * @since 4.0.19
     */
    public void setIsImportant(boolean setting) {
        GtkToolItem.setIsImportant(this, setting);
    }
}
