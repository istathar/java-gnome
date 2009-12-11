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
public class ValidateSettings extends GraphicalTestCase
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

        s1 = Settings.getDefault();

        screen = introspectDefaultScreen();
        s2 = Settings.getForScreen(screen);

        assertSame(s1, s2);
    }
}
