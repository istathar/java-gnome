/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Used to specify the filtering that should be applied when reading pixel
 * values from patterns. See Pattern's {@link Pattern#setFilter(Filter)
 * setFilter()} for specifying the desired filter to be used with a particular
 * pattern.
 * 
 * @author Will Temperley
 * @since 4.0.20
 */
public class Filter extends Constant
{
    private Filter(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * A high-performance filter, with quality similar to
     * {@link Filter#NEAREST}
     */
    public static final Filter FAST = new Filter(CairoFilter.FAST, "FAST");

    /**
     * A reasonable-performance filter, with quality similar to
     * {@link Filter#BILINEAR}
     */
    public static final Filter GOOD = new Filter(CairoFilter.GOOD, "GOOD");

    /**
     * The highest-quality available, performance may not be suitable for
     * interactive use
     */
    public static final Filter BEST = new Filter(CairoFilter.BEST, "BEST");

    /**
     * Nearest-neighbour filtering
     */
    public static final Filter NEAREST = new Filter(CairoFilter.NEAREST, "NEAREST");

    /**
     * Linear interpolation in two dimensions
     */
    public static final Filter BILINEAR = new Filter(CairoFilter.BILINEAR, "BILINEAR");

    /**
     * This filter value is currently unimplemented, and should not be used in
     * current code.
     */
    public static final Filter GAUSSIAN = new Filter(CairoFilter.GAUSSIAN, "GAUSSIAN");

}
