/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2008      Vreixo Formoso
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

import org.freedesktop.bindings.Flag;

/**
 * Constants used for selecting the weight of a font. {@link #NORMAL NORMAL}
 * is the default corresponding to text as we are generally accustomed to
 * seeing it.
 * 
 * <p>
 * A common use of these constants is to make text in a TextView strong; use
 * TextTag's {@link org.gnome.gtk.TextTag#setWeight(Weight) setWeight()} and
 * pass in {@link #BOLD BOLD}.
 * 
 * <p>
 * <i>Note that most fonts do <b>not</b> implement all the values here. If you
 * specify a weight that is not directly available, the result should be
 * approximated by the closest one, but you are somewhat at the mercy of
 * the</i> $diety <i> as to what you actually get.</i>
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.9
 */
/*
 * Yes, you can subclass this if you are desperate for font weights other than
 * the established constant values supplied here.
 */
/*
 * FIXME I'd prefer if this were a Constant; the fact that we've hacked it to
 * (define-flags...) is to get at the actual values. We got away with it in
 * ResponseType but generated PangoFontDescription has a flagFor() in it that
 * breaks.
 */
public class Weight extends Flag
{
    private Weight(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    protected Weight(int value) {
        super(validate(value), Integer.toString(value));
    }

    static final int validate(int value) {
        if ((value < 100) || (value > 900)) {
            throw new IllegalArgumentException("Valid font weights are 100 through 900");
        }
        return value;
    }

    /**
     * The default font weight.
     * 
     * <p>
     * <i>This has a value of <code>400</code>, apparently.</i>
     * 
     * @since 4.0.9
     */
    public static final Weight NORMAL = new Weight(PangoWeight.NORMAL, "NORMAL");

    /**
     * Bold text.
     * 
     * <p>
     * <i>This represents a font weight value of <code>700</code>,
     * apparently.</i>
     * 
     * @since 4.0.9
     */
    public static final Weight BOLD = new Weight(PangoWeight.BOLD, "BOLD");

    /**
     * The lightest text weight available. Not much to it. Anorexic, really.
     * 
     * @since 4.0.10
     */
    public static final Weight ULTRALIGHT = new Weight(PangoWeight.ULTRALIGHT, "ULTRALIGHT");

    /**
     * Light weight text.
     * 
     * @since 4.0.10
     */
    public static final Weight LIGHT = new Weight(PangoWeight.LIGHT, "LIGHT");

    /**
     * An intermediate weight between {@link #NORMAL NORMAL} and {@link #BOLD
     * BOLD}.
     * 
     * @since 4.0.10
     */
    public static final Weight SEMIBOLD = new Weight(PangoWeight.SEMIBOLD, "SEMIBOLD");

    /**
     * Even stronger than {@link #BOLD BOLD}, is <code>ULTRABOLD</code>!
     * Sounds like a health food product.
     * 
     * @since 4.0.10
     */
    public static final Weight ULTRABOLD = new Weight(PangoWeight.ULTRABOLD, "ULTRABOLD");

    /**
     * The heavy weight. This is clearly for people who missed out on the
     * healthier food in the lighter {@link #ULTRABOLD ULTRABOLD} department.
     * 
     * @since 4.0.10
     */
    public static final Weight HEAVY = new Weight(PangoWeight.HEAVY, "HEAVY");
}
