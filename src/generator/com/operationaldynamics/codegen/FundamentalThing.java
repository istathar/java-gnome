/*
 * FundamentalThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.driver.DefsFile;

/**
 * A Thing that represent a type that is fundamental in both Java and C.
 * 
 * <p>
 * This types are not Objects and can be used in the same way in both Java and
 * C, passing JNI boundary needs no special conversion.
 * 
 * @author Andrew Cowie
 */
public class FundamentalThing extends Thing
{
    public FundamentalThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, null, null, javaType, nativeType, jniType);
    }

    protected FundamentalThing() {}

    String translationToJava(String name, DefsFile data) {
        return name;
    }

    String translationToNative(String name) {
        return name;
    }

    String jniReturnErrorValue() {
        if ("jboolean".equals(jniType)) {
            return " JNI_FALSE";
        } else if ("jint".equals(jniType)) {
            return " 0";
        } else if ("jlong".equals(jniType) || "jdouble".equals(jniType)) {
            return " 0L";
        } else {
            return " FIXME";
        }
    }

    String extraTranslationToJava(String name, DefsFile data) {
        return null;
    }

    boolean needGuardAgainstNull() {
        return false;
    }

    String extraTranslationToNative(String name) {
        return null;
    }

    public Thing getTypeToImport() {
        return null;
    }

    boolean needExtraTranslation() {
        return false;
    }
}
