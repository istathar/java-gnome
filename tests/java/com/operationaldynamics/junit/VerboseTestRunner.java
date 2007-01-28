/*
 * VerboseTestRunner.java
 * 
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 *
 * This class imported from ObjectiveAccounts accounting package where it was
 * originally deployed as GPL code in generic.ui.VerboseTestRunner
 */
package com.operationaldynamics.junit;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.textui.TestRunner;

/**
 * An extension of the standard JUnit textui TestRunner which displays the
 * name of the test being run. This class overrides the more obnoxious output
 * methods in TestRunner, and uses our VerboseResultPrinter to output the name
 * of the test being run instead of {.,F,E}
 * 
 * @author Andrew Cowie
 */
/*
 * We actually ignore most of TestRunner - we just use its suite extraction
 * method and its return constants.
 */
public class VerboseTestRunner extends TestRunner
{
    /**
     * Create an instance of our VerboseTestRunner wrapper. It uses the
     * default output stream (ie, System.out) but overrides JUnit's
     * ResultPrinter with our customized VerboseResultPrinter.
     */
    public VerboseTestRunner() {
        super();
    }

    /**
     * Execute the test suite, specifiying our VerboseResultPrinter wrapper
     * instead of TestRunner's use of the ordinary ResultPrinter.
     */
    /*
     * super.doRun() blabs out output about how long execution takes, which we
     * don't particularly care about, and we need to override the TestListener
     * (ie, ResultPrinter) used to output results - the whole point of the
     * exercise.
     */
    public TestResult doRun(Test suite) {
        TestResult result = new TestResult();
        result.addListener(new VerboseResultPrinter(true));

        suite.run(result);

        return result;
    }

    /**
     * VerboseTestRunner's main entry point. This call's TestRunner's
     * getTest() method to parse the command line argument to find a
     * Test{,Suite}, and then calls our doRun() to get a TestResult from
     * executing that Suite.
     * 
     * @param args
     *            VerboseTestRunner takes one (and only one) argument, the
     *            name of the Test{,Suite} to run. From {@link TestRunner}:
     *            expects the name of a TestCase class as argument. If this
     *            class defines a static <code>suite</code> method it will
     *            be invoked and the returned test is run. Otherwise all the
     *            methods starting with "test" having no arguments are run.
     */
    /*
     * This code adapted from JUnit's TestRunner.main(), trimming it to only
     * deal with a single argument, and folding in the code from
     * TestRunner.start()
     */
    public static void main(String args[]) {
        if (((args[0] == null) || (args[0].equals("")) || (args.length != 1))) {
            System.err.println("You need to specicy one argument: the name of the Test{,Suite} to run.");
            System.exit(EXCEPTION_EXIT);
        }

        VerboseTestRunner runner = new VerboseTestRunner();
        Test suite = null;

        try {
            try {
                suite = runner.getTest(args[0]);
            } catch (Exception e) {
                throw new Exception("Could not create test suite: " + e);
            }

            TestResult r = runner.doRun(suite);

            int pass = r.runCount() - r.errorCount() - r.failureCount();
            System.out.print(pass + " of " + r.runCount() + " passed");

            if (r.wasSuccessful()) {
                System.out.println();
                System.exit(SUCCESS_EXIT);
            } else {
                System.out.println("; " + r.failureCount() + " tests failed, " + r.errorCount()
                        + " unexpected errors.");
                System.exit(FAILURE_EXIT);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(EXCEPTION_EXIT);
        }
    }

    /**
     * Runs a single Test{,Suite} and collects its results. If you have a
     * static Test suite() this method can be used to run it directly. For
     * example, from a main() specific to a given class [hierarchy] you can
     * do:
     * 
     * <pre>
     * public static void main(String[] args) {
     *     VerboseTestRunner.run(suite());
     * }
     * </pre>
     * 
     * @return a TestResult indicating the outcome of running the unit tests,
     *         unless an unexpected ("error" in JUnit speak) Exception
     *         propegates out of a test fixture.
     */
    public static TestResult run(Test test) {
        VerboseTestRunner runner = new VerboseTestRunner();
        return runner.doRun(test);
    }
}
