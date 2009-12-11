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

    /**
     * Set whether Buttons should have images (ie stock icons) showing on them
     * by default.
     * 
     * <pre>
     * settings.setButtonImages(true);
     * </pre>
     * 
     * <p>
     * <i>The default was</i> <code>true</code> <i>before GNOME 2.28, but it
     * seems to have been changed as a result of changing the default value of
     * GConf key</i> <code>/desktop/gnome/interface/buttons_have_icons</code>
     * <i>. This allows you to return that setting to normal for your
     * application.</i>
     * 
     * <p>
     * <i>The underlying GtkSetting is the "<var>gtk-button-images</var>"
     * property.</i>
     * 
     * @since 4.0.14
     */
    public void setButtonImages(boolean setting) {
        this.setPropertyBoolean("gtk-button-images", setting);
    }

    /**
     * Are images (icons) being shown on Buttons by default?
     * 
     * @since 4.0.14
     */
    public boolean getButtonImages() {
        return this.getPropertyBoolean("gtk-button-images");
    }
}
