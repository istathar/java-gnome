/*
 * Enchant.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.enchant;

import org.gnome.glib.Glib;

/**
 * Get a handle to an Enchant dictionary for spell checking.
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 * @see <a href="http://www.abisource.com/projects/enchant/">Enchant home
 *      page</a>
 */
public final class Enchant extends Glib
{
    private Enchant() {}

    private static Broker defaultBroker;

    /**
     * @since 4.0.14
     */
    /*
     * Here, or Broker.init()?
     */
    public static void init() {
        if (defaultBroker == null) {
            defaultBroker = new Broker();
        }
    }

    static Broker getDefault() {
        return defaultBroker;
    }

    /**
     * Get a Dictionary for the specified language.
     * 
     * <p>
     * Languages are indicated in a locale-like form; while you can use just
     * the langage code <code>en</code>, specifiying a specific language
     * variant such as <code>en_UK</code> or <code>fr_CA</code> is preferred.
     * 
     * <p>
     * Returns <code>null</code> if no suitable dictionary was found.
     * 
     * @since 4.0.14
     */
    public static Dictionary requestDictionary(String lang) {
        return EnchantBroker.requestDict(defaultBroker, lang);
    }

}
