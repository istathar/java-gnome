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
package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * Whether to hint the font rendering based on alignment to the integer pixel
 * grid. Forcing this {@link #OFF OFF} is necessary if you want perfect linear
 * scaling of your rendered fonts.
 * 
 * <p>
 * The default setting is to be inherited, and while probably <code>ON</code>;
 * the presence of {@link #DEFAULT DEFAULT} means you can generally leave this
 * alone and not worry about it.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public class HintMetrics extends Constant
{
    private HintMetrics(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Default is like "unset"; the existing value from the surrounding
     * environment (Context, Surface, Font in use, Font rendering back end,
     * etc) will be used.
     * 
     * @since 4.0.10
     */
    public static final HintMetrics DEFAULT = new HintMetrics(CairoHintMetrics.DEFAULT, "DEFAULT");

    /**
     * Turn <b>metric</b> hinting
     * 
     * @since 4.0.10
     */
    public static final HintMetrics OFF = new HintMetrics(CairoHintMetrics.OFF, "OFF");

    /**
     * Hinting font metrics means <i>"quantizing them so that they are integer
     * values"</i> in the target Surface's physical rendering co-ordinate
     * space. This is good for visual appearance but breaks perfect smoothness
     * when doing linear scaling (such as in animation if zooming in).
     * 
     * @since 4.0.10
     */
    public static final HintMetrics ON = new HintMetrics(CairoHintMetrics.ON, "ON");
}
