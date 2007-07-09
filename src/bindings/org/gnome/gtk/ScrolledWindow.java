/*
 * ScrolledWindow.java
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
 * A Bin widget with scrollbars.
 *
 * <p>This widget is a simple way of surrounding another widget with scrollbars.</p>
 * 
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class ScrolledWindow extends Bin
{
    protected ScrolledWindow(long pointer) {
        super(pointer);
    }

    /** 
     * Constructs a ScrolledWindow with default adjustments
     */
    public ScrolledWindow() {
        // call createScrolledWindow and let it create the adjustments
        super(GtkScrolledWindow.createScrolledWindow(null, null));
    }

    /**
     * Sets the scrollbar policy for the horizontal and vertical scrollbars.
     *
     * @param hscrollbarPolicy Policy for the horizontal scrollbar
     * @param vscrollbarPolicy Policy for the vertical scrollbar
     */
    public void setPolicy(PolicyType hscrollbarPolicy, PolicyType vscrollbarPolicy) {
        GtkScrolledWindow.setPolicy(this, hscrollbarPolicy, vscrollbarPolicy);
    }

    /**
     * Adds a widget with a new Viewport.
     *
     * <p>Use this method only for those widgets, which do no support scrolling directly,
     *    use {@link Container#add()} in the other case</p>
     * 
     * @param child The child widget
     */
    public void addWithViewport(Widget child) {
        GtkScrolledWindow.addWithViewport(this, child);
    }
}
