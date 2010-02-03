/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007 Vreixo Formoso
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
 * Output the file header necessary to declare the class containing the
 * constant objects of our representation of C enums via subclasses of
 * Constant. See {@link EnumBlock} for an example of a (define-enum ...)
 * stanza.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class EnumGenerator extends TypeGenerator
{
    private String[] values;

    /**
     * 
     * @param data
     *            a reference back to the DefsFile from which we will pull the
     *            gTypeName (as found in the defs files) of the enum that you
     *            are generating bindings code for.
     * @param values
     *            The different values enum could take.
     */
    public EnumGenerator(DefsFile data, String[][] values) {
        super(data);

        this.values = new String[values.length];
        for (int i = 0; i < values.length; ++i) {

            String value = values[i][0];

            /*
             * We need to convert values in the form: create-folder to
             * CREATE_FOLDER, etc.
             */
            this.values[i] = toAllCaps(value);
        }
    }

    public void writeTranslationCode(final PrintWriter out) {
        super.writeTranslationCode(out);
        writeTranslationValues(out);
    }

    // This is overridden by FlagsGenerator...
    protected void writeTranslationValues(final PrintWriter out) {
        for (int i = 0; i < values.length; ++i) {

            out.print("\n");
            out.print("    ");
            out.print("static final int ");
            out.print(values[i]);
            out.print(" = ");
            out.print(i);
            out.print(";\n");
        }
    }

    /*
     * Enums don't need a C file. Thus, this doesn't do anything, thus we
     * throw something (which gets caught up in the top level
     * BindingsGenerator driver).
     */
    public void writeJniCode(PrintWriter out) {
        throw new UnsupportedOperationException("No JNI code necessary for enums");
    }

    protected void publicPackageAndImports(final PrintWriter out) {
        out.print("package ");
        out.print(objectType.bindingsPackage);
        out.print(";\n\n");

        out.print("import org.freedesktop.bindings.Constant;\n\n");
    }

    protected void publicClassDeclaration(final PrintWriter out) {
        out.print("public final class ");
        out.print(objectType.javaType);
        out.print(" extends Constant");
        out.print("\n{\n");

        out.print("    ");
        out.print("private ");
        out.print(objectType.javaType);
        out.print("(int ordinal, String nickname) {\n");
        out.print("        ");
        out.print("super(ordinal, nickname);\n");
        out.print("    ");
        out.print("}\n");

        out.print("}\n");
    }
}
