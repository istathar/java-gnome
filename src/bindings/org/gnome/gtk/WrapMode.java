/*
 * WrapMode.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public final class WrapMode extends Constant
{
    private WrapMode(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
    
    /**
     * Do not wrap lines; just make the text area wider
     */
    public static final WrapMode NONE = new WrapMode(GtkWrapMode.NONE, "NONE");
    
    /**
     * Wrap text, breaking lines anywhere the cursor can appear (between 
     * characters, usually) 
     */
    public static final WrapMode CHAR = new WrapMode(GtkWrapMode.CHAR, "CHAR");
    
    /**
     * Wrap text, breaking lines in between words
     */
    public static final WrapMode WORD = new WrapMode(GtkWrapMode.WORD, "WORD");
    
    /**
     * Wrap text, breaking lines in between words, or if that is not enough, 
     * also between graphemes.
     */
    public static final WrapMode WORD_CHAR = new WrapMode(GtkWrapMode.WORD_CHAR, "WORD_CHAR");

}
