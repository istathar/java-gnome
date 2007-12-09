/*
 * ValidateConstants.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.gtk.TestCaseGtk;

/**
 * Exercise the handling of the internals of our Plumbing infrastructure for
 * turning Cairo sub types into concrete Java objects.
 * 
 * @author Andrew Cowie
 */
public class ValidateCairoInternals extends TestCaseGtk
{
    public final void testPatternProxyCreation() {
        final Context cr;
        final ImageSurface s;
        final Pattern p;

        s = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(s);

        /*
         * Create some arbitrary data so the thing doesn't completely do the 0
         * case escape.
         */

        cr.setSourceRGBA(0.0, 0.0, 1.0, 0.8);
        cr.moveTo(10, 10);
        cr.lineTo(20, 5);
        cr.stroke();

        /*
         * Call a method that returns an abstract type
         */

        p = new SurfacePattern(s);

        /*
         * And now I would gladly put a test that caused
         * Plumbing.createPattern() to be used, except that I cannot find a
         * non-constructor function that return a Pattern!
         */
        
        // TODO
    }
}
