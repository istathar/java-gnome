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

import com.operationaldynamics.defsparser.Block;
import com.operationaldynamics.defsparser.ObjectBlock;
import com.operationaldynamics.defsparser.ValidateDefsParsing;

/*
 * In the codegen package so we can see the Thing fields.
 */
public final class ValidateThingUsage extends ValidateDefsParsing
{
    /**
     * This is a somewhat contrived test; the same code is tested again
     * {@link com.operationaldynamics.defsparser.ValidateDefsParsing#testObjectBlockCreatesObjectThing()}
     * on "real" defs data, but this at least is an isolated an explicit
     * check.
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

    public final void testCreateObjectThing() {
        final Block[] blocks;
        final ObjectThing ot;

        blocks = parser.parseData();

        assertTrue(blocks[0] instanceof ObjectBlock);
        ot = (ObjectThing) blocks[0].createThing();
        assertEquals("GtkButton*", ot.gType);
        assertEquals("GtkButton", ot.bindingsClass);
        assertEquals("Button", ot.javaType);

        Thing.register(ot);
        assertSame(ot, Thing.lookup("GtkButton*"));
    }

    public final void testCreateConstVariant() {
        final Thing normal, variant, again;

        normal = Thing.lookup("GtkButton*");

        assertEquals("GtkButton*", normal.gType);
        assertEquals("GtkButton*", normal.cType);

        /*
         * Ok, let's see it make the constant variant Thing
         */

        variant = Thing.lookup("const-GtkButton*");
        assertEquals("const-GtkButton*", variant.gType);
        assertEquals("const GtkButton*", variant.cType);

        /*
         * Make sure it doesn't create another one!
         */

        again = Thing.lookup("const-GtkButton*");
        assertSame(variant, again);
    }
}
