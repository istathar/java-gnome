/*
 * SetterGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
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
 * Output a method to set a field in a GBoxed. Note that we would not expect
 * this to be called very much as most fields in GTK structs are read-only.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @see AccessorGenerator
 */
public class SetterGenerator extends AccessorGenerator
{
    protected final String cField;

    public SetterGenerator(final DefsFile data, final String gFieldType, final String gFieldName,
            final String[][] gParameters) {
        super(data, "set_" + gFieldName, "none", gParameters);

        this.cField = gFieldName;
    }

    protected void jniFunctionLibraryCall(PrintWriter out) {
        out.print("\n");
        out.print("\t// set field value\n");

        out.print("\tself->");
        out.print(cField);
        out.print(" = ");
        out.print(parameterNames[1]);
        out.print(";\n");
    }
}
