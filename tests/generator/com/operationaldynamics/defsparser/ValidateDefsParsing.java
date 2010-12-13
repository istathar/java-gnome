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

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import junit.framework.TestCase;

import com.operationaldynamics.codegen.Thing;

/**
 * Evaluate the internal methods in the DefsParser class.
 * 
 * @author Andrew Cowie
 */
public class ValidateDefsParsing extends TestCase
{
    private static final String inputDefsData;

    static {
        final StringBuffer buf;
        final String[] raw;

        raw = new String[] {
            "(define-object Button",
            "  (in-module \"Gtk\")",
            "  (parent \"GtkBin\")",
            "  (c-name \"GtkButton\")",
            "  (gtype-id \"GTK_TYPE_BUTTON\")",
            ")",
            "",
            "(define-function gtk_button_new",
            "  (is-constructor-of \"GtkButton\")",
            "  (c-name \"gtk_button_new\")",
            "  (caller-owns-return #t)",
            "  (return-type \"GtkWidget*\")",
            ")",
            "",
            "(define-method set_label",
            "  (of-object \"GtkButton\")",
            "  (c-name \"gtk_button_set_label\")",
            "  (return-type \"none\")",
            "  (parameters",
            "    '(\"const-gchar*\" \"label\")",
            "  )",
            ")",
            "",
            "(define-method leave",
            "  (of-object \"GtkButton\")",
            "  (c-name \"gtk_button_leave\")",
            "  (return-type \"none\")",
            "  (deprecated)",
            ")",
            "",
            "(define-virtual clicked",
            "  (of-object \"GtkButton\")",
            "  (return-type \"none\")",
            ")"
        };

        buf = new StringBuffer();
        for (int i = 0; i < raw.length; i++) {
            buf.append(raw[i]);
            buf.append("\n");
        }

        inputDefsData = buf.toString();
    }

    protected DefsParser parser;

    DefsLineNumberReader in;

    public void setUp() throws IOException {
        in = new DefsLineNumberReader(new StringReader(inputDefsData), "Mock data");

        parser = new DefsParser(in);
    }

    public void tearDown() throws IOException {
        in.close();
    }

    public final void testInputStreamToStanzas() throws ParseException {
        int i;

        for (i = 0; parser.readNextStanza(); i++) {
            ;
        }

        assertEquals(5, i);
    }

    public final void testObjectBlockCreated() throws ParseException {
        Block[] results;
        ObjectBlock o;

        results = parser.parseData();

        assertTrue(results[0] instanceof ObjectBlock);

        o = (ObjectBlock) results[0];

        assertEquals("Gtk", o.inModule);
        assertEquals("GtkBin", o.parent);
        assertEquals("GtkButton", o.cName);
        assertEquals("Button", o.blockName);
    }

    public final void testCantCreateThingFromNonTypeBlock() {
        Block[] blocks;
        Thing t = null;
        blocks = parser.parseData();

        assertFalse(blocks[1] instanceof TypeBlock);
        try {
            t = blocks[1].createThing();
            fail("Should have thrown UnsupportedOperationException");
        } catch (UnsupportedOperationException uoe) {
            // good
        }
        assertNull(t);
    }

    public final void testMethodReferenceToSelfInsertion() {
        Block[] results;
        MethodBlock block;

        results = parser.parseData();

        assertTrue(results[2] instanceof MethodBlock);

        block = (MethodBlock) results[2];

        assertTrue(block.parameters.length == 2);
        assertEquals("GtkButton*", block.parameters[0][0]);
        assertEquals("self", block.parameters[0][1]);
        assertEquals("const-gchar*", block.parameters[1][0]);
        assertEquals("label", block.parameters[1][1]);
    }

    public final void testIgnoreUnnecessaryBlocks() {
        Block[] results;

        results = parser.parseData();

        assertEquals(4, results.length);
    }
}
