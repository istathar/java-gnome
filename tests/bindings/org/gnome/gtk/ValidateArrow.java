/*
 * ValidateArrow.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test Arrow constructor and properties.
 * 
 * @author Serkan Kaba
 */
public class ValidateArrow extends GraphicalTestCase
{
    public final void testArrow() {
        final Arrow arrow;

        arrow = new Arrow(ArrowType.UP, ShadowType.NONE);
        assertEquals(arrow.getArrowType(), ArrowType.UP);
        assertEquals(arrow.getShadowType(), ShadowType.NONE);
        arrow.setArrowType(ArrowType.LEFT);
        arrow.setShadowType(ShadowType.ETCHED_IN);
        assertEquals(arrow.getArrowType(), ArrowType.LEFT);
        assertEquals(arrow.getShadowType(), ShadowType.ETCHED_IN);
    }
}
