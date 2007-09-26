/*
 * Tooltips.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Tooltips are notes that will be displayed if a user hovers the mouse
 * pointer over a Widget. They are usually used with controls such as Buttons
 * and Entries to brief the user about that Widget's function.
 * 
 * <p>
 * To add a tooltip to a Widget use the
 * {@link Widget#setTooltipText(String) setTooltipText()} method available on
 * Widget.
 * 
 * <p>
 * Note that the class Tooltips represents not only one tooltip; in fact it
 * represents a group of tooltips.
 * 
 * <p>
 * <b>WARNING: This class will not have public methods in java-gnome 4.0.4,
 * and will be removed entirely soon thereafter</b>.
 * 
 * <p>
 * <i>The underlying <code>GtkTooltips</code> class is deprecated in favour
 * of a new API in <code>GtkTooltip</code>, forthcoming in GTK 2.12.</i>
 * 
 * @author Thomas Schmitz
 */
public final class Tooltips extends Object
{
    protected Tooltips(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Tooltips group.
     */
    /*
     * Used by Widget until the new Tooltip class is available.
     */
    Tooltips() {
        super(GtkTooltips.createTooltips());
    }
}
