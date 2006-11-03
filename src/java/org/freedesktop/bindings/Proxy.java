/*
 * Proxy.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.freedesktop.bindings;

/**
 * A proxy object representing a native resource. Specifically, this is a
 * wrapper around a pointer.
 */
/*
 * And so the magic happens. This is where the pointer that this class proxies
 * is held. The JNI layer reaches up to this class and reads the pointer field
 * to get the address of the proxied GValue, etc. This is in its own library as
 * a means to keep the pointer handling isolated and cleanly installable. Also
 * useful given that Cairo doesn't actually depend on Glib.
 */
public abstract class Proxy {

    /*
     * This is an opaque representation of a memory address. It's a Java long,
     * which means it's 64 bits wide which in turn means it can hold an address
     * on a 64 bit system, but any interpretation that the Java language might
     * asign to a long (ie, that it's signed) is meaningless and incorrect! This
     * is package public so that Plumbing can see it, and final so that once
     * constructed its immutable.
     */
    final long pointer;

    /**
     * Create a new proxy object with the specified address as its pointer. This
     * it the top of the constructor chain.
     */
    public Proxy(long pointer) {
        this.pointer = pointer;

        Plumbing.registerObject(this);
    }

    /**
     * Exists mostly as a convenience. If you try to use this Proxy then you'll
     * make a big mess. TODO should we remove this?
     */
    protected Proxy() {
        /*
         * Corresponds to NULL
         */
        this.pointer = 0;
    }

    /*
     * This is a placeholder to remind us of the cleanup actions that will be
     * necessary. When we put the full memory management code in place this will
     * presumably be a function called release()
     */
    protected void finalize() {
        Plumbing.unregisterObject(this);
    }
}
