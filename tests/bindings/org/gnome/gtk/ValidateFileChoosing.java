/*
 * ValidatePacking.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import java.io.File;
import java.io.IOException;

/**
 * Test the use of interface FileChooser in FileChooserButton.
 * 
 * @author Andrew Cowie
 */
public class ValidateFileChoosing extends TestCaseGtk
{
    public final void testCurrentFolder() {
        final FileChooserButton fcb;
        String dir = null;

        fcb = new FileChooserButton("Testing folder selection", FileChooserAction.OPEN);

        fcb.setCurrentFolder("/tmp");

        cycleMainLoop();

        dir = fcb.getCurrentFolder();
        assertEquals("Directory retrieved does not match that set programatically.", "/tmp", dir);
        /*
         * A failure here could be because the main loop did not iterate
         * sufficiently, or perhaps the target doesn't exist, or...
         */
    }

    public final void testSettingFilename() {
        final FileChooserDialog fcd;

        fcd = new FileChooserDialog("", null, FileChooserAction.OPEN);
        fcd.setCurrentFolder("/");
        cycleMainLoop();
        assertEquals("/", fcd.getCurrentFolder());

        assertTrue(fcd.setFilename("/etc/passwd"));

        cycleMainLoop();
        assertEquals("/etc", fcd.getCurrentFolder());
        assertEquals("/etc/passwd", fcd.getFilename());
    }

    public void testAbsolutePathEnforcement() throws IOException {
        final FileChooserDialog fcd;
        final FileChooserButton fcb;

        assertEquals("/etc/passwd", new File("/etc/init.d/../passwd").getCanonicalPath());

        fcd = new FileChooserDialog("", null, FileChooserAction.OPEN);
        fcd.setCurrentFolder("/etc");
        cycleMainLoop();
        assertEquals("/etc", fcd.getCurrentFolder());

        try {
            assertTrue(fcd.setFilename("shadow"));
            fail("Should have thrown Exception");
        } catch (IllegalArgumentException iae) {
            // good
        }

        fcb = new FileChooserButton("", FileChooserAction.OPEN);
        fcb.setCurrentFolder("/etc");
        cycleMainLoop();
        assertEquals("/etc", fcb.getCurrentFolder());

        try {
            assertTrue(fcb.setFilename("shadow"));
            fail("Should have thrown Exception");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }
}
