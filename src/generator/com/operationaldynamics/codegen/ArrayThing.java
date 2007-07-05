/*
 * ArrayThing.java
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
 * A Thing to deal with array of a native type. By definition, the gtype is a
 * primitive C variable type followed by "[]"; we convert that to the pointer
 * symbol "*".
 * 
 * @author Vreixo Formoso
 */
public class ArrayThing extends FundamentalThing
{
    public ArrayThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, javaType, nativeType, jniType);
        cType = gType.substring(0, gType.length() - 2) + "*";
    }

    protected ArrayThing() {}

    String translationToJava(String name, DefsFile data) {
        return name;
    }

    String translationToNative(String name) {
        return name;
    }
}
