/*
 * StringArrayThing.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007-2008 Vreixo Formoso
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.driver.DefsFile;

/**
 * Arrays of strings.
 * 
 * @author Vreixo Formoso
 */
public class StringArrayThing extends ArrayThing
{
    public StringArrayThing(String gType) {
        super(gType, Thing.lookup("gchar*"), "jobjectArray");
    }

    protected StringArrayThing() {}

    String jniConversionDecode(String name) {
        return "bindings_java_convert_strarray_to_gchararray(env, _" + name + ")";
    }

    String jniConversionCleanup(String name) {
        return "bindings_java_convert_gchararray_to_strarray(env, (gchar**)" + name + ", _" + name + ")";
    }

    String jniReturnEncode(String name) {
        return "bindings_java_convert_gchararray_to_jarray(env, (const gchar**)" + name + ")";
    }

    String jniReturnCleanup(String name, char callerOwnsReturn) {
        if (callerOwnsReturn == 't') {
            return "g_strfreev(" + name + ")";
        } else if (callerOwnsReturn == 'l') {
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
