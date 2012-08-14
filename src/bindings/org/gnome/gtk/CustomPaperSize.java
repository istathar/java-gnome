/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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

import java.util.Locale;

/**
 * Page sizes different than the established standards available as constants
 * in the PaperSize, InternationalPaperSize, and NorthAmericanPaperSize
 * classes.
 * 
 * <p>
 * 
 * <pre>
 * paper = new CustomPaperSize(&quot;Business Card&quot;, 90, 55, Unit.MM);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 */
public class CustomPaperSize extends PaperSize
{
    /**
     * Create a PaperSize constant (or, at least, once you've created one you
     * don't need to create any more) for a custom size.
     * 
     * <p>
     * The <code>width</code> and <code>height</code> parameters are in terms
     * of <code>units</code>, obviously. Note that you must use a physical
     * measurement; you can't specify pixels.
     * 
     * <p>
     * You need to specify a human readable display name describing this
     * PaperSize in a word or two. You should mark it for translation. Try
     * <code>"Custom"</code> if you're lacking for inspiration.
     * 
     * @since 4.0.14
     */
    /*
     * We don't expose internal identifier strings or ordinals in java-gnome.
     * So, to create the handle we need for the first argument of the native
     * constructor function, munge the given display name into something
     * identifier-ish.
     */
    public CustomPaperSize(String name, double width, double height, Unit units) {
        super(GtkPaperSize.createPaperSizeCustom(mungeIntoHandle(name), name, width, height,
                checkUnits(units)));
    }

    private static String mungeIntoHandle(String name) {
        String str;

        if ((name == null) || (name.equals(""))) {
            throw new IllegalArgumentException("Must specify a human readable display name");
        }

        str = name.toLowerCase(Locale.ENGLISH);
        str = str.replaceAll(" ", "_");

        return str;
    }

    private static Unit checkUnits(Unit units) {
        if (units == Unit.PIXEL) {
            throw new IllegalArgumentException(
                    "Size can't be specified in pixels. You have to use a physical measurement");
        }
        return units;
    }
}
