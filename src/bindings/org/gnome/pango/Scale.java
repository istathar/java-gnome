/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.pango;

import org.freedesktop.bindings.DoubleConstant;

/**
 * Constants use to scale text by an amount relative to that around it. This
 * is used when specifying text size with TextTag's
 * {@link org.gnome.gtk.TextTag#setScale(Scale) setScale()}.
 * 
 * <p>
 * The default, such as it is, is {@link #NORMAL NORMAL} which represents a
 * scaling factor of <code>1.0</code>, (ie. no scaling).
 * 
 * <p>
 * <i>In case you're wondering, these scaling constants are</i>
 * <code>(1.2)<sup>n</sup></code> <i>where</i> <code>n</code> <i>is
 * <code>1,2,3</code> for</i> {@link #LARGE LARGE}, {@link #X_LARGE X_LARGE},
 * {@link #XX_LARGE XX_LARGE}<i>, and <code>-1,-2,-3</code> for</i>
 * {@link #SMALL SMALL}, {@link #X_SMALL X_SMALL}, <i>and</i>
 * {@link #XX_SMALL XX_SMALL} <i>respectively.</i>
 * 
 * @since 4.0.9
 */
/*
 * FUTURE Ordinarily we would go to native, but at present we don't have a
 * mechanism for extracting #defines of doubles. So we'll hard code it for
 * now. DANGER! Watch out for changes in Pango upstream; if that should even
 * be suggested, write an Override class to access these constants.
 */
public class Scale extends DoubleConstant
{
    protected Scale(double factor, String nickname) {
        super(factor, nickname);
    }

    public static final Scale XX_SMALL = new Scale(1.0 / (1.2 * 1.2 * 1.2), "XX_SMALL");

    public static final Scale X_SMALL = new Scale(1.0 / (1.2 * 1.2), "X_SMALL");

    public static final Scale SMALL = new Scale(1.0 / 1.2, "SMALL");

    private static final Scale MEDIUM = new Scale(1.0, "MEDIUM");

    /**
     * Normal (unscaled) text, scaling factor <code>1.0</code>.
     */
    public static final Scale NORMAL = MEDIUM;

    public static final Scale LARGE = new Scale(1.2, "LARGE");

    public static final Scale X_LARGE = new Scale(1.2 * 1.2, "X_LARGE");

    public static final Scale XX_LARGE = new Scale(1.2 * 1.2 * 1.2, "XX_LARGE");
}
