/*
 * GetterGenerator.java
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
 * Output a method to get a field from a GBoxed or struct (which are both
 * represented by (define-boxed ...).
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @see AccessorGenerator
 */
public class GetterGenerator extends AccessorGenerator
{
    protected final String cField;

    public GetterGenerator(DefsFile data, final String gFieldType, final String gFieldName,
            final String[][] gParameters) {
        super(data, "get_" + gFieldName, gFieldType, gParameters);

        this.cField = gFieldName;
    }

    protected void jniFunctionLibraryCall(PrintWriter out) {
        out.print("\n");
        out.print("\t// get field value\n");

        out.print("\tresult = self->");
        out.print(cField);
        out.print(";\n");
    }
}
