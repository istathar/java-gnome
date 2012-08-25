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
 * Constants specifying the compositing operating mode in effect. These are
 * set for a drawing Context using {@link Context#setOperator(Operator)
 * setOperator()}, and take effect when commands like {@link Context#paint()
 * paint()} are invoked.
 * 
 * <p>
 * The Context should be created using a cairo {@link Surface}. <i>The
 * operators don't seem to work correctly when a
 * {@link org.gnome.gdk.Drawable Drawable}</i> is used instead of a cairo
 * Surface.
 * 
 * <p>
 * The example images shown below were generated with the following code:
 * 
 * <pre>
 * cr.rectangle(0, 0, 75, 75);
 * cr.setSource(0.7, 0, 0, 0.8);
 * cr.fill();
 * 
 * cr.setOperator(Operator.FOO);
 * 
 * cr.rectangle(40, 40, 75, 75);
 * cr.setSource(0, 0, 0.9, 0.4);
 * cr.fill();
 * </pre>
 * 
 * <p>
 * The drawing operations in cairo are said to be <b>bounded</b> and
 * <b>unbounded</b> with reguards to the Surface to be drawn to.
 * 
 * <p>
 * When an operator is said to be bounded any cairo mask present determines
 * where the operation is applied.
 * 
 * <p>
 * When an operator is said to be unbounded the operation is applied ignoring
 * any present mask. <i>Note: Clipping can still limit an unbounded
 * operator.</i>
 * 
 * @author Andrew Cowie
 * @author Kenneth Prugh
 * @author Zak Fenton
 * @since 4.0.7
 */
public class Operator extends Constant
{
    private Operator(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Clear a surface to all transparent. <img src="Operator-clear.png"
     * class="snapshot">
     * 
     * <p>
     * This operator is bounded.
     * 
     * @since 4.0.7
     */
    public static final Operator CLEAR = new Operator(CairoOperator.CLEAR, "CLEAR");

    /**
     * Draw over existing pixels as if they were not present. <img
     * src="Operator-source.png" class="snapshot">
     * 
     * <p>
     * This operator is bounded.
     * 
     * <p>
     * As an example, you could set up the object to be drawn over:
     * 
     * <pre>
     * cr.setSource(0.7, 0, 0, 0.8);
     * cr.rectangle(15, 10, 50, 50);
     * cr.fill();
     * </pre>
     * 
     * Now set up the source object that will draw over the area beneath it:
     * 
     * <pre>
     * cr.setSource(0, 0, 0.9, 0.4);
     * cr.rectangle(35, 35, 50, 50);
     * cr.setOperator(Operator.SOURCE);
     * cr.fill();
     * </pre>
     * 
     * @since 4.0.16
     */
    public static final Operator SOURCE = new Operator(CairoOperator.SOURCE, "SOURCE");

    /**
     * Draws the specified source object over the underlying object as if both
     * objects were two overlapping panels of transparent glass. This only
     * applies to objects that have an alpha channel; if the objects do not
     * have an alpha channel, the source object simply paints over the
     * underlying object. <img class="snapshot" src="Operator-over.png">
     * 
     * <p>
     * This is the default operator.
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.10
     */
    public static final Operator OVER = new Operator(CairoOperator.OVER, "OVER");

    /**
     * Draw only where existing pixels are, clearing the rest of the surface.
     * <img src="Operator-in.png" class="snapshot">
     * 
     * <p>
     * This operator is unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator IN = new Operator(CairoOperator.IN, "IN");

    /**
     * Draw only where existing pixels are not present, leaving a shadow
     * behind where the two overlapped due to transparency. <img
     * src="Operator-out.png" class="snapshot">
     * 
     * <p>
     * This operator is unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator OUT = new Operator(CairoOperator.OUT, "OUT");

    /**
     * Draw only where existing pixels are, mixing the color of the
     * overlapping region. <img src="Operator-atop.png" class="snapshot">
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator ATOP = new Operator(CairoOperator.ATOP, "ATOP");

    /**
     * Any existing pixels are left untouched, while the current drawing is
     * discarded. <img src="Operator-dest.png" class="snapshot">
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator DEST = new Operator(CairoOperator.DEST, "DEST");

    /**
     * Draw below any existing pixels with similar results to the
     * {@link #OVER OVER} operator. <img src="Operator-dest_over.png"
     * class="snapshot">
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator DEST_OVER = new Operator(CairoOperator.DEST_OVER, "DEST_OVER");

    /**
     * Draw below the overlapping region, clearing everything outside the
     * region similar to the {@link #IN IN} Operator. <img
     * src="Operator-dest_in.png" class="snapshot">
     * 
     * <p>
     * This operator is unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator DEST_IN = new Operator(CairoOperator.DEST_IN, "DEST_IN");

    /**
     * Reduce the visibility of the overlapping region. <img
     * src="Operator-dest_out.png" class="snapshot">
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator DEST_OUT = new Operator(CairoOperator.DEST_OUT, "DEST_OUT");

    /**
     * Draw and clear any existing pixels outside the overlapping region, the
     * color of the overlapping region is mixed similar to the {@link #ATOP
     * ATOP} Operator. <img src="Operator-dest_atop.png" class="snapshot">
     * 
     * <p>
     * This operator is unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator DEST_ATOP = new Operator(CairoOperator.DEST_ATOP, "DEST_ATOP");

    /**
     * XOR the colors of the overlapping region. <img src="Operator-xor.png"
     * class="snapshot">
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator XOR = new Operator(CairoOperator.XOR, "XOR");

    /**
     * Add the colors of the overlapping region. <img src="Operator-add.png"
     * class="snapshot">
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator ADD = new Operator(CairoOperator.ADD, "ADD");

    /**
     * Saturate the colors of the overlapping region. <img
     * src="Operator-saturate.png" class="snapshot">
     * 
     * <p>
     * This operator has the same effect for bounded and unbounded.
     * 
     * @since 4.0.16
     */
    public static final Operator SATURATE = new Operator(CairoOperator.SATURATE, "SATURATE");
}
