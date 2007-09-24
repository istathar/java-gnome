/*
 * ValidateProperties.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test characteristic getters and setters to ensure correct values are
 * retrieved.
 * 
 * @author Andrew Cowie
 */
public class ValidateProperties extends TestCaseGtk
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
        assertSame("Default ReliefStyle is not what was expected, validity of test case is affected.",
                b.getRelief(), ReliefStyle.NORMAL);

        // NONE is #3 (ie ordinal 2, not zero)
        ReliefStyle rs = ReliefStyle.NONE;
        b.setRelief(rs);

        // verify we get that object back.
        assertSame("Set ReliefStyle constant is not the object that was retrieved when calling getter!",
                b.getRelief(), rs);
    }

    public final void testAlignmentConstructorProperties() {
        final Alignment a;

        a = new Alignment(0.1f, 0.2f, 0.8f, 0.9f);
        assertEquals(0.1f, a.getAlignmentX(), 0.001);
        assertEquals(0.2f, a.getAlignmentY(), 0.001);
        assertEquals(0.8f, a.getScaleX(), 0.001);
        assertEquals(0.9f, a.getScaleY(), 0.001);
    }

    public final void testAlignmentParameterChecks() {
        Alignment a;

        try {
            a = new Alignment(-0.1f, 0.2f, 0.8f, 0.9f);
            fail("Should have thrown exception due to negative paramater");
        } catch (IllegalArgumentException iae) {
            //
        }
        try {
            a = new Alignment(1.1f, 0.2f, 0.8f, 0.9f);
            fail("Should have thrown exception due to paramater out of bounds");
        } catch (IllegalArgumentException iae) {
            //
        }

        a = new Alignment(0.1f, 0.2f, 0.8f, 0.9f);
        a.setAlignment(0.3f, 0.4f, 0.5f, 0.6f);

        assertEquals(0.3f, a.getAlignmentX(), 0.001);
        assertEquals(0.4f, a.getAlignmentY(), 0.001);
        assertEquals(0.5f, a.getScaleX(), 0.001);
        assertEquals(0.6f, a.getScaleY(), 0.001);

        try {
            a.setAlignment(0.3f, 0.4f, 0.5f, -0.6f);
            fail("Should have thrown exception due to paramater out of bounds");
        } catch (IllegalArgumentException iae) {
            //
        }
        assertEquals(0.6f, a.getScaleY(), 0.001);
    }

    /*
     * Just a quick check deciding to expose some constants.
     */
    public final void testAlignmentConstants() {
        assertEquals(0.0f, Alignment.LEFT, 0.0001f);
        assertEquals(0.5f, Alignment.CENTER, 0.0001f);
        assertEquals(1.0f, Alignment.RIGHT, 0.0001f);
        assertEquals(0.0f, Alignment.TOP, 0.0001f);
        assertEquals(1.0f, Alignment.BOTTOM, 0.0001f);
    }
}
