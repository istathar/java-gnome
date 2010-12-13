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
package com.operationaldynamics.defsparser;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public final class ValidateBlockUsage extends TestCase
{

    public final void testBlockAddPointerSymbol() {
        assertEquals("GnomeBlah*", Block.addPointerSymbol("GnomeBlah"));
    }

    public final void testCharacteristicNameToSetterMethod() {
        assertEquals("setBeeBop", Block.nameToMethod("bee-bop"));
    }

    /**
     * Not only tests the reflector in processCharacteristics(), but makes
     * sure it works for subclasses even through the reflector code is in
     * Block.
     */
    public final void testBlockReflectorKnownCharacteristic() {
        List<String[]> characteristics;
        ObjectBlock b;

        characteristics = new ArrayList<String[]>();
        characteristics.add(new String[] {
            "in-module",
            "Atk"
        });

        b = new ObjectBlock("AtkFoo", characteristics, null);
        assertNotNull(b);
        assertEquals("Atk", b.inModule);
    }

    public final void testBlockReflectorBogusCharacteristic() {
        List<String[]> characteristics;
        ObjectBlock b;

        characteristics = new ArrayList<String[]>();
        characteristics.add(new String[] {
            "bee-bop",
            "is_a_cowboy"
        });

        b = null;
        try {
            b = new ObjectBlock("AnimeFoo", characteristics, null);
            fail("Should have thrown IllegalStateException because of a characteristic name that is no known");
        } catch (IllegalStateException ise) {
            // good
        }
        assertNull(b);
    }

    public final void testModuleNameToJavaPackageName() {
        assertEquals("org.gnome.gnome", TypeBlock.moduleToJavaPackage("Gnome"));
    }
}
