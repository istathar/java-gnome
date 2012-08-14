/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008      Vreixo Formoso
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
package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * The type of antialiasing to do when rendering text or shapes.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public class Antialias extends Constant
{
    private Antialias(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Use the default antialiasing for the subsystem and target device.
     * Assuming your X server is hehaving, this will Just Work (tm) properly,
     * and is what you want.
     */
    public static final Antialias DEFAULT = new Antialias(CairoAntialias.DEFAULT, "DEFAULT");

    /**
     * Perform single-color antialiasing.
     * 
     * <p>
     * <i>This means (for example) "using shades of gray for black text on a
     * white background".</i>
     */
    /*
     * FIXME what does this actually mean, really?
     */
    public static final Antialias GRAY = new Antialias(CairoAntialias.GRAY, "GRAY");

    /**
     * Don't do antialiasing.
     * 
     * <p>
     * <i>Strictly, this means using a "bilevel alpha mask".</i>
     */
    public static final Antialias NONE = new Antialias(CairoAntialias.NONE, "NONE");

    /**
     * Perform antialiasing based on the subpixel ordering.
     * 
     * <p>
     * When the layout of the individual colour elements making up each
     * individual pixel on an LCD panel is known, then Cairo is able to do an
     * even more subtle job of antialiasing.
     * 
     * <p>
     * This has no effect on CRT monitors, where the pixels are rendered as
     * unique points by the ray gun, and are not the result of the cumulative
     * effect of three different co-located light sources at each pixel.
     */
    public static final Antialias SUBPIXEL = new Antialias(CairoAntialias.SUBPIXEL, "SUBPIXEL");
}
