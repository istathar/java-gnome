/*
 * ValidateConstants.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.glib;

import org.gnome.gdk.WindowState;
import org.gnome.gtk.FileChooser;
import org.gnome.gtk.FileChooserAction;
import org.gnome.gtk.FileChooserButton;
import org.gnome.gtk.TestCaseGtk;

/**
 * Validate the handling of enums and flags.
 * 
 * @author Vreixo Formoso
 */
/*
 * FIXME add validator for flags
 */
public class ValidateConstants extends TestCaseGtk
{
    public final void testEnumHandling() {
        FileChooser fc;
        FileChooserAction action;

        fc = new FileChooserButton("Test chooser", FileChooserAction.OPEN);

        action = fc.getAction();
        assertSame(FileChooserAction.OPEN, action);

        fc.setAction(FileChooserAction.SELECT_FOLDER);

        action = fc.getAction();
        assertSame(FileChooserAction.SELECT_FOLDER, action);
    }

    public final void testFlagsORing() {
        WindowState flag1, flag2;

        flag1 = WindowState.or(WindowState.ABOVE, WindowState.ICONIFIED);
        flag2 = WindowState.or(WindowState.ABOVE, WindowState.ICONIFIED);

        /* the same flag must not be created twice */
        assertSame(flag1, flag2);

        /* check correct ORing */
        assertTrue(flag1.contains(WindowState.ABOVE));
        assertTrue(flag1.contains(WindowState.ICONIFIED));
        assertFalse(flag1.contains(WindowState.WITHDRAWN));
        assertFalse(flag1.contains(WindowState.MAXIMIZED));
        assertFalse(flag1.contains(WindowState.STICKY));
        assertFalse(flag1.contains(WindowState.FULLSCREEN));
        assertFalse(flag1.contains(WindowState.BELOW));

        /* a new one... */
        flag2 = WindowState.or(WindowState.BELOW, WindowState.ICONIFIED);
        assertNotSame(flag1, flag2);

        /* both have WindowState.ICONIFIED in common */
        assertTrue(flag1.contains(flag2));

        /* ...and a final one */
        flag2 = WindowState.or(WindowState.BELOW, WindowState.MAXIMIZED);
        assertFalse(flag1.contains(flag2));

        /* now OR with an already OR'ed flags */
        flag2 = WindowState.or(WindowState.FULLSCREEN, flag1);
        assertTrue(flag1.contains(flag2));

        assertTrue(flag2.contains(WindowState.ABOVE));
        assertTrue(flag2.contains(WindowState.ICONIFIED));
        assertFalse(flag2.contains(WindowState.WITHDRAWN));
        assertFalse(flag2.contains(WindowState.MAXIMIZED));
        assertFalse(flag2.contains(WindowState.STICKY));
        assertTrue(flag2.contains(WindowState.FULLSCREEN));
        assertFalse(flag2.contains(WindowState.BELOW));
    }
}
