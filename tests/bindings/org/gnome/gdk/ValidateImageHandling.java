/*
 * ValidateImageHandling.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gdk;

import java.io.FileNotFoundException;

import org.gnome.gtk.TestCaseGtk;

/**
 * @author Andrew Cowie
 */
public class ValidateImageHandling extends TestCaseGtk
{
    public final void testPixbufFromFile() {
        try {
            new Pixbuf("foo.jpg");
            fail("Should have complained not being able to find file");
        } catch (FileNotFoundException fnfe) {
            // good
        }

        try {
            new Pixbuf("README");
            fail("Specified file not an image, should have thrown");
        } catch (RuntimeException re) {
            // good
        } catch (FileNotFoundException fnfe) {
            fail("Should have found non-image file");
        }

        try {
            new Pixbuf("web/public/images/java-gnome_JavaDocLogo.png");
        } catch (FileNotFoundException fnfe) {
            fail("Target file should exist. Did someone refactor the website?");
        }
    }
}
