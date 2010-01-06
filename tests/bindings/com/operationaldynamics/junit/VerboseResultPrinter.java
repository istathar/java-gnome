/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
package com.operationaldynamics.junit;

/*
 * This class imported from ObjectiveAccounts accounting package where it was
 * originally deployed as GPL code in generic.ui.VerboseResultPrinter
 */

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freedesktop.bindings.Debug;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;

import com.operationaldynamics.ui.TextOutput;

/**
 * Output the test by test results of running a Test, printing the name of
 * each test along the way. This replaces the default notion of
 * junit.textui.ResultPrinter with an equivalent implementation of
 * TestListener. The various callbacks here get called as test cases get
 * started and then succeed, fail, or error. We use our
 * {@link generic.ui.TextOutput} as a superclass to get the various useful
 * output routines there. Instantiated by our
 * {@link generic.junit.VerboseTestRunner}.
 * 
 * @author Andrew Cowie
 */
public class VerboseResultPrinter extends TextOutput implements TestListener
{
    private PrintWriter out = null;

    private String currentClass = null;

    private boolean failed;

    private boolean haltOnBug;

    /**
     * @param haltOnBug
     *            if the program should stop if a failure or error is
     *            encountered
     */
    public VerboseResultPrinter(boolean haltOnBug) {
        /*
         * Calling super.toOutput(PrintStream) will wrap the PrintStream with
         * a PrintWriter and then in turn call this.toOutput(PrintWriter).
         * Convoluted perhaps, but might as well as it uses the abstract
         * method we have to implement anyway.
         */
        super.toOutput(System.out);
        this.haltOnBug = haltOnBug;
    }

    /**
     * Implements abstract toOutput() from TextOutput. We don't use this as
     * the entry point in the way that the real TextOutput subclasses do; this
     * just sets stdout to the output stream.
     */
    public void toOutput(PrintWriter out) {
        this.out = out;
    }

    public String filterTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);

        String trace = stringWriter.toString();

        Pattern lineNumber = Pattern.compile(currentClass + ".*\\((\\w+\\.java:\\d+)\\)");
        Matcher m = lineNumber.matcher(trace);
        if (m.find()) {
            // group 0 is the entire match aka group()
            return m.group(1);
        } else {
            return "(No line number available)";
        }

    }

    public void addError(Test test, Throwable t) {
        if (!Debug.MEMORY_MANAGEMENT) {
            out.println(pad("error", 10, LEFT));
        }
        out.println();
        out.println("Encoutered an error at " + filterTrace(t));
        out.println("The following exception was thrown:\n");
        out.println("   " + t.toString());
        out.println("\nNormally exceptions in unit tests are trapped and reported as failures;\n"
                + "this was unexpected. It could be a bug or something deeper with your\n"
                + "environment or setup.");
        out.println();
        if (haltOnBug) {
            System.exit(VerboseTestRunner.EXCEPTION_EXIT);
        }
        failed = true;
    }

    public void addFailure(Test test, AssertionFailedError t) {
        if (!Debug.MEMORY_MANAGEMENT) {
            out.println(pad("failed", 10, LEFT));
        }
        out.println();
        out.println("Unit test failed at " + filterTrace(t) + ",");
        String msg = t.getMessage();
        if (msg == null) {
            out.print("[no reason given by test case]");
        } else {
            out.print("\"" + msg + "\"");
        }
        out.println("\n");
        if (haltOnBug) {
            System.exit(VerboseTestRunner.FAILURE_EXIT);
        }
        failed = true;
    }

    public void endTest(Test test) {
        if (failed) {
            return;

        }
        if (Debug.MEMORY_MANAGEMENT) {
            return;
        }
        out.println(pad("ok", 10, LEFT));
    }

    private static Pattern regex = Pattern.compile("\\(.*\\)");

    public void startTest(Test test) {
        failed = false;
        String testClassName = test.getClass().getName();

        /*
         * If we're in a new class, then switch our reference and print the
         * new name.
         */
        if ((currentClass == null) || (!(currentClass.equals(testClassName)))) {
            currentClass = testClassName;
            out.println(currentClass);
        }

        /*
         * Test.toString returns a String of the form
         * "testCaseName(java.package.Class)". So we use a regular expression
         * to nuke out the characters between the brackers. We then use
         * TextOutput's static routines to trim the output to terminal width
         * less 10 but pad it out to that width. We then let the other
         * callbacks finish the line.
         */
        Matcher m = regex.matcher(test.toString());
        String testCaseName = chomp(" - " + m.replaceFirst("()"), COLUMNS - 10);
        if (Debug.MEMORY_MANAGEMENT) {
            out.println(testCaseName);
        } else {
            out.print(pad(testCaseName, COLUMNS - 10, LEFT));
            out.flush();
        }
    }
}
