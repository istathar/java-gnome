/*
 * ObjectThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

public class ObjectThing extends Thing
{
    ObjectThing(String gType, String bindingsPackage, String bindingsClass, String javaType) {
        super(gType, bindingsPackage, bindingsClass, javaType, "long", "jlong");
    }

    String translationToJava(String name) {
        return "objectFor(" + name + ")";
    }

    String translationToNative(String name) {
        return "pointerOf(" + name + ")";
    }
}
