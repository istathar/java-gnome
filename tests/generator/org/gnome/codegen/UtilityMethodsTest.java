package org.gnome.codegen;

import junit.framework.TestCase;

public class UtilityMethodsTest extends TestCase
{
    public final void testEncodeJavaNamesToJni() {
        assertEquals("org_gnome_glib_GtkButton", Generator.encodeJavaClassName("org.gnome.glib",
                "GtkButton"));
        assertEquals("gtk_1button_1set_1label", Generator.encodeJavaMethodName("gtk_button_set_label"));
        assertEquals("setLabelTheRightWay", Generator.toCamel("set_label_the_right_way"));
    }
}
