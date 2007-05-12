/*
 * BoxedGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
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
 * Output the file header and include statements necessary to begin the
 * translation code for a GBoxed. This Generator renders an BoxedBlock into
 * the compilation unit class declaration, along with necessary file headers
 * and include statements, care of its parent, {@link TypeGenerator}.
 * 
 * TODO also write setters and getters for fields
 * 
 * @author Vreixo Formoso
 */
public class BoxedGenerator extends TypeGenerator
{
    protected final Thing[] fieldTypes;

    protected final String[] fieldNames;

    /*
     * TODO We also need to write setters and getters for fields. This has two
     * problems to be resolved: a) Some fields are a GObject itself, not a
     * pointer, so, how to manage this? is not as easy as pass the address of
     * the object like a pointer: we need take care that the boxed is not
     * deleted until all internal objects are no more used from java. b) Other
     * are defined as "const-XXXX", where XXXX is a GObject, so we need to
     * drop the const-.
     */

    public BoxedGenerator(DefsFile data, String[][] fields) {
        super(data);

        fieldTypes = new Thing[fields.length];
        fieldNames = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fieldTypes[i] = Thing.lookup(fields[i][0]);
            fieldNames[i] = fields[i][1];
        }
    }

    public void writeTranslationCode(final PrintWriter out) {
        super.writeTranslationCode(out);

        // FIXME shit!, also imports for field types are needed

        for (int i = 0; i < fieldNames.length; ++i) {
            writeTranslationSetter(out, i);
            writeTranslationGetter(out, i);
        }
    }

    private void writeTranslationSetter(PrintWriter out, int index) {

        String fieldName = toCamel(fieldNames[index]);
        String fieldSetter = "set_" + fieldNames[index];

        /* declaration */
        out.print("\n");
        out.print("    ");
        out.print("static final void ");
        out.print(toCamel(fieldSetter));
        out.print("(");
        out.print(objectType.javaType);
        out.print(" self, ");
        out.print(fieldTypes[index].javaType);
        out.print(" ");
        out.print(fieldName);
        out.print(") {\n");

        /* call to native */
        out.print("        ");
        out.print(fieldSetter);
        // TODO call Thing? it's always a pointerOf(self)...
        out.print("( pointerOf(self), ");
        out.print(fieldTypes[index].translationToNative(fieldName));
        out.print(");\n");

        out.print("    }\n\n");

        /* native declaration */
        out.print("    ");
        out.print("private static native final void ");
        out.print(fieldSetter);
        out.print("(long self, ");
        out.print(fieldTypes[index].nativeType);
        out.print(" ");
        out.print(fieldName);
        out.print(");\n");
    }

    private void writeTranslationGetter(PrintWriter out, int index) {

        String fieldGetter = "get_" + fieldNames[index];

        /* declaration */
        out.print("\n");
        out.print("    ");
        out.print("static final ");
        out.print(fieldTypes[index].javaType);
        out.print(" ");
        out.print(toCamel(fieldGetter));
        out.print("(");
        out.print(objectType.javaType);
        out.print(" self) {\n");

        /* result declaration */
        out.print("        ");
        out.print(fieldTypes[index].nativeType);
        out.print(" result;\n\n");

        /* call to native */
        out.print("        ");
        out.print("result = ");
        out.print(fieldGetter);
        // TODO call Thing? it's always a pointerOf(self)...
        out.print("( pointerOf(self) );\n");

        /* and return */
        out.print("\n");
        out.print("        return (");
        out.print(fieldTypes[index].javaType);
        out.print(") ");
        out.print(fieldTypes[index].translationToJava("result"));
        out.print(";\n");
        out.print("    }\n\n");

        /* native declaration */
        out.print("    ");
        out.print("private static native final ");
        out.print(fieldTypes[index].nativeType);
        out.print(" ");
        out.print(fieldGetter);
        out.print("(long self);\n");
    }

    public void writeJniCode(final PrintWriter out) {
        super.writeJniCode(out);

        for (int i = 0; i < fieldNames.length; ++i) {
            writeJniSetter(out, i);
            writeJniGetter(out, i);
        }
    }

    /*
     * FIXME this has a lot of code similar to FunctionGenerator so maybe we
     * can refactor this to a util class TODO generate comments
     */
    private void writeJniSetter(PrintWriter out, int index) {
        out.print("\n");
        out.print("JNIEXPORT void JNICALL\n");

        /* method declaration */
        out.print("Java_");
        out.print(encodeJavaClassName(objectType.bindingsPackage, objectType.bindingsClass));
        out.print("_");
        out.print(encodeJavaMethodName("set_" + fieldNames[index]));
        out.print("\n(\n");
        out.print("\tJNIEnv* env,\n");
        out.print("\tjclass cls,\n");
        out.print("\tjlong _self,\n\t");
        out.print(fieldTypes[index].jniType);
        out.print(" _");
        out.print(fieldNames[index]);
        out.print("\n)\n{\n");

        out.print("\t");
        out.print(fieldTypes[index].cType);
        out.print(" ");
        out.print(fieldNames[index]);
        out.print(";\n");
        out.print("\t");
        out.print(objectType.cType);
        out.print(" self;\n\n");

        /* convert self */
        out.print("\tself = (");
        out.print(objectType.cType);
        out.print(") _self;\n");

        /* convert type parameter */
        out.print("\t");
        out.print(fieldNames[index]);
        out.print(" = (");
        out.print(fieldTypes[index].cType);
        out.print(") _");

        /* and specific codes */
        // TODO handle more types?
        if (fieldTypes[index].jniType.equals("jstring")) {
            out.print("(*env)->GetStringUTFChars(env, _");
            out.print(fieldNames[index]);
            out.print(", NULL);\n");

            // jniReturnIfExceptionThrown(out, index);
            out.print("\tif (");
            out.print(fieldNames[index]);
            out.print(" == NULL) {\n");
            out.print("\t\treturn; // Java Exception already thrown\n");
            out.print("\t}\n");
        } else {
            out.print(fieldNames[index]);
            out.print(";\n");
        }

        /* set parameter */
        out.print("\n\tself->");
        out.print(fieldNames[index]);
        out.print(" = ");
        out.print(fieldNames[index]);
        out.print(";\n");

        /* clean up if needed */
        if (fieldTypes[index].jniType.equals("jstring")) {
            out.print("\n\t(*env)->RelaseStringUTFChars(env, _");
            out.print(fieldNames[index]);
            out.print(", ");
            out.print(fieldNames[index]);
            out.print(");\n");
        }

        /* return */
        out.print("}\n");

    }

    /*
     * FIXME this has a lot of code similar to FunctionGenerator so maybe we
     * can refactor this to a util class TODO generate comments
     */
    private void writeJniGetter(PrintWriter out, int index) {
        out.print("\n");
        out.print("JNIEXPORT ");
        out.print(fieldTypes[index].jniType);
        out.print(" JNICALL\n");
        out.print("Java_");
        out.print(encodeJavaClassName(objectType.bindingsPackage, objectType.bindingsClass));
        out.print("_");
        out.print(encodeJavaMethodName("get_" + fieldNames[index]));
        out.print("\n(\n");
        out.print("\tJNIEnv* env,\n");
        out.print("\tjclass cls,\n");
        out.print("\tjlong _self\n)\n{\n");

        out.print("\t");
        out.print(fieldTypes[index].cType);
        out.print(" result;\n");
        out.print("\t");
        out.print(objectType.cType);
        out.print(" self;\n\n");

        /* convert self */
        out.print("\tself = (");
        out.print(objectType.cType);
        out.print(") _self;\n");

        /* get field */
        out.print("\n\tresult = self->");
        out.print(fieldNames[index]);
        out.print(";\n\n");

        /* and return */
        out.print("\treturn ");
        if (fieldTypes[index].jniType.equals("jstring")) {
            out.print("\t(*env)->NewStringUTF(env, result);");
        } else {
            out.print("(");
            out.print(fieldTypes[index].jniType);
            out.print(") result;");
        }
        out.print("\n}\n");

    }
}
