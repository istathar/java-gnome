/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd
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
 * Constants specifying what strategy is employed when drawing Patterns larger
 * than the area intrinsically specified when constructing the Pattern.
 * 
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class Extend extends Constant
{
    private Extend(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Pixels outside the Pattern area are transparent.
     * 
     * <p>
     * This is the default for {@link SurfacePattern}s.
     * 
     * @since 4.0.17
     */
    public static final Extend NONE = new Extend(CairoExtend.NONE, "NONE");

    /**
     * Tile the Pattern by repeating it continuously.
     * 
     * @since 4.0.17
     */
    public static final Extend REPEAT = new Extend(CairoExtend.REPEAT, "REPEAT");

    /**
     * Tile the Pattern by reflecting it at its edges.
     * 
     * @since 4.0.17
     */
    public static final Extend REFLECT = new Extend(CairoExtend.REFLECT, "REFLECT");

    /**
     * Extend the Pattern by continuing (copying) the last pixel at the edges.
     * 
     * <p>
     * This is the default for the gradient based {@link LinearPattern} and
     * {@link RadialPattern}.
     * 
     * @since 4.0.17
     */
    public static final Extend PAD = new Extend(CairoExtend.PAD, "PAD");
}
