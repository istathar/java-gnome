/*
 * ScrolledWindow.java
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
 * Add scrollbars to a Widget. There are times when you have a Widget which is
 * larger than the area you wish to constrain it to, and the usual way to deal
 * with this is to enhance the Widget with scrollbars. ScrolledWindow is a
 * Bin which enables you to control the scrollbars added in such cases.
 * 
 * <p>
 * Some Widgets have built in support for scrolling; in such cases you add
 * them directly with the usual {@link Container#add(Widget) add()} method;
 * however, if other Widgets need to be enhanced to support scrolling; in such
 * cases you must nest it inside a Viewport; use
 * {@link #addWithViewport(Widget) addWithViewport()} as a quick way to
 * achieve this.
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
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
     * @param hscrollbarPolicy
     *            Policy for the horizontal scrollbar
     * @param vscrollbarPolicy
     *            Policy for the vertical scrollbar
     */
    public void setPolicy(PolicyType hscrollbarPolicy, PolicyType vscrollbarPolicy) {
        GtkScrolledWindow.setPolicy(this, hscrollbarPolicy, vscrollbarPolicy);
    }

    /**
     * Create a new Viewport and embeds the child Widget in it before adding
     * it to the ScrolledWindow. This is a convenience function; you could
     * always create the Viewport yourself if you really wanted. Note that
     * this method is only for Widgets which do not support scrolling directly
     * themselves; use {@link Container#add()} directly for those Widgets that
     * do.
     */
    public void addWithViewport(Widget child) {
        GtkScrolledWindow.addWithViewport(this, child);
    }
}
