/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
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
