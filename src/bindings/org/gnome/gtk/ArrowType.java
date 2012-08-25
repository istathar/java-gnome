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

import org.freedesktop.bindings.Constant;

/**
 * Used to indicate the direction in which the arrow graphic on an
 * {@link Arrow} Widget will point.
 * 
 * @author Serkan Kaba
 * @author Andrew Cowie
 * @since 4.0.10
 */
public final class ArrowType extends Constant
{
    private ArrowType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Represents an upward pointing arrow.
     */
    public static final ArrowType UP = new ArrowType(GtkArrowType.UP, "UP");

    /**
     * Represents an downward pointing arrow.
     */
    public static final ArrowType DOWN = new ArrowType(GtkArrowType.DOWN, "DOWN");

    /**
     * Represents a left pointing arrow.
     */
    public static final ArrowType LEFT = new ArrowType(GtkArrowType.LEFT, "LEFT");

    /**
     * Represents an arrow pointing right.
     */
    public static final ArrowType RIGHT = new ArrowType(GtkArrowType.RIGHT, "RIGHT");

    /**
     * Don't draw an arrow.
     * 
     * <p>
     * <code>NONE</code> is a special mode which causes an Arrow Widget to
     * occupies the space that an normal Arrow would, but without having an
     * arrow graphic drawn in it. This can be useful in cases where you are
     * trying to normalize the size requests for a series of Widgets.
     */
    public static final ArrowType NONE = new ArrowType(GtkArrowType.NONE, "NONE");
}
