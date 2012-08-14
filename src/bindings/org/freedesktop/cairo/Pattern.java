/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * A Pattern source.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public abstract class Pattern extends Entity
{
    protected Pattern(long pointer) {
        super(pointer);
    }

    protected void release() {
        CairoPattern.destroy(this);
    }

    protected void checkStatus() {
        checkStatus(CairoPattern.status(this));
    }

    /**
     * Add a colour stop to a Pattern gradient. Equivalent to calling:
     * 
     * <pre>
     * addColorStopRGBA(offset, red, green, blue, 1.0);
     * </pre>
     * 
     * See {@link #addColorStopRGBA(double, double, double, double, double)
     * addColorStopRGBA()} for documentation of the <code>offset</code>
     * parameter. The colour parameters are the same as for
     * {@link Context#setSource(double, double, double) setSource()}.
     * 
     * @since 4.0.7
     */
    public void addColorStopRGB(double offset, double red, double green, double blue) {
        CairoPattern.addColorStopRgb(this, offset, red, green, blue);
        checkStatus();
    }

    /**
     * Add a colour stop to a Pattern gradient.
     * 
     * <p>
     * The <code>offset</code> parameter provides for the ordering of stops.
     * When a Pattern applies its colour stops, it works through them in the
     * order specified. If two stops are specified with identical
     * <code>offset</code> values, they will be sorted according to the order
     * in which the stops are added, is used for making sharp color
     * transitions instead of a blend.
     * 
     * <p>
     * Colour stops handle colour arguments the same way as
     * {@link Context#setSource(double, double, double) setSource()} does.
     * 
     * @since 4.0.7
     */
    public void addColorStopRGBA(double offset, double red, double green, double blue, double alpha) {
        CairoPattern.addColorStopRgba(this, offset, red, green, blue, alpha);
        checkStatus();
    }

    /**
     * Specify the strategy to be used when drawing the pattern outside of the
     * area implicit in the how the Pattern itself is described.
     * 
     * <p>
     * If you're looking at this, it's probably because you're wondering why
     * your Pattern isn't extending. Call this with {@link Extend#REPEAT
     * REPEAT}.
     * 
     * @since 4.0.17
     */
    public void setExtend(Extend extend) {
        CairoPattern.setExtend(this, extend);
    }

    /**
     * Get the Surface backing this Pattern. Obviously this only works with
     * SurfacePatterns.
     * 
     * @since 4.0.18
     */
    public Surface getSurface() {
        return CairoPatternOverride.getSurface(this);
    }

    /**
     * Sets the filter that resizing operations should use with this pattern.
     * 
     * @since 4.0.20
     */
    public void setFilter(Filter filter) {
        CairoPattern.setFilter(this, filter);
        checkStatus();
    }
}
