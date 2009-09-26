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
    public final void testLibraryInitialization() {
        Enchant.init();

        assertNotNull(Enchant.getDefault());
    }

    public final void testDictionaryCreation() {
        final Dictionary dict;

        assertNotNull(Enchant.getDefault());

        dict = Enchant.requestDictionary("en");
        assertNotNull(dict);
    }

    public final void testCheckWord() {
        final Dictionary dict;
        int result;

        dict = Enchant.requestDictionary("en");

        result = dict.check("hello");
        assertTrue(result == 0);

        result = dict.check("insain");
        assertTrue(result > 0);
    }
}
