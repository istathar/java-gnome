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
package com.operationaldynamics.defsparser;

import java.io.BufferedReader;
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

    BufferedReader in;

    public void setUp() throws IOException {
        in = new BufferedReader(new StringReader(inputDefsData));

        parser = new DefsParser(in);
    }

    public void tearDown() throws IOException {
        in.close();
    }

    public final void testInputStreamToStanzas() {
        int i;

        for (i = 0; parser.readNextStanza(); i++)
            ;

        assertEquals(4, i);
    }

    public final void testObjectBlockCreated() throws ParseException {
        Block result;
        ObjectBlock o;

        parser.readNextStanza();
        result = parser.parseStanza();

        assertTrue(result instanceof ObjectBlock);

        o = (ObjectBlock) result;

        assertEquals("Gtk", o.inModule);
        assertEquals("GtkBin", o.parent);
        assertEquals("GtkButton", o.cName);
//        assertNull(o.ofObject);
        assertEquals("Button", o.blockName);
    }
    
    //This test is not needed now
//    public final void testCantCreateThingFromNonTypeBlock() {
//        Block[] blocks;
//        Thing t = null;
//        blocks = parser.parseData();        
//        
//        assertFalse(blocks[1] instanceof TypeBlock);
//        try {
//            t = blocks[1].createThing();
//            fail("Should have thrown UnsupportedOperationException");
//        } catch (UnsupportedOperationException uoe) {
//            // good
//        }
//        assertNull(t);
//    }

}
