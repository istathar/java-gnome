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
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Constants describing different units which are be used when giving the
 * dimensions of a piece of paper. Used when getting dimensions from a
 * {@link PaperSize}.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public final class Unit extends Constant
{
    private Unit(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Size in "points", which are defined as 1/72<sup>nd</sup> of an inch,
     * which works out to about 0.35 millimetres.
     */
    public static final Unit POINTS = new Unit(GtkUnit.POINTS, "POINTS");

    /**
     * Size in millimetres, which are 1/10<sup>th</sup> of a centimetre and
     * 1/1000<sup>th</sup> of a metre.
     */
    public static final Unit MM = new Unit(GtkUnit.MM, "MM");

    /**
     * Size in "inches", America's pre-industrial age measuring unit. Roughly
     * corresponds to the size of your big toe (the fact that there are 12
     * inches to a foot seems a bit strange given that most of us have only 10
     * toes).
     * 
     * <p>
     * Interestingly, a "two by four" is a common description for a wooden
     * beam used in construction, stemming from their having originally had a
     * cross-section of 2 by 4 inches. Successive generations of profiteering
     * forestry companies and unscrupulous builders have, however, cut margins
     * and shortchanged customers to the point where a modern "two by four"
     * you can buy at a lumber yard is barely a miserable 0.79 by 1.57 inches
     * in cross-section. This, amazingly enough, is exactly 2 by 4
     * centimetres.
     * 
     * <p>
     * <i>Reports of a conspiracy to force the Americans to switch to metric
     * are, obviously, completely baseless.</i>
     */
    public static final Unit INCH = new Unit(GtkUnit.INCH, "INCH");

    /*
     * What good is this?
     */
    public static final Unit PIXEL = new Unit(GtkUnit.PIXEL, "PIXEL");
}
