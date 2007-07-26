/*
 * OutParameterFundamentalThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * Arrays of fundamental types. This is what we use to handle out parameters.
 * 
 * @author Andrew Cowie
 */
/*
 * This class may be replaced by internal capability (a flag perhaps) in
 * FundamentalThing or it may be joined by OutParameterObjectThing, etc.
 */
public class ArrayFundamentalThing extends FundamentalThing
{
    public ArrayFundamentalThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, javaType, nativeType, jniType);
    }

    protected ArrayFundamentalThing() {}

    String jniConversionDecode(String name) {
        if (jniType.equals("jfloatArray")) {
            return "(*env)->GetFloatArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jdoubleArray")) {
            return "(*env)->GetDoubleArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jbooleanArray")) {
            return "(*env)->GetBooleanArrayElements(env, _" + name + ", NULL)";
        } else if (jniType.equals("jintArray")) {
            return "(*env)->GetIntArrayElements(env, _" + name + ", NULL)";
        } else {
            throw new Error(
                    "Code generator asked to deal with an array case for which we do not have logic. Stop.");
        }
    }

    String jniConversionCleanup(String name) {
        if (jniType.equals("jfloatArray")) {
            return "(*env)->ReleaseFloatArrayElements(env, _" + name + ", " + name + ", 0)";
        } else if (jniType.equals("jdoubleArray")) {
            return "(*env)->ReleaseDoubleArrayElements(env, _" + name + ", " + name + ", 0)";
        } else if (jniType.equals("jbooleanArray")) {
            return "(*env)->ReleaseBooleanArrayElements(env, _" + name + ", " + name + ", 0)";
        } else if (jniType.equals("jintArray")) {
            return "(*env)->ReleaseIntArrayElements(env, _" + name + ", " + name + ", 0)";
        } else {
            throw new Error();
        }
    }

    String jniReturnErrorValue() {
        return "NULL";
    }
}
