/*
 * Boxed.java
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

/**
 * Parent class of proxied structures. Java side, these behave like normal
 * {@link org.gnome.glib.Object Object}s, but they are not as rich - they may
 * be missing getters or setters (or both!), for example.
 * 
 * <p>
 * In general, Boxed are short lived. They are commonly used to convey
 * parameters or represent state such as in an Iterator. As such, good
 * programming practice is to <b>not</b> hold on to these long term. Use them
 * and let them go out of scope so they can be reclaimed.
 * 
 * <p>
 * <i>In the underlying GLib library, a <code>GBoxed</code> is an opaque
 * wrapper around a C structure allowing it to be used within GLib's Type
 * system. <code>GBoxed</code> generally have custom <code>copy()</code> and
 * <code>free()</code> functions. Unlike <code>GObjects</code> they are not
 * memory managed via the reference counting mechanism, and so the owner of a
 * <code>GBoxed</code> is responsible to free it. The trick is to figure out
 * whether we are owner of the <code>GBoxed</code> or not...</i>
 * 
 * @author Andrew Cowie
 */
public abstract class Boxed extends Value
{
    /*
     * Default true, which is the case for most instances. TODO True?
     */
    boolean owner = true;

    protected Boxed(long pointer) {
        super(pointer);
    }

    /**
     * Check to see if we are the owner of this Boxed. Call the underlying
     * <code>free()</code> if we are, then carry on to
     * {@link org.freedesktop.bindings.Proxy#finalize() Proxy's finalize()}.
     */
    /*
     * This is a placeholder to remind us of the cleanup actions that will be
     * necessary, irrespective of the finalizer technique used.
     */
    protected void finalize() {
        if (owner) {
            release();
            owner = false;
        }
        super.finalize();
    }
}
