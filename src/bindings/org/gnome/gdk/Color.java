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
package org.gnome.gdk;

import org.gnome.glib.Boxed;

/**
 * Representation of an RGB colour. Used by GDK in drawing Widgets and related
 * elements.
 * 
 * <p>
 * <i>Regrettably, colour is spelt wrong in the underlying GTK library, so
 * sticking to our algorithmic API mapping we are forced to present it as
 * Color here.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public final class Color extends Boxed
{
    protected Color(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Color object. The <code>red</code>, <code>green</code>,
     * and <code>blue</code> parameters take values <code>0</code> to
     * <code>65535</code>.
     */
    public Color(int red, int green, int blue) {
        super(GdkColorOverride.createColor(red, green, blue));
    }

    protected void release() {
        GdkColor.free(this);
    }

    /**
     * Get the red component of this Color.
     */
    public int getRed() {
        return GdkColor.getRed(this);
    }

    /**
     * Get the green component of this Color.
     */
    public int getGreen() {
        return GdkColor.getGreen(this);
    }

    /**
     * Get the blue component of this Color.
     */
    public int getBlue() {
        return GdkColor.getBlue(this);
    }

    public static final Color BLACK = new Color(0, 0, 0);

    public static final Color WHITE = new Color(65535, 65535, 65535);

    public static final Color RED = new Color(65535, 0, 0);

    public boolean equals(Object obj) {
        final Color other;

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Color)) {
            return false;
        }

        other = (Color) obj;

        return GdkColor.equal(this, other);
    }

    public int hashCode() {
        return GdkColor.hash(this);
    }
}
