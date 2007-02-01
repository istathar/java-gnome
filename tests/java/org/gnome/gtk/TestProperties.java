/*
 * TestProperties.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import junit.framework.TestCase;

/**
 * Test characteristic getters and setters to ensure correct values are
 * retrieved.
 * 
 * @author Andrew Cowie
 */
public class TestProperties extends TestCase
{
    /**
     * Test changing the label of a Button and getting the label back.
     */
    public final void testButtonPropertyLabel() {
        final Button b;
        final String originalLabel = "Hello wonderful World";
        final String endingLabel = "Goodbye cruel World";

        b = new Button(originalLabel);
        assertEquals(
                "The text retrieved by getLabel() was not that set by the Button <init>() constructor!",
                b.getLabel(), originalLabel);

        b.setLabel(endingLabel);
        assertEquals(
                "Incorrect text retrieved from Button's getLabel() following a call to setLabel() with that text!",
                b.getLabel(), endingLabel);
    }

    /**
     * Test ReliefStyle, a series of enum constants. Attempt to set a non-zero
     * enum, and make sure we get it back, by identity
     */
    public final void testButtonReliefStyle() {
        final Button b;

        b = new Button();

        // Button's getRelief() is temporarily implemented with
        // getPropertyEnum().
        assertSame("Default ReliefStyle is not what was expected, validity of test case is affected.", b
                .getRelief(), ReliefStyle.NORMAL);

        // NONE is #3 (ie ordinal 2, not zero)
        ReliefStyle rs = ReliefStyle.NONE;
        b.setRelief(rs);

        // verify we get that object back.
        assertSame("Set ReliefStyle constant is not the object that was retrieved when calling getter!",
                b.getRelief(), rs);
    }
}
