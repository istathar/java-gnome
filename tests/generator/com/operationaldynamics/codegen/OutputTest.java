package com.operationaldynamics.codegen;

import com.operationaldynamics.codegen.ConstructorGenerator;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.MethodGenerator;
import com.operationaldynamics.codegen.ObjectGenerator;
import com.operationaldynamics.codegen.ObjectThing;
import com.operationaldynamics.codegen.Thing;

public class OutputTest
{
    public static void main(String[] args) {
        // mockup
        Thing button = new ObjectThing("GtkButton*", "org.gnome.gtk", "GtkButton", "Button");
        Thing.register(button);

        Generator gen1 = new ObjectGenerator(button);
        gen1.writeJava(System.out);

        Generator gen2 = new MethodGenerator(button, "set_label", "void", "gtk_button_set_label",
                new String[][] { new String[] { "const-gchar*", "label" } });

        Generator gen3 = new ConstructorGenerator(button, "gtk_button_new_with_label",
                new String[][] { new String[] { "const-gchar*", "label" } });

        gen2.writeJava(System.out);
        gen3.writeJava(System.out);
        System.out.println("}");

        // System.out.println("==========================================================================");
        // gen1.writeC(System.out);
        // gen2.writeC(System.out);
    }
}
