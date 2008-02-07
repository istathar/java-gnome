/*
 * ValidateUtilityMethods.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.defsparser.ValidateDefsParsing;
import com.operationaldynamics.driver.DefsFile;
import com.operationaldynamics.driver.ImproperDefsFileException;

/**
 * While much of the output from the code generator is subjective (ie
 * formatting and code style), there are numerous helper methods used to
 * transform the .defs data to that needed by the java-gnome bindings. These
 * we can test.
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
public final class ValidateUtilityMethods extends ValidateDefsParsing
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

    public final void testThingTranslationCode() throws ImproperDefsFileException {
        FundamentalThing ft;
        ObjectThing ot;
        DefsFile context;

        context = new DefsFile(parser.parseData());

        ft = new FundamentalThing("gboolean", "boolean", "boolean", "jboolean");
        assertEquals("a", ft.translationToNative("a"));
        assertEquals("a", ft.translationToJava("a", context));

        ot = new ObjectThing("GtkWidget*", "org.gnome.gtk", "GtkWidget", "Widget");
        assertEquals("pointerOf(b)", ot.translationToNative("b"));
        assertEquals("(Widget) objectFor(b)", ot.translationToJava("b", context));
    }

    public final void testSignalNameMunging() {
        assertEquals("DELETE_EVENT", Generator.toAllCaps("delete-event"));
    }

    public final void testPascalCaseBackToUnderscores() {
        assertEquals("gtk_tree_iter", AccessorGenerator.toUnderscores("GtkTreeIter"));
    }
}
