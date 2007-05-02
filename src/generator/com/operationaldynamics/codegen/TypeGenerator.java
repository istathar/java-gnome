/*
 * TypeGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.operationaldynamics.driver.DefsFile;

/**
 * Base class for the Generators which process the information declaring a
 * native type we are rendering into Java classes: GObjects, boxeds/structs,
 * enums, etc
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
abstract class TypeGenerator extends Generator
{
    protected final Thing objectType;

    public TypeGenerator(DefsFile data) {
        super(data);
        this.objectType = data.getType();
    }

    public void writeTranslationCode(final PrintWriter out) {
        commonFileHeader(out, objectType.bindingsClass + ".java");
        packageStatementAndImports(out);
        translationClassDeclaration(out);
    }

    public void writeJniCode(final PrintWriter out) {
        commonFileHeader(out, objectType.bindingsClass + ".c");
        hashIncludeStatements(out);
    }

    /**
     * Compose the copyright header common to all generated sources files.
     * 
     * @param fileName
     *            this is just cosmetic, but by convention we put the filename
     *            at the top of our source files.
     */
    /*
     * I truely cannot believe that I just converted a here doc into a series
     * of single line strings with \n at the end of each. Oh well, it's done
     * now, and this was really the only long block of text.
     */
    protected static void commonFileHeader(PrintWriter out, String fileName) {
        out.print("/*\n");
        out.print(" * ");
        out.print(fileName);
        out.print("\n *\n");
        out.print(" * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd, and Others\n");
        out.print(" *\n");
        out.print(" * The code in this file, and the library it is a part of, are made available\n");
        out.print(" * to you by the authors under the terms of the \"GNU General Public Licence,\n");
        out.print(" * version 2\" plus the \"Classpath Exception\" (you may link to this code as a\n");
        out.print(" * library into other programs provided you don't make a derivation of it).\n");
        out.print(" * See the LICENCE file for the terms governing usage and redistribution.\n");
        out.print(" *\n");
        out.print(" *                      THIS FILE IS GENERATED CODE!\n");
        out.print(" *\n");
        out.print(" * To modify its contents or behaviour, either update the generation program,\n");
        out.print(" * change the information in the source defs file, or implement an override for\n");
        out.print(" * this class.\n");
        out.print(" */\n");
    }

    protected void packageStatementAndImports(final PrintWriter out) {
        final List types;
        Iterator iter;

        out.print("package ");
        out.print(objectType.bindingsPackage);
        out.print(";\n\n");

        out.print("import org.gnome.glib.Plumbing;\n");

        /*
         * TODO maybe a TreeSet + compareTo in Thing would be a better
         * option (at least more efficient) for sorting this, but...
         */
        types = new ArrayList(data.usesTypes());
        Collections.sort(types, new Comparator() {

            public int compare(Object arg0, Object arg1) {
                Thing t1, t2;
                
                t1 = (Thing) arg0;
                t2 = (Thing) arg1;
                
                return t1.fullyQualifiedJavaClassName()
                       .compareTo(t2.fullyQualifiedJavaClassName());
            }
        });

        iter = types.iterator();
        while (iter.hasNext()) {
            Thing t = (Thing) iter.next();

            out.print("import ");

            out.print(t.fullyQualifiedJavaClassName());
            out.print(";\n");
        }

    }

    protected void translationClassDeclaration(final PrintWriter out) {
        out.print("\n");
        out.print("final class ");
        out.print(objectType.bindingsClass);
        out.print(" extends Plumbing\n{\n");

        out.print("    ");
        out.print("private ");
        out.print(objectType.bindingsClass);
        out.print("() {}\n");
    }

    protected void hashIncludeStatements(final PrintWriter out) {
        out.print("\n");
        out.print("#include <jni.h>\n");
        out.print("#include <gtk/gtk.h>\n");
        out.print("#include \"bindings_java.h\"\n");

        out.print("#include \"");
        out.print(encodeJavaClassName(objectType.bindingsPackage, objectType.bindingsClass));
        out.print(".h\";\n");
    }
}
