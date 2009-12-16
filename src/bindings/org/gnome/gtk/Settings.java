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

import org.gnome.glib.Object;

/**
 * Global settings for a GTK application. Get this object by calling the
 * factory function {@link Gtk#getSettings() Gtk.getSettings()}.
 * 
 * <pre>
 * settings = Gtk.getSettings();
 * settings.setButtonImages(true);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 */
/*
 * The functions to get the GtkSettings objects aren't here for a couple
 * reasons. One is aesthetic; we've concentrated other functions on the
 * package class rather than having them as local static methods. The other
 * reason is completion space; there are property getters on this class, and
 * getDefault() just doesn't make sense alongside them.
 */
public class Settings extends Object
{
    protected Settings(long pointer) {
        super(pointer);
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

    /**
     * Set whether MenuItems should have images (notably stock icons) showing
     * on them by default.
     * 
     * <pre>
     * settings.setMenuImages(true);
     * </pre>
     * 
     * <p>
     * <i>Somewhat amazingly, GtkSettings properties are dynamic at runtime;
     * this property does not even exist until the underlying GtkImageMenuItem
     * class has initialized and installed the required property. So if wish
     * to call this you need to do so after you've built some menus.</i>
     * </p>
     * 
     * <p>
     * <i>The underlying GtkSetting is the "<var>gtk-menu-images</var>"
     * property.</i>
     * 
     * @since 4.0.14
     */
    /*
     * The function that needs to have run appears to be
     * gtk_image_menu_item_class_init() in gtk/gtkimagemenuitem.c
     */
    public void setMenuImages(boolean setting) {
        this.setPropertyBoolean("gtk-menu-images", setting);
    }

    /**
     * Are images (icons) being shown in menus by default?
     * 
     * @since 4.0.14
     */
    public boolean getMenuImages() {
        return this.getPropertyBoolean("gtk-menu-images");
    }

    /**
     * Should the context menus of TextViews and Entries have a menu item
     * offering to let you change the InputMethod?
     * 
     * <p>
     * Normally they do, so <code>true</code> is the default.
     * 
     * @since 4.0.14
     */
    public void setShowInputMethodMenu(boolean setting) {
        this.setPropertyBoolean("gtk-show-input-method-menu", setting);
    }

    /**
     * Do Entry and TextView popup context menus have a menu item allowing you
     * to change the InputMethod?
     * 
     * @since 4.0.14
     */
    public boolean getShowInputMethodMenu() {
        return this.getPropertyBoolean("gtk-show-input-method-menu");
    }

    /**
     * Should the context menus of TextViews and Entries have a menu item
     * offering to let you input unusual Unicode control sequences?
     * 
     * <p>
     * By default they do, so you can expect this to be <code>true</code>.
     * 
     * @since 4.0.14
     */
    public void setShowUnicodeMenu(boolean setting) {
        this.setPropertyBoolean("gtk-show-unicode-menu", setting);
    }

    /**
     * Are Entry and TextView popup context menus showing a menu item allowing
     * you to enter Unicode control characters?
     * 
     * @since 4.0.14
     */
    public boolean getShowUnicodeMenu() {
        return this.getPropertyBoolean("gtk-show-unicode-menu");
    }
}
