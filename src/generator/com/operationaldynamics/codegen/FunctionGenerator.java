/*
 * FunctionGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintStream;

/**
 * Generate Java and C code for constructors and methods.
 * 
 * <p>
 * Subclasses can override the individual steps of the generation sequence if
 * they wish, however adjusting input passing up to this classes's constructor
 * has been thus far been enough for constructors blocks and methods blocks.
 * 
 * @author Andrew Cowie
 */

abstract class FunctionGenerator extends Generator
{
    protected final ObjectThing ofObject;

    /**
     * The name of the method that is exposed package visible to bindings
     * hackers. Expected to be overridden by subclasses such as
     * ConstuctorGenerator.
     */
    protected String translationMethodName;

    protected final Thing returnType;

    protected final String nativeMethodName;

    /**
     * These are ordered collections so that sublcasses like MethodGenerator
     * can insert the reference-to-self as a first argument.
     */
    protected final Thing[] parameterTypes;

    protected final String[] parameterNames;

    /**
     * 
     * @param ofObject
     *            the class to which the block we are generating code for
     *            pertains.
     * @param blockName
     *            however the .defs data named this block. Usually it's a
     *            "short" name such as "set_label".
     * @param gReturnType
     *            the return type, as specified in the .defs data
     * @param cFunctionName
     *            the C function name, as specified in the .defs data. This
     *            will be used to name the native method.
     * @param gParameters
     *            an array of String[2] arrays, listing the type and name of
     *            each parameter.
     */
    FunctionGenerator(final Thing ofObject, final String blockName, final String gReturnType,
            final String cFunctionName, final String[][] gParameters) {

        if (ofObject == null) {
            throw new IllegalArgumentException(
                    "\nYou need to work out which class this block goes with before calling this constructor.");
        }
        this.ofObject = (ObjectThing) ofObject;

        this.translationMethodName = toCamel(blockName);

        this.returnType = Thing.lookup(gReturnType);

        this.nativeMethodName = cFunctionName;

        parameterTypes = new Thing[gParameters.length];
        parameterNames = new String[gParameters.length];

        for (int i = 0; i < gParameters.length; i++) {
            parameterTypes[i] = Thing.lookup(gParameters[i][0]);
            parameterNames[i] = gParameters[i][1];
        }
    }

    protected String translationMethodDeclaration() {
        StringBuffer buf;

        buf = new StringBuffer();

        buf.append("\n");
        buf.append("    ");
        buf.append("static final ");
        buf.append(returnType.javaType);
        buf.append(" ");
        buf.append(translationMethodName);

        buf.append("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                buf.append(", ");
            }

            buf.append(parameterTypes[i].javaType);
            buf.append(" ");
            buf.append(parameterNames[i]);
        }

        buf.append(")");
        buf.append(" {\n");

        return buf.toString();
    }

    protected String translationMethodConversionCode() {
        StringBuffer buf;

        buf = new StringBuffer();

        /*
         * Declare translation variables as necessary
         */

        if (!returnType.javaType.equals("void")) {
            buf.append("        ");
            buf.append(returnType.nativeType);
            buf.append(" result;\n\n");
        }

        /*
         * Convert (translate) variables from public Java to JNI boundary
         * crossing
         */

        return buf.toString();
    }

    protected String translationMethodNativeCall() {
        StringBuffer buf;
        buf = new StringBuffer();

        buf.append("        ");
        if (!returnType.javaType.equals("void")) {
            buf.append("result = ");
        }
        buf.append(nativeMethodName);
        buf.append("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                buf.append(", ");
            }

            if (parameterTypes[i].translationCode != null) {
                buf.append(parameterTypes[i].translationCode);
                buf.append("(");
                buf.append(parameterNames[i]);
                buf.append(")");
            } else {
                buf.append(parameterNames[i]);
            }
        }

        buf.append(");\n");

        return buf.toString();
    }

    protected String translationMethodReturnCode() {
        StringBuffer buf;

        buf = new StringBuffer();

        if (!returnType.javaType.equals("void")) {
            buf.append("\n");
            buf.append("        return ");
            if (returnType instanceof ObjectThing) {
                buf.append("objectFor(result)");
            } else if (returnType instanceof EnumThing) {
                buf.append("constantFor(");
                buf.append(returnType.bindingsPackage);
                buf.append(".");
                buf.append(returnType.javaType);
                buf.append(".class, result)");
            } else {
                buf.append("result");
            }
            buf.append(";\n");
        }

        buf.append("    }\n");
        return buf.toString();
    }

    protected String nativeMethodDeclaration() {
        StringBuffer buf;

        buf = new StringBuffer();

        buf.append("    ");
        buf.append("private static native final ");
        buf.append(returnType.nativeType);
        buf.append(" ");
        buf.append(nativeMethodName);
        buf.append("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                buf.append(", ");
            }

            buf.append(parameterTypes[i].nativeType);
            buf.append(" ");
            buf.append(parameterNames[i]);
        }

        buf.append(");\n");

        return buf.toString();
    }

    protected String jniFunctionDeclaration(String jniReturnType, String javaPackageName,
            String javaClassName, String javaMethodName) {
        StringBuffer buf;

        buf = new StringBuffer();

        buf.append("JNIEXPORT ");
        buf.append(jniReturnType);
        buf.append(" JNICALL\n");

        buf.append("Java_");
        buf.append(encodeJavaClassName(javaPackageName, javaClassName));
        buf.append("_");
        buf.append(javaClassName);
        buf.append("_");
        buf.append(encodeJavaMethodName(javaMethodName));
        buf.append("\n(\n");
        buf.append("\tJNIEnv* env,\n");
        buf.append("\tjclass cls");

        return buf.toString();
    }

    protected void jniFunctionConversionCode() {

    }

    protected void jniFunctionLibraryCall() {

    }

    protected void jniFunctionReturnCode() {

    }

    void writeJava(PrintStream out) {
        out.print(translationMethodDeclaration());
        out.print(translationMethodConversionCode());
        out.print(translationMethodNativeCall());
        out.print(translationMethodReturnCode());

        out.println();

        out.print(nativeMethodDeclaration());
    }

    void writeC(PrintStream out) {
        out.println("TODO");
    }

}
