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
    /*
     * WARNING: if any fields are added here, code to clone them must be added
     * to the createConstVariant() method as well.
     */

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
    protected Thing(String gType, String javaPackage, String javaClass, String javaType,
            String nativeType, String jniType) {
        this.gType = gType;
        this.bindingsPackage = javaPackage;
        this.bindingsClass = javaClass;
        this.javaType = javaType;
        this.nativeType = nativeType;
        this.jniType = jniType;
        this.cType = gType;
    }

    /**
     * All Thing subclasses require a protected visibility no-args constructor
     * so that clone() can instatiate them.
     */
    protected Thing() {}

    private static HashMap things;

    static {
        things = new HashMap(400);

        register(new FundamentalThing("none", "void", "void", "void"));
        register(new FundamentalThing("gchar*", "String", "String", "jstring"));
        register(new FundamentalThing("gint", "int", "int", "jint"));
        register(new FundamentalThing("guint", "int", "int", "jint"));
        register(new FundamentalThing("glong", "long", "long", "jlong"));
        register(new FundamentalThing("gboolean", "boolean", "boolean", "jboolean"));
        register(new FundamentalThing("gfloat", "float", "float", "jfloat"));
        register(new OutParameterFundamentalThing("gint*", "int[]", "int[]", "jintArray"));
        register(new OutParameterFundamentalThing("gfloat*", "float[]", "float[]", "jfloatArray"));

        register(new ObjectThing("Signal", "org.gnome.glib", "", "Signal"));
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
        Thing stored, dupe;
        String bareGType;

        /*
         * Lookup the type. This will work most of the time...
         */

        stored = (Thing) things.get(gType);

        /*
         * ...but not if it needs a const Thing variant, and one hasn't been
         * created yet. So strip off the "const-" prefix, and look up the
         * type. If we're still stuck, then that is indeed fatal, otherwise
         * create a new Thing to represent the const case.
         */

        if (stored == null) {
            if (gType.startsWith("const-")) {
                bareGType = gType.substring(6);
                stored = (Thing) things.get(bareGType);

                if (stored == null) {
                    throw new IllegalStateException("\nYou've asked for the Thing corresponding to \""
                            + gType + "\"\n, but nothing is registered for \"" + bareGType
                            + "\" so we're stuck.");
                }

                dupe = stored.createConstVariant();

                register(dupe);
                return dupe;
            } else {
                throw new IllegalStateException("\nYou've asked for the Thing corresponding to \""
                        + gType + "\", but it isn't registered.");
            }
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

    /**
     * Get the fully qualified name of the public Java type, ie, for
     * GtkButton, return "org.gnome.gtk.Button". This is used to create import
     * statements for types that are used.
     */
    public String fullyQualifiedJavaClassName() {
        StringBuffer buf;

        buf = new StringBuffer();
        buf.append(bindingsPackage);
        buf.append(".");
        buf.append(javaType);

        return buf.toString().intern();
    }

    /**
     * This is where we deal with the "const-" modifier by creating a clone of
     * the object and then changing the cType field. We use reflection to
     * create the appropriate fully derived instance.
     */
    /*
     * This is effectively clone(). WARNING this doesn't deal with fields in
     * subclasses. At the moment there aren't any, and we don't expect any. We
     * could use reflection over the fields if we really had to. Yuk. In the
     * mean time, this works.
     */
    private Thing createConstVariant() {
        Thing t;

        try {
            t = (Thing) this.getClass().newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException("No nullary constructor available in " + this.getClass() + "?",
                    ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException("Constructor " + this.getClass() + "() private?", iae);
        }

        t.bindingsClass = this.bindingsClass;
        t.bindingsPackage = this.bindingsPackage;
        t.gType = ("const-" + this.gType).intern();
        t.cType = ("const " + this.cType).intern();
        t.javaType = this.javaType;
        t.jniType = this.jniType;
        t.nativeType = this.nativeType;

        return t;
    }
}
