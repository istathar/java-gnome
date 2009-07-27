/*
 * StringThing.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
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
        return "bindings_java_getString(env, _" + name + ")";
    }

    boolean jniConversionCanFail() {
        return true;
    }

    boolean jniConversionHandlesNull() {
        return false;
    }

    String jniConversionCleanup(String name) {
        return "bindings_java_releaseString(" + name + ")";
    }

    String jniReturnEncode(String name) {
        return "bindings_java_newString(env, " + name + ")";
    }

    String jniReturnErrorValue() {
        return "NULL";
    }

    String jniReturnCleanup(String name, char callerOwnsReturn) {
        if (callerOwnsReturn == 't') {
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
