/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.freedesktop.bindings.Debug;
import org.freedesktop.bindings.ValidateEnvironment;
import org.freedesktop.bindings.ValidateInternationalization;
import org.freedesktop.bindings.Version;
import org.freedesktop.cairo.ValidateCairoContext;
import org.freedesktop.cairo.ValidateCairoInternals;
import org.freedesktop.cairo.ValidateDrawingToFile;
import org.freedesktop.enchant.ValidateEnchantInternals;
import org.freedesktop.icons.ValidateIconItems;
import org.gnome.gdk.ValidateImageHandling;
import org.gnome.gdk.ValidateKeyboardHandling;
import org.gnome.gdk.ValidateScreensAndDisplays;
import org.gnome.glib.ValidateConstants;
import org.gnome.glib.ValidateGFileMethods;
import org.gnome.glib.ValidateGListMethods;
import org.gnome.glib.ValidateMemoryManagement;
import org.gnome.glib.ValidateReferenceCounting;
import org.gnome.glib.ValidateUtilityFunctions;
import org.gnome.gtk.ValidateArrow;
import org.gnome.gtk.ValidateAssistant;
import org.gnome.gtk.ValidateComboBox;
import org.gnome.gtk.ValidateEntry;
import org.gnome.gtk.ValidateEntryCompletion;
import org.gnome.gtk.ValidateFileChoosing;
import org.gnome.gtk.ValidateGlobalSettings;
import org.gnome.gtk.ValidateIconView;
import org.gnome.gtk.ValidateInputMethods;
import org.gnome.gtk.ValidateLinkBehaviour;
import org.gnome.gtk.ValidateNotebookBehaviour;
import org.gnome.gtk.ValidateOutParameters;
import org.gnome.gtk.ValidatePacking;
import org.gnome.gtk.ValidatePrinting;
import org.gnome.gtk.ValidateProperties;
import org.gnome.gtk.ValidateRadioThing;
import org.gnome.gtk.ValidateResponseType;
import org.gnome.gtk.ValidateScrolling;
import org.gnome.gtk.ValidateSignalEmission;
import org.gnome.gtk.ValidateSnapshotUtilities;
import org.gnome.gtk.ValidateStockItems;
import org.gnome.gtk.ValidateStyleContext;
import org.gnome.gtk.ValidateSwitch;
import org.gnome.gtk.ValidateTextBuffer;
import org.gnome.gtk.ValidateTextViewBorderWindows;
import org.gnome.gtk.ValidateTextViewProperties;
import org.gnome.gtk.ValidateTreeModel;
import org.gnome.gtk.ValidateTreeModelFilter;
import org.gnome.gtk.ValidateTreeStore;
import org.gnome.gtk.ValidateTreeView;
import org.gnome.gtk.ValidateUnicode;
import org.gnome.gtk.ValidateUniqueApplications;
import org.gnome.pango.ValidatePangoAttributeUsage;
import org.gnome.pango.ValidatePangoTextRendering;
import org.gnome.pango.ValidatePangoWrapBehaviour;
import org.gnome.rsvg.ValidateVectorIllustrations;
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

        TestSuite suite = new TestSuite("All Unit Tests for java-gnome " + Version.getAPI());

        suite.addTestSuite(ValidateUtilityMethods.class);
        suite.addTestSuite(ValidateBlockUsage.class);
        suite.addTestSuite(ValidateDefsParsing.class);
        suite.addTestSuite(ValidateThingUsage.class);
        suite.addTestSuite(ValidateEnvironment.class);
        suite.addTestSuite(ValidateInternationalization.class);
        suite.addTestSuite(ValidateReferenceCounting.class);
        suite.addTestSuite(ValidateMemoryManagement.class);
        suite.addTestSuite(ValidateGFileMethods.class);
        suite.addTestSuite(ValidateGListMethods.class);
        suite.addTestSuite(ValidateConstants.class);
        suite.addTestSuite(ValidateProperties.class);
        suite.addTestSuite(ValidateUtilityFunctions.class);
        suite.addTestSuite(ValidateSignalEmission.class);
        suite.addTestSuite(ValidateScreensAndDisplays.class);
        suite.addTestSuite(ValidateKeyboardHandling.class);
        suite.addTestSuite(ValidateImageHandling.class);
        suite.addTestSuite(ValidateGlobalSettings.class);
        suite.addTestSuite(ValidateCairoInternals.class);
        suite.addTestSuite(ValidateCairoContext.class);
        suite.addTestSuite(ValidateDrawingToFile.class);
        suite.addTestSuite(ValidateOutParameters.class);
        suite.addTestSuite(ValidatePacking.class);
        suite.addTestSuite(ValidateNotebookBehaviour.class);
        suite.addTestSuite(ValidateFileChoosing.class);
        suite.addTestSuite(ValidateStockItems.class);
        suite.addTestSuite(ValidateIconItems.class);
        suite.addTestSuite(ValidateResponseType.class);
        suite.addTestSuite(ValidateTreeModel.class);
        suite.addTestSuite(ValidateTreeStore.class);
        suite.addTestSuite(ValidateTreeModelFilter.class);
        suite.addTestSuite(ValidateTreeView.class);
        suite.addTestSuite(ValidateIconView.class);
        suite.addTestSuite(ValidateScrolling.class);
        suite.addTestSuite(ValidateComboBox.class);
        suite.addTestSuite(ValidateLinkBehaviour.class);
        suite.addTestSuite(ValidateEntry.class);
        suite.addTestSuite(ValidateEntryCompletion.class);
        suite.addTestSuite(ValidateSnapshotUtilities.class);
        suite.addTestSuite(ValidateAssistant.class);
        suite.addTestSuite(ValidateTextBuffer.class);
        suite.addTestSuite(ValidateUnicode.class);
        suite.addTestSuite(ValidateInputMethods.class);
        suite.addTestSuite(ValidateTextViewProperties.class);
        suite.addTestSuite(ValidateTextViewBorderWindows.class);
        suite.addTestSuite(ValidateArrow.class);
        suite.addTestSuite(ValidateRadioThing.class);
        suite.addTestSuite(ValidateSwitch.class);
        suite.addTestSuite(ValidateStyleContext.class);
        suite.addTestSuite(ValidatePangoTextRendering.class);
        suite.addTestSuite(ValidatePangoAttributeUsage.class);
        suite.addTestSuite(ValidatePangoWrapBehaviour.class);
        suite.addTestSuite(ValidateEnchantInternals.class);
        suite.addTestSuite(ValidatePrinting.class);
        suite.addTestSuite(ValidateSourceView.class);
        suite.addTestSuite(ValidateUniqueApplications.class);
        suite.addTestSuite(ValidateVectorIllustrations.class);

        return suite;
    }
}
