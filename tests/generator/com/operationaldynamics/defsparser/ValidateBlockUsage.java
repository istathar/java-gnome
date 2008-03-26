/*
 * ValidateBlockUsage.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
                "in-module", "Atk"
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
                "bee-bop", "is_a_cowboy"
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
