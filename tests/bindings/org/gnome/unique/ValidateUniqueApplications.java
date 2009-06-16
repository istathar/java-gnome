/*
 * ValidateUniqueApplications.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.unique;

import org.gnome.gtk.TestCaseGtk;

/**
 * Evaluate the behaviour of LibUnique
 */
public class ValidateUniqueApplications extends TestCaseGtk
{
    public final void testInstantiateApplicationObject() {
        final Application app;

        app = new Application("", null);
    }
}
