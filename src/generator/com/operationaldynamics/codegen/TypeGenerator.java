/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007      Vreixo Formoso
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
import java.util.ArrayList;
import java.util.Collections;
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
        commonFileHeader(out, objectType.javaType + ".java");
        publicPackageAndImports(out);
        publicClassJavadocComment(out);
        publicClassDeclaration(out);
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
     * @param stub
     *            if this is actually a stub of a public API class, rather
     *            than a "generated" translation or JNI later file.
     */
    /*
     * I truly cannot believe that I just converted a here doc into a series
     * of single line strings with \n at the end of each. Oh well, it's done
     * now, and this was really the only long block of text.
     */
    protected static void commonFileHeader(PrintWriter out, String fileName) {
        out.print("/*\n");
        out.print(" * java-gnome, a UI library for writing GTK and GNOME programs from Java!\n");
        out.print(" *\n");
        out.print(" * Copyright © 2006-2012 Operational Dynamics Consulting, Pty Ltd and Others\n");
        out.print(" *\n");
        out.print(" * The code in this file, and the program it is a part of, is made available\n");
        out.print(" * to you by its authors as open source software: you can redistribute it\n");
        out.print(" * and/or modify it under the terms of the GNU General Public License version\n");
        out.print(" * 2 (\"GPL\") as published by the Free Software Foundation.\n");
        out.print(" *\n");
        out.print(" * This program is distributed in the hope that it will be useful, but WITHOUT\n");
        out.print(" * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or\n");
        out.print(" * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.\n");
        out.print(" *\n");
        out.print(" * You should have received a copy of the GPL along with this program. If not,\n");
        out.print(" * see http://www.gnu.org/licenses/. The authors of this program may be\n");
        out.print(" * contacted through http://java-gnome.sourceforge.net/.\n");
        out.print(" *\n");
        out.print(" * Linking this library statically or dynamically with other modules is making\n");
        out.print(" * a combined work based on this library. Thus, the terms and conditions of\n");
        out.print(" * the GPL cover the whole combination. As a special exception (the\n");
        out.print(" * \"Classpath Exception\"), the copyright holders of this library give you\n");
        out.print(" * permission to link this library with independent modules to produce an\n");
        out.print(" * executable, regardless of the license terms of these independent modules,\n");
        out.print(" * and to copy and distribute the resulting executable under terms of your\n");
        out.print(" * choice, provided that you also meet, for each linked independent module,\n");
        out.print(" * the terms and conditions of the license of that module. An independent\n");
        out.print(" * module is a module which is not derived from or based on this library. If\n");
        out.print(" * you modify this library, you may extend the Classpath Exception to your\n");
        out.print(" * version of the library, but you are not obligated to do so. If you do not\n");
        out.print(" * wish to do so, delete this exception statement from your version.\n");
        out.print(" */\n");
    }

    protected void generatedFileWarning(final PrintWriter out) {
        out.print("/*\n");
        out.print(" * THIS FILE IS GENERATED CODE!\n");
        out.print(" *\n");
        out.print(" * To modify its contents or behaviour, either update the generation program,\n");
        out.print(" * change the information in the source defs file, or implement an override for\n");
        out.print(" * this class.\n");
        out.print(" */\n");
    }

    protected void packageStatementAndImports(final PrintWriter out) {
        final List<String> types;

        out.print("package ");
        out.print(objectType.bindingsPackage);
        out.print(";\n\n");
        generatedFileWarning(out);
        out.print("\n");

        types = new ArrayList<String>();
        types.add(objectType.bindingsPackage + ".Plumbing");

        for (Thing type : data.getTypesToImport()) {
            types.add(type.fullyQualifiedJavaClassName());
        }

        /* sort the types */
        Collections.sort(types);

        for (String t : types) {
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
        generatedFileWarning(out);
        out.print("\n");

        out.print("#include <jni.h>\n");
        out.print("#include <gtk/gtk.h>\n");

        if (objectType.importHeaders != null) {
            for (String header : objectType.importHeaders) {
                out.print("#include <" + header + ">\n");
            }
        }

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
        out.print(" * this type. Replace this comment with appropriate javadoc including author\n");
        out.print(" * and since tags. Note that the class may need to be made abstract, implement\n");
        out.print(" * interfaces, or even have its parent changed. No API stability guarantees\n");
        out.print(" * are made about this class until it has been reviewed by a hacker and this\n");
        out.print(" * comment has been replaced.\n");
        out.print(" */\n");
    }

    protected abstract void publicClassDeclaration(final PrintWriter out);
}
