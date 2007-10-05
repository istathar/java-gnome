/*
 * BoxedThing.java
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
 * Types that are unembelished sublcasses of Proxy. Memory management is done
 * manually.
 * 
 * @author Andrew Cowie
 */
public class EntityThing extends ProxiedThing
{
    public EntityThing(String gType, String bindingsPackage, String bindingsClass, String javaType) {
        super(gType, bindingsPackage, bindingsClass, javaType);
    }

    protected EntityThing() {}

    /*
     * FIXME using instanceFor() will let it compile for now, but doesn't deal
     * with the case where there isn't already a Proxy for this pointer. Will
     * probably be trouble, but fixing it will require some sort of
     * registration mechanism to get around the inheritance visibility
     * problem.
     */
    String translationToJava(String name, DefsFile data) {
        return "(" + javaTypeInContext(data) + ") instanceFor(" + name + ")";
    }
}
