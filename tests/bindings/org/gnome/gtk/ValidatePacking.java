/*
 * ValidatePacking.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Evaluate the box packing model that underlies GTK layouts
 * 
 * @author Andrew Cowie
 */
public class ValidatePacking extends TestCaseGtk
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

        x.packStart(b);

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
}
