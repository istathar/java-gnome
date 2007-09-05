/*
 * InterfaceThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

public class InterfaceThing extends ObjectThing
{
    public InterfaceThing(String gType, String bindingsPackage, String bindingsClass, String javaType) {
        super(gType, bindingsPackage, bindingsClass, javaType);
    }

    protected InterfaceThing() {}

    /*
     * GInterfaces are modelled as Java interfaces, but since objectFor()
     * takes Proxy as its type, we need to cast the passed self references
     * (which are concrete Proxies subclasses, of course)
     */
    String translationToNative(String name) {
        return "pointerOf((Object) " + name + ")";
    }
}
