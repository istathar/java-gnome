/*
 * OutParameterFundamentalThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * Base class for collections of Things and output parameters, that are treated
 * in Java as an array.
 * 
 * @author Vreixo Formoso
 */
public abstract class ArrayThing extends Thing
{
    protected Thing type;
    
    public ArrayThing(String gType, Thing type) {
        super(gType, null, null, type.javaType + "[]", type.nativeType + "[]", type.jniType + "Array");
        this.type = type;
    }

//    protected ArrayThing() {}

//    String jniConversionDecode(String name) {
//        if (jniType.equals("jfloatArray")) {
//            return "(*env)->GetFloatArrayElements(env, _" + name + ", NULL)";
//        } else if (jniType.equals("jdoubleArray")) {
//            return "(*env)->GetDoubleArrayElements(env, _" + name + ", NULL)";
//        } else if (jniType.equals("jbooleanArray")) {
//            return "(*env)->GetBooleanArrayElements(env, _" + name + ", NULL)";
//        } else if (jniType.equals("jintArray")) {
//            return "(*env)->GetIntArrayElements(env, _" + name + ", NULL)";
//        } else {
//            throw new Error(
//                    "Code generator asked to deal with an array case for which we do not have logic. Stop.");
//        }
//    }
//
//    String jniConversionCleanup(String name) {
//        if (jniType.equals("jfloatArray")) {
//            return "(*env)->ReleaseFloatArrayElements(env, _" + name + ", " + name + ", 0)";
//        } else if (jniType.equals("jdoubleArray")) {
//            return "(*env)->ReleaseDoubleArrayElements(env, _" + name + ", " + name + ", 0)";
//        } else if (jniType.equals("jbooleanArray")) {
//            return "(*env)->ReleaseBooleanArrayElements(env, _" + name + ", " + name + ", 0)";
//        } else if (jniType.equals("jintArray")) {
//            return "(*env)->ReleaseIntArrayElements(env, _" + name + ", " + name + ", 0)";
//        } else {
//            throw new Error();
//        }
//    }

    String jniReturnErrorValue() {
        return "NULL";
    }
    
    boolean jniConversionCanFail() {
        return true;
    }

    public Thing getTypeToImport() {
        return type.getTypeToImport();
    }

//    String translationToJava(String name, DefsFile data) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    String translationToNative(String name) {
//        // TODO Auto-generated method stub
//        return null;
//    }
}
