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

/**
 * @author Andrew Cowie
 * @since 4.0.14
 * 
 */
public class Dictionary extends Entity
{
    protected Dictionary(long pointer) {
        super(pointer);
    }

    protected void release() {
        final Broker broker;

        broker = Enchant.getDefault();
        EnchantBroker.freeDict(broker, this);
    }

    /**
     * Check the spelling of the given word.
     * 
     * <p>
     * Returns <code>true</code> if the word is deemed to be spelt correctly,
     * <code>false</code> if incorrect.
     * 
     * <p>
     * <i>The underlying library documentation also notes an error state;
     * should it be encountered we throw an IllegalStateException.</i>
     * 
     * @since 4.0.14
     */
    public boolean check(String word) {
        final int result;

        if (word.length() == 0) {
            /*
             * Enchant crashes if you try to spell check a zero width string.
             * So we'll define an empty String to be correctly spelled.
             */
            return true;
        }

        result = EnchantDict.check(this, word, -1);

        if (result == 0) {
            return true;
        } else if (result > 0) {
            return false;
        } else {
            throw new IllegalStateException("Internal problem in Enchant wrapper library");
        }
    }

    /**
     * Offer alternate suggestions of how to spell a word.
     * 
     * <p>
     * Beware that you can get suggestions even from a word that is correctly
     * spelt! This means that you need to call {@link #check(String) check()}
     * first to find out whether or not to offer a list of corrections.
     * 
     * @since 4.0.14
     */
    /*
     * FIXME we need to call enchant_dict_free_string_list() on the C side
     * char**, but there's no way for the code generator to do that for us. By
     * having declared caller-owns-return as true in the .defs data we
     * generate a call to g_strfreev() which is what the enchant code does
     * internally, so this is not a leak. But we should replace this with an
     * Override that calls the Enchant function properly.
     */
    public String[] suggest(String word) {
        return EnchantDict.suggest(this, word, -1, null);
    }

    /**
     * Add a word to the personal dictionary.
     * 
     * @since 4.0.14
     */
    public void add(String word) {
        EnchantDict.add(this, word, -1);
    }

    /**
     * Remove a word from the personal dictionary.
     * 
     * <p>
     * <b>FIXME</b><br>
     * This does remove the word from the Enchant personal word list, but it
     * then <i>adds</i> it to something called the "exclude" list. What is
     * that all about?
     * 
     * @since 4.0.14
     */
    public void remove(String word) {
        EnchantDict.remove(this, word, -1);
    }
}
