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
     * Returns <code>0</code> if the word is deemed to be spelled correctly,
     * positive if incorrect, and negative if there was an error.
     * 
     * @since 4.0.14
     */
    public int check(String word) {
        return EnchantDict.check(this, word, -1);
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
     * FIXME leak! We need to call enchant_dict_free_suggestions(), and
     * there's no way for the generated code to know that.
     */
    public String[] suggest(String word) {
        return EnchantDict.suggest(this, word, -1, null);
    }
}
