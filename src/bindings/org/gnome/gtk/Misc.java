/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Base class for Widgets that have notions of alignment and padding.
 * 
 * @author Andrew Cowie
 * @author Nat Pryce
 * @since 4.0.0
 */
public abstract class Misc extends Widget
{
    protected Misc(long pointer) {
        super(pointer);
    }

    /**
     * Set the horizontal and vertical alignment attributes, enabling the
     * Widget to be positioned within its allocated area. Note that if the
     * Widget is added to a Container in such a way that it expands
     * automatically to fill its allocated area, the alignment settings will
     * not alter the Widget's position.
     * 
     * @param xalign
     *            the horizontal alignment, from <code>0.0f</code> (full left)
     *            to <code>1.0f</code> (full right).
     * @param yalign
     *            the vertical alignment, from <code>0.0f</code> (top) to
     *            <code>1.0f</code> (bottom).
     * @since 4.0.4
     */
    /*
     * TODO add reference to Container (or where ever) when we get around to
     * documenting the Request to Allocation process.
     */
    public void setAlignment(float xalign, float yalign) {
        GtkMisc.setAlignment(this, xalign, yalign);
    }

    /**
     * Gets the horizontal alignment of the Widget within its allocation. See
     * {@link #setAlignment(float, float) setAlignment()}.
     * 
     * @since 4.0.4
     */
    public float getAlignmentX() {
        float[] xalign = new float[1];
        float[] yalign = new float[1];
        GtkMisc.getAlignment(this, xalign, yalign);
        return xalign[0];
    }

    /**
     * Gets the vertical alignment of the Widget within its allocation. See
     * {@link #setAlignment(float, float) setAlignment()}.
     * 
     * @since 4.0.4
     */
    public float getAlignmentY() {
        float[] xalign = new float[1];
        float[] yalign = new float[1];
        GtkMisc.getAlignment(this, xalign, yalign);
        return yalign[0];
    }

    /**
     * Set the amount of extra horizontal and vertical padding space to added
     * around the Widget.
     * 
     * @param xpad
     *            the amount of space to add on the left and right of the
     *            Widget, in pixels.
     * @param ypad
     *            the amount of space to add on the top and bottom of the
     *            Widget, in pixels.
     * @since 4.0.4
     */
    public void setPadding(int xpad, int ypad) {
        GtkMisc.setPadding(this, xpad, ypad);
    }

    /**
     * Returns the horizontal padding of the Widget, in pixels.
     * 
     * @since 4.0.4
     */
    public int getPaddingX() {
        int[] xpad = new int[1];
        int[] ypad = new int[1];
        GtkMisc.getPadding(this, xpad, ypad);
        return xpad[0];
    }

    /**
     * Returns the vertical padding of the Widget, in pixels.
     * 
     * @since 4.0.4
     */
    public int getPaddingY() {
        int[] xpad = new int[1];
        int[] ypad = new int[1];
        GtkMisc.getPadding(this, xpad, ypad);
        return ypad[0];
    }
}
