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
     * Whether or not this Thing is actually a fake or is otherwise a
     * prohibited type.
     */
    boolean blacklisted;

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
        this.blacklisted = false;
    }

    /**
     * All Thing subclasses require a protected visibility no-args constructor
     * so that clone() can instatiate them.
     */
    protected Thing() {}

    public boolean isBlacklisted() {
        return blacklisted;
    }

    private static HashMap things;

    static {
        things = new HashMap(400);

        register(new FundamentalThing("none", "void", "void", "void"));
        register(new FundamentalThing("gchar", "char", "char", "jchar"));
        register(new FundamentalThing("guchar", "char", "char", "jchar"));
        register(new FundamentalThing("gunichar", "char", "char", "jchar"));
        register(new FundamentalThing("gchar*", "String", "String", "jstring"));

        /*
         * A certain class of bugs arise from discarding the most significant
         * bit in the unsigned integers but in practice it never seems to be a
         * problem. Nevertheless, this is where to fix it if a problem crops
         * up.
         */

        register(new FundamentalThing("gint", "int", "int", "jint"));
        register(new FundamentalThing("guint", "int", "int", "jint"));
        register(new FundamentalThing("gint8", "byte", "byte", "jbyte"));
        register(new FundamentalThing("guint8", "byte", "byte", "jbyte"));
        register(new FundamentalThing("gint16", "short", "short", "jshort"));
        register(new FundamentalThing("guint16", "char", "char", "jchar"));
        register(new FundamentalThing("gint32", "int", "int", "jint"));
        register(new FundamentalThing("guint32", "int", "int", "jint"));
        register(new FundamentalThing("gint64", "long", "long", "jlong"));
        register(new FundamentalThing("glong", "long", "long", "jlong"));
        register(new FundamentalThing("gulong", "long", "long", "jlong"));
        register(new FundamentalThing("gboolean", "boolean", "boolean", "jboolean"));
        register(new FundamentalThing("gfloat", "float", "float", "jfloat"));
        register(new FundamentalThing("gdouble", "double", "double", "jdouble"));

        /*
         * Out parameters for fundamental types are special cases, probably,
         * so we will continue to register their information here for now.
         */
        register(new OutParameterFundamentalThing("gint*", "int[]", "int[]", "jintArray"));
        register(new OutParameterFundamentalThing("guint*", "int[]", "int[]", "jintArray"));
        register(new OutParameterFundamentalThing("gfloat*", "float[]", "float[]", "jfloatArray"));
        register(new OutParameterFundamentalThing("gdouble*", "double[]", "double[]", "jdoubleArray"));
        register(new OutParameterFundamentalThing("gboolean*", "boolean[]", "boolean[]", "jbooleanArray"));
        // and so on

        /*
         * The kludge of repeating certain basic types is here because Owen
         * couldn't be bothered to follow the conventions that are used
         * everywhere else in the GNOME stack when he worked on Pango. See
         * http://bugzilla.gnome.org/show_bug.cgi?id=442823
         */
        register(new FundamentalThing("int", "int", "int", "jint"));
        register(new FundamentalThing("double", "double", "double", "jdouble"));
        register(new FundamentalThing("char*", "String", "String", "jstring"));
        register(new OutParameterFundamentalThing("int*", "int[]", "int[]", "jintArray"));

        // is this actually correct?
        register(new FundamentalThing("time_t", "long", "long", "jlong"));

        /*
         * Types with are either already defined by the java-gnome
         * architecture and for which (by design) there is no .defs data, or
         * those for which we have no need at all (ie GType).
         */

        register(new ObjectThing("GObject*", "org.gnome.glib", "GObject", "Object"));
        register(new ValueThing());
        register(new BlacklistedThing("GClosure*"));
        register(new BlacklistedThing("GType"));

        /*
         * Types we register purely for the cosmetic purposes of including in
         * the sorted includes declarations
         */
        register(new ObjectThing("Signal", "org.gnome.glib", "", "Signal"));
        register(new ObjectThing("Proxy", "org.freedesktop.bindings", "", "Proxy"));
        register(new ObjectThing("Blacklist", "org.freedesktop.bindings", "", "BlacklistedMethodError"));
        register(new ObjectThing("FIXME", "org.freedesktop.bindings", "", "FIXME"));

        /*
         * FIXME! Weirdo cases we haven't figured out what to do with yet.
         * Should these be a new (define...) type intead of here?
         */
        register(new TypedefFundamentalThing("GQuark", "int", "int", "jint"));
        register(new TypedefFundamentalThing("GdkNativeWindow", "long", "long", "jlong"));
        register(new TypedefFundamentalThing("GdkWChar*", "String", "String", "jstring"));
        register(new FixmeThing("gpointer"));
        register(new FixmeThing("gpointer*"));
        register(new FixmeThing("GdkAtom"));
        register(new FixmeThing("GdkAtom*"));

        /*
         * These seem to be motif-isms.
         */
        register(new BlacklistedThing("GdkWMDecoration*"));
        register(new BlacklistedThing("GdkWMFunction"));

        /*
         * From gdk_draw_rgb_image(): "The pixel data, represented as packed
         * 24-bit data." No idea how we could possibly handle that properly.
         */
        register(new BlacklistedThing("guchar*"));
        /*
         * From gdk_property_get(), which is marked "don't use"
         */
        register(new BlacklistedThing("guchar**"));

        /*
         * GList/GSList and related typedefs... FIXME change this when we
         * properly map the list and array returns.
         */
        register(new FixmeThing("GList*"));
        register(new FixmeThing("GSList*"));
        register(new FixmeThing("AtkAttributeSet*"));

        /*
         * FUTURE no Cairo bindings, yet
         */
        register(new BlacklistedThing("cairo_t*"));
        register(new BlacklistedThing("cairo_surface_t*"));
        register(new BlacklistedThing("cairo_font_options_t*"));

        /*
         * And what on earth are...
         */
        register(new BlacklistedThing("GtkFileSystemVolume*"));
        register(new BlacklistedThing("GtkFilePath**"));
        register(new BlacklistedThing("GtkFileSystemGetInfoCallback"));
        register(new BlacklistedThing("GtkFileSystemGetFolderCallback"));

        /*
         * Dealing with out-parameters is now handled by lookup() and
         * createOutVariant() in this class, so the manual declarations we had
         * been accumulating are no longer included. As we were pushing
         * through the full suite of .defs data, however, numerous comments
         * were made which are preserved here:
         * 
         * FIXME Out-parameter Proxies? Oh, joy
         * 
         * FIXME Out-parameter enums and flags? Grr
         * 
         * FIXME An array of outparameters? Make it stop!
         * 
         * Clearly, there is further work to be done in this regard. Finally,
         * 
         * FIXME String[] is a common return type, so out-parameter is
         * probably not the way to deal with it. But on the other hand, arrays
         * _are_ how we deal with out-parameters, so perhaps this will turn
         * out to be close to what we want in the end after all.
         */
        register(new OutParameterFundamentalThing("gchar**", "String[]", "String[]", "jobjectArray"));
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
        Thing stored, dupe, black;
        String bareGType;

        /*
         * Lookup the type. This will work most of the time...
         */

        stored = (Thing) things.get(gType);

        if (stored != null) {
            return stored;
        }

        /*
         * ...but not if it needs a const Thing variant, and one hasn't been
         * created yet. So strip off the "const-" prefix, and look up the
         * type.
         */

        if (gType.startsWith("const-")) {
            bareGType = gType.substring(6);
            stored = (Thing) things.get(bareGType);

            if (stored != null) {
                dupe = stored.createConstVariant();

                register(dupe);
                return dupe;
            }
        } else if (gType.endsWith("*")) {
            bareGType = gType.substring(0, gType.length() - 1);
            stored = (Thing) things.get(bareGType);

            if (stored != null) {
                dupe = stored.createOutVariant();

                register(dupe);
                return dupe;
            }
        }

        /*
         * If we're still stuck, then that is would be fatal, except that we
         * create a dummy type as a placeholder so we can proceed with the
         * bindings generation without total closure.
         */
        System.out.println("Warning: no type data for " + gType + ", blacklisting");
        black = new BlacklistedThing(gType);
        register(black);
        return black;
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
        t.blacklisted = this.blacklisted;

        return t;
    }

    /**
     * FIXME Figuring out what to do with out-parameters is still a work in
     * progress; for now these are marked as blacklisted. The code above in
     * createConstVariant() will probably be useful.
     */
    private Thing createOutVariant() {
        return new FixmeThing(this.gType + "*");
    }

    /**
     * The name of the class that will contain the generated translation layer
     * code. For example, GdkWindow return "org.gnome.gdk.GdkWindow". This is
     * used by the BindingsGenerator to figure out where to create the output
     * code.
     */
    public String fullyQualifiedTranslationClassName() {
        StringBuffer buf;

        buf = new StringBuffer();
        buf.append(bindingsPackage);
        buf.append(".");
        buf.append(bindingsClass);

        return buf.toString().intern();
    }

    public String toString() {
        return gType;
    }
}
