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

import org.gnome.glib.Glib;

final class Pango extends Glib
{
    private Pango() {}

    /**
     * Conversion factor to go from Cairo device units to "Pango Units".
     */
    /*
     * The Pango library uses a fixed point system, scaled up by this
     * constant; Cairo on the other hand uses doubles. Since all the drawing
     * we're doing with Pango will be in the context of a Cairo drawing
     * operation, we expose our API as doubles to match Cairo, and quietly
     * convert internally.
     * 
     * Seeing as how Pango draws on Cairo, it'd be nice if Pango just exposed
     * the doubles and was done with it.
     * 
     * Since we divide by this frequently, we keep it here as a double to
     * reduce the risk of creating divide-by-integer errors.
     * 
     * FUTURE retreive from native as this is subject to change in the future.
     */
    static final double SCALE = 1024.0;
}
