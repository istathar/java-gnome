/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gdk;

import org.gnome.glib.Boxed;

/**
 * Representation of an RGB colour. Used by GDK in drawing Widgets and related
 * elements.
 * 
 * @author Andrew Cowie
 * @since 4.0.20
 */
// cloned from Color
public final class RGBA extends Boxed
{
    protected RGBA(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new RGBA object. The <code>red</code>, <code>green</code>,
     * <code>blue</code> and <code>alpha</code> parameters take values
     * <code>0.0</code> to <code>1.0</code>.
     * 
     * @since 4.0.20
     */
    public RGBA(double red, double green, double blue, double alpha) {
        super(GdkRGBAOverride.createRGBA(red, green, blue, alpha));
    }

    protected void release() {
        GdkRGBA.free(this);
    }

    /**
     * Get the red component of this Color.
     * 
     * @since 4.0.20
     */
    public double getRed() {
        return GdkRGBA.getRed(this);
    }

    /**
     * Get the green component of this Color.
     * 
     * @since 4.0.20
     */
    public double getGreen() {
        return GdkRGBA.getGreen(this);
    }

    /**
     * Get the blue component of this Color.
     * 
     * @since 4.0.20
     */
    public double getBlue() {
        return GdkRGBA.getBlue(this);
    }

    /**
     * Get the blue component of this Color.
     * 
     * @since 4.1.1
     */
    public double getAlpha() {
        return GdkRGBA.getBlue(this);
    }

    /**
     * @since 4.0.20
     */
    public static final RGBA BLACK = new RGBA(0.0, 0.0, 0.0, 1.0);

    /**
     * @since 4.0.20
     */
    public static final RGBA WHITE = new RGBA(1.0, 1.0, 1.0, 1.0);

    /**
     * @since 4.0.20
     */
    public static final RGBA RED = new RGBA(1.0, 0.0, 0.0, 1.0);

    /**
     * @since 4.0.20
     */
    public static final RGBA GREEN = new RGBA(0.0, 1.0, 0.0, 1.0);

    /**
     * @since 4.0.20
     */
    public static final RGBA BLUE = new RGBA(0.0, 0.0, 1.0, 1.0);

    public boolean equals(Object obj) {
        final RGBA other;

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RGBA)) {
            return false;
        }

        other = (RGBA) obj;

        return GdkRGBA.equal(this, other);
    }

    public int hashCode() {
        return GdkRGBA.hash(this);
    }
}
