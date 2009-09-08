/*
 * ValidateVectorIllustrations.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 * 
 * Inspired by org.gnome.gdk.ValidateImageHandling
 */
package org.gnome.rsvg;

import java.io.FileNotFoundException;

import org.gnome.gtk.GraphicalTestCase;

/**
 * @author Andrew Cowie
 */
public class ValidateVectorIllustrations extends GraphicalTestCase
{
    public final void testInitializing() {
        Rsvg.init();
    }

    public final void testLoadingFromFile() {
        try {
            new Handle("Linux_Tux.svg");
            fail("Should have complained not being able to find file");
        } catch (FileNotFoundException fnfe) {
            // good
        }

        try {
            new Handle("README");
            fail("Specified file not an image, should have thrown");
        } catch (RuntimeException re) {
            // good
        } catch (FileNotFoundException fnfe) {
            fail("Should have found non-image file");
        }

        try {
            new Handle("tests/bindings/org/gnome/rsvg/Linux_Tux.svg");
        } catch (FileNotFoundException fnfe) {
            fail("Target file should exist. Did someone move the test image?");
        }
    }

    public final void testHandleMethods() throws FileNotFoundException {
        final Handle handle;

        handle = new Handle("tests/bindings/org/gnome/rsvg/Linux_Tux.svg");

        handle.close();
    }
}
