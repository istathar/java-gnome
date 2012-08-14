/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Indicate if you want a TextView to wrap, and if so, where it should break
 * lines. These constants are used with TextView's
 * {@link TextView#setWrapMode(WrapMode) setWrapMode()} method.
 * 
 * @author Stefan Prelle
 * @author Andrew Cowie
 * @since 4.0.9
 */
public final class WrapMode extends Constant
{
    private WrapMode(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Do not wrap lines; attempt to make the TextView as wide as the widest
     * line in the underlying TextBuffer; lines will be truncated if they are
     * longer than the width allocated to the TextView.
     */
    public static final WrapMode NONE = new WrapMode(GtkWrapMode.NONE, "NONE");

    /**
     * Wrap text, breaking lines anywhere the cursor can appear (between
     * characters, usually)
     */
    public static final WrapMode CHAR = new WrapMode(GtkWrapMode.CHAR, "CHAR");

    /**
     * Wrap text, breaking lines in between words. This is usually the one you
     * want if you are trying to enable word wrapping, but keep in mind that
     * something <i>also</i> has to act to constrain the width of the TextView
     * as it is packed into a Container hierarchy or no wrapping will occur.
     */
    public static final WrapMode WORD = new WrapMode(GtkWrapMode.WORD, "WORD");

    /**
     * Wrap text, breaking lines in between words, or if that is not enough,
     * also between graphemes. FIXME What is a grapheme?
     */
    public static final WrapMode WORD_CHAR = new WrapMode(GtkWrapMode.WORD_CHAR, "WORD_CHAR");

}
