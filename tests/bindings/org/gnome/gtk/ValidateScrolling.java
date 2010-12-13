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
 * @author Andrew Cowie
 */
public class ValidateScrolling extends GraphicalTestCase
{
    public final void testDisallowAddWithViewportIfNative() {
        final TreeView view;
        final ScrolledWindow scroll;

        view = new TreeView();
        scroll = new ScrolledWindow();

        try {
            scroll.addWithViewport(view);
            fail("Should have been prevented");
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            scroll.add(view);
        } catch (IllegalArgumentException iae) {
            fail("Should not have thrown");
        }
    }

    public final void testClearViewportShadowType() {
        final ScrolledWindow scroll;
        final Label label;
        final Widget child;
        final Viewport port;
        ShadowType type;

        label = new Label("Big deal");
        scroll = new ScrolledWindow();

        try {
            scroll.add(label);
            fail("Should have been prevented");
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            scroll.addWithViewport(label);
        } catch (IllegalArgumentException iae) {
            fail("Should not have thrown");
        }

        child = scroll.getChild();
        assertTrue(child instanceof Viewport);
        port = (Viewport) child;

        /*
         * Test that our convenience in addWithViewport() indeed cleared the
         * ShadowType in the created Viewport.
         */

        type = port.getShadowType();
        assertSame(ShadowType.NONE, type);

        /*
         * And meanwhile,
         */

        type = scroll.getShadowType();
        assertSame(ShadowType.NONE, type);
    }
}
