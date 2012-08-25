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
 * Arrow is a widget to draw simple arrows pointing to up, down, left, or
 * right. Its style can be either bevelled inwards, bevelled outwards, sunken
 * or raised. <img src="Arrow.png" class="snapshot">
 * 
 * @author Serkan Kaba
 * @author Andrew Cowie
 * @since 4.0.10
 */
/*
 * TODO Mention of defaults only makes sense if we have a no-arg constructor
 * using those defaults.
 */
public class Arrow extends Misc
{
    protected Arrow(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Arrow widget with given direction and shadow type.
     * 
     * @since 4.0.10
     */
    public Arrow(ArrowType direction, ShadowType type) {
        super(GtkArrow.createArrow(direction, type));
    }

    /**
     * Sets the direction to one of {@link ArrowType#UP UP},
     * {@link ArrowType#DOWN DOWN}, {@link ArrowType#LEFT LEFT},
     * {@link ArrowType#RIGHT RIGHT}. There's also a special mode,
     * {@link ArrowType#NONE NONE} which is an Arrow Widget but with no
     * graphic presently drawn on it.
     * 
     * <p>
     * The default is <code>RIGHT</code>.
     * 
     * @since 4.0.10
     */
    public void setArrowType(ArrowType direction) {
        setPropertyEnum("arrow-type", direction);
    }

    /**
     * Returns the direction of Arrow. See {@link #setArrowType(ArrowType)
     * setArrowType()} for possible values.
     * 
     * @since 4.0.10
     */
    public ArrowType getArrowType() {
        return (ArrowType) getPropertyEnum("arrow-type");
    }

    /**
     * Sets the shadow type to one of {@link ShadowType#IN IN},
     * {@link ShadowType#OUT OUT}, {@link ShadowType#ETCHED_IN ETCHED_IN} or
     * {@link ShadowType#ETCHED_OUT ETCHED_OUT}.
     * 
     * <p>
     * The default is <code>OUT</code>.
     * 
     * @since 4.0.10
     */
    public void setShadowType(ShadowType type) {
        setPropertyEnum("shadow-type", type);
    }

    /**
     * Returns the shadow type of Arrow. See
     * {@link #setShadowType(ShadowType) setShadowType()} for possible values.
     * 
     * @since 4.0.10
     */
    public ShadowType getShadowType() {
        return (ShadowType) getPropertyEnum("shadow-type");
    }

    /**
     * Returns amount of spaced used by arrow in the widget. This is the
     * <var>arrow-scaling</var> style property. Values will be in the range of
     * <code>0.0</code> to <code>1.0</code>. Its default is <code>0.7</code>,
     * though really, it's actual value will entirely be up to the theme
     * engine.
     * 
     * @since 4.0.10
     */
    public float getArrowScaling() {
        return getPropertyFloat("arrow-scaling");
    }
}
