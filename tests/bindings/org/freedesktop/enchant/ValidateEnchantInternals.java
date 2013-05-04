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
 */
package org.freedesktop.enchant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.gtk.GraphicalTestCase;

/**
 * Exercise the Enchant spelling facade API.
 * 
 * @author Andrew Cowie
 */
public class ValidateEnchantInternals extends GraphicalTestCase
{
    public final void setUp() {
        Enchant.init();
    }

    public final void testLibraryInitialization() {
        assertNotNull(Enchant.getDefault());
    }

    /*
     * This is probably the worst test in here. How can we know what
     * dictionaries are installed? In any event, this at least ensures the
     * code path in the Override is exercised.
     */
    public final void testListAllDictionaries() {
        final String[] list;

        list = Enchant.listDictionaries();

        assertTrue(list.length > 0);

        for (String tag : list) {
            if (tag.equals("en") || tag.startsWith("en_")) {
                return;
            }
        }
        fail("Needed to find at least \"en\" as a dictionary!");
    }

    public final void testDictionaryKnownToExist() {
        final boolean result;

        result = Enchant.existsDictionary("en") || Enchant.existsDictionary("en_GB")
                || Enchant.existsDictionary("en_US");
        assertTrue(result);
    }

    public final void testDictionaryKnownNotToExist() {
        final boolean result;

        result = Enchant.existsDictionary("klingon");
        assertFalse(result);
    }

    /*
     * Bit silly, but do all dictionaries actually exist?
     */
    public final void testAllDictionariesExist() {
        final String[] list;
        boolean result;

        list = Enchant.listDictionaries();

        for (String tag : list) {
            result = Enchant.existsDictionary(tag);
            assertTrue(result);
        }
    }

    public final void testDictionaryCreation() {
        final Dictionary dict;

        assertNotNull(Enchant.getDefault());

        dict = Enchant.requestDictionary("en");
        assertNotNull(dict);
    }

    public final void testCreateDictionaryUnspecified() {
        assertNotNull(Enchant.getDefault());

        try {
            Enchant.requestDictionary("");
            fail("Can't request a Dictionary with an empty language tag");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }

    public final void testCreateInvalidDictionary() {
        final Dictionary dict;

        assertNotNull(Enchant.getDefault());

        dict = Enchant.requestDictionary("klingon");
        assertNull(dict);
    }

    public final void testCheckWord() {
        final Dictionary dict;
        boolean result;

        dict = Enchant.requestDictionary("en");

        result = dict.check("hello");
        assertTrue(result);

        result = dict.check("insain");
        assertFalse(result);
    }

    public final void testSuggestionsWordCorrect() {
        final Dictionary dict;
        String[] list;
        int i, count;

        dict = Enchant.requestDictionary("en");

        /*
         * Dramatically complicating matters, if the word is correct, you can
         * still get suggestions.
         */

        list = dict.suggest("catamaran");
        assertTrue(list.length > 0);

        count = 0;
        for (i = 0; i < list.length; i++) {
            if (list[i].equals("catamaran")) {
                count++;
            }
        }
        assertEquals("Our correctly spelt word should be a suggestion!", 1, count);
    }

    public final void testSuggestionsWordIncorrect() {
        final Dictionary dict;
        String[] list;
        int i, count;

        dict = Enchant.requestDictionary("en");

        list = dict.suggest("katamaran");
        assertTrue(list.length > 0);

        count = 0;
        for (i = 0; i < list.length; i++) {
            if (list[i].equals("catamaran")) {
                count++;
            }
        }
        assertEquals("Should only be one suggestion correctly spelling our word!", 1, count);
    }

    public final void testSuggestionsWordUnknown() {
        final String bogus;
        final Dictionary dict;
        String[] list;
        boolean result;

        dict = Enchant.requestDictionary("en");

        /*
         * Finding a word that really has no suggestions is tough. Good old
         * supercalifragilisticexpialidocious would have been good (it offered
         * no suggestions in gedit when writing this test) but it's still a
         * real word. Try this one.
         */

        bogus = "metakathamrthamorosishmockinggutgoberlize";

        result = dict.check(bogus);

        if (result) {
            fail("Need a better word to use, one known not to result in any suggestions");
        }

        list = dict.suggest(bogus);
        assertNull(list);
    }

    public final void testAddingCustomWord() {
        final String bogus;
        final Dictionary dict;
        boolean result;

        dict = Enchant.requestDictionary("en");

        /*
         * Something surely not in anyone's word list:
         */

        bogus = "bindings_java_spell_checking_test";

        result = dict.check(bogus);
        assertFalse(result);

        dict.add(bogus);

        result = dict.check(bogus);
        assertTrue(result);

        dict.remove(bogus);

        result = dict.check(bogus);
        assertFalse(result);
    }

    public final void testPersonalWordList() throws IOException {
        File target;
        Dictionary dict;

        Enchant.init();

        dict = null;
        try {
            dict = Enchant.requestPersonalWordList("");
            fail("Should have thrown FileNotFoundException");
            return;
        } catch (FileNotFoundException fnfe) {
            // good
        }

        target = new File("tmp/personalWordList.dic");
        if (target.exists()) {
            target.delete();
        }
        target.createNewFile();

        try {
            dict = Enchant.requestPersonalWordList(target.getAbsolutePath());
        } catch (FileNotFoundException fnfe) {
            fail("Why not found?");
        }

        assertFalse(dict.check("jazz"));

        dict.add("jazz");
        assertTrue(dict.check("jazz"));

        dict.remove("jazz");
        assertFalse(dict.check("jazz"));
    }

    public final void testWordsWithPunctuation() {
        final Dictionary dict;
        boolean result;

        dict = Enchant.requestDictionary("en");

        /*
         * Something surely not in anyone's word list:
         */

        result = dict.check("system");
        assertTrue(result);

        result = dict.check("system.");
        assertTrue(result);

        result = dict.check("systematic");
        assertTrue(result);

        result = dict.check("system.atic");
        assertFalse(result);
    }

    /*
     * It's nice that . is accepted [see above] but other punctuation and
     * symbolic characters seem to be taken as a spelling mistake. Would have
     * preferred that it could have been smarter about this.
     */
    public final void testWordsWithSymbols() {
        final Dictionary dict;
        boolean result;

        dict = Enchant.requestDictionary("en");

        result = dict.check("correct");
        assertTrue(result);

        result = dict.check("correct)");
        assertFalse(result);

        result = dict.check("(correct");
        assertFalse(result);

        result = dict.check("(correct)");
        assertFalse(result);

        result = dict.check("correct,");
        assertFalse(result);
    }

    public final void testContractions() {
        final Dictionary dict;
        boolean result;

        dict = Enchant.requestDictionary("en");

        result = dict.check("do");
        assertTrue(result);

        result = dict.check("not");
        assertTrue(result);

        result = dict.check("do not");
        assertFalse(result);

        result = dict.check("don't");
        assertTrue(result);
    }

    public final void testEmpty() {
        final Dictionary dict;
        boolean result;

        dict = Enchant.requestDictionary("en");

        result = dict.check("");
        assertTrue(result);
    }
}
