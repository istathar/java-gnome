/*
 * ValidateInputMethods.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test GTK's input methods APIs.
 * 
 * @author Andrew Cowie
 */
/*
 * This really isn't much of a test class yet. In order to exercise the any of
 * the real input method API we will likely need to be able to simulate GDK
 * Events, specifically EventKey. That's not possible as things stand now.
 */
public class ValidateInputMethods extends GraphicalTestCase
{
    public final void testConstructors() {
        InputMethod im;

        im = new SimpleInputMethod();
        assertTrue(im instanceof SimpleInputMethod);

        im = new MulticontextInputMethod();
        assertTrue(im instanceof MulticontextInputMethod);
    }
}
