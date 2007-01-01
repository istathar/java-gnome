/*
 * Widget.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * The base class of all GTK Widgets. Graphical user interface toolkits have
 * long been built up from individual controls and presentation mechanisms
 * that are nested together. These elements are collectively called Widgets.
 * Quite a lot of Widgets contain other Widgets; those are called
 * {@link Container Container}s.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public abstract class Widget extends org.gnome.gtk.Object
{

    protected Widget(long pointer) {
        super(pointer);
    }

    /**
     * Cause this Widget to be mapped to the screen. Flags a widget to be
     * displayed. Any widget that isn't shown will not appear on the screen.
     * 
     * <p>
     * There are a bunch of quirks you need to be aware of:
     * 
     * <ul>
     * <li>You have to show the Containers containing a Widget, in addition
     * to the Widget itself, before it will appear on the display.
     * <li>When a toplevel Container is shown (ie, your {@link Window Window}),
     * it is immediately realized and mapped, and any Widgets within it that
     * are shown are then realized and mapped.
     * <li>You can't get information about the actual size that has been
     * allocated to a Widget until it gets mapped to the screen.
     * </ul>
     * 
     * <p>
     * If you want to show all the widgets in a container, it's actually much
     * easier to just call {@link #showAll()} on the container, rather than
     * calling show manually one each individual Widget you've added to it.
     * 
     * @since 4.0.0
     */
    public void show() {
        GtkWidget.show(this);
    }

    /**
     * Cause this Widget, and any Widgets it contains, to be mapped to the
     * screen. You typically call this on a {@link Window Window} after you've
     * finished all the work necessary to set it up.
     * <p>
     * Quite frequently you also want to cause a Window to appear on the
     * screen as well (ie, not be buried under a whole bunch of other
     * applications' Windows), so calling Window's
     * {@link Window#present() present()} is usually next.
     * 
     * <p>
     * <i>Don't be surprised if this takes a few hundred milliseconds.
     * Realizing and mapping all the zillion elements that ultimately make up
     * a Window is one of the most resource intensive operations that GTK,
     * GDK, Pango, your X server, and your kernel have to churn through.
     * Sometimes, you just gotta wait.</i>
     * 
     * @since 4.0.0
     */
    public void showAll() {
        GtkWidget.showAll(this);
    }
}
