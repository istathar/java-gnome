/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
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
 *         Gtk.init(args);
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
 * <p>
 * The meat of this library is the {@link Notification} class; see there.
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
        if (isInitialized()) {
            throw new IllegalStateException("Notification already initialized");
        }

        /*
         * Initialize notification system.
         */
        return NotifyMain.notifyInit(applicationName);
    }

    /**
     * Uninitialize the notification system. <b>This should be called when
     * notification is no longer needed (i.e. upon exit).</b>
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
    public static boolean isInitialized() {
        return NotifyMain.notifyIsInitted();
    }

    /**
     * Returns the registered application name. This is as passed when you
     * called {@link #init(String) Notify.init()}.
     * 
     * @since 4.0.12
     */
    public static String getApplicationName() {
        return NotifyMain.notifyGetAppName();
    }

    /**
     * Returns a list of features supported by the notification system.
     * 
     * <p>
     * See the "D-BUS Protocol" section in <i>Desktop Notifications
     * Specification</i> for standard capabilities.
     * 
     * @since 4.0.12
     */
    public static String[] getServerCapabilities() {
        if (!isInitialized()) {
            throw new IllegalStateException("Notification isn't initialized");
        }
        return NotifyMainOverride.getServerCapabilities();
    }
}
