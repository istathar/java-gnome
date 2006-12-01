/*
 * GtkWidget.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Proxy;
import org.gnome.glib.Plumbing;
import org.gnome.glib.Signal;

// generated
final class GtkWidget extends Plumbing
{

    private GtkWidget() {}

    static final Widget instanceFor(long pointer) {
        Proxy obj = proxyFor(pointer);

        if (obj == null) {
            // FIXME can't instantiate Widget, so now what?
            return null;
        } else {
            return (Widget) obj;
        }
    }

    static final void show(Widget self) {
        gtk_widget_show(pointerOf(self));
    }

    private static native final void gtk_widget_show(long self);

    static final void showAll(Widget self) {
        gtk_widget_show_all(pointerOf(self));
    }

    private static native final void gtk_widget_show_all(long self);

    interface DELETE_EVENT extends Signal
    {
        boolean onDeleteEvent(Widget source, Object event);
    }

    static final void connect(Widget self, GtkWidget.DELETE_EVENT handler) {
        connectSignal(self, handler, GtkWidget.class, "delete-event");
    }

    static final boolean handleDeleteEvent(Signal handler, long source, long event) {
        return ((GtkWidget.DELETE_EVENT) handler).onDeleteEvent(instanceFor(source), null); // FIXME
    }

}
