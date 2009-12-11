/*
 * Settings.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Screen;
import org.gnome.glib.Object;

/**
 * Global settings for a GTK application.
 * 
 * @since 4.0.14
 */
public class Settings extends Object
{
    protected Settings(long pointer) {
        super(pointer);
    }

    /**
     * Get the Settings object for the default Screen.
     * 
     * @since 4.0.14
     */
    public static Settings getDefault() {
        return GtkSettings.getDefault();
    }

    /**
     * Get the Settings object for the given Screen
     * 
     * @since 4.0.14
     */
    public static Settings getForScreen(Screen screen) {
        return GtkSettings.getForScreen(screen);
    }
}
