/*
 * FundamentalArrayThing.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.driver.DefsFile;

/**
 * Arrays of fundamental types. This is what we use to handle out parameters.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class FundamentalArrayThing extends ArrayThing
{
    public FundamentalArrayThing(String gType, String baseType) {
        super(gType, Thing.lookup(baseType));

        /* needed to prevent cType to be xxx[] */
        this.cType = baseType + '*';
    }

    protected FundamentalArrayThing() {}

    String jniConversionDecode(String name) {
        if (jniType.equals("jfloatArray")) {
            return "(*env)->GetFloatArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jdoubleArray")) {
            return "(*env)->GetDoubleArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jbooleanArray")) {
            return "(*env)->GetBooleanArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jintArray")) {
            return "(*env)->GetIntArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jbyteArray")) {
            return "(*env)->GetByteArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jlongArray")) {
            return "(*env)->GetLongArrayElements(env, _" + name + ", NULL)";
        } else {
            throw new Error(
                    "Code generator asked to deal with an array case for which we do not have logic. Stop.");
        }
    }

    String jniConversionCleanup(String name) {
        if (jniType.equals("jfloatArray")) {
            return "(*env)->ReleaseFloatArrayElements(env, _" + name + ", (jfloat*)" + name + ", 0)";
        } else if (jniType.equals("jdoubleArray")) {
            return "(*env)->ReleaseDoubleArrayElements(env, _" + name + ", (jdouble*)" + name + ", 0)";
        } else if (jniType.equals("jbooleanArray")) {
            return "(*env)->ReleaseBooleanArrayElements(env, _" + name + ", (jboolean*)" + name + ", 0)";
        } else if (jniType.equals("jintArray")) {
            return "(*env)->ReleaseIntArrayElements(env, _" + name + ", (jint*)" + name + ", 0)";
        } else if (jniType.equals("jbyteArray")) {
            return "(*env)->ReleaseByteArrayElements(env, _" + name + ", (jbyte*)" + name + ", 0)";
        } else if (jniType.equals("jlongArray")) {
            return "(*env)->ReleaseLongArrayElements(env, _" + name + ", (jlong*)" + name + ", 0)";
        } else {
            throw new Error();
        }
    }

    /*
     * FIXME we would need a way to figure out the size of the native array,
     * and then create a new java array with NewXXXArray and copy there the
     * elements. This is a clear candidate for code override, as it seems to
     * be very hard to manage in an automatic way.
     */
    String jniReturnEncode(String name) {
        System.out.println("Warning: Not supported return of fundamental array.");
        return "NULL";
    }

    String jniReturnCleanup(String name, char callerOwnsReturn) {
        if (callerOwnsReturn != 'f') {
            return "g_free(" + name + ")";
        } else {
            return null;
        }
    }

    String translationToJava(String name, DefsFile data) {
        return name;
    }

    String translationToNative(String name) {
        return name;
    }

}
