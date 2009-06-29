/*
 * UnitTests.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import junit.framework.Test;
import junit.framework.TestSuite;

import org.freedesktop.bindings.Debug;
import org.freedesktop.bindings.ValidateEnvironment;
import org.freedesktop.bindings.ValidateInternationalization;
import org.freedesktop.cairo.ValidateCairoContext;
import org.freedesktop.cairo.ValidateCairoInternals;
import org.freedesktop.cairo.ValidateDrawingToFile;
import org.gnome.gdk.ValidateImageHandling;
import org.gnome.gdk.ValidateKeyboardHandling;
import org.gnome.gdk.ValidateScreensAndDisplays;
import org.gnome.glib.ValidateConstants;
import org.gnome.glib.ValidateGListMethods;
import org.gnome.glib.ValidateMemoryManagement;
import org.gnome.glib.ValidateReferenceCounting;
import org.gnome.gtk.TestCaseGtk;
import org.gnome.gtk.ValidateArrow;
import org.gnome.gtk.ValidateAssistant;
import org.gnome.gtk.ValidateComboBox;
import org.gnome.gtk.ValidateEntryCompletion;
import org.gnome.gtk.ValidateFileChoosing;
import org.gnome.gtk.ValidateIconView;
import org.gnome.gtk.ValidateNotebookBehaviour;
import org.gnome.gtk.ValidateOutParameters;
import org.gnome.gtk.ValidatePacking;
import org.gnome.gtk.ValidatePrinting;
import org.gnome.gtk.ValidateProperties;
import org.gnome.gtk.ValidateResponseType;
import org.gnome.gtk.ValidateSignalEmission;
import org.gnome.gtk.ValidateSnapshotUtilities;
import org.gnome.gtk.ValidateStockItems;
import org.gnome.gtk.ValidateTextBuffer;
import org.gnome.gtk.ValidateTextViewBorderWindows;
import org.gnome.gtk.ValidateTextViewProperties;
import org.gnome.gtk.ValidateTextViewSpelling;
import org.gnome.gtk.ValidateTreeModel;
import org.gnome.gtk.ValidateTreeModelFilter;
import org.gnome.gtk.ValidateTreeStore;
import org.gnome.gtk.ValidateTreeView;
import org.gnome.pango.ValidatePangoAttributeUsage;
import org.gnome.pango.ValidatePangoTextRendering;
import org.gnome.sourceview.ValidateSourceView;

import com.operationaldynamics.codegen.ValidateThingUsage;
import com.operationaldynamics.codegen.ValidateUtilityMethods;
import com.operationaldynamics.defsparser.ValidateBlockUsage;
import com.operationaldynamics.defsparser.ValidateDefsParsing;
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
     */
    public static void main(String[] args) {
        VerboseTestRunner.run(suite(args));

        if (Debug.MEMORY_MANAGEMENT) {
            System.out.println("Done!");
            System.out.flush();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // ignore
        }
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // ignore
        }

        if (Debug.MEMORY_MANAGEMENT) {
            System.out.println("Exiting.");
        }
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
     * Eclipse's TestRunner. Note that all our TestCases actually extend
     * TestCaseGtk which allows them to be used standalone and still have Gtk
     * available.
     */
    private static Test suite(String[] args) {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);

        TestCaseGtk.init(args);

        TestSuite suite = new TestSuite("All Unit Tests for java-gnome 4.0");

        suite.addTestSuite(ValidateUtilityMethods.class);
        suite.addTestSuite(ValidateBlockUsage.class);
        suite.addTestSuite(ValidateDefsParsing.class);
        suite.addTestSuite(ValidateThingUsage.class);
        suite.addTestSuite(ValidateEnvironment.class);
        suite.addTestSuite(ValidateInternationalization.class);
        suite.addTestSuite(ValidateReferenceCounting.class);
        suite.addTestSuite(ValidateMemoryManagement.class);
        suite.addTestSuite(ValidateGListMethods.class);
        suite.addTestSuite(ValidateConstants.class);
        suite.addTestSuite(ValidateProperties.class);
        suite.addTestSuite(ValidateSignalEmission.class);
        suite.addTestSuite(ValidateScreensAndDisplays.class);
        suite.addTestSuite(ValidateKeyboardHandling.class);
        suite.addTestSuite(ValidateImageHandling.class);
        suite.addTestSuite(ValidateCairoInternals.class);
        suite.addTestSuite(ValidateCairoContext.class);
        suite.addTestSuite(ValidateDrawingToFile.class);
        suite.addTestSuite(ValidateOutParameters.class);
        suite.addTestSuite(ValidatePacking.class);
        suite.addTestSuite(ValidateNotebookBehaviour.class);
        suite.addTestSuite(ValidateFileChoosing.class);
        suite.addTestSuite(ValidateStockItems.class);
        suite.addTestSuite(ValidateResponseType.class);
        suite.addTestSuite(ValidateTreeModel.class);
        suite.addTestSuite(ValidateTreeStore.class);
        suite.addTestSuite(ValidateTreeModelFilter.class);
        suite.addTestSuite(ValidateTreeView.class);
        suite.addTestSuite(ValidateIconView.class);
        suite.addTestSuite(ValidateComboBox.class);
        suite.addTestSuite(ValidateSnapshotUtilities.class);
        suite.addTestSuite(ValidateAssistant.class);
        suite.addTestSuite(ValidateTextBuffer.class);
        suite.addTestSuite(ValidateTextViewProperties.class);
        suite.addTestSuite(ValidateTextViewBorderWindows.class);
        suite.addTestSuite(ValidateTextViewSpelling.class);
        suite.addTestSuite(ValidateArrow.class);
        suite.addTestSuite(ValidatePangoTextRendering.class);
        suite.addTestSuite(ValidatePangoAttributeUsage.class);
        suite.addTestSuite(ValidatePrinting.class);
        suite.addTestSuite(ValidateSourceView.class);
        suite.addTestSuite(ValidateEntryCompletion.class);

        return suite;
    }
}
