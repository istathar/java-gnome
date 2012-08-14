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
package org.gnome.gdk;

/**
 * Constants describing the keys on a keypad (also known as a number pad).
 * 
 * <p>
 * This file serves mostly as an example of how Keyval can be subclassed to
 * make additional Key constants available that you may need.
 * 
 * <p>
 * <i>Beware that this class has to be loaded for these constants to be
 * recognized!</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class KeypadKeyval extends Keyval
{
    protected KeypadKeyval(int keyval, String name) {
        super(keyval, name);
    }

    public static final Keyval KPSpace = new Keyval(0xff80, "KPSpace");

    public static final Keyval KPTab = new Keyval(0xff89, "KPTab");

    public static final Keyval KPEnter = new Keyval(0xff8d, "KPEnter");

    public static final Keyval KPHome = new Keyval(0xff95, "KPHome");

    public static final Keyval KPLeft = new Keyval(0xff96, "KPLeft");

    public static final Keyval KPUp = new Keyval(0xff97, "KPUp");

    public static final Keyval KPRight = new Keyval(0xff98, "KPRight");

    public static final Keyval KPDown = new Keyval(0xff99, "KPDown");

    public static final Keyval KPPageUp = new Keyval(0xff9a, "KPPageUp");

    public static final Keyval KPPageDown = new Keyval(0xff9b, "KPPageDown");

    public static final Keyval KPEnd = new Keyval(0xff9c, "KPEnd");

    public static final Keyval KPInsert = new Keyval(0xff9e, "KPInsert");

    public static final Keyval KPDelete = new Keyval(0xff9f, "KPDelete");

    public static final Keyval KPEqual = new Keyval(0xffbd, "KPEqual");

    public static final Keyval KPMultiply = new Keyval(0xffaa, "KPMultiply");

    public static final Keyval KPAdd = new Keyval(0xffab, "KPAdd");

    public static final Keyval KPSubtract = new Keyval(0xffad, "KPSubtract");

    public static final Keyval KPDecimal = new Keyval(0xffae, "KPDecimal");

    public static final Keyval KPDivide = new Keyval(0xffaf, "KPDivide");

    /**
     * The middle key (the one labeled <b>5</b>) on a keyboard with a numeric
     * keypad. This is distinct from {@link #KP5}, which is what is emitted if
     * <b>NumLock</b> is on.
     * 
     * <p>
     * <i>Why "Begin"? I would have thought "Center". Weird legacy crap, no
     * doubt.</i>
     */
    public static final Keyval KPBegin = new Keyval(0xff9d, "KPBegin");

    public static final Keyval KP0 = new Keyval(0xffb0, "KP0");

    public static final Keyval KP1 = new Keyval(0xffb1, "KP1");

    public static final Keyval KP2 = new Keyval(0xffb2, "KP2");

    public static final Keyval KP3 = new Keyval(0xffb3, "KP3");

    public static final Keyval KP4 = new Keyval(0xffb4, "KP4");

    public static final Keyval KP5 = new Keyval(0xffb5, "KP5");

    public static final Keyval KP6 = new Keyval(0xffb6, "KP6");

    public static final Keyval KP7 = new Keyval(0xffb7, "KP7");

    public static final Keyval KP8 = new Keyval(0xffb8, "KP8");

    public static final Keyval KP9 = new Keyval(0xffb9, "KP9");
}
