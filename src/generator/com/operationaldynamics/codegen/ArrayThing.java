/*
 * ArrayThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * A Thing to deal with array of a native type. By definition, the gtype is a
 * primitive C variable type followed by "[]"; we convert that to the pointer
 * symbol "*".
 * 
 * @author Vreixo Formoso
 */
/*
 * Implementation note: Given that OutParameterFundamentalThing just got
 * renamed to ArrayFundamentalThing, perhaps this class can merge with it.
 * That said, I'm not sure that ArrayFundamentalThing should be a subclass of
 * FundamentalThing; perhaps a top level ArrayThing abstract class will serve
 * us well.
 */
public class ArrayThing extends FundamentalThing
{
    public ArrayThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, javaType, nativeType, jniType);
        cType = gType.substring(0, gType.length() - 2) + "*";
    }

    protected ArrayThing() {}

    String jniReturnErrorValue() {
        return "NULL";
    }
}
