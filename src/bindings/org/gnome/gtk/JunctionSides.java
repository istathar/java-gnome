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
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Constants that describe how a rendered element connects to adjacent
 * elements.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public final class JunctionSides extends Constant
{
    public JunctionSides(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * No junctions.
     */
    public static final JunctionSides NONE = new JunctionSides(GtkJunctionSides.NONE, "NONE");

    /**
     * Element connects on the top-left corner.
     */
    public static final JunctionSides CORNER_TOPLEFT = new JunctionSides(
            GtkJunctionSides.CORNER_TOPLEFT, "CORNER_TOPLEFT");

    /**
     * Element connects on the top-right corner.
     */
    public static final JunctionSides CORNER_TOPRIGHT = new JunctionSides(
            GtkJunctionSides.CORNER_TOPRIGHT, "CORNER_TOPRIGHT");

    /**
     * Element connects on the bottom-left corner.
     */
    public static final JunctionSides CORNER_BOTTOMLEFT = new JunctionSides(
            GtkJunctionSides.CORNER_BOTTOMLEFT, "CORNER_BOTTOMLEFT");

    /**
     * Element connects on the bottom-right corner.
     */
    public static final JunctionSides CORNER_BOTTOMRIGHT = new JunctionSides(
            GtkJunctionSides.CORNER_BOTTOMRIGHT, "CORNER_BOTTOMRIGHT");

    /**
     * Element connects on the top side.
     */
    public static final JunctionSides TOP = new JunctionSides(GtkJunctionSides.TOP, "TOP");

    /**
     * Element connects on the bottom side.
     */
    public static final JunctionSides BOTTOM = new JunctionSides(GtkJunctionSides.BOTTOM, "BOTTOM");

    /**
     * Element connects on the left side.
     */
    public static final JunctionSides LEFT = new JunctionSides(GtkJunctionSides.LEFT, "LEFT");

    /**
     * Element connects on the right side.
     */
    public static final JunctionSides RIGHT = new JunctionSides(GtkJunctionSides.RIGHT, "RIGHT");
}
