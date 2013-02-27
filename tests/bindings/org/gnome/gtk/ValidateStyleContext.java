/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011-2013 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Test case for StyleContext.
 * 
 * @author Guillaume Mazoyer
 */
public class ValidateStyleContext extends GraphicalTestCase
{
    public final void testAddRemoveClass() {
        final Button button;
        final StyleContext context;

        button = new Button();
        context = button.getStyleContext();

        context.addClass(StyleClass.RADIO);
        assertTrue(context.hasClass(StyleClass.RADIO));

        context.removeClass(StyleClass.RADIO);
        assertFalse(context.hasClass(StyleClass.RADIO));
    }

    public final void testAddRemoveRegion() {
        final Button button;
        final StyleContext context;

        button = new Button();
        context = button.getStyleContext();

        context.addRegion(StyleRegion.TAB, RegionFlags.FIRST);
        assertEquals(RegionFlags.NULL, context.hasRegion(StyleRegion.ROW));
        assertEquals(RegionFlags.FIRST, context.hasRegion(StyleRegion.TAB));

        context.removeRegion(StyleRegion.TAB);
        assertEquals(RegionFlags.NULL, context.hasRegion(StyleRegion.TAB));
    }

    public final void testSaveRestore() {
        final Button button;
        final StyleContext context;

        button = new Button();
        context = button.getStyleContext();

        context.addClass(StyleClass.RADIO);
        context.save();
        assertTrue(context.hasClass(StyleClass.RADIO));

        context.addClass(StyleClass.PRIMARY_TOOLBAR);
        context.restore();
        assertFalse(context.hasClass(StyleClass.PRIMARY_TOOLBAR));
    }

    public final void testListStyles() {
        final Toolbar toolbar;
        final StyleContext context;
        final String[] classes, regions;

        toolbar = new Toolbar();
        context = toolbar.getStyleContext();

        context.addClass(StyleClass.PRIMARY_TOOLBAR);
        classes = context.listClasses();
        assertEquals("primary-toolbar", classes[0]);

        context.addRegion(StyleRegion.TAB, RegionFlags.LAST);
        regions = context.listRegions();
        assertEquals("tab", regions[0]);
    }

    public final void testJunctionSides() {
        final Button button;
        final StyleContext context;

        button = new Button();
        context = button.getStyleContext();

        context.setJunctionSides(JunctionSides.TOP);
        assertEquals(JunctionSides.TOP, context.getJunctionSides());
    }

    public final void testTextDirection() {
        final Label label;
        final StyleContext context;

        label = new Label("This is a test case");
        context = label.getStyleContext();

        context.setDirection(TextDirection.RIGHT_TO_LEFT);
        assertEquals(TextDirection.RIGHT_TO_LEFT, context.getDirection());
    }
}
