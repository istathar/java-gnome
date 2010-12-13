/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007-2008 Vreixo Formoso
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

import java.io.PrintWriter;

import com.operationaldynamics.driver.DefsFile;

/**
 * Generate Java and C code for constructors, methods and other kind of
 * functions.
 * <p>
 * Subclasses can override the individual steps of the generation sequence if
 * they wish, however adjusting input passing up to this classes's constructor
 * has been thus far been enough for constructors blocks and methods blocks.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class FunctionGenerator extends Generator
{
    /**
     * The Thing describing the object we are generating code relative to.
     */
    protected final Thing proxyType;

    /**
     * The name of the method that is exposed package visible to bindings
     * hackers. Expected to be overridden by subclasses such as
     * ConstuctorGenerator.
     */
    protected String translationMethodName;

    /**
     * Not final so that we can play with the return type variable in the JNI
     * conversion code section.
     */
    protected Thing returnType;

    protected final String nativeMethodName;

    /**
     * These are ordered collections so that sublcasses like MethodGenerator
     * can insert the reference-to-self as a first argument.
     */
    protected final Thing[] parameterTypes;

    protected final String[] parameterNames;

    /**
     * This is filled with true if the parameter can be null (indicated with a
     * null-ok in .defs).
     */
    protected final boolean[] parameterNullOk;

    /**
     * If a blacklistedType type is detected in this block, set it here.
     */
    private Thing blacklistedType;

    private final boolean addSentinal;

    /**
     * Whether the caller owns the return value. It is either 'f' (caller
     * doesn't own return), 't' (caller own return) or 'l' (return type is a
     * list/array... and only the list itself is own, not its contents)
     */
    private final char callerOwnsReturn;

    /**
     * @param data
     *            the information about the class to which the block we are
     *            generating code for pertains.
     * @param blockName
     *            however the .defs data named this block. Usually it's a
     *            "short" name such as "set_label". Will be transformed into
     *            the translation method name.
     * @param gReturnType
     *            the return type, as specified in the .defs data
     * @param cFunctionName
     *            the C function name, as specified in the .defs data. This
     *            will be used to name the native method.
     * @param gParameters
     *            an array of String[2] arrays, listing the type and name of
     *            each parameter.
     * @param callerOwnsReturn
     *            whether the caller owns the returned value.
     */
    public FunctionGenerator(final DefsFile data, final String blockName, final String gReturnType,
            final String cFunctionName, final String[][] gParameters, char callerOwnsReturn) {
        super(data);
        final int len;

        this.proxyType = data.getType();

        this.translationMethodName = toCamel(blockName);

        this.returnType = Thing.lookup(gReturnType);

        this.nativeMethodName = cFunctionName;

        this.callerOwnsReturn = callerOwnsReturn;

        /*
         * If ... is passed through as the last parameter, it means that we
         * have a varargs and will need to a) add a NULL sentinel to the arg
         * list, and b) chop this pseudo-parameter off the end of the array.
         * This is a bit ugly, but the alternative was adding a boolean to
         * every Generator constructor. This is better: varargs is a parameter
         * type, and one we may someday choose to deal with.
         */

        if ((gParameters.length > 0) && ("...".equals(gParameters[gParameters.length - 1][0]))) {
            this.addSentinal = true;
            len = gParameters.length - 1;
        } else {
            this.addSentinal = false;
            len = gParameters.length;
        }

        parameterTypes = new Thing[len];
        parameterNames = new String[len];
        parameterNullOk = new boolean[len];

        for (int i = 0; i < len; i++) {
            parameterTypes[i] = Thing.lookup(gParameters[i][0]);
            parameterNames[i] = toCamel(gParameters[i][1]);
            parameterNullOk[i] = "yes".equals(gParameters[i][2]);
        }

        blacklistedType = null;
    }

    protected void translationMethodDeclaration(PrintWriter out) {
        boolean hasGError = false;

        out.print("\n");

        if (blockContainsBlacklistedThings()) {
            out.print("    ");
            out.print("@SuppressWarnings(\"unused\")\n");
        }

        out.print("    ");
        out.print("static final ");
        out.print(returnType.javaTypeInContext(data));
        out.print(" ");
        out.print(translationMethodName);

        out.print("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                hasGError = true;
                continue;
            }

            if (i > 0) {
                out.print(", ");
            }

            out.print(parameterTypes[i].javaTypeInContext(data));
            out.print(" ");
            out.print(parameterNames[i]);
        }

        out.print(")");

        if (hasGError && !blockContainsBlacklistedThings()) {
            out.print(" throws GlibException");
        }

    }

    protected void translationMethodThrowBlacklisted(PrintWriter out) {
        out.print(" throws BlacklistedMethodError {\n");

        out.print("        ");
        out.print("throw new BlacklistedMethodError(\"");
        out.print(blacklistedType.gType);
        out.print("\");\n");

        out.print("    ");
        out.print("}\n");
    }

    protected void translationMethodConversionCode(PrintWriter out) {
        int declarations = 0;

        out.print(" {\n");

        /*
         * Declare translation variables as necessary
         */

        if (!returnType.javaType.equals("void")) {
            out.print("        ");
            out.print(returnType.nativeType);
            out.print(" result;\n");
            declarations++;
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            if (parameterTypes[i].needExtraTranslation()) {
                out.print("        ");
                out.print(parameterTypes[i].nativeType);
                out.print(" _" + parameterNames[i] + ";\n");
                declarations++;
            }
        }

        if (declarations > 0) {
            out.print("\n");
        }

        /*
         * Guard against null in parameters that can't be null
         */
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            if (!parameterNullOk[i] && !(parameterTypes[i] instanceof FundamentalThing)) {
                out.print("        if (" + parameterNames[i] + " == null) {\n");
                out.print("            throw new IllegalArgumentException(\"" + parameterNames[i]
                        + " can't be null\");\n");
                out.print("        }\n\n");
            }
        }

        /*
         * convert (translate) variables from public Java to JNI boundary
         * crossing (ie, out-parameters)
         */

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            if (parameterTypes[i].needExtraTranslation()) {
                out.print("        ");
                out.print("_" + parameterNames[i] + " = ");
                out.print(parameterTypes[i].extraTranslationToNative(parameterNames[i]));
                out.print(";\n\n");
            }
        }

        /*
         * And now enter the GDK lock prior to making the native calls. FUTURE
         * this might have to be conditional if we ever have an environment
         * where there is strictly zero possibility of a library depending on
         * GTK. At the moment we're not allowing that as a strict KISS
         * measure, and besides, most of the GNOME libraries need to be thread
         * safe via the global GDK lock regardless.
         */

        out.print("        ");
        out.print("synchronized (lock) {\n");
    }

    protected void translationMethodNativeCall(PrintWriter out) {
        out.print("            ");
        if (!returnType.javaType.equals("void")) {
            out.print("result = ");
        }
        out.print(nativeMethodName);
        out.print("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            if (i > 0) {
                out.print(", ");
            }

            out.print(parameterTypes[i].translationToNative(parameterNames[i]));
        }

        out.print(");\n");
    }

    protected void translationMethodParamConversion(PrintWriter out) {
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            if (parameterTypes[i].needExtraTranslation()) {
                out.print("            ");
                out.print(parameterTypes[i].extraTranslationToJava(parameterNames[i], data));
                out.print(";\n");
            }
        }
    }

    protected void translationMethodReturnCode(PrintWriter out) {
        if (!returnType.nativeType.equals("void")) {
            out.print("\n");
            out.print("            ");
            out.print("return ");
            out.print(returnType.translationToJava("result", data));
            out.print(";\n");
        }
        out.print("        }\n");
        out.print("    }\n");
    }

    protected void nativeMethodDeclaration(PrintWriter out) {
        boolean hasGError = false;

        out.print("    ");
        out.print("private static native final ");
        out.print(returnType.nativeType);
        out.print(" ");
        out.print(nativeMethodName);
        out.print("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                hasGError = true;
                continue;
            }
            if (i > 0) {
                out.print(", ");
            }

            out.print(parameterTypes[i].nativeType);
            out.print(" ");
            out.print(parameterNames[i]);
        }

        if (hasGError) {
            out.print(") throws GlibException;\n");
        } else {
            out.print(");\n");
        }
    }

    protected void jniFunctionDeclaration(PrintWriter out) {
        out.print("\n");
        out.print("JNIEXPORT ");
        out.print(returnType.jniType);
        out.print(" JNICALL\n");

        out.print("Java_");
        out.print(encodeJavaClassName(proxyType.bindingsPackage, proxyType.bindingsClass));
        out.print("_");
        out.print(encodeJavaMethodName(nativeMethodName));
        out.print("\n(\n");
        out.print("\tJNIEnv* env,\n");
        out.print("\tjclass cls");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            out.print(",\n\t");

            out.print(parameterTypes[i].jniType);
            out.print(" _");
            out.print(parameterNames[i]);
        }

        out.print("\n)\n{\n");
    }

    protected void jniFunctionConversionCode(PrintWriter out) {
        /*
         * Declare conversion variables as necessary
         */
        if (!returnType.javaType.equals("void")) {
            out.print("\t");
            out.print(returnType.cType);
            out.print(" result;\n");
            out.print("\t");
            out.print(returnType.jniType);
            out.print(" _result;\n");
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            out.print("\t");

            out.print(parameterTypes[i].cType);
            out.print(" ");
            out.print(parameterNames[i]);
            if (parameterTypes[i] instanceof GErrorThing) {
                out.print(" = NULL");
            }
            out.print(";\n");
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            /*
             * Comment
             */
            out.print("\n\t// convert parameter ");
            out.print(parameterNames[i]);
            out.print("\n");

            /*
             * If a parameter can be null, we need an extra if to avoid the
             * conversion if it is in fact NULL
             */
            if (parameterNullOk[i] && !parameterTypes[i].jniConversionHandlesNull()) {
                out.print("\tif (_" + parameterNames[i] + " == NULL) {\n");
                out.print("\t\t" + parameterNames[i] + " = NULL;\n");
                out.print("\t} else {\n");
                out.print("\t");
            }

            /*
             * variable equals
             */
            out.print("\t");
            out.print(parameterNames[i]);

            /*
             * always a cast
             */
            out.print(" = (");
            out.print(parameterTypes[i].cType);
            out.print(") ");

            /*
             * and now a type specific decode. For many types, the cast is
             * enough, so it's just the plain name.
             */

            out.print(parameterTypes[i].jniConversionDecode(parameterNames[i]));
            out.print(";\n");
            if (parameterTypes[i].jniConversionCanFail()) {
                jniReturnIfExceptionThrown(out, i);
            }

            if (parameterNullOk[i] && !parameterTypes[i].jniConversionHandlesNull()) {
                /* close the "else" */
                out.print("\t}\n");
            }
        }
    }

    /**
     * If a JNI access function hits a problem (ie, OutOfMemoryError) it needs
     * to exit immediately. A Java Exception is already thown, so we just need
     * to bail. This is tricky, however, since the return statement must
     * return something of the return type of the function.
     * 
     * @param i
     *            the index into the parameterNames array (you're calling this
     *            from inside a for loop iterating over the parameters).
     */
    private void jniReturnIfExceptionThrown(PrintWriter out, int i) {
        String extraTab;

        /*
         * When the parameter can be null, we need an extra tab because of the
         * if.
         */
        extraTab = parameterNullOk[i] && !parameterTypes[i].jniConversionHandlesNull() ? "\t" : "";

        out.print(extraTab + "\tif (");
        out.print(parameterNames[i]);
        out.print(" == NULL) {\n");
        out.print(extraTab + "\t\treturn");

        if (!("void".equals(returnType.jniType))) {
            out.print(" ");
            out.print(returnType.jniReturnErrorValue());
        }

        out.print("; // Java Exception already thrown\n");
        out.print(extraTab + "\t}\n");
    }

    protected void jniFunctionLibraryCall(PrintWriter out) {
        out.print("\n");
        out.print("\t// call function\n");

        out.print("\t");
        if (!returnType.jniType.equals("void")) {
            out.print("result = ");
        }
        out.print(nativeMethodName);
        out.print("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                out.print(", ");
            }

            if (parameterTypes[i] instanceof GErrorThing) {
                /*
                 * We pass the GError address
                 */
                out.print("&" + parameterNames[i]);
            } else {
                out.print(parameterNames[i]);
            }

        }

        if (addSentinal) {
            out.print(", NULL");
        }

        out.print(");\n");
    }

    /**
     * Cleanup and return the result if not a void function.
     */
    protected void jniFunctionReturnCode(PrintWriter out) {
        String cleanup;
        String paramGError = null;

        /*
         * type specific cleanup:
         */

        for (int i = 0; i < parameterTypes.length; i++) {

            if (parameterTypes[i] instanceof GErrorThing) {
                paramGError = parameterNames[i];
                continue;
            }

            out.print("\n");
            out.print("\t// cleanup parameter ");
            out.print(parameterNames[i]);
            out.print("\n");

            cleanup = parameterTypes[i].jniConversionCleanup(parameterNames[i]);

            if (cleanup == null) {
                continue;
            }

            /*
             * clean-up is not needed when the parameter is null
             */
            if (parameterNullOk[i] && !parameterTypes[i].jniConversionHandlesNull()) {
                out.print("\tif (" + parameterNames[i] + " != NULL) {\n");
                out.print("\t");
            }
            out.print("\t");
            out.print(cleanup);
            out.print(";\n");
            if (parameterNullOk[i] && !parameterTypes[i].jniConversionHandlesNull()) {
                out.print("\t}\n");
            }
        }

        /*
         * When the function takes a GError pointer as an out-parameter, we
         * need to check if the function has efectivelly thrown an error.
         * After doing the needed clean-up, we check for that situation and
         * throw an exception from JNI side.
         */
        if (paramGError != null) {
            out.print("\n");
            out.print("\t// check for error\n");

            out.print("\tif (" + paramGError + ") {\n");

            out.print("\t\tbindings_java_throwGlibException(env, ");
            out.print(paramGError);
            out.print(");\n");
            out.print("\t\treturn");
            if (!returnType.jniType.equals("void")) {
                out.print(" " + returnType.jniReturnErrorValue());
            }
            out.print(";\n");
            out.print("\t}\n");
        }

        /*
         * return result if applicable. Specific code for certain types; most
         * others, just a cast.
         */

        if (!returnType.jniType.equals("void")) {
            out.print("\n");
            out.print("\t// translate return value to JNI type\n");
            out.print("\t_result = ");
            out.print("(");
            out.print(returnType.jniType);
            out.print(") ");
            out.print(returnType.jniReturnEncode("result"));
            out.print(";\n");

            jniFunctionReturnCleanUp(out);

            out.print("\n");
            out.print("\t// and finally\n");
            out.print("\treturn _result;\n");
        }
        out.print("}\n");
    }

    protected void jniFunctionReturnCleanUp(PrintWriter out) {

        String cleanup;
        cleanup = returnType.jniReturnCleanup("result", callerOwnsReturn);

        /*
         * TODO this assumes all type that need cleanup can be compared with
         * NULL.
         */
        if (cleanup != null) {
            out.print("\n");
            out.print("\t// cleanup return value\n");
            out.print("\tif (result != NULL) {\n");
            out.print("\t");
            out.print("\t");
            out.print(cleanup);
            out.print(";\n");
            out.print("\t}\n");
        }
    }

    /**
     * Quickly scan the type information to see if there is a blacklistedType
     * type present. If so, we use that to output a throws declaration instead
     * of a real method block. As this gets called twice, we could cache this,
     * but whatever.
     */
    private boolean blockContainsBlacklistedThings() {
        if (returnType.blacklisted) {
            blacklistedType = returnType;
            return true;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].blacklisted) {
                blacklistedType = parameterTypes[i];
                return true;
            }
        }
        return false;
    }

    /**
     * See {@link DefsFile#generatePublicLayer(PrintWriter)} for a discussion
     * of why this is not to be touched. I'm serious. Don't even think about
     * it.
     */
    public final void writePublicCode(final PrintWriter out) {
        System.err.println("Not paying attention, are we? Abort.");
        System.exit(2);
    }

    public void writeTranslationCode(final PrintWriter out) {
        translationMethodDeclaration(out);

        if (blockContainsBlacklistedThings()) {
            translationMethodThrowBlacklisted(out);
            return;
        }

        translationMethodConversionCode(out);
        translationMethodNativeCall(out);
        translationMethodParamConversion(out);
        translationMethodReturnCode(out);

        out.print("\n");

        nativeMethodDeclaration(out);
    }

    public void writeJniCode(final PrintWriter out) {
        if (blockContainsBlacklistedThings()) {
            return;
        }

        jniFunctionDeclaration(out);
        jniFunctionConversionCode(out);
        jniFunctionLibraryCall(out);
        jniFunctionReturnCode(out);
    }
}
