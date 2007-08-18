/*
 * ValidateOutParameters.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.gtk.Button;

public class ValidateOutParameters extends TestCaseGtk
{
    public final void testButtonAlignment() {
        final Button b;

        b = new Button("Text");

        b.setAlignment(0.7f, 0.3f);

        assertEquals(
                "Stored alignment not retreived; assumptions underlying out-parameter handling may be in error",
                0.7f, b.getAlignmentX(), 0.00001);
        assertEquals(
                "Stored alignment not retreived; assumptions underlying out-parameter handling may be in error",
                0.3f, b.getAlignmentY(), 0.00001);
    }

    /*
     * See also ValidateProperties.testAlignmentAlignmentAndScale(), a test
     * carried out earlier in the UnitTests sequence.
     */
    public final void testAlignmentPadding() {
        final Alignment a;

        a = new Alignment(0.1f, 0.2f, 0.8f, 0.9f);
        a.setPadding(2, 3, 6, 12);

        assertEquals(2, a.getPaddingTop());
        assertEquals(3, a.getPaddingBottom());
        assertEquals(6, a.getPaddingLeft());
        assertEquals(12, a.getPaddingRight());
    }
}
