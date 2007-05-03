/*
 * FlagsGenerator.java
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
 * Constant. See {@link FlagsBlock} for an example of a (define-flags ...)
 * stanza.
 * 
 * @author Vreixo Formoso
 */
public class FlagsGenerator extends TypeGenerator
{
    private String[][] values;

    /**
     * 
     * @param data
     *            a reference back to the DefsFile from which we will pull the
     *            gTypeName (as found in the defs files) of the enum that you
     *            are generating bindings code for.
     * @param values
     *            The different values enum could take.
     */
    public FlagsGenerator(DefsFile data, String[][] values) {
        super(data);

        this.values = new String[values.length][2];
        for (int i = 0; i < values.length; ++i) {

            String value = values[i][0];

            /*
             * We need to convert values in the form: create-folder to
             * CREATE_FOLDER, etc.
             */
            this.values[i][0] = toAllCaps(value);
            this.values[i][1] = values[i][1];
        }
    }

    public void writeTranslationCode(PrintWriter out) {
        super.writeTranslationCode(out);

        /* and write the values */
        for (int i = 0; i < values.length; ++i) {

            /* write the static field */
            out.print("\n");
            out.print("    ");
            out.print("static final int ");
            out.print(values[i][0]);
            out.print(" = get_flag_");
            out.print(values[i][1]);
            out.print("();\n");
            
            /* and the native method */
            out.print("\n");
            out.print("    ");
            out.print("private static native final int get_flag_");
            out.print(values[i][1]);
            out.print("();\n");            
        }
    }

    public void writeJniCode(PrintWriter out) {
        
        /* write file header and includes */
        super.writeJniCode(out);
        
        /* and now the methods for get the values */
        for (int i = 0; i < values.length; ++i) {
            
            out.print("\n");
            out.print("JNIEXPORT jint JNICALL\n");

            out.print("Java_");
            out.print(encodeJavaClassName(objectType.bindingsPackage, objectType.bindingsClass));
            out.print("_get_1flag_");
            out.print(encodeJavaMethodName(values[i][1]));
            out.print("\n  (JNIEnv* env, jclass cls)\n");
            out.print("{\n");
            out.print("    return ");
            out.print(values[i][1]);
            out.print(";\n");
            out.print("}\n");
        }
        
    }
}
