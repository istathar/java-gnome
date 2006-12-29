/*
 * Plumbing.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

import org.freedesktop.bindings.Proxy;

/**
 * Translation layer class which adds the ability to connect signals to
 * GObjects. See {@link org.freedesktop.bindings.Plumbing Plumbing} for
 * utiltiy methods which work with Proxy and Enum. <b>No one using developing
 * applications which happen to use bindings based on this package should ever
 * need to see, or subclass, this.</b>
 * 
 * @author Andrew Cowie
 */
public abstract class Plumbing extends org.freedesktop.bindings.Plumbing
{
    /**
     * Retrieve an appropriate Java Proxy for this pointer. This will return
     * the Proxy instance already created by a real constructor if one was
     * created Java side; where no Proxy exists it looks up the underlying
     * <code>GType</code> and does its best to create it.
     * 
     * @see org.freedesktop.bindings.Plumbing#instanceFor(long)
     */
    protected static Proxy instanceFor(long pointer) {
        Proxy obj = org.freedesktop.bindings.Plumbing.instanceFor(pointer);

        if (obj == null) {
            long type = GValue.g_value_type(pointer);
            return new StringValue(pointer);
            // throw new UnsupportedOperationException();
        } else {
            return obj;
        }
    }

    /**
     * Connect a signal handler to a GObject.
     * 
     * @param instance
     *            the Object Proxy you want to connet the singnal to
     * @param handler
     *            the object implementing the Signal subinterface we defined
     *            in one of our public classes.
     * @param name
     *            the name of the signal being connected. <i>By design, the
     *            literal name of the interface being connected is the name of
     *            the signal in capitals by our naming convention.
     *            Nevertheless, you have to explicitly name the signal in
     *            because self-delegation means we never quite know which is
     *            being hooked up. This should be generated!</i>
     */
    /*
     * In this case we need to go back to GObject in order to be able to make
     * the native call to our g_signal_connect() wrapper.
     */
    protected static final void connectSignal(Object instance, Signal handler, Class receiver,
            String name) {

        GObject.g_signal_connect(pointerOf(instance), handler, receiver, name);
    }

    /*
     * We place the guard on the assumption that anyone calling this is about
     * to pass it through to the native side as a GType.
     */
    protected static final long typeOf(Value reference) {
        return 0L; // FIXME REMOVE
    }
}
