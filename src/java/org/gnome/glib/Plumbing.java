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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.IdentityHashMap;

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
    protected Plumbing() {}

    private static IdentityHashMap knownClasses;

    static {
        knownClasses = new IdentityHashMap(100);

        registerType("gchararray", StringValue.class);
        registerType("gboolean", BooleanValue.class);
        registerType("gint", IntegerValue.class);
        registerType("guint", IntegerValue.class);
        registerType("gint32", IntegerValue.class);
        registerType("guint32", IntegerValue.class);
    }

    /**
     * Register a GType name as corresponding to the given Proxy subclass.
     * This form, where a classname is specified by a string, is used by the
     * generated translation layer classes to get around the case where a hand
     * written wrapper class presenting a public API has not been (or will not
     * be) written.
     */
    protected static void registerType(String nativeName, String javaClassName) {
        final Class javaClass;

        assert (javaClassName != null) : "Java class being registered cannot be null";

        try {
            javaClass = Class.forName(javaClassName);
        } catch (ClassNotFoundException e) {
            /*
             * By design we ignore this and return. There is a logic hole
             * here, though - if someone mistypes a class that does exist,
             * then it won't be registered. The bug will be exposed by an
             * UnsupportedOperationException being thrown by instanceFor()
             * when it can't look up what it rightly should be able to.
             */
            return;
        }

        registerType(nativeName, javaClass);
    }

    /**
     * Register a GType name as corresponding to the given Proxy subclass. Use
     * this form when you're hand writing a translation layer class - after
     * all, you should also have implemented the public class that goes along
     * with ti!
     */
    protected static void registerType(String nativeName, Class javaClass) {
        final Constructor c;

        assert ((nativeName != null) && (!nativeName.equals(""))) : "GType name being registered cannot be null or empty";
        assert (javaClass != null) : "Java class being registered cannot be null";

        try {
            c = javaClass.getDeclaredConstructor(new Class[] { long.class });
        } catch (NoSuchMethodException nsme) {
            throw new IllegalArgumentException("Lookup of pointer Constructor failed", nsme);
        }
        knownClasses.put(nativeName, c);
    }

    private static Constructor lookupType(String nativeName) {
        return (Constructor) knownClasses.get(nativeName);
    }

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

        if (obj != null) {
            /*
             * A Proxy exists for this. Great! Simply return it.
             */
            return obj;
        } else {
            /*
             * Oh. A proxy doesn't exist (yet). Okay. Lookup the GType name,
             * and see if we've got it registered. If we do, then we can go
             * ahead and try to instantiate a Proxy. If not, blow an
             * exception.
             */
            final String name;
            final Constructor c;

            /*
             * Somewhat crucually, we intern the returned GType name string to
             * reduce memory pressure and to permit lookup by identity.
             */
            name = GValue.g_type_name(pointer).intern();

            if ((c = lookupType(name)) != null) {
                try {
                    obj = (Proxy) c.newInstance(new java.lang.Object[] { new Long(pointer) });
                    return obj;

                } catch (IllegalArgumentException e) {
                    // the number, type, or marshalling of arguments was wrong
                    throw new IllegalStateException(e);
                } catch (InstantiationException e) {
                    // the class was abstract.
                    throw new IllegalStateException(e);
                } catch (IllegalAccessException e) {
                    // constructor inaccessible
                    throw new IllegalStateException(e);
                } catch (InvocationTargetException e) {
                    // constructor threw an exception
                    throw new IllegalStateException(e);
                }
            } else {
                throw new UnsupportedOperationException("No mapping for " + name);
            }

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
}
