/*
 * ArrowType.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Used to indicate the direction in which a {@link Arrow Arrow} should point.
 * 
 * @author Serkan Kaba
 * @since 4.0.10
 */
public final class ArrowType extends Constant
{
    private ArrowType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
    
    /**
     * Represents an upward pointing arrow.
     */
    public static final ArrowType UP = new ArrowType(GtkArrowType.UP, "GTK_ARROW_UP");
    
    /**
     * Represents an downward pointing arrow.
     */
    public static final ArrowType DOWN = new ArrowType(GtkArrowType.DOWN, "GTK_ARROW_DOWN");
    
    /**
     * Represents a left pointing arrow.
     */
    public static final ArrowType LEFT = new ArrowType(GtkArrowType.LEFT, "GTK_ARROW_LEFT");
    
    /**
     * Represents a left pointing right.
     */
    public static final ArrowType RIGHT = new ArrowType(GtkArrowType.RIGHT, "GTK_ARROW_RIGHT");
    
    /**
     * No arrow.
     */
    public static final ArrowType NONE = new ArrowType(GtkArrowType.NONE, "GTK_ARROW_NONE");
}
