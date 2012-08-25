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
 * A separator between groups of related ToolItems in a Toolbar.
 * 
 * <p>
 * A SeparatorToolItem usually appears as a vertical line, and is used to
 * create a visual distinction between logically related items in a Toolbar.
 * 
 * <p>
 * Somewhat unusually, a SeparatorToolItem can be used to force align other
 * ToolItems at the right of the Toolbar by adding one with
 * {@link #setDraw(boolean) setDraw(false)} and {@link #setExpand(boolean)
 * setExpand(true)} between the two "sides".
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.4
 */
public class SeparatorToolItem extends ToolItem
{
    protected SeparatorToolItem(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new SeparatorToolItem.
     */
    public SeparatorToolItem() {
        super(GtkSeparatorToolItem.createSeparatorToolItem());
    }

    /**
     * Set whether the separator will display a vertical line.
     * SeparatorToolItems have the curious property that you can tell them not
     * to present themselves in an obtrusive manner while still carrying out
     * their spacing function.
     */
    public void setDraw(boolean draw) {
        GtkSeparatorToolItem.setDraw(this, draw);
    }

    /**
     * Is the separator being displayed as a vertical line, or is it just
     * blank?
     */
    public boolean getDraw() {
        return GtkSeparatorToolItem.getDraw(this);
    }
}
