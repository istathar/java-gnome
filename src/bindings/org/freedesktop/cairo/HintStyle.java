/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd
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
package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * What style of hinting to apply. Note that not all styles are supported by
 * all back ends, which is why you probably want {@link #DEFAULT DEFAULT}.
 * Like HintMetrics, you can generally leave this alone.
 * 
 * <p>
 * Worse, vendors like Canonical patch their distribution's Cairo package in
 * ways that have voided their support from the upstream Cairo and Pango
 * authors. So setting {@link #SLIGHT SLIGHT} will unfortunately have varying
 * and unpredictable effects, depending on what Linux your users are on.
 * 
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class HintStyle extends Constant
{
    private HintStyle(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Default is like "unset"; the existing value from the surrounding
     * environment (Context, Surface, Font in use, Font rendering back end,
     * etc) will be used.
     * 
     * @since 4.0.17
     */
    public static final HintStyle DEFAULT = new HintStyle(CairoHintStyle.DEFAULT, "DEFAULT");

    /**
     * Turn hinting off, being most faithful to the actual outlines in the
     * font.
     * 
     * @since 4.0.17
     */
    public static final HintStyle NONE = new HintStyle(CairoHintStyle.NONE, "NONE");

    /**
     * Slight hinting.
     * 
     * @since 4.0.17
     */
    public static final HintStyle SLIGHT = new HintStyle(CairoHintStyle.SLIGHT, "SLIGHT");

    /**
     * Medium hinting.
     * 
     * @since 4.0.17
     */
    public static final HintStyle MEDIUM = new HintStyle(CairoHintStyle.MEDIUM, "MEDIUM");

    /**
     * Full hinting, maximizing contrast.
     * 
     * @since 4.0.17
     */
    public static final HintStyle FULL = new HintStyle(CairoHintStyle.FULL, "FULL");
}
