/*
 * Dictionary.java
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

/**
 * @author Andrew Cowie
 * @since 4.0.14
 * 
 */
class Dictionary extends Entity
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
     * Returns <code>true</code> if the word is deemed to be spelled
     * correctly, <code>false</code> if incorrect.
     * 
     * <p>
     * <i>The underlying library documentation also notes an error state;
     * should it be encountered we throw an IllegalStateException.</i>
     * 
     * @since 4.0.14
     */
    public boolean check(String word) {
        final int result;
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
     * spelled! This means that you need to call {@link #check(String)
     * check()} first to find out whether or not to offer a list of
     * corrections.
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
     * @since 4.0.14
     */
    public void remove(String word) {
        EnchantDict.remove(this, word, -1);
    }
}
