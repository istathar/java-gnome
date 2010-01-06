/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 * "Claspath Exception"), the copyright holders of this library give you
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
     * Set the GdkWindow underlying this Widget to receive visibility events!
     * As a necessary convenience, the Widget will be realized first if
     * necessary.
     */
    static final void setEventsVisibility(Widget self) {
        synchronized (lock) {
            gtk_widget_set_events_visibility(pointerOf(self));
        }
    }

    private static native final void gtk_widget_set_events_visibility(long self);
}
