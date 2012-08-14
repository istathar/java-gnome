/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.pango;

import org.freedesktop.bindings.Constant;

/**
 * Constants specifying the type of underlining to be used in a given span of
 * text.
 * 
 * @author Andrew Cowie
 * @since 4.0.9
 */
public final class Underline extends Constant
{
    private Underline(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * No underline.
     */
    public static final Underline NONE = new Underline(PangoUnderline.NONE, "NONE");

    /**
     * A single horizontal underline stroke below the text.
     */
    public static final Underline SINGLE = new Underline(PangoUnderline.SINGLE, "SINGLE");

    /**
     * A double underline stroke below the text.
     */
    public static final Underline DOUBLE = new Underline(PangoUnderline.DOUBLE, "DOUBLE");

    /**
     * An interesting special case of single underlining, provide an underline
     * decoration that is absolutely below and clear of the bottom edge of the
     * drawn glyphs. This is exclusively intended for marking up mnemonic
     * characters in Labels.
     * 
     * <p>
     * Note that this is <b>not</b> to be applied for extended spans of text.
     * Use {@link #SINGLE SINGLE} for normal single underlining.
     */
    public static final Underline LOW = new Underline(PangoUnderline.LOW, "LOW");

    /**
     * The squiggly underline typically used to note spelling mistakes or
     * compile errors.
     */
    public static final Underline ERROR = new Underline(PangoUnderline.ERROR, "ERROR");
}
