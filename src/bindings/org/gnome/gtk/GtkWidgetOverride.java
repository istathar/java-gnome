/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2014 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.gdk.EventMask;

/**
 * Hand crafted to get at the fields of Widget.
 * 
 * @author Andrew Cowie
 * @author Guillaume Mazoyer
 */
final class GtkWidgetOverride extends Plumbing
{
    private GtkWidgetOverride() {}

    static final void getAllocation(Widget self, Allocation allocation) {
        synchronized (lock) {
            gtk_widget_get_allocation(pointerOf(self), pointerOf(allocation));
        }
    }

    private static native final void gtk_widget_get_allocation(long self, long allocation);

    static final void getRequisition(Widget self, Requisition requisition) {
        synchronized (lock) {
            gtk_widget_get_requisition(pointerOf(self), pointerOf(requisition));
        }
    }

    private static native final void gtk_widget_get_requisition(long self, long requisition);

    /**
     * Set the events that the underlying GdkWindow will receive. As a
     * necessary convenience, the Widget will be realized first if necessary.
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

    static final Clipboard getPrimaryClipboard(Widget self) {
        long result;

        synchronized (lock) {
            result = gtk_widget_get_primary_clipboard(pointerOf(self));

            return (Clipboard) objectFor(result);
        }
    }

    private static native final long gtk_widget_get_primary_clipboard(long self);
}
