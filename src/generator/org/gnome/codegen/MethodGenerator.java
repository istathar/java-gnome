/*
 * MethodGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.codegen;

import java.io.PrintStream;

class MethodGenerator extends FunctionGenerator
{

    MethodGenerator(MethodBlock block) {
        super(block);
    }

    void writeJava(PrintStream out) {
        out.print(translationMethodDeclaration());
        out.print(translationMethodConversionCode());
        out.print(translationMethodNativeCall());
        out.print(translationMethodReturnCode());

        out.println();
        
        out.print(nativeMethodDeclaration());
    }

    void writeC(PrintStream out) {
        out.print(jniFunctionDeclaration("void", "org.gnome.glib", "GtkButton", "gtk_button_set_label"));
    }
}
