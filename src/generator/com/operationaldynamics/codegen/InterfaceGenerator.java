/*
 * InterfaceGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;

import com.operationaldynamics.driver.DefsFile;

public class InterfaceGenerator extends ObjectGenerator
{
    public InterfaceGenerator(final DefsFile data, final String parentGType,
            final String[] implementedGInterfaces) {
        super(data, null, implementedGInterfaces);

        if (parentGType == null) {
            parentType = null;
        } else {
            parentType = Thing.lookup(parentGType);
        }
    }

    protected void publicClassDeclaration(final PrintWriter out) {
        out.print("public interface ");
        out.print(objectType.javaType);

        if (parentType != null) {
            out.print(" extends ");
            out.print(parentType.javaType);
        }

        for (int i = 0; i < implementedTypes.length; i++) {
            out.print(", ");
            out.print(implementedTypes[i].javaType);
        }

        out.print("\n{\n}\n");
    }
}
