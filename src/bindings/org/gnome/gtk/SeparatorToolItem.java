/*
 * SeparatorToolItem.java
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
 * A separator between groups of related ToolItems in a Toolbar.
 * 
 * <p>
 * A SeparatorToolItem usually appears as a vertical line, and is used to
 * separate between logically related items in a Toolbar.
 * 
 * <p>
 * SeparatorToolItems can also be used to align other ToolItems at the right
 * of the Toolbar, by setting <i>draw</i> property to <code>false</code>
 * and <i>expand</i> to <code>true</code>.
 * 
 * @author Vreixo Formoso
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
     */
    public void setDraw(boolean draw) {
        GtkSeparatorToolItem.setDraw(this, draw);
    }

    /**
     * Get if the separator will be displayed as a vertical line, or just
     * blank.
     */
    public boolean getDraw() {
        return GtkSeparatorToolItem.getDraw(this);
    }

}
