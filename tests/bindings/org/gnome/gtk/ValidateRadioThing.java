/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 */
public class ValidateRadioThing extends GraphicalTestCase
{
    public final void testRadioMenuItemGroup() {
        final RadioGroup group;
        final RadioMenuItem first, second;
        Label child;

        // Create a group
        group = new RadioGroup();

        // Here, there is no member so it should be 'null'
        assertNull(group.getMember());

        // Create a first item
        first = new RadioMenuItem(group, "First");

        // Now the member of the group should not be null
        assertNotNull(group.getMember());

        // So we can create a second item
        second = new RadioMenuItem(group, "Second");

        // Check content
        child = (Label) first.getChild();
        assertEquals("First", child.getText());

        child = (Label) second.getChild();
        assertEquals("Second", child.getText());
    }

    public final void testRadioActionGroup() {
        final RadioGroup group;
        final RadioAction first, second;
        final MenuItem one, two;
        Label child;

        // Create a group
        group = new RadioGroup();

        // Here, there is no member so it should be 'null'
        assertNull(group.getMember());

        // Create a first item
        first = new RadioAction(group, "first", "First");

        // Now the member of the group should not be null
        assertNotNull(group.getMember());

        // So we can create a second item
        second = new RadioAction(group, "second", "Second");

        // Test create menu item
        one = first.createMenuItem();
        two = second.createMenuItem();

        // Check content
        child = (Label) one.getChild();
        assertEquals("First", child.getText());

        child = (Label) two.getChild();
        assertEquals("Second", child.getText());
    }
}
