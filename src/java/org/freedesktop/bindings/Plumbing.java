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
 * Parent class of the translation layer. This exists so that generated code
 * can access the native values held in Proxy, Enum, etc (which are limited to
 * package visibility). <b>No one using developing applications which happen
 * to use bindings based on this package should ever need to see, or subclass,
 * this.</b>
 * 
 * @author Andrew Cowie
 */
public abstract class Plumbing
{

    /**
     * Every Proxy that gets created gets added to this Set so that if a call
     * down to the native layer returns an object that has already been
     * created Java side that instance is returned rather than creating a new
     * one.
     */
    static WeakHashMap knownObjects;

    static {
        // any particular reason to pick a starting array size?
        knownObjects = new WeakHashMap();
    }

    /**
     * When a Proxy is created, it must call this method so that other methods
     * that need that to return that particular Proxy (but, coming from the
     * native side, only know the pointer address) can do so by doing a
     * lookup.
     */
    static void registerObject(Proxy obj) {
        knownObjects.put(new Long(obj.pointer), obj);
    }

    /**
     * Called by the release() function of a Proxy object, this method cleans
     * up any entries of the Proxy in the Plumbings internals.
     */
    static void unregisterObject(Proxy obj) {
        knownObjects.remove(new Long(obj.pointer));
    }

    /**
     * Get the memory address which is the location of the Object or Structure
     * that a given Proxy represents. That doesn't mean anything on the Java
     * side so don't try to interpret it - it's for use by the translation
     * layer as they marshall objects through to the native layer.
     * 
     * @return opaque data to be passed to native methods only.
     */
    /*
     * We go to considerable effort to keep this method out of the visibility
     * of plublic users which is why translation layer code subclass this
     * org.freedesktop.bindings.Pluming which has package visibility of Proxy
     * and Enum.Even more, there's nothing we can do about this being
     * protected, so we choose a method name other than getPointer() to keep
     * it totally of out of view from get<COMPLETE>.
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
     * <p>
     * This will return the Proxy instance already created by a concrete
     * constructor if one was created Java side. This is sufficient if you are
     * only using this for Proxys that were created as a result of objects
     * being constructed in Java. If, on the other hand, you are calling this
     * from a native to Java code path, then you need to account for the fact
     * that it is likely that a returned pointer will not yet have a Proxy
     * here. Override this method with your own <code>instanceFor()</code>
     * implementation; call this method to find out if there is a Proxy
     * already; then if not take appropriate action to create (and in so
     * doing, register) a new Proxy object.
     * 
     * <p>
     * Note that under this architecture, denaturation should <b>not</b>
     * occur because if we created the type, then we will already and always
     * have a reference to it. Regardless if our type is a much derived
     * subclass of whatever the native library's equivalent is, any look up of
     * that pointer will be routed to our Proxy subtype.
     * 
     * <p>
     * <i><b>This must be overridden by any library using these bindings, or
     * you will only be able to get instances for objects created Java side.</b></i>.
     * 
     * @param pointer
     *            opaque memory reference as passed from the C side.
     * @return the instance corresponding to a given poiner, or null if that
     *         pointer isn't registered.
     */
    /*
     * This is a skeleton implementation with the necessary functionality of
     * looking up existing Proxys but nevertheless this is, in effect, an
     * "abstract" method; if you are using java-gnome to wrap a non GLib based
     * library, you will need to implement an instanceFor() that knows how to
     * create appropriate Proxy instances based on something that can be
     * looked up.
     */
    protected static Proxy instanceFor(long pointer) {
        return (Proxy) knownObjects.get(new Long(pointer));
    }
}
