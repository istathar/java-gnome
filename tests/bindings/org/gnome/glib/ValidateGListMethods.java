/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 */
package org.gnome.glib;

import org.gnome.gtk.Button;
import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * Validate the behavior of methods that return a GList*, verifying the
 * behaviour of the system used by java-gnome for converting that native
 * GList*s into Java arrays.
 * 
 * @author Vreixo Formoso
 */
public class ValidateGListMethods extends GraphicalTestCase
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

        assertNotNull(children);
        assertEquals(0, children.length);
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

        /*
         * Vreixo asks if we can we make any assumptions about the order? Hard
         * to say, really. Depends on what the actual internal storage type
         * inside GtkContainer is for the children, and whether it preserves
         * order. It appears to be a linked list, so the following test seems
         * valid. Vreixo's original idea to test both possibilities is always
         * something we can revert to if we have to.
         */
        // assertTrue((children[0] == b1 && children[1] == b2) || (children[1]
        // == b1 && children[0] == b2));
        assertTrue(children[0] == b1 && children[1] == b2);
    }
}
