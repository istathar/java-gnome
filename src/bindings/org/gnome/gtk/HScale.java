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
package org.gnome.gtk;

/**
 * A horizontal slider allowing you to visually represent data and offer the
 * user the ability to manipulate it. <img src="HScale.png" class="snapshot">
 * See {@link Scale} and {@link Range} for the methods used to manipulate
 * instances of these classes.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class HScale extends Scale
{
    protected HScale(long pointer) {
        super(pointer);
    }

    /**
     * Create a new HScale allowing the user to enter a number a number
     * between <code>min</code> and <code>max</code>, sliding in increments of
     * <code>step</code>.
     * 
     * <p>
     * The internal algorithms work best if <code>step</code> is specified as
     * a power of 10. That shouldn't hassle you, as you can round the value
     * showing in the HScale with {@link Scale#setDigits(int) setDigits()}.
     * And in any case, <code>step</code> only impacts the jumps that are made
     * if the HScale is changed via the <b><code>Left</code></b> and <b>
     * <code>Right</code></b> key strokes.
     * 
     * @since 4.0.6
     */
    public HScale(int min, int max, int step) {
        super(GtkHScale.createHScaleWithRange(min, max, step));
    }
}
