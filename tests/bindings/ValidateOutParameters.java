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
import org.gnome.gtk.Button;
import org.gnome.gtk.TestCaseGtk;

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
}
