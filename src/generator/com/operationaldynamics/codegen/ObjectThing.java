/*
 * ObjectThing.java
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

public class ObjectThing extends ProxiedThing
{
    public ObjectThing(String gType, String bindingsPackage, String bindingsClass, String javaType) {
        super(gType, bindingsPackage, bindingsClass, javaType);
    }

    protected ObjectThing() {}

    String translationToJava(String name, DefsFile data) {
        return "(" + javaTypeInContext(data) + ") objectFor(" + name + ")";
    }

    @Override
    String jniReturnCleanup(String name, char callerOwnsReturn) {
        return "bindings_java_memory_cleanup((GObject*)" + name + ", "
                + (callerOwnsReturn == 't' ? "TRUE" : "FALSE") + ")";
    }
}
