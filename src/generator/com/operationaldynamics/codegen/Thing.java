/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2013 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 */
package com.operationaldynamics.codegen;

import java.util.HashMap;
import java.util.List;

import com.operationaldynamics.driver.DefsFile;

/**
 * Things are our wrapper around types, with information about the type at all
 * levels. These are to be created as encountered by the parser, and
 * registered.
 * <p>
 * Things corresponding to primative types (int, String, etc) for which there
 * are of course no (define- ...) blocks are registered internally by this
 * class.
 * <p>
 * Things collaborate with Generators in the generation of code, and are
 * responsible for generation of type-dependent code.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
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
     * Additional C header files, as necessary.
     */
    /*
     * TODO not super happy with this being here (because it is fed by a
     * custom variation we have made to the .defs files; hopefully there is
     * another way we can derive this information without needing to do that).
     */
    List<String> importHeaders;

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

    private static HashMap<String, Thing> things;

    static {
        things = new HashMap<String, Thing>(500);

        register(new FundamentalThing("none", "void", "void", "void"));
        register(new FundamentalThing("gchar", "char", "char", "jchar"));
        register(new FundamentalThing("guchar", "char", "char", "jchar"));
        register(new FundamentalThing("gunichar", "int", "int", "jint"));
        register(new StringThing("gchar*"));

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
        register(new FundamentalThing("gssize", "int", "int", "jint"));
        register(new FundamentalThing("gsize", "long", "long", "jlong"));
        register(new FundamentalThing("gint64", "long", "long", "jlong"));
        register(new FundamentalThing("guint64", "long", "long", "jlong"));
        register(new FundamentalThing("glong", "long", "long", "jlong"));
        register(new FundamentalThing("gulong", "long", "long", "jlong"));
        register(new FundamentalThing("gboolean", "boolean", "boolean", "jboolean"));
        register(new FundamentalThing("cairo_bool_t", "boolean", "boolean", "jboolean"));
        register(new FundamentalThing("gfloat", "float", "float", "jfloat"));
        register(new FundamentalThing("gdouble", "double", "double", "jdouble"));
        register(new FundamentalThing("goffset", "long", "long", "jlong"));

        /*
         * Types for array parameters
         */
        register(new FundamentalArrayThing("gfloat[]", "gfloat"));
        register(new FundamentalArrayThing("gint8[]", "gint8"));

        /*
         * and for string arrays
         */
        register(new StringArrayThing("gchar**"));
        register(new StringArrayThing("char**"));

        /* these seem a bit harder */
        register(new FixmeThing("const-gchar*[]"));
        register(new FixmeThing("gchar**[]"));
        register(new FixmeThing("gint**"));
        register(new FixmeThing("guint**"));

        /*
         * Out parameters for fundamental types are special cases, probably,
         * so we will continue to register their information here for now.
         */
        register(new FundamentalArrayThing("gint*", "gint"));
        register(new FundamentalArrayThing("guint*", "guint"));
        register(new FundamentalArrayThing("gfloat*", "gfloat"));
        register(new FundamentalArrayThing("gdouble*", "gdouble"));
        register(new FundamentalArrayThing("gboolean*", "gboolean"));
        register(new FundamentalArrayThing("gsize*", "gsize"));
        // and so on

        /*
         * The kludge of repeating certain basic types is here because Owen
         * couldn't be bothered to follow the conventions that are used
         * everywhere else in the GNOME stack when he worked on Pango. See
         * http://bugzilla.gnome.org/show_bug.cgi?id=442823
         */
        register(new FundamentalThing("int", "int", "int", "jint"));
        register(new FundamentalThing("double", "double", "double", "jdouble"));
        register(new FundamentalArrayThing("double*", "double"));
        register(new StringThing("char*"));
        register(new FundamentalArrayThing("int*", "int"));

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
         * Types needed for GError management
         */
        register(new GErrorThing());
        register(new ObjectThing("GlibException", "org.gnome.glib", "", "GlibException"));

        /*
         * FIXME! Weirdo cases we haven't figured out what to do with yet.
         * Should these be a new (define...) type intead of here?
         */
        register(new TypedefFundamentalThing("GQuark", "int", "int", "jint"));
        register(new TypedefFundamentalThing("GdkNativeWindow", "long", "long", "jlong"));
        register(new FixmeThing("gpointer"));
        register(new FixmeThing("gpointer*"));
        register(new FixmeThing("GdkAtom"));
        register(new FixmeThing("GdkAtom*"));

        register(new TypedefEnumThing("GdkKeyval", "guint", "org.gnome.gdk", "GdkKeyval", "Keyval"));
        register(new TypedefEnumThing("GdkMouseButton", "guint", "org.gnome.gdk", "GdkMouseButton",
                "MouseButton"));

        /*
         * Typedefs of fundamental type
         */
        register(new TypedefFundamentalThing("PangoGlyphUnit", "int", "int", "jint"));

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

        register(new FixmeThing("AtkAttributeSet*"));

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
         * were made which are preserved here: FIXME Out-parameter Proxies?
         * Oh, joy FIXME Out-parameter enums and flags? Grr FIXME An array of
         * outparameters? Make it stop! Clearly, there is further work to be
         * done in this regard. Finally, FIXME String[] is a common return
         * type, so out-parameter is probably not the way to deal with it. But
         * on the other hand, arrays _are_ how we deal with out-parameters, so
         * perhaps this will turn out to be close to what we want in the end
         * after all.
         */
        // register(new OutParameterFundamentalThing("gchar**", "String[]",
        // "String[]", "jobjectArray"));
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

        stored = things.get(gType);

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
            stored = things.get(bareGType);

            if (stored != null) {
                dupe = stored.createConstVariant();

                register(dupe);
                return dupe;
            }
        } else if (gType.startsWith("GList-") || gType.startsWith("GSList-")) {
            bareGType = gType.split("-")[1];
            stored = things.get(bareGType);

            if (stored != null) {
                dupe = stored.createListVariant(gType);

                register(dupe);
                return dupe;
            }
        } else if (gType.endsWith("*")) {
            bareGType = gType.substring(0, gType.length() - 1);
            stored = things.get(bareGType);

            /*
             * we don't support arrays of arrays yet. the !(stored instanceof
             * ArrayThing) is needed to prevent multiple recursion on the
             * pointer star.
             */
            if ((stored != null) && !(stored instanceof ArrayThing)) {
                dupe = stored.createArrayVariant();

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
     * Returns the Java type of this Thing. In most cases this is just the
     * bare type name. However, when there is an import conflict, the fully
     * qualified java class name is retuned instead.
     * <p>
     * Note that <code>javaType</code> is still exposed - use it directly if
     * you need it.
     */
    String javaTypeInContext(DefsFile context) {
        if (context.doesTypeConflict(this)) {
            return fullyQualifiedJavaClassName();
        } else {
            return javaType;
        }
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
     * Check if this type needs an extra translation other that the simple
     * on-the-fly translationToNative(). If this is <code>true</code>, the
     * correspondent generator will call extraTranslationToNative() and
     * extraTranslationToJava() when needed.
     */
    boolean needExtraTranslation() {
        return false;
    }

    /**
     * When the translation to native needs some lines of code, or just it
     * can't be done on-the-fly inside an argument list, this is called before
     * the native method.
     * 
     * <p>
     * For most Things, the extra translation is not needed. Only composes
     * types such as some arrays / out parameters need this.
     */
    String extraTranslationToNative(String name) {
        return null;
    }

    /**
     * Like {@link #translationToJava(String, DefsFile) translationToJava()},
     * but intented for "clean-up" of parameters.
     * 
     * TODO change name!!!
     */
    String extraTranslationToJava(String name, DefsFile data) {
        return null;
    }

    /**
     * Check if the type need to the guard against null values when null-ok is
     * not present. For example, a fundamental "int" never needs a guard.
     */
    boolean needGuardAgainstNull() {
        return true;
    }

    /**
     * Check whether the jni conversion function can safety deal with a NULL
     * input value, or is better to do a if like:
     * 
     * <pre>
     * if (_x == NULL) {
     *     x = NULL;
     * } else {
     *     ....
     * }
     * </pre>
     * 
     * to prevent the conversion function to be called with null values. Note
     * that even if the funcion handles nulls well, using this is a good idea
     * as it manages null-ok in a better way.
     */
    boolean jniConversionHandlesNull() {
        return true;
    }

    /**
     * The translation code necessary to convert this type from native JNI
     * crossing type back to Java.
     * 
     * @param name
     *            the variable name being converted
     * @param data
     *            Information about the file is thing is to be translated
     * @return the code for the conversion. For Proxies "objectFor(name)" will
     *         be the result.
     */
    abstract String translationToJava(String name, DefsFile data);

    String jniConversionDecode(String name) {
        return "_" + name;
    }

    /**
     * Chek if the JNI conversion done by code generated by
     * {@link #jniConversionDecode(String) jniConversionDecode()} can fail. If
     * so, usually this requires some check code to be generated.
     */
    boolean jniConversionCanFail() {
        return false;
    }

    /**
     * @return null signiying no cleanup code is necessary
     */
    String jniConversionCleanup(String name) {
        return null;
    }

    String jniReturnEncode(String name) {
        return name;
    }

    /**
     * 
     * @param name
     * @param callerOwnsReturn
     *            Whether the caller owns the return value. It is either 'f'
     *            (caller doesn't own return), 't' (caller own return) or 'l'
     *            (return type is a list/array... and only the list itself is
     *            own, not its contents)
     * @return
     */
    String jniReturnCleanup(String name, char callerOwnsReturn) {
        return null;
    }

    /**
     * Little utility function so that when aborting out of a C function
     * (because an Exception has been thrown) the correct "emtpy" value is
     * used. Stick this after a "return" statement.
     */
    abstract String jniReturnErrorValue();

    /**
     * Get the type that a Java class needs to import. In most cases it will
     * be <code>this</code>, but arrays, for example, are managed in a
     * different way.
     * 
     * @return The type to import or <code>null</code> meaning no import
     *         needed.
     */
    public Thing getTypeToImport() {
        return this;
    }

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
     * Get the name of the type for Class name used for the translation layer
     * of the bindings, ie, for Thing "GtkButton*" which is exposed publicly
     * as "org.gnome.gtk.Button", return "GtkButton". We use this to generate
     * typeMapping.properties which needs to match the result of
     * <code>g_type_name()</code>.
     */

    public String bareTranslationClassName() {
        return bindingsClass;
    }

    /**
     * Get the java type this Thing represents
     */
    public String bareJavaClassName() {
        return javaType;
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
            t = this.getClass().newInstance();
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

        /*
         * Added to support cloning of ArrayThings, that have a field, but
         * looks very ugly!
         */
        if (this instanceof ArrayThing) {
            ((ArrayThing) t).type = ((ArrayThing) this).type;
        }

        return t;
    }

    /**
     * Array variants are the way we manage both arrays and output parameters.
     */
    /*
     * TODO for efficience reasons, in a near future we will want to deal with
     * output params/arrays and input arrays in a different way.
     */
    private Thing createArrayVariant() {
        if (this instanceof ProxiedThing) {
            return new ProxiedArrayThing(gType + "*", this);
        } else if (this instanceof EnumThing) {
            return new ConstantArrayThing(gType + "*", this);
        } else {
            /*
             * only Proxied/Constant arrays are supported yet. Note that
             * fundamental arrays are managed in Thing static block.
             */
            System.out.println("Warning: Unsupported " + gType);
            return new BlacklistedThing(this.gType + "*");
        }
    }

    /**
     * This is where we deal with both "GList" and "GSList" variants.
     */
    private Thing createListVariant(String gType) {
        if (this instanceof ProxiedThing) {
            return new GListThing(gType, this);
        } else {
            System.out.println("Warning: Unsupported " + gType);
            return new BlacklistedThing(gType);
        }
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

    /**
     * Setter for importHeader.
     */
    /*
     * Other Thing members are set in the constructor, but this is set in its
     * own method because only a very few object really need it, so we can use
     * the setter only in the needed Block.
     */
    public void setImportHeader(List<String> importHeader) {
        this.importHeaders = importHeader;
    }

    public String toString() {
        return gType;
    }
}
