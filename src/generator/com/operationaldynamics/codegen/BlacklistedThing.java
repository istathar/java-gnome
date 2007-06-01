/*
 * BlacklistedThing.java
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
 * Types that are blacklisted for either code generator or java-gnome architectural reasons.
 * The example that led to the creation of this Thing category were function pointers, which
 * we don't have a representation for yet.
 * 
 * @author Andrew Cowie
 */
public class BlacklistedThing extends FundamentalThing
{
    public BlacklistedThing(String gType) {
        super(gType, "long", "long", "jlong");
    }

    protected BlacklistedThing() {}
}
