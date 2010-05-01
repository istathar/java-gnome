/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gtk;

/**
 * Evaluate the box packing model that underlies GTK layouts
 * 
 * @author Andrew Cowie
 */
public class ValidatePacking extends GraphicalTestCase
{

    /**
     * Pack some widgets, and verify that getChild() and getParent() return
     * those actual objects, by identity, and not clones.
     */
    public final void testGetChildAndParent() {
        final Window w;
        final VBox x;
        final Button b;

        w = new Window();

        x = new VBox(false, 3);

        b = new Button("Change Me?");

        assertNull(b.getParent());

        x.packStart(b, false, false, 0);

        w.add(x);

        assertSame(
                "The child of a Bin (in this case, a Window) was supposed to be the VBox we packed into it!",
                w.getChild(), x);

        // "parent" is a property on Widget
        assertSame("The parent of a Button packed into a VBox should be that box!", b.getParent(), x);

        assertNull("A top level Widget should have had null as its parent!", w.getParent());
    }

    public final void testWidgetContainerHierarchies() {
        final Button b;
        final VBox x;
        final Window w;

        b = new Button("Hello");
        x = new VBox(false, 0);

        assertSame(b, b.getToplevel());
        assertSame(x, x.getToplevel());

        x.add(b);
        assertSame(x, b.getToplevel());

        w = new Window();
        w.add(x);
        assertSame(w, b.getToplevel());
        assertSame(w, x.getToplevel());
        assertSame(w, w.getToplevel());
    }

    public final void testBoxSpacing() {
        final VBox box;

        box = new VBox(false, 3);

        assertEquals(3, box.getSpacing());
        box.setSpacing(1);
        assertEquals(1, box.getSpacing());
        box.setSpacing(0);
        assertEquals(0, box.getSpacing());

        try {
            box.setSpacing(-1);
            fail();
        } catch (IllegalArgumentException iae) {
            // good
        }
    }
}
