/*
 * Operator.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * Constants specifying the compositing operating mode in effect. These are
 * set for a drawing Context using {@link Context#setOperator(Operator)
 * setOperator()}, and take effect when commands like {@link Context#paint()
 * paint()} are invoked.
 * 
 * @author Andrew Cowie
 * @author Zak Fenton
 * @since 4.0.7
 */
public class Operator extends Constant
{
    private Operator(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Clear a surface to all transparent.
     * 
     * @since 4.0.7
     */
    public static final Operator CLEAR = new Operator(CairoOperator.CLEAR, "CLEAR");

    /**
     * Draws the specified source object over the underlying object as if the
     * area below the source object did not exist.
     * 
     * <img class="snapshot" src="Operator-source.png">
     * 
     * <p>
     * Example:
     * <p>
     * Set up the object to be drawn over:
     * 
     * <pre>
     * cr.setSource(0.0, 1.0, 0.0);
     * cr.rectangle(15, 10, 50, 50);
     * cr.fill();
     * </pre>
     * <p>
     * Now set up the source object that will draw over the area beneath it:
     * 
     * <pre>
     * cr.setSource(1.0, 0.0, 0.0);
     * cr.rectangle(35, 35, 50, 50);
     * cr.setOperator(Operator.SOURCE);
     * cr.fill();
     * </pre>
     * 
     * @since 4.0.10
     */
    public static final Operator SOURCE = new Operator(CairoOperator.SOURCE, "SOURCE");

    /**
     * Default operator: draw over existing pixels.
     * 
     * @since 4.0.10
     */
    public static final Operator OVER = new Operator(CairoOperator.OVER, "OVER");
}
