/*
 * UnitTests.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import junit.framework.Test;
import junit.framework.TestSuite;

import org.freedesktop.bindings.EnvironmentTest;
import org.gnome.gtk.PropertiesTest;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.PackingTest;

import com.operationaldynamics.junit.VerboseTestRunner;

/**
 * Top level test harness to run all JUnit unit test cases that ship with
 * java-gnome. FUTURE: How will this extend when functional tests arrive on
 * the scene?
 * 
 * @author Andrew Cowie
 * @since 4.0.2
 */
public class UnitTests
{
    /**
     * Entry point from the command line, of course. Uses VerboseTestRunner to
     * do a more pretty printing of the test output.
     * 
     * @param args
     */
    public static void main(String[] args) {
        VerboseTestRunner.run(suite(args));
    }

    /**
     * Entry point used by Eclipse's built in JUnit TestRunner
     */
    public static Test suite() {
        return suite(null);
    }

    /*
     * It is necessary to initialize GTK (and load the native library while
     * we're at it) regardless of whether we're called via main() or via
     * Eclipse's TestRunner. TODO: what happens if you want to run an
     * individual test case? Answer: move the Gtk.init() to a static {} in a
     * superclass of all GUI test cases.
     */
    private static Test suite(String[] args) {
        Gtk.init(args);

        TestSuite suite = new TestSuite("All Unit Tests for java-gnome 4.0");

        suite.addTestSuite(EnvironmentTest.class);
        suite.addTestSuite(PropertiesTest.class);
        suite.addTestSuite(PackingTest.class);

        return suite;
    }
}
