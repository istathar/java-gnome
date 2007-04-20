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

class ObjectGenerator extends TypeGenerator
{
    ObjectThing forObject;

    ObjectGenerator(Thing forObject) {
        this.forObject = (ObjectThing) forObject;
    }

    void writeJava(PrintStream out) {
        out.print(commonFileHeader(forObject.bindingsClass + ".java"));
        
        out.print(packageStatementAndImports());
    }

    protected String packageStatementAndImports() {
        StringBuffer buf;

        buf = new StringBuffer();

        buf.append("package ");
        buf.append(forObject.bindingsPackage);
        buf.append(";\n\n");

        buf.append("import org.gnome.glib.Plumbing;\n\n");

        buf.append("final class ");
        buf.append(forObject.bindingsClass);
        buf.append(" extends Plumbing\n{\n");

        buf.append("    ");
        buf.append("private ");
        buf.append(forObject.bindingsClass);
        buf.append("() { }\n");

        return buf.toString();
    }

    void writeC(PrintStream out) {
        out.println(commonFileHeader(forObject.bindingsClass + ".c"));

        out.println(includeStatements());
    }

    protected String includeStatements() {
        StringBuffer buf;

        buf = new StringBuffer();
        buf.append("#include <jni.h>\n");
        buf.append("#include <gtk/gtk.h>\n");
        buf.append("#include \"bindings_java.h\"\n");

        buf.append("#include \"");
        buf.append(encodeJavaClassName(forObject.bindingsPackage, forObject.bindingsClass));
        buf.append(".h\";\n");

        return buf.toString();
    }

}
