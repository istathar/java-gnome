/*
 * GObject.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

/*
 * crafted since the setProperty() and connectSignal() functionality is
 * somewhat custom transform not especially connected to either the pulblic
 * API or the underlying G code.
 */
final class GObject extends Plumbing
{
    private GObject() {}

    static final void setProperty(org.gnome.glib.Object self, String name, Value value) {
        g_object_set_property(pointerOf(self), name, pointerOf(value));
    }

    private static final native void g_object_set_property(long self, String name, long value);

    static final Value getProperty(org.gnome.glib.Object self, String name) {
        return (Value) instanceFor(g_object_get_property(pointerOf(self), name));
    }

    private static final native long g_object_get_property(long self, String name);

    /*
     * Atypically, this is package visible so that org.gnome.glib.Plumbing can
     * see it. That class exposes a method with the name connectSignal() which
     * is then visible to all the generated classes (ie Gdk, Gtk, etc) that
     * offer signal events that can be hooked up. Plumbing.connectSignal() is
     * the only method which calls this one.
     */
    final static native void g_signal_connect(long instance, java.lang.Object handler, Class receiver,
            String name);

    /**
     * Calls g_object_unref() of the argument passed. You'd really best only
     * do this once.
     */
    static void unref(org.gnome.glib.Object reference) {
        long pointer = pointerOf(reference);
        // guard against absurdity.
        if (pointer == 0) {
            return;
        }
        g_object_unref(pointer);
    }

    private static native final void g_object_unref(long reference);
}
