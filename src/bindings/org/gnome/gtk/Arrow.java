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

/*
 * Arrow is a widget to draw simple arrows pointing to
 * <code>ArrowType.UP</code>, <code>ArrowType.DOWN</code>,
 *  <code>ArrowType.LEFT</code> or <code>ArrowType.RIGHT</code>.
 * Its style can be either of <code>ShadowType.IN</code>, <code>ShadowType.OUT</code>,
 * <code>ShadowType.ETCHED_IN</code> or <code>ShadowType.ETCHED_OUT</code>.
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
     * Create a new Arrow widget with given direction and style.
     */
    public Arrow(ArrowType arrowType, ShadowType shadowType) {
        super(GtkArrow.createArrow(arrowType, shadowType));
    }
    
    /**
     * Sets the direction and style of Arrow widget.
     */
    public void set(ArrowType arrowType, ShadowType shadowType) {
        GtkArrow.set(this, arrowType, shadowType);
    }
}
