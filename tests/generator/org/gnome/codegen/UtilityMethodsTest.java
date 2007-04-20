package org.gnome.codegen;

import junit.framework.TestCase;

public class UtilityMethodsTest extends TestCase
{
    public final void testEncodeJavaNamesToJni() {
        assertEquals("org_gnome_gtk_GtkButton", Generator.encodeJavaClassName("org.gnome.gtk",
                "GtkButton"));
        assertEquals("gtk_1button_1set_1label", Generator.encodeJavaMethodName("gtk_button_set_label"));
        assertEquals("setLabelTheRightWay", Generator.toCamel("set_label_the_right_way"));
    }

    public final void testConstructorNameMunging() {
        Thing button = new ObjectThing("GtkButton*", "org.gnome.gtk", "GtkButton", "Button");

        String munged1 = ConstructorGenerator.mungeConstructorName(button, "gtk_button_new_with_label");
        assertEquals("createButtonWithLabel", munged1);

        String munged2 = ConstructorGenerator.mungeConstructorName(button, "gtk_button_new");
        assertEquals("createButton", munged2);
    }

    public final void testMethodReferenceToSelfInsertion() {
        String[][] input, output;
        Thing button;

        button = new ObjectThing("GtkButton*", "org.gnome.gtk", "GtkButton", "Button");

        input = new String[][] { new String[] { "const-gchar*", "label" } };
        assertTrue(input.length == 1);
        assertEquals("const-gchar*", input[0][0]);
        assertEquals("label", input[0][1]);

        output = MethodGenerator.prependReferenceToSelf(button, input);
        assertTrue(output.length == 2);
        assertEquals("GtkButton*", output[0][0]);
        assertEquals("self", output[0][1]);
        assertEquals("const-gchar*", output[1][0]);
        assertEquals("label", output[1][1]);

    }
}
