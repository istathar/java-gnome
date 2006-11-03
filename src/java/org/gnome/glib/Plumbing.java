/*
 * Plumbing.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */
package org.gnome.glib;

/**
 * Translation layer class which adds the ability to connect signals to
 * GObjects. See {@link org.freedesktop.bindings.Plumbing Plumbing} for utiltiy
 * methods which work with Proxy and Enum. <b>No one using developing
 * applications which happen to use bindings based on this package should ever
 * need to see, or subclass, this.</b>
 * 
 * @author Andrew Cowie
 */
public abstract class Plumbing extends org.freedesktop.bindings.Plumbing {

    /**
     * Connect a signal handler to a GObject.
     */
    /*
     * In this case we need to go back to GObject in order to be able to make
     * the native call to our g_signal_connect() wrapper.
     */
    protected static final void connectSignal(Object instance, Signal handler) {
        GObject.g_signal_connect(pointerOf(instance), handler);
    }

    /**
     * @return an opaque value which can be passed as a <code>GType</code> to
     *         the underlying libraries.
     */
    /*
     * We place the guard on the assumption that anyone calling this is about to pass
     * it through to the native side as a GType.
     */
    protected static final long typeOf(Value reference) {
        if (reference.type == 0L) {
            throw new IllegalStateException("GType not set for this Value");
        }
        return reference.type;
    }
}
