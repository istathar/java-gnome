/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
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
