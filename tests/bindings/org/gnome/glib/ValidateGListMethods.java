/*
 * ValidateGListMethods.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.glib;

import org.gnome.gtk.Button;
import org.gnome.gtk.TestCaseGtk;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * Validate the behavior of methods that return a GList*. I.e., validates the
 * system used by java-gnome for converting that native GList* to a java
 * array.
 * 
 * @author Vreixo Formoso
 */
public class ValidateGListMethods extends TestCaseGtk
{
    public final void testGetSingleGObjectChild() {
        Button b;
        VBox x;
        Widget[] children;

        b = new Button("Give me a brother, mum!");

        x = new VBox(false, 3);

        // add the button to the box.
        x.add(b);

        children = x.getChildren();

        assertEquals(1, children.length);
        assertSame(b, children[0]);
    }

    public final void testGetEmptyGObjectChildren() {
        VBox x;
        Widget[] children;

        x = new VBox(false, 3);

        children = x.getChildren();

        assertNull(children);
    }

    public final void testMultipleGObjectChildren() {
        Button b1, b2;
        VBox x;
        Widget[] children;

        b1 = new Button("Button1");
        b2 = new Button("Button2");

        x = new VBox(false, 3);

        // add the first button to the box.
        x.add(b1);

        children = x.getChildren();

        assertEquals(1, children.length);
        assertSame(b1, children[0]);

        // add the second button to the box.
        x.add(b2);

        children = x.getChildren();

        assertEquals(2, children.length);

        /* can we make any assumptions about the order? */
        assertTrue((children[0] == b1 && children[1] == b2) || (children[1] == b1 && children[0] == b2));
    }
}
