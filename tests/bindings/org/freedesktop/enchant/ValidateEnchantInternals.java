/*
 * ValidateEnchantInternals.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.enchant;

import org.gnome.gtk.GraphicalTestCase;

/**
 * @author Andrew Cowie
 */
public class ValidateEnchantInternals extends GraphicalTestCase
{
    public final void testDictionaryCreation() {
        Enchant.init();

        assertNotNull(Enchant.getDefault());
    }
}
