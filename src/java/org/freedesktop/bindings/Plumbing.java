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
package org.freedesktop.bindings;

import java.util.WeakHashMap;

/**
 * Parent class of the translation layer. This exists so that generated code can
 * access the native values held in Proxy, Enum, etc (which are limited to
 * package visibility). <b>No one using developing applications which happen to
 * use bindings based on this package should ever need to see, or subclass,
 * this.</b>
 * 
 * @author Andrew Cowie
 */
public abstract class Plumbing {

    /**
     * Every Proxy that gets created gets added to this Set so that if a call
     * down to the native layer returns an object that has already been created
     * Java side that instance is returned rather than creating a new one.
     */
    static WeakHashMap knownObjects;

    static {
        // any particular reason to pick a starting array size?
        knownObjects = new WeakHashMap();
    }

    /**
     * When a Proxy is created, it must call this method so that other methods
     * that need that to return that particular Proxy (but, coming from the
     * native side, only know the pointer address) can do so by doing a lookup.
     */
    static void registerObject(Proxy obj) {
        knownObjects.put(new Long(obj.pointer), obj);
    }

    /**
     * Called by the release() function of a Proxy object, this method cleans up
     * any entries of the Proxy in the Plumbings internals.
     */
    static void unregisterObject(Proxy obj) {
        knownObjects.remove(new Long(obj.pointer));
    }

    /**
     * Get the memory address which is the location of the Object or Structure
     * that a given Proxy represents. That doesn't mean anything on the Java
     * side so don't try to interpret it - it's for use by the translation layer
     * as they marshall objects through to the native layer.
     * 
     * @return opaque data to be passed to native methods only.
     */
    /*
     * We go to considerable effort to keep this method out of the visibility of
     * plublic users which is why translation layer code subclass this
     * org.freedesktop.bindings.Pluming which has package visibility of Proxy
     * and Enum.Even more, there's nothing we can do about this being protected,
     * so we choose a method name other than getPointer() to keep it totally of
     * out of view from get<COMPLETE>.
     */
    protected static final long pointerOf(Proxy reference) {
        return reference.pointer;
    }

    protected static final int numOf(Enum reference) {
        return reference.num;
    }

    /**
     * Given a pointer, find out if we already have a Proxy for it Java side.
     * 
     * @param pointer
     *            opaque memory reference as passed from the C side.
     * @return the instance corresponding to a given poiner, or null if that
     *         pointer isn't registered.
     */
    /*
     * It's up to the generated class using Plumbing to react if there isn't
     * already a Proxy instance for this pointer. It's easier to use the actual
     * Java constructor there with the known (generated hard coded) name than
     * mucking around with Class.forName().newInstance() here trying to figure
     * out the classname here.
     */
    protected static Proxy proxyFor(long pointer) {
        return (Proxy) knownObjects.get(new Long(pointer));
    }
}
