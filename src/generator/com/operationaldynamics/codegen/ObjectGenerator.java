/*
 * ObjectGenerator.java
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

/**
 * Output the file header and include statements necessary to begin the
 * translation code for a GObject. This Generator renders an ObjectBlock into
 * the compilation unit class declaration, along with necessary file headers
 * and include statements, care of its parent, {@link TypeGenerator}
 * 
 * @author Andrew Cowie
 */
public class ObjectGenerator extends TypeGenerator
{
    protected Thing parentType;

    protected Thing[] implementedTypes;

    public ObjectGenerator(DefsFile data, String parentGType, String[] implementedGInterfaces) {
        super(data);
        if (parentGType == null) {
            parentType = Thing.lookup("GObject*");
        } else {
            parentType = Thing.lookup(parentGType);
        }

        implementedTypes = new Thing[implementedGInterfaces.length];
        for (int i = 0; i < implementedGInterfaces.length; i++) {
            implementedTypes[i] = Thing.lookup(implementedGInterfaces[i]);
        }

    }

    protected void publicPackageAndImports(final PrintWriter out) {
        out.print("package ");
        out.print(objectType.bindingsPackage);
        out.print(";\n\n");

        if ((parentType == null) || (objectType.bindingsPackage.equals(parentType.bindingsPackage))) {
            return;
        }

        out.print("import ");
        out.print(parentType.bindingsPackage);
        out.print(".");
        out.print(parentType.javaType);
        out.print(";\n\n");
    }

    protected void publicClassDeclaration(final PrintWriter out) {
        out.print("public class ");
        out.print(objectType.javaType);
        out.print(" extends ");
        out.print(parentType.javaType);

        for (int i = 0; i < implementedTypes.length; i++) {
            if (i == 0) {
                out.print(" implements ");
            } else {
                out.print(", ");
            }
            out.print(implementedTypes[i].javaType);
        }

        out.print("\n{\n");

        out.print("    ");
        out.print("protected ");
        out.print(objectType.javaType);
        out.print("(long pointer) {\n");
        out.print("        ");
        out.print("super(pointer);\n");
        out.print("    ");
        out.print("}\n");

        out.print("}\n");
    }
}
