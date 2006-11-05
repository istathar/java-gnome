/*
 * GObject.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.glib;

/*
 * crafted since the setProperty() and connectSignal() functionality is somewhat
 * custom transform not especially connected to either the pulblic API or the
 * underlying G code. Note that this exends the base Plumbing, not the one here
 * in org.gnome.glib (as it calls this class!).
 */
final class GObject extends org.freedesktop.bindings.Plumbing {

    // no instantiation
    private GObject() {
    }

    static final void setProperty(org.gnome.glib.Object self, String name,
            org.gnome.glib.Value value) {
        g_object_set_property(pointerOf(self), name, pointerOf(value));
    }

    private static final native void g_object_set_property(long self,
            String name, long value);

    static final Value getProperty(org.gnome.glib.Object self, String name) {
        return (Value) proxyFor(g_object_get_property(pointerOf(self), name));  
    }
    
    private static final native long g_object_get_property(long self, String name);
    
    // /*
    // * This is package visible so that org.gnome.glib.Plumbing can see it.
    // That
    // * class exposes a method with this name and signature which is then
    // visible
    // * to all the generated classes (ie Gdk, Gtk, etc) that offer signal
    // events
    // * that can be hooked up.
    // */
    // static final void connectSignal(org.gnome.glib.Object instance,
    // org.gnome.glib.Signal handler) {
    // g_signal_connect(pointerOf(instance), handler);
    // }

    // or

    /*
     * Atypically, this is package visible so that org.gnome.glib.Plumbing can
     * see it. That class exposes a method with the name connectSignal() which
     * is then visible to all the generated classes (ie Gdk, Gtk, etc) that
     * offer signal events that can be hooked up. Plumbing.connectSignal() is
     * the only method which calls this one.
     */
    final static native void g_signal_connect(long instance,
            java.lang.Object handler);
}
