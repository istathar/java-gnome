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
         * Do something arbitrary.
         */

        cr.setSourceRGBA(0.0, 0.0, 1.0, 0.8);

        /*
         * Call a method that returns an abstract type, in this case a
         * Pattern.
         */

        p = cr.getSource();
        assertNotNull(p);
        assertTrue(p instanceof SolidPattern);

        /*
         * Note: The preceeding assertion doesn't have to be a SolidPattern.
         * It just needs to be something concrete, and more to the point
         * something that we didn't already have a Proxy for. Obviously we
         * can't have instantiated a non-concrete class, but this makes the
         * point clearly that we got something out of it that we didn't have
         * on the Java side before.
         */
    }
}
