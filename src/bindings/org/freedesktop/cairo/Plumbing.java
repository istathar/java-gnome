/*
 * Plumbing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.gdk.Gdk;

public abstract class Plumbing extends org.freedesktop.bindings.Plumbing
{
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
}
