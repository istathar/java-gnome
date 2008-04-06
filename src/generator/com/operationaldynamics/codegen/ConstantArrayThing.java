/*
 * ConstantArrayThing.java
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
 * Represent arrays of Constants, Enums or Flags, used as ouput parameters.
 * 
 * @author Vreixo Formoso
 */
public class ConstantArrayThing extends ArrayThing
{

    public ConstantArrayThing(String gType, Thing type) {
        super(gType, type);
    }

    protected ConstantArrayThing() {}

    /*
     * FIXME do we need this?
     */
    String translationToJava(String name, DefsFile data) {
        System.out.println("Warning: Not supported return of Constant array.");
        return "null";
    }

    String translationToNative(String name) {
        return "_" + name;
    }

    String jniConversionDecode(String name) {
        return "(*env)->GetIntArrayElements(env, _" + name + ", NULL)";
    }

    String jniConversionCleanup(String name) {
        return "(*env)->ReleaseIntArrayElements(env, _" + name + ", (jint*)" + name + ", 0)";
    }

    /*
     * FIXME do we need this?
     */
    String jniReturnEncode(String name) {
        System.out.println("Warning: Not supported return of Constant array.");
        return "NULL";
    }

    String jniReturnErrorValue() {
        return "NULL";
    }

    String extraTranslationToJava(String name, DefsFile data) {
        if (type instanceof FlagsThing) {
            return "fillFlagArray(" + type.javaTypeInContext(data) + ".class, " + name + ", _" + name
                    + ")";
        } else {
            return "fillEnumArray(" + type.javaTypeInContext(data) + ".class, " + name + ", _" + name
                    + ")";
        }
    }

    String extraTranslationToNative(String name) {
        return "numsOf(" + name + ")";
    }

    boolean needExtraTranslation() {
        return true;
    }
}
