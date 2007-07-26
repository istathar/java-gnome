/*
 * ProxiedThing.java
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
 * @author Vreixo Formoso
 * @author Andrew Cowie
 */
public abstract class ProxiedThing extends Thing
{
    public ProxiedThing(String gType, String bindingsPackage, String bindingsClass, String javaType) {
        super(gType, bindingsPackage, bindingsClass, javaType, "long", "jlong");
    }

    protected ProxiedThing() {}

    String translationToNative(String name) {
        return "pointerOf(" + name + ")";
    }
    
    String jniReturnErrorValue() {
        return "0L";
    }
}
