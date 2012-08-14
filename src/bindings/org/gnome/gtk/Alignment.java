/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Control the alignment and size of a child Widget. It has four settings:
 * <var>xscale</var>, <var>yscale</var>, <var>xalign</var>, and
 * <var>yalign</var>. You can also specify padding around the child.
 * 
 * <p>
 * The alignment settings are used to place the child within the available
 * area. The values range from <code>0.0f</code> (top or left) to
 * <code>1.0f</code> (bottom or right).
 * 
 * <p>
 * The scale settings are used to specify how much the child should expand to
 * fill the space allocated to the Alignment. The values can range from
 * <code>0.0f</code> (meaning the child doesn't expand at all) to
 * <code>1.0f</code> (meaning the child will expand to fill all of the
 * allocated space). If both scale settings are set to <code>1.0f</code>, the
 * two alignment values will have no effect on the child Widget.
 * 
 * @author Nat Pryce
 * @author Andrew Cowie
 * @since 4.0.4
 */
public class Alignment extends Bin
{
    protected Alignment(long pointer) {
        super(pointer);
    }

    /*
     * These constants are not necessary by any stretch of the imagination,
     * but really they are the only cases you really use, and with static
     * imports they can make one's code look a bit more meaningful.
     */

    /**
     * Align to the left. A convenience constant for the value
     * <code>0.0f</code>.
     */
    public static final float LEFT = 0.0f;

    /**
     * Align to the center. A convenience constant for the value
     * <code>0.5f</code>.
     */
    public static final float CENTER = 0.5f;

    /**
     * Align to the right. A convenience constant for the value
     * <code>1.0f</code>.
     */
    public static final float RIGHT = 1.0f;

    /**
     * Align to the top. A convenience constant for the value
     * <code>0.0f</code>.
     */
    public static final float TOP = 0.0f;

    /**
     * Align to the bottom. A convenience constant for the value
     * <code>1.0f</code>.
     */
    public static final float BOTTOM = 1.0f;

    private static final float check(final float param) {
        if ((param < 0.0) || (param > 1.0)) {
            throw new IllegalArgumentException("Parameters must be between 0.0 and 1.0");
        }
        return param;
    }

    /**
     * Creates an empty Alignment. The child Widget can later be added by
     * calling the {@link Container#add(Widget) add()} method.
     * 
     * @since 4.0.4
     */
    public Alignment(float xalign, float yalign, float xscale, float yscale) {
        this(GtkAlignment.createAlignment(check(xalign), check(yalign), check(xscale), check(yscale)));
    }

    /**
     * Creates an empty Alignment. This is essentially a no-op; the Widget
     * will be top/left which may well be what would have happened if you had
     * not used an Alignment in the first place.
     * 
     * <p>
     * This is here so that calling
     * {@link #setAlignment(float, float, float, float) setAlignment()} after
     * constructing looks a little cleaner. It's not more "efficient" to use
     * this.
     * 
     * @since 4.0.17
     */
    public Alignment() {
        this(GtkAlignment.createAlignment(Alignment.LEFT, Alignment.TOP, 0.0f, 0.0f));
    }

    /**
     * Creates an Alignment wrapping an existing Widget.
     * 
     * @since 4.0.4
     */
    public Alignment(float xalign, float yalign, float xscale, float yscale, Widget child) {
        this(GtkAlignment.createAlignment(check(xalign), check(yalign), check(xscale), check(yscale)));
        add(child);
    }

    /**
     * Set the padding on the different sides of the Widget. The padding adds
     * blank space to the sides of the Widget. For instance, this can be used
     * to indent the child towards the right by adding padding on the left.
     * 
     * <p>
     * <b>WARNING</b><br>
     * Pay close attention to the order of the sides; it might be different
     * than you are expecting.
     * 
     * @since 4.0.4
     */
    public void setPadding(int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        GtkAlignment.setPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
    }

    /**
     * Returns the padding being added to the top of the child.
     * 
     * @since 4.0.4
     */
    public int getPaddingTop() {
        int[] paddingTop = new int[1];
        int[] paddingBottom = new int[1];
        int[] paddingLeft = new int[1];
        int[] paddingRight = new int[1];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingTop[0];
    }

    /**
     * Returns the padding being added below the bottom of the child.
     */
    public int getPaddingBottom() {
        int[] paddingTop = new int[1];
        int[] paddingBottom = new int[1];
        int[] paddingLeft = new int[1];
        int[] paddingRight = new int[1];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingBottom[0];
    }

    /**
     * Returns the padding being added to the left of the child.
     * 
     * @since 4.0.4
     */
    public int getPaddingLeft() {
        int[] paddingTop = new int[1];
        int[] paddingBottom = new int[1];
        int[] paddingLeft = new int[1];
        int[] paddingRight = new int[1];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingLeft[0];
    }

    /**
     * Returns the padding being added to the right of the child.
     * 
     * @since 4.0.4
     */
    public int getPaddingRight() {
        int[] paddingTop = new int[1];
        int[] paddingBottom = new int[1];
        int[] paddingLeft = new int[1];
        int[] paddingRight = new int[1];

        GtkAlignment.getPadding(this, paddingTop, paddingBottom, paddingLeft, paddingRight);
        return paddingRight[0];
    }

    /**
     * Set the alignment and scale values. See the discussion at
     * {@link Alignment top} for the interpretation of the values. All
     * parameters must be within the range of <code>0.0f</code> to
     * <code>1.0f</code>.
     */
    public void setAlignment(float xalign, float yalign, float xscale, float yscale) {
        GtkAlignment.set(this, check(xalign), check(yalign), check(xscale), check(yscale));
    }

    /**
     * Get the <var>xalign</var> value.
     * 
     * @since 4.0.4
     */
    public float getAlignmentX() {
        return getPropertyFloat("xalign");
    }

    /**
     * Get the <var>yalign</var> value.
     * 
     * @since 4.0.4
     */
    public float getAlignmentY() {
        return getPropertyFloat("yalign");
    }

    /**
     * Get the <var>xscale</var> value.
     * 
     * @since 4.0.4
     */
    public float getScaleX() {
        return getPropertyFloat("xscale");
    }

    /**
     * Get the <var>yscale</var> value.
     * 
     * @since 4.0.4
     */
    public float getScaleY() {
        return getPropertyFloat("yscale");
    }
}
