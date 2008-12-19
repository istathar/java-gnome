/*
 * TypedefFundamentalThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * Types that are declared as typedef of a fundamental type, rather than of a
 * struct. FIXME; no idea if this is the correct way to deal with this.
 * 
 * @author Andrew Cowie
 */
public class TypedefFundamentalThing extends FundamentalThing
{
    public TypedefFundamentalThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, javaType, nativeType, jniType);
    }

    protected TypedefFundamentalThing() {}
}
