/*
 * Align.java
 * 
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 * 
 * This class imported from ObjectiveAccounts accounting package where it was
 * originally deployed as GPL code in generic.ui.Align
 */
package com.operationaldynamics.ui;

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
