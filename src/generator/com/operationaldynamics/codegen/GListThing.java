/*
 * GListThing.java
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
 * A Thing that represents an array of Java objects. This is the Thing used to
 * deal with GList and GSList types.
 * 
 * @author Vreixo Formoso
 */
public class GListThing extends Thing
{
    /** Type stored in the list */
    private Thing type;

    public GListThing(String gType, Thing type) {
        super(gType.split("-")[0], type.bindingsPackage, type.bindingsClass, type.javaType + "[]",
                "long[]", "jlongArray");
        this.type = type;
        this.cType = this.gType + "*";
    }

    protected GListThing() {}

    String translationToJava(String name, DefsFile data) {
        String newArray = "new " + type.javaTypeInContext(data) + "[" + name + ".length]";
        if (type instanceof ObjectThing) {
            return "(" + javaTypeInContext(data) + ") objectArrayFor(" + name + ", " + newArray + ")";
        } else {
            return "(" + javaTypeInContext(data) + ") boxedArrayFor(" + type.javaTypeInContext(data)
                    + ".class, " + name + ", " + newArray + ")";
        }
    }

    String translationToNative(String name) {
        return "pointersOf(" + name + ")";
    }

    public Thing arrayType() {
        return type;
    }
}
