/*
 * EnumGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;

import com.operationaldynamics.driver.DefsFile;

/**
 * Output the file header necessary to declare the class containing the
 * constant objects of our representation of C enums via subclasses of
 * Constant.
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

    public void writeTranslationCode(PrintWriter out) {
        super.writeTranslationCode(out);

        /* and write the values */
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
}
