/*
 * Plumbing.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Properties;

import org.freedesktop.bindings.Constant;
import org.freedesktop.bindings.Proxy;

/**
 * Translation layer class which adds the ability to connect signals to
 * GObjects. See {@link org.freedesktop.bindings.Plumbing Plumbing} for
 * utility methods which work with Proxy and Constant. <b>No one using
 * developing applications which happen to use bindings based on this package
 * should ever need to see, or subclass, this.</b>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public abstract class Plumbing extends org.freedesktop.bindings.Plumbing
{
    protected Plumbing() {}

    private static final IdentityHashMap typeMapping;

    private static final IdentityHashMap enumMapping;

    private static final String TYPE_MAPPING = "typeMapping.properties";

    static {
        typeMapping = new IdentityHashMap(100);
        enumMapping = new IdentityHashMap(100);

        registerType("gchararray", StringValue.class);
        registerType("gboolean", BooleanValue.class);
        registerType("gint", IntegerValue.class);
        registerType("guint", IntegerValue.class);
        registerType("gint32", IntegerValue.class);
        registerType("guint32", IntegerValue.class);

        Properties p = new Properties();

        try {
            ClassLoader cl = Plumbing.class.getClassLoader();
            InputStream is = cl.getResourceAsStream(TYPE_MAPPING);
            if (is == null) {
                throw new NullPointerException("InputStream for " + TYPE_MAPPING + " is null");
            }
            p.load(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nUnable to load GType to Java class mapping file " + TYPE_MAPPING);

            System.exit(2);
        }

        for (Iterator iter = p.keySet().iterator(); iter.hasNext();) {
            String gType = (String) iter.next();
            String javaClass = (String) p.get(gType);
            registerType(gType, javaClass);
        }
    }

    /**
     * Register a GType name as corresponding to the given Proxy subclass.
     * This form, where a class name is specified by a string, is used by the
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

        /*
         * If the thing is an Constant (aka our Enum), then we have to treat
         * it differently. We deliberately set the constructor to null, using
         * it as a marker in instanceFor(). Remember - we only call
         * instanceFor() on an enum when we know it was wrapped in a GValue -
         * ie, the getProperty() case. When handling signal callbacks, we
         * reverse translate the int directly using constantFor()
         */

        if (Constant.class.isAssignableFrom(javaClass)) {
            enumMapping.put(nativeName.intern(), javaClass);
            c = null;
        } else {
            try {
                c = javaClass.getDeclaredConstructor(new Class[] { long.class });
            } catch (NoSuchMethodException nsme) {
                throw new IllegalArgumentException("Lookup of pointer Constructor failed", nsme);
            }
        }

        typeMapping.put(nativeName.intern(), c);
    }

    /**
     * Retrieve an appropriate Java Proxy for this pointer. This will return
     * the Proxy instance already created using a real constructor if one was
     * created Java side; where no Proxy exists it looks up the underlying
     * <code>GType</code> and does its best to create it.
     * 
     * @throw UnsupportedOperationException if no class has been registered
     *        for this <code>GType</code>.
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
            final Class type;

            /*
             * Somewhat crucially, we intern the returned GType name string to
             * reduce memory pressure and to permit lookup by identity.
             */
            name = GValue.g_type_name(pointer).intern();

            /*
             * First we handle the usual case of getting the instance for a
             * Proxy subclass. That is the case when the Constructor in the
             * Map is not null.
             */

            c = (Constructor) typeMapping.get(name);

            if (c != null) {
                try {
                    obj = (Proxy) c.newInstance(new java.lang.Object[] { new Long(pointer) });
                    return obj;

                } catch (IllegalArgumentException e) {
                    // the number, type, or marshaling of arguments was wrong
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
            }

            /*
             * If the constructor _was_ null, then we might be in the special
             * case where actually the native type is an enum, and we're only
             * calling this method because we're trying to unwrap a property
             * returned as a GValue - ie, the enum is wrapped in a GValue. In
             * this case we have a Fundamental subclass called EnumValue with
             * a special constructor, which we can call by name (no need to
             * use reflection here).
             */

            type = (Class) enumMapping.get(name);

            if (type != null) {
                obj = new EnumValue(pointer, type);
                return obj;
            }

            /*
             * But failing that, the constructor being null indicates that we
             * don't have any information about this native type and how to
             * map it to Java. So,
             */
            throw new UnsupportedOperationException("No binding for " + name + " (yet)");
        }
    }

    /**
     * Connect a signal handler to a GObject.
     * 
     * @param instance
     *            the Object Proxy you want to connect the signal to
     * @param handler
     *            the object implementing the Signal subinterface we defined
     *            in one of our public classes.
     * @param name
     *            the name of the signal being connected. <i>By design, the
     *            literal name of the interface being connected is the name of
     *            the signal in capitals by our naming convention.
     *            Nevertheless, you have to explicitly name the signal in
     *            because self-delegation means we never quite know which is
     *            being hooked up. Because all the calls to connectSignal()
     *            will be in generated code, the signal name is sourced from
     *            .defs data and should always be correct!</i>
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
