/*
 * ValidateSignalEmission.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test methods which cause signal emission.
 * 
 * @author Andrew Cowie
 */
public class ValidateSignalEmission extends TestCaseGtk
{
    private boolean result;

    public void setUp() {
        super.setUp();
        result = false;
    }

    public final void testAdjustmentValueChanged() {
        final Adjustment adj;

        adj = new Adjustment(5, 0, 100, 2, 5, 10);
        adj.connect(new Adjustment.VALUE_CHANGED() {
            public void onValueChanged(Adjustment source) {
                result = true;
            }
        });
        adj.emitValueChanged();

        cycleMainLoop();
        assertTrue("VALUE_CHANGED wasn't handled", result);
    }

    public final void testAdjustmentChanged() {
        final Adjustment adj;

        assertFalse(result);

        adj = new Adjustment(5, 0, 100, 2, 5, 10);
        adj.connect(new Adjustment.CHANGED() {
            public void onChanged(Adjustment source) {
                result = true;
            }
        });
        adj.emitChanged();

        cycleMainLoop();
        assertTrue("CHANGED wasn't handled", result);
    }
}
