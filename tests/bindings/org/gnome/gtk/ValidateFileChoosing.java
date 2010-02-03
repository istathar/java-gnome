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
package org.gnome.gtk;

import java.io.File;
import java.io.IOException;

/**
 * Test the use of interface FileChooser in FileChooserButton.
 * 
 * @author Andrew Cowie
 */
public class ValidateFileChoosing extends GraphicalTestCase
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
        String retreived;

        fcd = new FileChooserDialog("", null, FileChooserAction.OPEN);
        cycleMainLoop();
        fcd.setCurrentFolder("/");
        cycleMainLoop();
        assertEquals("/", fcd.getCurrentFolder());

        assertTrue(fcd.setFilename("/etc/passwd"));

        cycleMainLoop();
        assertEquals("/etc", fcd.getCurrentFolder());

        do {
            cycleMainLoop();
            retreived = fcd.getFilename();
        } while (retreived == null);
        assertEquals("/etc/passwd", retreived);
    }

    public void testAbsolutePathEnforcement() throws IOException {
        final FileChooserDialog fcd;
        final FileChooserButton fcb;

        assertEquals("/etc/passwd", new File("/etc/../etc/passwd").getCanonicalPath());

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
