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
 * somewhat custom transform not especially connected to either the public API
 * or the underlying G code.
 */
final class GObject extends Plumbing
{
    private GObject() {}

    static final void setProperty(org.gnome.glib.Object self, String name, Value value) {
        g_object_set_property(pointerOf(self), name, pointerOf(value));
    }

    private static final native void g_object_set_property(long self, String name, long value);

    static final Value getProperty(org.gnome.glib.Object self, String name) {
        return valueFor(g_object_get_property(pointerOf(self), name));
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

    /**
     * Get the name registered for a the GType corresponding to this
     */

    /**
     * Lookup the type name for a given Value. <i>When a GType such as a
     * primitive (fundamental) type or a class is registered in GObject, it is
     * given a name.
     * 
     * <p>
     * <i>We do not use or even provide a mechanism to retrieve the GType
     * itself. This value would be opaque and in any case changes from run to
     * run.</i>
     * 
     * @param value
     *            the pointer address of the <code>GValue</code> you are
     *            looking at
     * 
     * @return the name which is used to identify the <code>GType</code> in
     *         the underlying libraries.
     */
    /*
     * We don't really need this, but we'll leave it here for bindings hackers
     * to use if debugging.
     */
    static final String type(Object object) {
        return g_type_name(pointerOf(object));
    }

    /*
     * Atypically, this native method is package visible so that the crucial
     * instanceForObject() method in org.gnome.glib.Plumbing can see it. That
     * method needs to call this _before_ it can (and in order to) construct a
     * Proxy.
     */
    static native final String g_type_name(long object);

    private static native final void g_object_unref(long reference);
}
