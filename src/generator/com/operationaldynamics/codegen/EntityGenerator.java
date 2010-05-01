/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Output the file header and include statements necessary to begin the
 * translation code for a Cairo entity.
 * 
 * @author Andrew Cowie
 */
public class EntityGenerator extends TypeGenerator
{
    public EntityGenerator(DefsFile data) {
        super(data);
    }

    protected void publicPackageAndImports(final PrintWriter out) {
        out.print("package ");
        out.print(objectType.bindingsPackage);
        out.print(";\n\n");

        out.print("import org.freedesktop.bindings.Proxy;\n\n");
    }

    protected void publicClassDeclaration(final PrintWriter out) {
        out.print("public final class ");
        out.print(objectType.javaType);
        out.print(" extends Proxy");
        out.print("\n{\n");

        out.print("    ");
        out.print("protected ");
        out.print(objectType.javaType);
        out.print("(long pointer) {\n");
        out.print("        ");
        out.print("super(pointer);\n");
        out.print("    ");
        out.print("}\n");

        out.print("\n");
        out.print("    ");
        out.print("protected void release() {\n");
        out.print("        ");
        out.print("/*\n");
        out.print("        ");
        out.print(" * FIXME This class's release() method must be implemented to call\n");
        out.print("        ");
        out.print(" * the correct free() or unref() function before it can be used.\n");
        out.print("        ");
        out.print("*/\n");
        out.print("        ");
        out.print("throw new UnsupportedOperationException(\"Not yet implemented\");\n");
        out.print("    ");
        out.print("}\n");

        out.print("}\n");
    }
}
