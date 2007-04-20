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

    protected void translationMethodDeclaration(PrintStream out) {
        out.print("\n");
        out.print("    ");
        out.print("static final ");
        out.print(returnType.javaType);
        out.print(" ");
        out.print(translationMethodName);

        out.print("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                out.print(", ");
            }

            out.print(parameterTypes[i].javaType);
            out.print(" ");
            out.print(parameterNames[i]);
        }

        out.print(")");
        out.print(" {\n");
    }

    protected void translationMethodConversionCode(PrintStream out) {
        /*
         * Declare translation variables as necessary
         */

        if (!returnType.javaType.equals("void")) {
            out.print("        ");
            out.print(returnType.nativeType);
            out.print(" result;\n\n");
        }

        /*
         * TODO convert (translate) variables from public Java to JNI boundary
         * crossing (ie, out-parameters)
         */
    }

    protected void translationMethodNativeCall(PrintStream out) {
        out.print("        ");
        if (!returnType.javaType.equals("void")) {
            out.print("result = ");
        }
        out.print(nativeMethodName);
        out.print("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                out.print(", ");
            }

            if (parameterTypes[i].translationCode != null) {
                out.print(parameterTypes[i].translationCode);
                out.print("(");
                out.print(parameterNames[i]);
                out.print(")");
            } else {
                out.print(parameterNames[i]);
            }
        }

        out.print(");\n");
    }

    protected void translationMethodReturnCode(PrintStream out) {
        if (!returnType.nativeType.equals("void")) {
            out.print("\n");
            out.print("        return ");
            if (returnType instanceof ObjectThing) {
                out.print("objectFor(result)");
            } else if (returnType instanceof EnumThing) {
                out.print("constantFor(");
                out.print(returnType.bindingsPackage);
                out.print(".");
                out.print(returnType.javaType);
                out.print(".class, result)");
            } else {
                out.print("result");
            }
            out.print(";\n");
        }

        out.print("    }\n");
    }

    protected void nativeMethodDeclaration(PrintStream out) {
        out.print("    ");
        out.print("private static native final ");
        out.print(returnType.nativeType);
        out.print(" ");
        out.print(nativeMethodName);
        out.print("(");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                out.print(", ");
            }

            out.print(parameterTypes[i].nativeType);
            out.print(" ");
            out.print(parameterNames[i]);
        }

        out.print(");\n");
    }

    protected void jniFunctionDeclaration(PrintStream out) {
        out.print("\n");
        out.print("JNIEXPORT ");
        out.print(returnType.jniType);
        out.print(" JNICALL\n");

        out.print("Java_");
        out.print(encodeJavaClassName(ofObject.bindingsPackage, ofObject.bindingsClass));
        out.print("_");
        out.print(encodeJavaMethodName(nativeMethodName));
        out.print("\n(\n");
        out.print("\tJNIEnv* env,\n");
        out.print("\tjclass cls");

        for (int i = 0; i < parameterTypes.length; i++) {
            out.print(",\n\t");

            out.print(parameterTypes[i].jniType);
            out.print(" _");
            out.print(parameterNames[i]);
        }

        out.print("\n)\n{\n");
    }

    protected void jniFunctionConversionCode(PrintStream out) {
        for (int i = 0; i < parameterTypes.length; i++) {
            out.print("\t");
            out.print(parameterTypes[i].cType);
            out.print(" ");
            out.print(parameterNames[i]);
            out.print(";\n");
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            /*
             * Comment
             */
            out.print("\n\t// convert parameter ");
            out.print(parameterNames[i]);
            out.print("\n");

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
             * and now a type specific decode:
             */
            if (parameterTypes[i].jniType.equals("jstring")) {
                out.print("(*env)->GetStringUTFChars(env, _");
                out.print(parameterNames[i]);
                out.print(", NULL);\n");

                out.print("\tif (");
                out.print(parameterNames[i]);
                out.print(" == NULL) {\n");
                out.print("\t\treturn");
                out.print(errorReturn(returnType));
                out.print("; // OutOfMemoryError already thrown\n");
                out.print("\t}\n");
            } else {
                out.print(parameterNames[i]);
                out.print(";\n");
            }
            // TODO handle arrays carrying out parameters
        }
    }

    /**
     * Little utility function so that when aborting out of a C function
     * (because an Exception has been thrown) the correct syntax is used.
     */
    private String errorReturn(Thing returnType) {
        if (returnType.jniType.equals("void")) {
            return "";
        } else if (returnType.jniType.equals("jboolean")) {
            return " JNI_FALSE";
        } else if (returnType.jniType.equals("jstring")) {
            return " NULL";
        } else if (returnType.jniType.equals("jint")) {
            return " 0";
        } else if (returnType.jniType.equals("jlong")) {
            return " 0L";
        } else {
            return " FIXME";
        }
    }

    protected void jniFunctionLibraryCall(PrintStream out) {
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

            out.print(parameterNames[i]);
        }
        out.print(");\n");
    }

    /**
     * Cleanup and return the result if not a void function.
     */
    protected void jniFunctionReturnCode(PrintStream out) {
        /*
         * type specific cleanup:
         */

        for (int i = 0; i < parameterTypes.length; i++) {
            out.print("\n");
            out.print("\t// cleanup parameter ");
            out.print(parameterNames[i]);
            out.print("\n");

            if (parameterTypes[i].jniType.equals("jstring")) {
                out.print("\t(*env)->RelaseStringUTFChars(env, _");
                out.print(parameterNames[i]);
                out.print(", ");
                out.print(parameterNames[i]);
                out.print(");\n");
            }
        }

        /*
         * return result if applicable. Specific code for certain types; most
         * others, just a cast.
         */

        if (!returnType.jniType.equals("void")) {
            out.print("\n");
            out.print("\t// and finally\n");

            out.print("\treturn ");

            if (returnType.jniType.equals("jstring")) {
                out.print("\t(*env)->NewStringUTF(env, result);");
            } else {
                out.print("(");
                out.print(returnType.jniType);
                out.print(") result;");
            }
            out.print("\n");
        }

        out.print("}\n");
    }

    void writeJava(PrintStream out) {
        translationMethodDeclaration(out);
        translationMethodConversionCode(out);
        translationMethodNativeCall(out);
        translationMethodReturnCode(out);

        out.println();

        nativeMethodDeclaration(out);
    }

    void writeC(PrintStream out) {
        jniFunctionDeclaration(out);
        jniFunctionConversionCode(out);
        jniFunctionLibraryCall(out);
        jniFunctionReturnCode(out);
    }
}
