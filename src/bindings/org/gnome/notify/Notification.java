/*
 * Notification.java
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

import org.gnome.gdk.Pixbuf;
import org.gnome.gdk.Screen;
import org.gnome.glib.GlibException;
import org.gnome.gtk.StatusIcon;
import org.gnome.gtk.Widget;

/**
 * Notification represents actual item to be displayed by notification system.
 * You can simply create and display a Notification after the system is
 * initialized with {@link Notify#init(String applicationName)
 * Notify.init(String applicationName)}
 * 
 * @see Notify
 * 
 * @author Serkan Kaba
 * @since 4.0.11
 */
public class Notification extends org.gnome.glib.Object
{
    /**
     * Constant for default timeout duration.
     */
    public static final int NOTIFY_EXPIRES_DEFAULT = -1;

    /**
     * Constant for infinite timeout duration.
     */
    public static final int NOTIFY_EXPIRES_NEVER = 0;

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
     * Attaches Notification to a {@link Widget} setting hints to its location.
     */
    public void attach(Widget attach) {
        NotifyNotification.attachToWidget(this, attach);
    }

    /**
     * Attaches Notification to a {@link StatusIcon} setting hints to its location.
     */
    public void attach(StatusIcon statusIcon) {
        NotifyNotification.attachToStatusIcon(this, statusIcon);
    }
    
    public void setGeometryHints(Screen screen, int x, int y) {
        NotifyNotification.setGeometryHints(this, screen, x, y);
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
            throw new RuntimeException(e.getMessage());
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

    /**
     * Set the category of the notification which may later be used to
     * displayer or filter out the notification.
     * 
     * @since 4.0.11
     */
    public void setCategory(String category) {
        NotifyNotification.setCategory(this, category);
    }

    /**
     * Sets the urgency to one of {@link Urgency#LOW LOW},
     * {@link Urgency#NORMAL NORMAL}, {@link Urgency#CRITICAL CRITICAL}.
     * 
     * @since 4.0.11
     */
    public void setUrgency(Urgency urgency) {
        NotifyNotification.setUrgency(this, urgency);
    }

    /**
     * Sets the icon of the notification from a {@link Pixbuf}
     * 
     * @since 4.0.11
     */
    public void setIcon(Pixbuf icon) {
        NotifyNotification.setIconFromPixbuf(this, icon);
    }
    
    public void setHint(String key,int value) {
        NotifyNotification.setHintInt32(this,key,value);
    }
    
    public void setHint(String key,double value) {
        NotifyNotification.setHintDouble(this,key,value);
    }
    
    public void setHint(String key,String value) {
        NotifyNotification.setHintString(this,key,value);
    }
    
    public void clearHints() {
        NotifyNotification.clearHints(this);
    }
    
    //TODO: setHintByte

    /**
     * Hide the notification on screen.
     * 
     * @since 4.0.11
     */
    public void close() {
        try {
            NotifyNotification.close(this);
        } catch (GlibException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
