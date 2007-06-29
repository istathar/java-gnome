/*
 * FundamentalThing.java
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
 * @author Andrew Cowie
 */
public class FundamentalThing extends Thing
{
    public FundamentalThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, null, null, javaType, nativeType, jniType);
    }

    protected FundamentalThing() {}

    String translationToJava(String name, DefsFile data) {
        return name;
    }

    String translationToNative(String name) {
        return name;
    }
}
