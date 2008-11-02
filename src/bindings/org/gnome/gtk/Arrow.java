/*
 * Arrow.java
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

/**
 * Arrow is a widget to draw simple arrows
 * pointing to up, down,left or right or no arrow.
 * Its style can be either bewelled inwards,
 * bevelled outwards, sunken or raised.
 * 
 * @author Serkan Kaba
 * @since 4.0.10
 */
public class Arrow extends Misc
{
    protected Arrow(long pointer) {
        super(pointer);
    }
    
    /**
     * Create a new Arrow widget with given direction and shadow type.
     * 
     * @since 4.0.10
     */
    public Arrow(ArrowType arrowType, ShadowType shadowType) {
        super(GtkArrow.createArrow(arrowType, shadowType));
    }
    
    /**
     * Sets the direction to one of <code>ArrowType.UP</code>, <code>ArrowType.DOWN</code>,
     * <code>ArrowType.LEFT</code>, <code>ArrowType.RIGHT</code> or <code>ArrowType.NONE</code>.<br>
     * <code>ArrowType.NONE</code> is a special type which occupies the widget space but no arrow is drawn.<br>
     * Default is <code>ArrowType.RIGHT</code>.
     * 
     * @since 4.0.10
     */
    public void setArrowType(ArrowType arrowType) {
        setPropertyEnum("arrow-type", arrowType);
    }
    
    /**
     * Returns the direction of Arrow. See {@link setArrowType(ArrowType arrowType)}
     * for possible values.
     * 
     * @since 4.0.10
     */
    public ArrowType getArrowType() {
        return (ArrowType)getPropertyEnum("arrow-type");
    }
    
    /**
     * Sets the shadow type to one of
     * <code>ShadowType.IN</code>, <code>ShadowType.OUT</code>,
     * <code>ShadowType.ETCHED_IN</code> or <code>ShadowType.ETCHED_OUT</code>.<br>
     * Default is <code>ShadowType.OUT</code>.
     * 
     * @since 4.0.10
     */
    public void setShadowType(ShadowType shadowType) {
        setPropertyEnum("shadow-type", shadowType);
    }
    
    /**
     * Returns the shadow type of Arrow. See {@link setShadowType(ShadowType shadowType)}
     * for possible values.
     * 
     * @since 4.0.10
     */
    public ShadowType getShadowType() {
        return (ShadowType)getPropertyEnum("shadow-type");
    }
    
    /**
     * Returns amount of spaced used by arrow in the widget.<br>
     * Its values are in [0,1] range and default is 0.7.
     * 
     * @since 4.0.10
     */
    public float getArrowScaling() {
        return getPropertyFloat("arrow-scaling");
    }
}
