/*
 * Boxed.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.glib;

/**
 * Parent class of proxied Boxed structures. JAva side, these behave like normal
 * {@link org.gnome.glib.Object Object}s, but they are not as rich.
 * 
 * <p>
 * <i>In the underlying GLib library, a <code>GBoxed</code> is an opaque
 * wrapper around a C structure allowing it to be used within GLib's Type
 * system. <code>GBoxed</code> generally have custom <code>copy()</code> and
 * <code>free()</code> functions as well. This are tricky, because unlike
 * <code>GObjects</code> they are not memory managed and so the owner of one
 * is responsible to free it. The trick is to figure out whether we are owner of
 * the <code>GBoxed</code> or not.</i>
 * 
 * @author Andrew Cowie
 */
/*
 * We rely on the GType being set in the Value object that we are a subclass of
 * in order to be able to call the appropriate free function, as noted at GType
 * registration time.
 */
public abstract class Boxed extends Value {
    boolean owner = false;

    /*
     * This is a placeholder to remind us of the cleanup actions that will be
     * necessary. When we put the full memory management code in place this will
     * presumably be a function called release()
     */
    /*
     * Checks to see if we are the owner of this Boxed, calls the underlying
     * free if we are, then carries on to
     * {@link org.freedesktop.bindings.Proxy#finalize()}.
     */
    public void finalize() {
        if (owner) {
            GBoxed.free(this);
        }
        super.finalize();
    }
}
