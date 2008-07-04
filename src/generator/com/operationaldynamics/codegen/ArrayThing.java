/*
 * OutParameterFundamentalThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * Base class for collections of Things and output parameters, that are
 * treated in Java as an array.
 * 
 * @author Vreixo Formoso
 */
public abstract class ArrayThing extends Thing
{
    protected Thing type;

    public ArrayThing(String gType, Thing type) {
        super(gType, null, null, type.javaType + "[]", type.nativeType + "[]", type.jniType + "Array");
        this.type = type;
    }

    public ArrayThing(String gType, Thing type, String jniArrayType) {
        super(gType, null, null, type.javaType + "[]", type.nativeType + "[]", jniArrayType);
        this.type = type;
    }

    protected ArrayThing() {}

    String jniReturnErrorValue() {
        return "NULL";
    }

    boolean jniConversionCanFail() {
        return true;
    }

    public Thing getTypeToImport() {
        return type.getTypeToImport();
    }

    boolean jniConversionHandlesNull() {
        return false;
    }
}
