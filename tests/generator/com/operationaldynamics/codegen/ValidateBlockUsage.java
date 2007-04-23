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
        List characteristics;
        ObjectBlock b;

        characteristics = new ArrayList();
        characteristics.add(new String[] {
                "in-module", "Atk"
        });

        b = new ObjectBlock("AtkFoo", characteristics, null);
        assertNotNull(b);
        assertEquals("Atk", b.inModule);
    }

    public final void testBlockReflectorBogusCharacteristic() {
        List characteristics;
        ObjectBlock b;

        characteristics = new ArrayList();
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

    /**
     * This is a somewhat contrived test; the same code is tested again
     * {@link com.operationaldynamics.codegen.ValidateDefsParsing#testObjectBlockCreatesObjectThing()}
     * on "real" defs data.
     */
    public final void testObjectBlockCreatesObjectThing() {
        List characteristics;
        ObjectBlock b;
        Thing t;
        ObjectThing ot;

        characteristics = new ArrayList();
        characteristics.add(new String[] {
                "in-module", "Gtk"
        });
        characteristics.add(new String[] {
                "parent", "GtkBin"
        });
        characteristics.add(new String[] {
                "c-name", "GtkButton"
        });

        b = new ObjectBlock("Button", characteristics, null);
        assertNotNull(b);

        t = b.createThing();
        assertNotNull(t);
        assertTrue(t instanceof ObjectThing);
        ot = (ObjectThing) t;
        assertEquals("org.gnome.gtk", ot.bindingsPackage);
        assertEquals("GtkButton", ot.bindingsClass);

        assertEquals("Button", ot.javaType);
        assertEquals("long", ot.nativeType);
        assertEquals("jlong", ot.jniType);
    }
}
