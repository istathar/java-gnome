package com.operationaldynamics.codegen;

import junit.framework.TestCase;

/**
 * While much of the output from the code generator is subjective (ie
 * formatting and code style), there are numerous helper methods used to
 * transform the .defs data to that needed by the java-gnome bindings. These
 * we can test.
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
public final class ValidateUtilityMethods extends TestCase
{
    public final void testEncodeJavaNamesToJni() {
        assertEquals("org_gnome_gtk_GtkButton", Generator.encodeJavaClassName("org.gnome.gtk",
                "GtkButton"));
        assertEquals("gtk_1button_1set_1label", Generator.encodeJavaMethodName("gtk_button_set_label"));
        assertEquals("setLabelTheRightWay", Generator.toCamel("set_label_the_right_way"));
    }

    public final void testFullyQualifiedJavaName() {
        Thing button = new ObjectThing("GtkButton*", "org.gnome.gtk", "GtkButton", "Button");
        assertEquals("org.gnome.gtk.Button", button.fullyQualifiedJavaClassName());
    }

    public final void testConstructorNameMunging() {
        Thing button = new ObjectThing("GtkButton*", "org.gnome.gtk", "GtkButton", "Button");
        Thing.register(button);

        String munged1 = ConstructorGenerator.mungeConstructorName("GtkButton*",
                "gtk_button_new_with_label");
        assertEquals("createButtonWithLabel", munged1);

        String munged2 = ConstructorGenerator.mungeConstructorName("GtkButton*", "gtk_button_new");
        assertEquals("createButton", munged2);
    }

    public final void testThingTranslationCode() {
        FundamentalThing ft;
        ObjectThing ot;

        ft = new FundamentalThing("gboolean", "boolean", "boolean", "jboolean");
        assertEquals("a", ft.translationToNative("a"));
        assertEquals("a", ft.translationToJava("a", null));

        ot = new ObjectThing("GtkWidget*", "org.gnome.gtk", "GtkWidget", "Widget");
        assertEquals("pointerOf(b)", ot.translationToNative("b"));
        assertEquals("objectFor(b)", ot.translationToJava("b", null));
    }

    public final void testSignalNameMunging() {
        assertEquals("DELETE_EVENT", Generator.toAllCaps("delete-event"));
    }

    public final void testPascalCaseBackToUnderscores() {
        assertEquals("gtk_tree_iter", AccessorGenerator.toUnderscores("GtkTreeIter"));
    }
}
