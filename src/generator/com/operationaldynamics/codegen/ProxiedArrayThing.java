/*
 * ProxiedArrayThing.java
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

/**
 * FIXME
 * 
 * @author Vreixo Formoso
 */
public class ProxiedArrayThing extends ArrayThing
{

    public ProxiedArrayThing(String gType, Thing type) {
        super(gType, type);
        // super(gType.split("-")[0], type.bindingsPackage,
        // type.bindingsClass, type.javaType + "[]",
        // "long[]", "jlongArray");
        // this.type = type;
        this.cType = this.gType + "*"; //TODO should this be only on GList
    }

    // protected ProxiedArrayThing() {}

    String translationToJava(String name, DefsFile data) {
        String newArray = "new " + type.javaTypeInContext(data) + "[" + name + ".length]";
        if (type instanceof ObjectThing) {
            return "(" + javaTypeInContext(data) + ") objectArrayFor(" + name + ", " + newArray + ")";
        } else {
            return "(" + javaTypeInContext(data) + ") boxedArrayFor(" + type.javaTypeInContext(data)
                    + ".class, " + name + ", " + newArray + ")";
        }
    }

    String translationToNative(String name) {
        return "pointersOf(" + name + ")";
    }

    String jniConversionDecode(String name) {
        return "bindings_java_convert_jarray_to_gpointer(env, _" + name + ")";
    }

    String jniConversionCleanup(String name) {
        return "bindings_java_convert_gpointer_to_jarray(env, " + name + ", _" + name + ")";
    }
    
    /*
     * FIXME we would need a way to figure out the size of the array,
     * and then create a new java array with NewXXXArray and copy there the
     * elements.
     * This is a clear candidate for code override, as it seems to be very
     * hard to manage in an automatic way.
     */
    String jniReturnEncode(String name) {
        System.err.println("[WARNING] Not supported return of GObject/GBoxed array.");
        return "NULL";
    }

    String jniReturnErrorValue() {
        return "NULL";
    }

    String extraTranslationToJava(String name, DefsFile data) {
        return "fillObjectArray(" + name + ", _" + name + ")";
    }

    String extraTranslationToNative(String name) {
        return "pointersOf(" + name + ")";
    }

    boolean needExtraTranslation() {
        return true;
    }
}
