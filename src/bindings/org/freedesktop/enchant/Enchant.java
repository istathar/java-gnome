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

import java.io.File;
import java.io.FileNotFoundException;

import org.gnome.glib.Glib;

/**
 * Get a handle to an Enchant dictionary for spell checking.
 * 
 * <p>
 * Enchant is not itself a spell checking library; it is, rather a facade to
 * various common spelling mechanisms. It provides a simple and sufficient API
 * for doing spelling operations. Which actual back-end provider will be
 * employed depends on the system and user "ordering" files.
 * 
 * <h2>Usage</h2>
 * 
 * <p>
 * Enchant is straight-forward to use. For possibly misspelled
 * <code>word</code>, you can do
 * 
 * <pre>
 * Enchant.init();
 * 
 * dict = Enchant.requestDictionary(&quot;en_CA&quot;);
 * 
 * if (dict.check(word)) {
 *     return &quot;Spelled correctly!&quot;;
 * } else {
 *     possibles = dict.suggest(word);
 *     
 *     str.append(&quot;The word &quot;);
 *     str.append(word);
 *     str.append(&quot; was misspelled. You could correct it with one of:\n&quot;
 *     
 *     for (i = 0; i &lt; possibles.length; i++) {
 *         str.append(possibles[i]);
 *         str.append('\n');
 *     }
 *     
 *     return str.toString();
 * }
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 * @see <a href="http://www.abisource.com/projects/enchant/">Enchant home
 *      page</a>
 */
/*
 * This API could have been exposed as an init function returning a Broker,
 * and then calling the Broker method to get a Dictionary, but that doesn't
 * really seem to add anything to the experience.
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
     * the language code <code>en</code>, specifying a specific language
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

    /**
     * Get a Dictionary for the specified personal word list.
     * 
     * <p>
     * Word lists are simple files with one word per line. By creating a
     * Dictionary of a personal word list you can add words to a file that is
     * independent of a normal spelling engine back-end.
     * 
     * @since 4.0.14
     */
    /*
     * TODO Enchant has its own idea of error reporting, which we should check
     * as well.
     */
    public static Dictionary requestPersonalWordList(String filename) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(filename);
        }
        return EnchantBroker.requestPwlDict(defaultBroker, filename);
    }
}
