/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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

/**
 * A radial gradient Pattern.
 * 
 * <p>
 * After calling this and before using the RadialPattern you need to call
 * {@link Pattern#addColorStopRGBA(double, double, double, double, double)
 * addColorStopRGBA()} a few times to set up the gradient. For example, to
 * create an circular alpha blend:
 * 
 * <pre>
 * pattern = new RadialPattern(75, 75, 25, 75, 75, 120);
 * pattern.addColorStopRGBA(0.0, 0.0, 0.0, 0.0, 0.0);
 * pattern.addColorStopRGBA(1.0, 0.0, 0.0, 0.0, 1.0);
 * </pre>
 * 
 * and then you can get on with using the Pattern in drawing operations:
 * 
 * <pre>
 * cr.mask(pattern);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public class RadialPattern extends Pattern
{
    protected RadialPattern(long pointer) {
        super(pointer);
    }

    /**
     * Create a Pattern with a radial gradient between two circles. The first
     * circle is centered at <code>cx0</code>,<code>cy0</code> with a radius
     * of <code>radius0</code>, and the second circle is centered at
     * <code>cx1</code>,<code>cy1</code> with a radius of <code>radius1</code>
     * .
     * 
     * <p>
     * Quite typically, you will want a strict radial pattern from a common
     * centre, in which case have <code>cx1</code>,<code>cy1</code> equal to
     * <code>cx0</code>,<code>cy0</code>.
     * 
     * @since 4.0.7
     */
    public RadialPattern(double cx0, double cy0, double radius0, double cx1, double cy1, double radius1) {
        super(CairoPattern.createPatternRadial(cx0, cy0, radius0, cx1, cy1, radius1));
        checkStatus();
    }
}
