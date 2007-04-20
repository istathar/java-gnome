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
package org.gnome.codegen;

abstract class FunctionGenerator extends Generator
{
    private FunctionBlock block;

    protected String javaReturn;

    protected String javaMethod;

    FunctionGenerator(FunctionBlock block) {
        this.block = block;
    }

    protected String translationMethodDeclaration() {
        StringBuffer buf;
        String args[][];

        buf = new StringBuffer();

        buf.append("    ");
        buf.append("static final ");
        buf.append(toCamel(block.blockName));

        buf.append("(");

        args = block.gParameters;
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                buf.append(", ");
            }

            buf.append(Thing.lookup(args[i][0]).javaType);
            buf.append(" ");
            buf.append(args[i][1]);
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

        if (!block.gReturnType.equals("void")) {
            buf.append("        ");
            buf.append(Thing.lookup(block.gReturnType).nativeType);
            buf.append(" result\n\n");
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
        buf.append(block.cFunctionName);
        buf.append("(");

        buf.append("pointerOf(this)");
        for (int i = 0; i < block.gParameters.length; i++) {
            buf.append(", ");

            String translation = Thing.lookup(block.gParameters[i][0]).translationCode;
            if (translation != null) {
                buf.append(translation);
                buf.append("(");
                buf.append(block.gParameters[i][1]);
                buf.append(")");
            } else {
                buf.append(block.gParameters[i][1]);
            }
        }

        buf.append(");\n");

        return buf.toString();
    }

    protected String translationMethodReturnCode() {
        StringBuffer buf;

        buf = new StringBuffer();

        if (!block.gReturnType.equals("void")) {
            // if instanceOf ObjectThing...
            buf.append("        return ");
            buf.append(Thing.lookup(block.gReturnType).nativeType);
            buf.append(" result\n");
        }

        buf.append("    }\n");
        return buf.toString();
    }

    protected String nativeMethodDeclaration() {
        StringBuffer buf;

        buf = new StringBuffer();

        buf.append("    ");
        buf.append("private static final ");
        buf.append(Thing.lookup(block.gReturnType).nativeType);
        buf.append(" ");
        buf.append(block.cFunctionName);
        buf.append("(");

        buf.append("long self");
        for (int i = 0; i < block.gParameters.length; i++) {
            buf.append(", ");

            buf.append(Thing.lookup(block.gParameters[i][0]).nativeType);
            buf.append(" ");
            buf.append(block.gParameters[i][1]);
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
}
