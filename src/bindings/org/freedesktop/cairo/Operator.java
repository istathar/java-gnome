/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * "Claspath Exception"), the copyright holders of this library give you
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
 * Constants specifying the compositing operating mode in effect. These are
 * set for a drawing Context using {@link Context#setOperator(Operator)
 * setOperator()}, and take effect when commands like {@link Context#paint()
 * paint()} are invoked.
 * 
 * @author Andrew Cowie
 * @author Zak Fenton
 * @author Kenneth Prugh
 * @since 4.0.7
 */
public class Operator extends Constant
{
    private Operator(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Clear a surface to all transparent.
     * 
     * @since 4.0.7
     */
    public static final Operator CLEAR = new Operator(CairoOperator.CLEAR, "CLEAR");

    /**
     * Draw over existing pixels as if they were not present.
     * 
     * @since 4.0.16
     */
    public static final Operator SOURCE = new Operator(CairoOperator.SOURCE, "SOURCE");

    /**
     * Default operator: draw over existing pixels.
     * 
     * @since 4.0.10
     */
    public static final Operator OVER = new Operator(CairoOperator.OVER, "OVER");

    /**
     * Draw only where existing pixels are, clearing the rest of the surface.
     * 
     * @since 4.0.16
     */
    public static final Operator IN = new Operator(CairoOperator.IN, "IN");

    /**
     * Draw only where existing pixels are not present, leaving a shadow behind
     * where the two overlapped due to transparency.
     * 
     * @since 4.0.16
     */
    public static final Operator OUT = new Operator(CairoOperator.OUT, "OUT");

    /**
     * Draw only where existing pixels are, mixing the color of the overlapping
     * region.
     * 
     * @since 4.0.16
     */
    public static final Operator ATOP = new Operator(CairoOperator.ATOP, "ATOP");

    /**
     * Any existing pixels are left untouched, while the current drawing is discarded.
     * 
     * @since 4.0.16
     */
    public static final Operator DEST = new Operator(CairoOperator.DEST, "DEST");

    /**
     * Draw below any existing pixels with similar results as the OVER
     * operator.
     * 
     * @since 4.0.16
     */
    public static final Operator DEST_OVER = new Operator(CairoOperator.DEST_OVER, "DEST_OVER");
}
