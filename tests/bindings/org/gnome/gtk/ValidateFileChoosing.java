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

        assertEquals("Directory retrieved does not match that set programatically.", "/tmp",
                fcb.getCurrentFolder());
        /*
         * A failure here could be because the main loop did not iterate
         * sufficiently, or perhaps the target doesn't exist, or...
         */
    }
}
