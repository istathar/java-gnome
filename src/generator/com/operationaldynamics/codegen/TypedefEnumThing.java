/*
 * TypedefEnumThing.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * This is effectively a <code>typedef</code>, except it's hard wired to work
 * the other way: java-gnome effectively declares a type (and uses it in the
 * .defs data) but at the conversion to C layer it is returned to an int of
 * the cType specified.
 * 
 * @author Andrew Cowie
 */
public class TypedefEnumThing extends EnumThing
{
    public TypedefEnumThing(String gType, String cType, String javaPackage, String javaClass,
            String javaType) {
        super(gType, javaPackage, javaClass, javaType);
        super.cType = cType;
    }
}
