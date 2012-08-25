/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
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
     * See {@link Enchant#existsDictionary(String) existsDictionary()} for
     * discussion of valid language values. You probably want to call that if
     * you're considering user input values.
     * 
     * <p>
     * Returns <code>null</code> if no suitable dictionary was found.
     * 
     * @since 4.0.14
     */
    public static Dictionary requestDictionary(String lang) {
        if (lang.equals("")) {
            throw new IllegalArgumentException();
        }
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

    /**
     * Does a dictionary exist for the given "language"?
     * 
     * <p>
     * Languages are indicated in a locale-like form; while you can use just
     * the language code <code>en</code>, specifying a specific language
     * variant such as <code>"en_UK"</code> or <code>"fr_CA"</code> is
     * preferred.
     * 
     * @since 4.0.17
     */
    public static boolean existsDictionary(String lang) {
        final int result;

        result = EnchantBroker.dictExists(defaultBroker, lang);

        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get a list of available dictionaries as known to Enchant. This returns
     * an unsorted array of Strings of the form:
     * 
     * <pre>
     * en
     * en_AU
     * en_CA
     * en_GB
     * en_US
     * en_ZA
     * es
     * fr_BE
     * fr_CA
     * fr_CH
     * fr_FR
     * fr_LU
     * fr_MC
     * </pre>
     * 
     * (that was the list on a computer with English, French, and Spanish
     * dictionaries installed via packages <code>language-support-en</code>,
     * <code>language-support-fr</code>, <code>language-support-es</code>
     * respectively on, in this case, Ubuntu Linux).
     * 
     * <p>
     * You don't necessarily need to callt this function. You can test for the
     * existance of a dictionary with {@link #existsDictionary(String)
     * Enchant.existsDictionary()}, or even just get on directly with loading
     * a dictionary with {@link #requestDictionary(String)
     * requestDictionary()}.
     * 
     * <p>
     * If you are using the results of this funciton to create a list in a
     * user interface, you'll probably want to present the language and
     * country names translated. Use
     * {@link org.freedesktop.bindings.Internationalization#translateLanguageName(String)
     * Internationalization.translateLanguageName()} and
     * {@link org.freedesktop.bindings.Internationalization#translateCountryName(String)
     * Internationalization.translateCountryName()} although you'll have to
     * look up the proper ISO 639 and ISO 3166 names in
     * <code>/usr/share/xml/iso-codes/iso_{639,3166}.xml</code> first.
     * 
     * @since 4.0.17
     */
    public static String[] listDictionaries() {
        return EnchantBrokerOverride.listDicts(defaultBroker);
    }
}
