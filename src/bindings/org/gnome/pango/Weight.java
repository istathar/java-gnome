/*
 * Weight.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.freedesktop.bindings.Constant;

/**
 * The weight (boldness) of a font. Note that many fonts do not implement all
 * the values here, in that case it will be aproximated by the closest one.
 * 
 * @author Vreixo Formoso
 * @since 4.0.8
 */
/*
 * Values are hardcoded. This enum is a bit different, as it does not consist
 * of 0 and consecutive positive numbers, and it is not a flag. So, our code
 * generation cannot handle it, and writing an override is not needed.
 */
public final class Weight extends Constant
{
    private Weight(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The most tiny weight.
     */
    public final static Weight ULTRALIGHT = new Weight(200, "ULTRALIGHT");

    /**
     * Light weigth.
     */
    public final static Weight LIGHT = new Weight(300, "LIGHT");

    /**
     * Normal weigth of a Font.
     */
    public final static Weight NORMAL = new Weight(400, "NORMAL");

    /**
     * A weight intermediate between NORMAL and BOLD.
     */
    public final static Weight SEMIBOLD = new Weight(600, "SEMIBOLD");

    /**
     * Bold weight.
     */
    public final static Weight BOLD = new Weight(700, "BOLD");

    /**
     * Ultrabold weight.
     */
    public final static Weight ULTRABOLD = new Weight(800, "ULTRABOLD");

    /**
     * The heavy weight.
     */
    public final static Weight HEAVY = new Weight(900, "HEAVY");
}
