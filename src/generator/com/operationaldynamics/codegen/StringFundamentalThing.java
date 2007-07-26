/*
 * StringFundamentalThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

public class StringFundamentalThing extends FundamentalThing
{
    StringFundamentalThing(String gType) {
        super(gType, "String", "String", "jstring");
    }

    protected StringFundamentalThing() {}

    String jniConversionDecode(String name) {
        return "(*env)->GetStringUTFChars(env, _" + name + ", NULL)";
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
}
