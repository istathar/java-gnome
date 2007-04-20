/*
 * ObjectGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintStream;

/**
 * Output the file header and include statements necessary to begin the
 * translation code for a GObject.
 * 
 * @author Andrew Cowie
 */
class ObjectGenerator extends TypeGenerator
{
    ObjectThing forObject;

    ObjectGenerator(Thing forObject) {
        this.forObject = (ObjectThing) forObject;
    }

    protected void packageStatementAndImports(PrintStream out) {
        out.print("package ");
        out.print(forObject.bindingsPackage);
        out.print(";\n\n");

        out.print("import org.gnome.glib.Plumbing;\n\n");

        out.print("final class ");
        out.print(forObject.bindingsClass);
        out.print(" extends Plumbing\n{\n");

        out.print("    ");
        out.print("private ");
        out.print(forObject.bindingsClass);
        out.print("() {}\n");
    }

    protected void includeStatements(PrintStream out) {
        out.print("\n");
        out.print("#include <jni.h>\n");
        out.print("#include <gtk/gtk.h>\n");
        out.print("#include \"bindings_java.h\"\n");

        out.print("#include \"");
        out.print(encodeJavaClassName(forObject.bindingsPackage, forObject.bindingsClass));
        out.print(".h\";\n");
    }

    void writeJava(PrintStream out) {
        commonFileHeader(out, forObject.bindingsClass + ".java");
        packageStatementAndImports(out);
    }

    void writeC(PrintStream out) {
        commonFileHeader(out, forObject.bindingsClass + ".c");
        includeStatements(out);
    }
}
