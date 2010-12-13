/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 */
package com.operationaldynamics.ui;

/*
 * This class imported from ObjectiveAccounts accounting package where it was
 * originally deployed as GPL code in generic.ui.Align
 */

/**
 * Alignment constants, for convenience. Use object indentity, ie
 * 
 * <pre>
 *           if (justify == Align.LEFT) { ... }
 * </pre>
 * 
 * or just grab a reference to use locally or statically in your class.
 * <p>
 * Originally created for {@link TextOutput}, now generally available for
 * anything that needs to specifiy alignment.
 * 
 * @author Andrew Cowie
 */
public final class Align
{
    private String name;

    private Align(String name) {
        this.name = name;
    }

    /**
     * Specify that you want to align whatever you are formatting or laying to
     * or on the left; or, use the left slot in a layout.
     */
    public static final Align LEFT = new Align("LEFT");

    /**
     * Indicate that you want to align whatever you are formatting with a
     * central tendancy, or, use the center slot in a layout (assuming, of
     * course that there is one).
     */
    public static final Align CENTER = new Align("CENTER");

    /**
     * Specify that you want to align whatever you are formatting or laying to
     * or on the right; or, use the right slot in a layout.
     */
    public static final Align RIGHT = new Align("RIGHT");

    /*
     * Just for debugging
     */
    public String toString() {
        return "Align." + name;
    }
}
