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
