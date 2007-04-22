/*
 * ValidateBlockUsage.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public final class ValidateBlockUsage extends TestCase
{

    public final void testBlockSetOfObjectAddsPointerSymbol() {
        Block block = new ObjectBlock("Blah", null, null);

        block.setOfObject("GnomeBlah");
        assertEquals("GnomeBlah*", block.ofObject);
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
        List l;
        ObjectBlock b;

        l = new ArrayList();
        l.add(new String[] {
                "in-module", "Atk"
        });

        b = new ObjectBlock("AtkFoo", l, null);
        assertNotNull(b);
        assertEquals("Atk", b.inModule);
    }

    public final void testBlockReflectorBogusCharacteristic() {
        List l;
        ObjectBlock b;

        l = new ArrayList();
        l.add(new String[] {
                "bee-bop", "is_a_cowboy"
        });

        b = null;
        try {
            b = new ObjectBlock("AnimeFoo", l, null);
            fail("Should have thrown IllegalStateException because of a characteristic name that is no known");
        } catch (IllegalStateException ise) {
            // good
        }
        assertNull(b);
    }
}
