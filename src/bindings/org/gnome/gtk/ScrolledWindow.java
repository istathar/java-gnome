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
 * Add scrollbars to a Widget. There are times when you have a Widget which is
 * larger than the area you wish to constrain it to, and the usual way to deal
 * with this is to enhance the Widget with scrollbars. ScrolledWindow is a Bin
 * which enables you to control the scrollbars added in such cases.
 * 
 * <p>
 * Some Widgets have built in support for scrolling; in such cases you add
 * them directly with the usual {@link Container#add(Widget) add()} method;
 * however, if other Widgets need to be enhanced to support scrolling; in such
 * cases you must nest it inside a Viewport; use
 * {@link #addWithViewport(Widget) addWithViewport()} as a quick way to
 * achieve this.
 * 
 * <p>
 * <i>This is very poorly named. It is <b>not</b> a subclass of Window; it
 * refers instead to a viewport scrolling around on an underlying canvas.</i>
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
/*
 * TODO document the relationship between the subordinate Adjustments and
 * Scrollbars.
 */
public class ScrolledWindow extends Bin
{
    protected ScrolledWindow(long pointer) {
        super(pointer);
    }

    /**
     * Construct a ScrolledWindow. This will automatically create default
     * Adjustments for managing the horizontal and vertical ScrollBars.
     * 
     * @since 4.0.3
     */
    public ScrolledWindow() {
        // call createScrolledWindow and let it create the adjustments
        super(GtkScrolledWindow.createScrolledWindow(null, null));
    }

    /**
     * Construct a ScrolledWindow with the specified Adjustments
     */
    /*
     * FIXME This doesn't work, at least not the way I expect it to. In fact,
     * every time I've tried it the other ScrolledWindow whose Adjustments I
     * borrow breaks. If someome can figure out the proper use of this, and
     * document it, we can make this public.
     */
    ScrolledWindow(Adjustment hadjustment, Adjustment vadjustment) {
        super(GtkScrolledWindow.createScrolledWindow(hadjustment, vadjustment));
    }

    /**
     * Set the scrollbar policy for the horizontal and vertical scrollbars.
     * 
     * @since 4.0.3
     */
    public void setPolicy(PolicyType hscrollbarPolicy, PolicyType vscrollbarPolicy) {
        GtkScrolledWindow.setPolicy(this, hscrollbarPolicy, vscrollbarPolicy);
    }

    public void add(Widget child) {
        if (!((child instanceof TextView) || (child instanceof TreeView) || (child instanceof IconView) || (child instanceof Layout) || (child instanceof Viewport))) {
            throw new IllegalArgumentException(
                    "You can't directly add() a Widget that doesn't have scrolling support built in.\n"
                            + "Use ScrolledWindow's addWithViewport() instead, or create your own Viewport.");
        }
        GtkContainer.add(this, child);
    }

    /**
     * Create a new Viewport and embeds the child Widget in it before adding
     * it to the ScrolledWindow. This is a convenience function; you could
     * always create the Viewport yourself if you really wanted. Note that
     * this method is only for Widgets which do not support scrolling directly
     * themselves; use {@link Container#add(Widget) add()} directly for those
     * Widgets that do.
     * 
     * <p>
     * As a convienience, calling this method will set the ShadowType of the
     * created Viewport to <code>NONE</code>, meaning that if you do want a
     * decoration, you can achieve it in a single place here with a call to
     * ScrolledWindow's {@link #setShadowType(ShadowType) setShadowType()}.
     * 
     * @since 4.0.15
     */
    public void addWithViewport(Widget child) {
        final Viewport port;

        if ((child instanceof TextView) || (child instanceof TreeView) || (child instanceof Layout)) {
            // in future, IconView; for now we allow IconView in both add() and addWithViewport()
            // any others?
            throw new IllegalArgumentException(
                    "You must not addWithViewport() a Widget that already has scrolling support built in. Use Container's add() instead.");
        }
        GtkScrolledWindow.addWithViewport(this, child);

        port = (Viewport) GtkBin.getChild(this);
        port.setShadowType(ShadowType.NONE);
    }

    /**
     * Get the Scrollbar Widget that is being used to draw the horizontal
     * scroll bar on the bottom edge of this ScrolledWindow.
     * 
     * @since 4.0.8
     */
    public Scrollbar getHScrollbar() {
        return (Scrollbar) GtkScrolledWindow.getHscrollbar(this);
    }

    /**
     * Get the Scrollbar Widget that is being used to draw the vertical scroll
     * bar on the right hand side of this ScrolledWindow.
     * 
     * @since 4.0.8
     */
    public Scrollbar getVScrollbar() {
        return (Scrollbar) GtkScrolledWindow.getVscrollbar(this);
    }

    /**
     * Get the Adjustment that is being used to drive the horizontal position
     * of the scroll bar on the bottom edge of this ScrolledWindow.
     * 
     * @since 4.0.8
     */
    public Adjustment getHAdjustment() {
        return GtkScrolledWindow.getHadjustment(this);
    }

    /**
     * Get the Adjustment that is being used to drive the vertical position of
     * the scroll bar on the right hand side of this ScrolledWindow.
     * 
     * @since 4.0.8
     */
    public Adjustment getVAdjustment() {
        return GtkScrolledWindow.getVadjustment(this);
    }

    /**
     * Set the type of decoration you want around the child Widget in the
     * ScrolledWindow. You probably don't need this, since the default is
     * {@link ShadowType#NONE NONE}.
     * 
     * @since 4.0.15
     */
    public void setShadowType(ShadowType type) {
        GtkScrolledWindow.setShadowType(this, type);
    }

    /**
     * Get the decoration currently set for this ScrolledWindow.
     * 
     * @since 4.0.15
     */
    public ShadowType getShadowType() {
        return GtkScrolledWindow.getShadowType(this);
    }

    /**
     * Get the amount of spacing being drawn between the Viewport and the
     * Scrollbars.
     * 
     * <p>
     * This is the <var>scrollbar-spacing</var> style property.
     * 
     * @since 4.0.17
     */
    /*
     * This is a prototype of exercising the ability to access style
     * properties.
     */
    public int getScrollbarSpacing() {
        return super.getStylePropertyInteger("scrollbar-spacing");
    }
}
