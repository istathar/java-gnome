/*
 * OutParameterFundamentalThing.java
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
 * Fundamental types that are (to our great misfortune), out parameters.
 * 
 * @author Andrew Cowie
 */
/*
 * I can't say I'm thrilled about the name of this class.
 */
public class OutParameterFundamentalThing extends FundamentalThing
{
    public OutParameterFundamentalThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, javaType, nativeType, jniType);
    }
}
