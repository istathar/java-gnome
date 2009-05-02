/*
 * Notify.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

package org.gnome.notify;

import org.gnome.glib.Glib;

/**
 * Notification system initialization point. A notification enabled program
 * written with java-gnome will boil down to this:
 * 
 * <pre>
 * public class ExampleNotificationApp
 *   
 *     public static void main(String[] args) {
 *         
 *         Gtk.init(args);
 *         
 *         Notify.init(&quot;example-app&quot;);
 *           
 *         // build user interface
 *         
 *         // create and display notifications for application events
 *           
 *         Gtk.main();
 *     }
 * }
 * </pre>
 * 
 * @see Notification
 * 
 * @author Serkan Kaba
 * @since 4.0.12
 * 
 * @see <a href="http://www.galago-project.org/specs/notification/">Desktop
 *      Notifications Spec</a>
 */
public final class Notify extends Glib
{
    private Notify() {}

    /**
     * Initialize the notification system. <b>This must be called before any
     * {@link Notification} methods are used.</b>
     * 
     * @param applicationName
     *            Name of the application initializing notification system.
     * @since 4.0.12
     */
    public static boolean init(String applicationName) {
        if (isInitted()) {
            throw new IllegalStateException("Notification already initialized");
        }

        /*
         * Initialize notification system.
         */
        return NotifyMain.notifyInit(applicationName);
    }

    /**
     * Uninitialize the notification system. <b>This should be called when
     * notification is no longer needed (i.e. upon exist).</b>
     * 
     * @since 4.0.12
     */
    public static void uninit() {
        NotifyMain.notifyUninit();
    }

    /**
     * Tests whether notification system is initialized or not.
     * 
     * @since 4.0.12
     */
    public static boolean isInitted() {
        return NotifyMain.notifyIsInitted();
    }
    
    /**
     * Returns the registered application name.
     * 
     * @see #init(String)
     * @since 4.0.12
     */
    public static String getApplicationName() {
        return NotifyMain.notifyGetAppName();
    }
    
    /**
     * Returns a list of features supported by the notification system.<br>
     * See <em>D-BUS Protocol</em> section in desktop notifications spec for
     * standard hints.
     * 
     * @since 4.0.12
     */
    public static String[] getServerCapabilities() {
        if (!isInitted()) {
            throw new IllegalStateException("Notification isn't initialized");
        }
        return NotifyMainOverride.getServerCapabilities();
    }
}
