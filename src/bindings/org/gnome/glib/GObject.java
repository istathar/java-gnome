/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
    final static native void g_signal_connect(long instance, java.lang.Object handler,
            Class<?> receiver, String name, boolean after);

    /**
     * Call g_object_add_toggle_ref() on the argument passed. This should only
     * called when we're creating a Proxy for a GObject.
     */
    static void addToggleRef(org.gnome.glib.Object reference) {
        long pointer = pointerOf(reference);
        /*
         * if are transmitting NULL from the G side to null on the Java sde,
         * we don't need to do anything.
         */
        if (pointer == 0) {
            return;
        }
        synchronized (lock) {
            g_object_add_toggle_ref(pointer, reference);
        }
    }

    /**
     * Call g_object_remove_toggle_ref() on the argument passed. You'd really
     * best only do this once, since removing this ref will undoubtedly cause
     * the destruction of the underlying GObject.
     */
    static void removeToggleRef(org.gnome.glib.Object reference) {
        long pointer = pointerOf(reference);
        // guard against absurdity.
        if (pointer == 0) {
            return;
        }
        synchronized (lock) {
            g_object_remove_toggle_ref(pointer);
        }
    }

    /**
     * Lookup the type name for a given Object. <i>When a GType such as a
     * primitive (fundamental) type or a class is registered in GObject, it is
     * given a name.
     * 
     * <p>
     * <i>We do not use or even provide a mechanism to retrieve the GType
     * itself. This value would be opaque and in any case changes from run to
     * run.</i>
     * 
     * @param value
     *            the pointer address of the <code>GObject</code> you are
     *            looking at
     * 
     * @return the name which is used to identify the <code>GType</code> in
     *         the underlying libraries.
     */
    /*
     * We don't really need this, but we'll leave it here for bindings hackers
     * to use if debugging.
     */
    static final String typeName(Object object) {
        return g_type_name(pointerOf(object));
    }

    /*
     * Atypically, this native method is package visible so that the crucial
     * instanceForObject() method in org.gnome.glib.Plumbing can see it. That
     * method needs to call this _before_ it can (and in order to) construct a
     * Proxy.
     */
    static final String typeName(long object) {
        return g_type_name(object).intern();
    }

    private static native final String g_type_name(long object);

    private static native final void g_object_add_toggle_ref(long reference, Object target);

    private static native final void g_object_remove_toggle_ref(long reference);
}
