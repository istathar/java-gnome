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
import java.util.Iterator;
import java.util.List;

import com.operationaldynamics.driver.DefsFile;

/**
 * Base class for the Generators which process the information declaring a
 * native type we are rendering into Java classes: GObjects, boxeds/structs,
 * enums, etc. Note that these Generators simply output the code necessary for
 * a Block objects that result from (define- ...) stanzas that declare data
 * types. <b>This does not imply the Java side type as a whole - just its
 * declaration.</b> All Blocks are independent and by themselves, no relation
 * to any other Blocks, and likewise the Generators for them are just about
 * the necessary file headers and opening statements. Any necessary context is
 * provided by the {@link DefsFile} argument which is the first parameter to
 * all Generator constructors.
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

    public void writePublicCode(final PrintWriter out) {
        commonFileHeader(out, objectType.javaType + ".java", true);
        publicPackageAndImports(out);
        publicClassJavadocComment(out);
        publicClassDeclaration(out);
    }

    public void writeTranslationCode(final PrintWriter out) {
        commonFileHeader(out, objectType.bindingsClass + ".java", false);
        packageStatementAndImports(out);
        translationClassDeclaration(out);
    }

    public void writeJniCode(final PrintWriter out) {
        commonFileHeader(out, objectType.bindingsClass + ".c", false);
        hashIncludeStatements(out);
    }

    /**
     * Compose the copyright header common to all generated sources files.
     * 
     * @param fileName
     *            this is just cosmetic, but by convention we put the filename
     *            at the top of our source files.
     * @param stub
     *            if this is actually a stub of a public API class, rather
     *            than a "generated" translation or JNI later file.
     */
    /*
     * I truly cannot believe that I just converted a here doc into a series
     * of single line strings with \n at the end of each. Oh well, it's done
     * now, and this was really the only long block of text.
     */
    protected static void commonFileHeader(PrintWriter out, String fileName, boolean stub) {
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

        if (stub) {
            out.print(" */\n");
            return;
        }

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

        types = new ArrayList();
        types.add("org.gnome.glib.Plumbing");

        iter = data.usesTypes().iterator();
        while (iter.hasNext()) {
            Thing type = (Thing) iter.next();

            types.add(type.fullyQualifiedJavaClassName());
        }

        /* sort the types */
        Collections.sort(types);

        iter = types.iterator();
        while (iter.hasNext()) {
            String t = (String) iter.next();

            out.print("import ");

            out.print(t);
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
        out.print(".h\"\n");
    }

    /*
     * Code to output public stubs. DO NOT TOUCH
     */

    protected abstract void publicPackageAndImports(final PrintWriter out);

    protected void publicClassJavadocComment(final PrintWriter out) {
        out.print("/*\n");
        out.print(" * FIXME this is a placeholder stub for what will become the public API for\n");
        out.print(" * this type. Replace this comment with appropriate javadoc including\n");
        out.print(" * @author and @since tags.\n");
        out.print(" */\n");
    }

    protected abstract void publicClassDeclaration(final PrintWriter out);
}
