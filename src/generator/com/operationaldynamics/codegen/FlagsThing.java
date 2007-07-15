/*
 * FlagsThing.java
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
 * Types corresponding to objects defined in (define-flags ...) blocks. These
 * map to Flag subclasses in our bindings.
 * 
 * @author Vreixo Formoso
 */
public class FlagsThing extends EnumThing
{
    public FlagsThing(String gType, String javaPackage, String javaClass, String javaType) {
        super(gType, javaPackage, javaClass, javaType);
    }

    protected FlagsThing() {}

    String translationToJava(String name, DefsFile data) {
        return "(" + javaTypeInContext(data) + ") flagFor(" + javaTypeInContext(data) + ".class, "
                + name + ")";
    }
}
