package org.gnome.codegen;


import org.gnome.codegen.Block;
import org.gnome.codegen.Generator;
import org.gnome.codegen.MethodBlock;
import org.gnome.codegen.ObjectBlock;
import org.gnome.codegen.ObjectThing;
import org.gnome.codegen.Thing;

public class OutputTest
{
    public static void main(String[] args) {
        Thing button = new ObjectThing("org.gnome.gtk", "GtkButton", "Button", "GtkButton*");
        
        Block block1 = new ObjectBlock(button, "Button");
        
        Block block2 = new MethodBlock(button, "set_label", "void", "gtk_button_set_label", new String[][] { new String [] { "const-gchar*", "label" } });
        
        Generator gen1 = block1.generator();
        gen1.writeJava(System.out);
        
        Generator gen2 = block2.generator();
        gen2.writeJava(System.out);
        System.out.println("}");
   
//        System.out.println("==========================================================================");
//        gen1.writeC(System.out);
//        gen2.writeC(System.out);
    }
}
