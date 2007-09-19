/*
 * CellRenderer.java
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
 * <p>
 * Note that there is not one CellRenderer per cell in the table like
 * presentation of a TreeView! Instead, a CellRenderer is an engine called
 * upon to draw <i>many</i> cells. The Widget employing them will, by turn,
 * set various properties (the actual data for the current cell in question
 * would be what changes, though most of the rest of the properties would
 * likely remain constant) and then ask the CellRenderer to return the
 * rendered result.
 * 
 * <p>
 * It's easy to be tempted into thinking that CellRenderers are Widgets,
 * especially as they get <code>pack()</code>ed into TreeViewColumns on
 * their way to being used in TreeViews. They are, however, merely utility
 * elements that are used to facilitate drawing, and <i>not</i> full power
 * Widgets, as you would see from a closer look at the class hierarchy.
 * 
 * @author Andrew Cowie
 */
/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public abstract class CellRenderer extends Object
{
    protected CellRenderer(long pointer) {
        super(pointer);
    }
}
