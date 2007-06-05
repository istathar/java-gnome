/*
 * ConstantThing.java
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
 * Types corresponding to objects defined in (define-enum ...) and
 * (define-flags ...) blocks. These map to Constant subclasses in our
 * bindings.
 * 
 * @author Andrew Cowie
 */
public class ConstantThing extends Thing
{
    public ConstantThing(String gType, String javaPackage, String javaClass, String javaType) {
        super(gType, javaPackage, javaClass, javaType, "int", "jint");
    }

    protected ConstantThing() {}

    String translationToJava(String name) {
        return "(" + javaType + ") constantFor(" + bindingsPackage + "." + javaType + ".class, " + name
                + ")";
    }

    String translationToNative(String name) {
        return "numOf(" + name + ")";
    }
}
