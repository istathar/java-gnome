/*
 * StringThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.driver.DefsFile;

public class StringThing extends Thing
{
    StringThing(String gType) {
        super(gType, null, null, "String", "String", "jstring");
    }

    protected StringThing() {}

    String jniConversionDecode(String name) {
        return "(*env)->GetStringUTFChars(env, _" + name + ", NULL)";
    }
    
    boolean jniConversionCanFail() {
        return true;
    }

    boolean jniConversionHandlesNull() {
        return false;
    }

    String jniConversionCleanup(String name) {
        return "(*env)->ReleaseStringUTFChars(env, _" + name + ", " + name + ")";
    }

    String jniReturnEncode(String name) {
        return "(*env)->NewStringUTF(env, " + name + ")";
    }

    String jniReturnErrorValue() {
        return "NULL";
    }

    String translationToJava(String name, DefsFile data) {
        return name;
    }

    String translationToNative(String name) {
        return name;
    }

    String extraTranslationToJava(String name, DefsFile data) {
        return null;
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
