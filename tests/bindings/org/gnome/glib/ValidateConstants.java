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

import org.gnome.gtk.FileChooser;
import org.gnome.gtk.FileChooserAction;
import org.gnome.gtk.FileChooserButton;
import org.gnome.gtk.TestCaseGtk;

/**
 * Validate the handling of enums and flasgs.
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
}
