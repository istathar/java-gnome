/*
 * GtkWidgetOverride.java
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

import org.gnome.gdk.EventMask;

/**
 * Hand crafted to get at the fields of Widget.
 * 
 * @author Andrew Cowie
 */
final class GtkWidgetOverride extends Plumbing
{
    private GtkWidgetOverride() {}

    static final org.gnome.gdk.Window getWindow(Widget self) {
        long result;

        synchronized (lock) {
            result = gtk_widget_get_window(pointerOf(self));

            return (org.gnome.gdk.Window) objectFor(result);
        }
    }

    private static native final long gtk_widget_get_window(long self);

    /**
     * Allocation is yet another accessible GObject field. We really need to
     * deal with this into the code generator.
     */
    static final Allocation getAllocation(Widget self) {
        long result;

        synchronized (lock) {
            result = gtk_widget_get_allocation(pointerOf(self));

            return (Allocation) boxedFor(Allocation.class, result);
        }
    }

    private static native final long gtk_widget_get_allocation(long self);

    static final Requisition getRequisition(Widget self) {
        long result;

        synchronized (lock) {
            result = gtk_widget_get_requisition(pointerOf(self));

            return (Requisition) boxedFor(Requisition.class, result);
        }
    }

    private static native final long gtk_widget_get_requisition(long self);

    /**
     * Set the events that the underlying GdkWindow will receive.
     * As a necessary convenience, the Widget will be realized first if
     * necessary.
     */
    static final void setEvents(Widget self, EventMask eventMask) {
        if (self == null) {
            throw new IllegalArgumentException("self can't be null");
        }

        if (eventMask == null) {
            throw new IllegalArgumentException("eventMask can't be null");
        }

        synchronized (lock) {
            gtk_widget_set_events(pointerOf(self), numOf(eventMask));
        }
    }

    private static native final void gtk_widget_set_events(long self, int eventMask);

    static final EventMask getEvents(Widget self) {
        int result;

        if (self == null) {
            throw new IllegalArgumentException("self can't be null");
        }

        synchronized (lock) {
            result = gtk_widget_get_events(pointerOf(self));

            return (EventMask) flagFor(EventMask.class, result);
        }
    }

    private static native final int gtk_widget_get_events(long self);
}
