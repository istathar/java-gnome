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
public class ValidateInputMethods extends TestCaseGtk
{
    public final void testLatin1Supplement() {
        IMContext context;

        context = new IMContextSimple();

        assertTrue(context instanceof IMContextSimple);
    }
}
