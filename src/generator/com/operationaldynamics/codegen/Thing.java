/*
 * Thing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.util.HashMap;

/**
 * Things are our wrapper around types, with information about the type at all
 * levels. These are to be created as encountered by the parser, and
 * registered.
 * 
 * <p>
 * Things corresponding to primative types (int, String, etc) for which there
 * are of course no (define- ...) blocks are registered internally by this
 * class.
 * 
 * @author Andrew Cowie
 */
public abstract class Thing
{
    String gType;

    String bindingsPackage;

    String bindingsClass;

    String javaType;

    String nativeType;

    String jniType;

    /**
     * This is gType, of course, except for things like "const-gchar*", which
     * need to be decoded to "const gchar*". We store the clean version here
     * to avoid messier code later.
     */
    String cType;

    /**
     * All Things, with the excpetion of the FundamentalThings representing
     * primative types, need to know where they slot in.
     */
    Thing(String gType, String javaPackage, String javaClass, String javaType, String nativeType,
            String jniType) {
        this.gType = gType;
        this.bindingsPackage = javaPackage;
        this.bindingsClass = javaClass;
        this.javaType = javaType;
        this.nativeType = nativeType;
        this.jniType = jniType;
        this.cType = gType;
    }

    private static HashMap things;

    static {
        things = new HashMap(400);

        register(new FundamentalThing("none", "void", "void", "void"));
        register(new FundamentalThing("const-gchar*", "String", "String", "jstring"));
        register(new FundamentalThing("gchar*", "String", "String", "jstring"));
        register(new FundamentalThing("glong", "long", "long", "jlong"));
        register(new FundamentalThing("gboolean", "boolean", "boolean", "jboolean"));
        register(new FundamentalThing("gfloat", "float", "float", "jfloat"));

    }

    public static void register(Thing t) {
        if (t.gType == null) {
            throw new IllegalStateException();
        }

        things.put(t.gType, t);
    }

    /**
     * Lookup the Thing object registered as corresponding to
     * <code>gType</code>
     */
    public static Thing lookup(String gType) {
        Thing stored;

        stored = (Thing) things.get(gType);
        if (stored == null) {
            throw new IllegalStateException("\nYou've asked for the Thing corresponding to \"" + gType
                    + "\" but it isn't registered.");
        }

        return stored;
    }

    /**
     * The translation code necessary to convert this type from Java to the
     * native JNI crossing type.
     * 
     * @param name
     *            the variable name being converted
     * @return the code for the conversion. Usually a no-op, ie, "name" is
     *         returned, but for Proxies and Constants, "pointerOf(name)" will
     *         be the result.
     */
    abstract String translationToNative(String name);

    /**
     * The translation code necessary to convert this type from native JNI
     * crossing type back to Java.
     * 
     * @param name
     *            the variable name being converted
     * @return the code for the conversion. For Proxies "objectFor(name)" will
     *         be the result.
     */
    abstract String translationToJava(String name);
}
