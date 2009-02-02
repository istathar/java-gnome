/*
 * VirtualGenerator.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;

import com.operationaldynamics.driver.DefsFile;

/**
 * Generate Java and C code for signal callbacks (which are how we map
 * virtuals).
 * 
 * 
 * @author Andrew Cowie
 */
/*
 * We don't use much of FunctionGenerators output code, but its field
 * definitions (as translated from its constructor) are useful.
 */
public class VirtualGenerator extends FunctionGenerator
{
    // TODO use a Thing instead?

    protected final String javaSignalClass;

    protected final String cSignalName;

    protected final String receiverMethodName;

    protected final String interfaceMethodName;

    /**
     * 
     * @param gObjectType
     * @param blockName
     *            the block name as given in the define statement. The
     *            VirtualGenerator will work out how to transform into the
     *            signal interface name as necessary.
     * @param gReturnType
     * @param gParameters
     *            the parameters array describing the signature of the signal
     *            handler callback.
     */
    /*
     * We let FunctionGenerator split up the parameters, but they aren't used
     * for the translationMethod singature, but rather the interface method.
     */
    public VirtualGenerator(final DefsFile data, final String blockName, final String gReturnType,
            final String[][] gParameters) {
        super(data, "connect", gReturnType, null, gParameters, 'f');

        this.javaSignalClass = toPascalCase(blockName) + "Signal";
        this.cSignalName = blockName.replace('_', '-');
        this.receiverMethodName = toCamel("receive_" + blockName);
        this.interfaceMethodName = toCamel("on_" + blockName);
    }

    /*
     * Ripoff override of FunctionGenerator's method by this name.
     */
    protected void translationMethodDeclaration(PrintWriter out) {
        out.print("\n");
        out.print("    ");
        out.print("static final void ");
        out.print(translationMethodName);
        out.print("(");
        out.print(proxyType.javaTypeInContext(data));
        out.print(" self, ");
        out.print(proxyType.bindingsClass);
        out.print(".");
        out.print(javaSignalClass);
        out.print(" handlerInstance, boolean after) {");
        out.print("\n");
    }

    protected void translationMethodSuperCall(PrintWriter out) {
        out.print("        ");
        out.print("connectSignal(");
        if (proxyType instanceof InterfaceThing) {
            out.print("(Object) ");
        }
        out.print("self, handlerInstance, ");
        out.print(proxyType.bindingsClass);
        out.print(".class, \"");
        out.print(cSignalName);
        out.print("\", after);\n");

        out.print("    }\n");
    }

    protected void receiverMethodDeclaration(PrintWriter out) {
        out.print("\n");
        out.print("    ");
        out.print("protected static final ");
        out.print(returnType.nativeType);
        out.print(" ");
        out.print(receiverMethodName);

        out.print("(Signal handler, long source");

        for (int i = 0; i < parameterTypes.length; i++) {

            if (parameterTypes[i] instanceof GErrorThing) {
                System.out.println("Warning: unsupported GError usage in a virtual");
                continue;
            }
            out.print(", ");

            out.print(parameterTypes[i].nativeType);
            out.print(" ");
            out.print(parameterNames[i]);
        }

        out.print(")");
        out.print(" {\n");
    }

    protected void receiverMethodConversionCode(PrintWriter out) {
        int declarations = 0;

        if (!returnType.javaType.equals("void")) {
            out.print("        ");
            out.print(returnType.javaTypeInContext(data));
            out.print(" result;\n");
            declarations++;
        }
        if (returnType.needExtraTranslation()) {
            out.print("        ");
            out.print(returnType.nativeType);
            out.print(" _result;\n");
            declarations++;
        }

        if (declarations > 0) {
            out.print("\n");
        }
    }

    /**
     * This is an ugly, complicated expression, but that's the way it is. Be
     * happy you aren't writing this by hand a thousand times.
     */
    protected void receiverMethodInvokeInstance(PrintWriter out) {
        out.print("        ");

        if (!returnType.nativeType.equals("void")) {
            out.print("result = ");
        }

        out.print("((");
        out.print(proxyType.bindingsClass);
        out.print(".");
        out.print(javaSignalClass);
        out.print(") handler).");
        out.print(interfaceMethodName);
        out.print("((");
        out.print(proxyType.javaTypeInContext(data));
        out.print(") objectFor(source)");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            out.print(", ");
            out.print(parameterTypes[i].translationToJava(parameterNames[i], data));
        }

        out.print(");\n");
        if (returnType.needExtraTranslation()) {
            out.print("        ");
            out.print("_result = ");
            out.print(returnType.extraTranslationToNative("result"));
            out.print(";\n");
        }
    }

    protected void receiverMethodReturnCode(PrintWriter out) {
        if (!returnType.nativeType.equals("void")) {
            out.print("\n");
            out.print("        ");
            out.print("return ");
            out.print(returnType.translationToNative("result"));
            out.print(";\n");
        }

        out.print("    }\n");
    }

    protected void interfaceClassDeclaration(PrintWriter out) {
        out.print("\n");
        out.print("    ");
        out.print("interface ");
        out.print(javaSignalClass);
        out.print(" extends Signal");
        out.print("\n");
        out.print("    ");
        out.print("{");
        out.print("\n");
    }

    protected void interfaceMethodDeclaration(PrintWriter out) {
        out.print("        ");
        out.print(returnType.javaTypeInContext(data));
        out.print(" ");
        out.print(interfaceMethodName);
        out.print("(");
        out.print(proxyType.javaTypeInContext(data));
        out.print(" source");

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof GErrorThing) {
                continue;
            }
            out.print(", ");
            out.print(parameterTypes[i].javaTypeInContext(data));
            out.print(" ");
            out.print(parameterNames[i]);
        }
        out.print(");");
        out.print("\n");

    }

    protected void interfaceClassClose(PrintWriter out) {
        out.print("    ");
        out.print("}");
        out.print("\n");
    }

    /*
     * Note that we don't use any of FunctionGenerator's code output methods;
     * all these calls are here in VirtualGenerator.
     */
    public void writeTranslationCode(final PrintWriter out) {
        interfaceClassDeclaration(out);
        interfaceMethodDeclaration(out);
        interfaceClassClose(out);

        translationMethodDeclaration(out);
        translationMethodSuperCall(out);

        receiverMethodDeclaration(out);
        receiverMethodConversionCode(out);
        receiverMethodInvokeInstance(out);
        receiverMethodReturnCode(out);
    }

    /*
     * No JNI code necessary, but nor is it necessary to throw an exception;
     * we just don't want it to do anything here, that's all. So we override
     * the implementation from FunctionGenerator with an empty block.
     */
    public void writeJniCode(final PrintWriter out) {}
}
