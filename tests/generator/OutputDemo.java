

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.operationaldynamics.codegen.ConstructorGenerator;
import com.operationaldynamics.codegen.EnumThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.MethodGenerator;
import com.operationaldynamics.codegen.ObjectGenerator;
import com.operationaldynamics.codegen.ObjectThing;
import com.operationaldynamics.codegen.Thing;

public class OutputDemo
{
    //FIXME write this again!!
//    public static void main(String[] args) {
//        final Generator[] generators;
//        final Thing[] things;
//        final Thing button;
//        final PrintWriter out;
//
//        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
//
//        // mockup
//        button = new ObjectThing("GtkButton*", "org.gnome.gtk", "GtkButton", "Button");
//        things = new Thing[] {
//                button,
//                new ObjectThing("GtkWidget*", "org.gnome.gtk", "GtkWidget", "Widget"),
//                new EnumThing("GtkReliefStyle", "org.gnome.gtk", "GtkReliefStyle", "ReliefStyle")
//        };
//
//        for (int i = 0; i < things.length; i++) {
//            Thing.register(things[i]);
//        }
//
//        generators = new Generator[] {
//                new ObjectGenerator("GtkButton*"),
//
//                new ConstructorGenerator("GtkButton*", "gtk_button_new_with_label", new String[][] {
//                    new String[] {
//                            "const-gchar*", "label"
//                    }
//                }),
//
//                new MethodGenerator("GtkButton*", "set_label", "void", "gtk_button_set_label",
//                        new String[][] {
//                            new String[] {
//                                    "const-gchar*", "label"
//                            }
//                        }),
//
//                new MethodGenerator("GtkButton*", "get_image", "GtkWidget*", "gtk_button_get_image",
//                        null),
//
//                new MethodGenerator("GtkButton*", "get_relief", "GtkReliefStyle",
//                        "gtk_button_get_relief", null)
//        };
//
//        for (int i = 0; i < generators.length; i++) {
//            generators[i].writeJavaBody(out);
//            out.flush();
//        }
//
//        out.println("}");
//
//        out.println("==========================================================================");
//        for (int i = 0; i < generators.length; i++) {
//            generators[i].writeCBody(out);
//            out.flush();
//        }
//
//        out.close();
//    }
}
