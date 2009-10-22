/*
 * Plumbing.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.

 */
package org.freedesktop.enchant;

import org.gnome.gdk.Gdk;

abstract class Plumbing extends org.freedesktop.bindings.Plumbing
{
    protected Plumbing() {}

    /*
     * Expose access to the global GDK lock.
     */
    /*
     * TODO I'm not sure we need this. In fact, I rather expect that this is
     * in the way, but we'd have to make the synchronized block output by the
     * code generator conditional.
     */
    protected static final java.lang.Object lock;

    static {
        lock = Gdk.lock;
    }

    protected static Entity entityFor(Class<?> type, long pointer) {
        Entity obj;

        obj = (Entity) org.freedesktop.bindings.Plumbing.instanceFor(pointer);

        if (obj != null) {
            return obj;
        } else {
            if (type == Dictionary.class) {
                obj = new Dictionary(pointer);
            } else if (type == Broker.class) {
                throw new UnsupportedOperationException("Not yet implemented");
            }
            return obj;
        }
    }
}
