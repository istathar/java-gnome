/*
 * OutputDemo.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class OutputDemo
{
    public static void main(String[] args) {
        final Generator[] generators;
        final Thing[] things;
        final Thing button;
        final PrintWriter out;

        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
        
        // mockup
        button = new ObjectThing("GtkButton*", "org.gnome.gtk", "GtkButton", "Button");
        things = new Thing[] { button,
                new ObjectThing("GtkWidget*", "org.gnome.gtk", "GtkWidget", "Widget"),
                new EnumThing("GtkReliefStyle", "org.gnome.gtk", "GtkReliefStyle", "ReliefStyle") };

        for (int i = 0; i < things.length; i++) {
            Thing.register(things[i]);
        }

        generators = new Generator[] {
                new ObjectGenerator(button),

                new ConstructorGenerator(button, "gtk_button_new_with_label",
                        new String[][] { new String[] { "const-gchar*", "label" } }),

                new MethodGenerator(button, "set_label", "void", "gtk_button_set_label",
                        new String[][] { new String[] { "const-gchar*", "label" } }),

                new MethodGenerator(button, "get_image", "GtkWidget*", "gtk_button_get_image", null),

                new MethodGenerator(button, "get_relief", "GtkReliefStyle", "gtk_button_get_relief",
                        null) };

        for (int i = 0; i < generators.length; i++) {
            generators[i].writeJava(out);
            out.flush();
        }

        out.println("}");

        out.println("==========================================================================");
        for (int i = 0; i < generators.length; i++) {
            generators[i].writeC(out);
            out.flush();
        }
        
        out.close();
    }
}
