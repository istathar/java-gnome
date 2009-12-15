/*
 * ValidateSettings.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import java.lang.reflect.Method;

import org.gnome.gdk.Screen;

/**
 * Test GtkSettings
 * 
 * @author Andrew Cowie
 */
public class ValidateGlobalSettings extends GraphicalTestCase
{
    /*
     * Screen.getDefault() isn't public [will it ever be?].
     */
    private static Screen introspectDefaultScreen() {
        final Method method;
        java.lang.Object result;

        try {
            method = Screen.class.getDeclaredMethod("getDefault", new Class<?>[] {});
            method.setAccessible(true);
            result = method.invoke(null, new java.lang.Object[] {});
            return (Screen) result;
        } catch (Exception e) {
            fail(e.toString());
            return null;
        }

    }

    public final void testFactory() {
        final Settings s1, s2;
        final Screen screen;

        s1 = Gtk.getSettings();

        screen = introspectDefaultScreen();
        s2 = GtkSettings.getForScreen(screen);

        assertSame(s1, s2);
    }

    public final void testShowImagesOnButtons() {
        final Settings settings;

        settings = Gtk.getSettings();

        assertTrue(settings.getButtonImages());
        settings.setButtonImages(false);
        assertFalse(settings.getButtonImages());
        settings.setButtonImages(true);
        assertTrue(settings.getButtonImages());
    }

    public final void testShowImagesInMenus() {
        final Settings settings;
        final ImageMenuItem quit;

        settings = Gtk.getSettings();

        /*
         * Amazingly, GtkSettings properties are dynamic at runtime; it's
         * GtkImageMenuItem's gtk_image_menu_item_class_init() that installs
         * the required property.
         */

        quit = new ImageMenuItem(Stock.QUIT);
        quit.getName();

        /*
         * So hooray, now we should be able to call the getter and setter and
         * not have GLib die for lack of a GParamSpec.
         */

        assertTrue(settings.getMenuImages());
        settings.setMenuImages(false);
        assertFalse(settings.getMenuImages());
        settings.setMenuImages(true);
        assertTrue(settings.getMenuImages());
    }

    public final void testShowInputMethodMenu() {
        final Settings settings;

        settings = Gtk.getSettings();

        assertTrue(settings.getShowInputMethodMenu());

        settings.setShowInputMethodMenu(false);
        assertFalse(settings.getShowInputMethodMenu());

        settings.setShowInputMethodMenu(true);
        assertTrue(settings.getShowInputMethodMenu());
    }

    public final void testShowUnicodeMenu() {
        final Settings settings;

        settings = Gtk.getSettings();

        assertTrue(settings.getShowUnicodeMenu());

        settings.setShowUnicodeMenu(false);
        assertFalse(settings.getShowUnicodeMenu());

        settings.setShowUnicodeMenu(true);
        assertTrue(settings.getShowUnicodeMenu());
    }
}
