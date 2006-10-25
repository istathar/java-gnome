/*
 * GObjectPlumbing.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.glib;

/**
 * Translation layer class which adds the ability to connect signals to
 * GObjects. See {@link org.freedesktop.bindings.Plumbing Plumbing} for utiltiy
 * methods which work with Proxy and Enum. <b>No one using developing applications
 * which happen to use bindings based on this package should ever need to see,
 * or subclass, this.</b>
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
}
