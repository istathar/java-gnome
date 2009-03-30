/*
 * Notification.java
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

import org.gnome.glib.GlibException;
import org.gnome.gtk.StatusIcon;
import org.gnome.gtk.Widget;

public class Notification extends org.gnome.glib.Object
{
    /**
     * Constant for default timeout duration.
     */
    public static int NOTIFY_EXPIRES_DEFAULT = -1;

    /**
     * Constant for infinite timeout duration.
     */
    public static int NOTIFY_EXPIRES_NEVER = 0;

    protected Notification(long pointer) {
        super(pointer);
    }

    /**
     * Create a new notification.<br>
     * Summary appears on the titlebar of notification and body appears as its
     * text.<br>
     * Icon may be a string defining a theme icon or the filename identifying
     * the icon that appears next to text.<br>
     * Attach identifies the widget that the notification relates to.<br>
     * Note that all but summary is nullable.
     * 
     * @since 4.0.11
     */

    public Notification(String summary, String body, String icon, Widget attach) {
        super(NotifyNotification.createNotification(summary, body, icon, attach));
    }

    /**
     * Create a new notification attached to a {@link StatusIcon}.<br>
     * see {@link #Notification(String,String,String,Widget) Notification()}
     * for other parameters.
     * 
     * @since 4.0.11
     */
    public Notification(String summary, String body, String icon, StatusIcon statusIcon) {
        super(NotifyNotification.createNotificationWithStatusIcon(summary, body, icon, statusIcon));
    }

    /**
     * Updates the notification with given parameters see
     * {@link #Notification(String,String,String,Widget) Notification()} for
     * parameters.
     * 
     * @since 4.0.11
     */
    public void update(String summary, String body, String icon) {
        if (!NotifyNotification.update(this, summary, body, icon))
            throw new RuntimeException("Notification update failed.");
    }

    /**
     * Display the notification on screen.
     * 
     * @since 4.0.11
     */
    public void show() {
        try {
            NotifyNotification.show(this);
        } catch (GlibException e) {
            new RuntimeException(e.getMessage());
        }
    }

    /**
     * Set the timeout that the notification disappears.<br>
     * Use {@link Notification#NOTIFY_EXPIRES_DEFAULT NOTIFY_EXPIRES_DEFAULT}
     * for the default timeout duration.<br>
     * Use {@link Notification#NOTIFY_EXPIRES_NEVER NOTIFY_EXPIRES_NEVER} for
     * infinite timeout duration.<br>
     * 
     * @since 4.0.11
     */
    public void setTimeout(int timeout) {
        NotifyNotification.setTimeout(this, timeout);
    }

}
