/*
 * ValidateCodeOutput.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import junit.framework.TestCase;

import com.operationaldynamics.defsparser.DefsLineNumberReader;
import com.operationaldynamics.defsparser.DefsParser;
import com.operationaldynamics.driver.DefsFile;

/**
 * Acceptation tests for code generator.
 * 
 * <p>
 * The code in this class is not a Unit Test for codegen internals, but a
 * validator of what codegen does. It not validates how things are done, it
 * just ensure generated code is what we want.
 * 
 * <p>
 * The idea is to have a set to simple .defs files with the types, methods,
 * etc... we want to generate code, together with a hand-written template of
 * how the generated code should be. This class runs the code generator over
 * that set of .defs, checking that results are the same as we are expected.
 * 
 * <p>
 * This can help us to prevent regression, and also to have a place to put
 * mock-up code of what we want the code generator to produce.
 * 
 * <p>
 * The files need to be stored under files sub-package, with the same name and
 * extensions .defs for the .defs file, .jni for the expected JNI layer and
 * .trans for the expected translation layer. Note that you must add a
 * testXXX() method for each file you want to test.
 * 
 * @author Vreixo Formoso
 */
public class ValidateCodeOutput extends TestCase
{
    private static ClassLoader loader;

    static {
        loader = ValidateCodeOutput.class.getClassLoader();
    }

    public void setUp() {
        /* register some types */
        Thing.register(new ObjectThing("GtkBin*", "org.gnome.gtk", "GtkBin", "Bin"));
        Thing.register(new ObjectThing("GtkWidget*", "org.gnome.gtk", "GtkWidget", "Widget"));
        Thing.register(new FlagsThing("GtkFlags", "org.gnome.gtk", "GtkFlags", "Flags"));
        Thing.register(new EnumThing("GtkEnum", "org.gnome.gtk", "GtkEnum", "Enum"));
    }

    private void doTest(String name, boolean noTrans, boolean noJni) throws Exception {
        DefsLineNumberReader defs;
        PrintWriter trans, jni;
        DefsParser parser;
        DefsFile file;
        CharArrayWriter transWriter, jniWriter;
        BufferedReader reader;

        transWriter = new CharArrayWriter();
        jniWriter = new CharArrayWriter();

        InputStream str = loader.getResourceAsStream("com/operationaldynamics/codegen/files/" + name
                + ".defs");

        defs = new DefsLineNumberReader(new InputStreamReader(str), "test");

        parser = new DefsParser(defs);
        file = new DefsFile(parser.parseData());

        str.close();
        try {
            trans = new PrintWriter(transWriter);
            file.generateTranslationLayer(trans);
            assertFalse(noTrans);

            str = loader.getResourceAsStream("com/operationaldynamics/codegen/files/" + name + ".trans");
            reader = new BufferedReader(new InputStreamReader(str));
            StringBuffer buf = new StringBuffer();
            String s;
            while ((s = reader.readLine()) != null) {
                buf.append(s + '\n');
            }
            reader.close();
            str.close();
            assertEquals(buf.toString(), transWriter.toString());
        } catch (UnsupportedOperationException uoe) {
            assertTrue(noTrans);
        }
        try {
            jni = new PrintWriter(jniWriter);
            file.generateJniLayer(jni);
            assertFalse(noJni);

            str = loader.getResourceAsStream("com/operationaldynamics/codegen/files/" + name + ".jni");
            reader = new BufferedReader(new InputStreamReader(str));
            StringBuffer buf = new StringBuffer();
            String s;
            while ((s = reader.readLine()) != null) {
                buf.append(s + '\n');
            }
            reader.close();
            assertEquals(buf.toString(), jniWriter.toString());
        } catch (UnsupportedOperationException uoe) {
            assertTrue(noJni);
        }

    }

    /**
     * Test correct class definition for a GObject without methods
     */
    public void testObject() throws Exception {
        doTest("Object", false, false);
    }

    /**
     * Test correct class definition for an enum
     */
    public void testEnum() throws Exception {
        doTest("Enum", false, true);
    }

    /**
     * Test correct class definition for an flags
     */
    public void testFlags() throws Exception {
        doTest("Flags", false, false);
    }

    /**
     * test with a void constructor
     */
    public void testConstructor() throws Exception {
        doTest("Constructor", false, false);
    }

    /**
     * test with a constructor that takes a native parameter
     */
    public void testConstructorNative() throws Exception {
        doTest("ConstructorNative", false, false);
    }

    /**
     * test with a constructor that takes a String parameter
     */
    public void testConstructorString() throws Exception {
        doTest("ConstructorString", false, false);
    }

    /**
     * Test methods that take void and return void
     */
    public void testMethod() throws Exception {
        doTest("Method", false, false);
    }

    /**
     * Test methods that take native params and return void
     */
    public void testMethodWithNativeParams() throws Exception {
        doTest("MethodNativeParams", false, false);
    }

    /**
     * Test methods that return a native value (without parameters)
     */
    public void testMethodWithNativeReturn() throws Exception {
        doTest("MethodNativeReturn", false, false);
    }

    /**
     * Test methods that take string params and return void
     */
    public void testMethodWithStringParams() throws Exception {
        doTest("MethodStringParams", false, false);
    }

    /**
     * Test methods that return a string
     */
    public void testMethodWithStringReturn() throws Exception {
        doTest("MethodStringReturn", false, false);
    }

    /**
     * Test methods that take Object params and return void
     */
    public void testMethodWithObjectParams() throws Exception {
        doTest("MethodObjectParams", false, false);
    }

    /**
     * Test methods that return an Object
     */
    public void testMethodWithObjectReturn() throws Exception {
        doTest("MethodObjectReturn", false, false);
    }

    /**
     * Test methods that take Enum params and return void
     */
    public void testMethodWithEnumParams() throws Exception {
        doTest("MethodEnumParams", false, false);
    }

    /**
     * Test methods that return an Enum
     */
    public void testMethodWithEnumReturn() throws Exception {
        doTest("MethodEnumReturn", false, false);
    }

    /**
     * Test methods that take Flag params and return void
     */
    public void testMethodWithFlagParams() throws Exception {
        doTest("MethodFlagParams", false, false);
    }

    /**
     * Test correct class definition for virtuals
     */
    public void testVirtual() throws Exception {
        doTest("Virtual", false, false);
    }

    // virtuals non-void
    // name import collision
    
}
