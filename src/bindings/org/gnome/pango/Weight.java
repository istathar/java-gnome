/*
 * Weight.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008      Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
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
 * <i>Note that many fonts do not implement all the values here, in that case
 * it will be aproximated by the closest one.</i>
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
     */
    public static final Weight NORMAL = new Weight(PangoWeight.NORMAL, "NORMAL");

    /**
     * Bold text.
     * 
     * <p>
     * <i>This represents a font weight value of <code>700</code>,
     * apparently.</i>
     */
    public static final Weight BOLD = new Weight(PangoWeight.BOLD, "BOLD");

    /**
     * The most tiny weight.
     */
    public static final Weight ULTRALIGHT = new Weight(200, "ULTRALIGHT");

    /**
     * Light weigth.
     */
    public static final Weight LIGHT = new Weight(300, "LIGHT");

    /**
     * A weight intermediate between NORMAL and BOLD.
     */
    public static final Weight SEMIBOLD = new Weight(600, "SEMIBOLD");

    /**
     * Ultrabold weight.
     */
    public static final Weight ULTRABOLD = new Weight(800, "ULTRABOLD");

    /**
     * The heavy weight.
     */
    public static final Weight HEAVY = new Weight(900, "HEAVY");

}
