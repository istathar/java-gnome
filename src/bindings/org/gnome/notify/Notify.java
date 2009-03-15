/*
 * Notify.java
 *
 * Copyright (c) 2006-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

package org.gnome.notify;

import org.gnome.glib.Glib;

public final class Notify extends Glib
{
    private Notify() {}
    
    public static boolean init(String applicationName) {
        if (isInitted()) {
            throw new IllegalStateException("Notification already initialized");
        }

        /*
         * Initialize notification system.
         */
        return NotifyMain.init(applicationName);
    }
    
    public static void uninit() {
        NotifyMain.uninit();
    }
    
    public static boolean isInitted() {
        return NotifyMain.isInitted();
    }
}
