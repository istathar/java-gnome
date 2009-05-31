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
 * Notification represents an actual item to be displayed by notification
 * system. You can simply create and display a Notification after the system
 * is initialized with {@link Notify#init(String) Notify.init()}.
 * 
 * @author Serkan Kaba
 * @since 4.0.12
 * 
 * @see <a href="http://www.galago-project.org/specs/notification/">Desktop
 *      Notifications Spec</a>
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
     * Create a new notification.
     * 
     * <p>
     * The summary appears on the titlebar of notification and body appears as
     * its text. Icon may be a string defining a theme icon or the filename
     * identifying the icon that appears next to text. Attach identifies the
     * widget that the notification relates to.
     * 
     * <p>
     * Note that all but summary is nullable.
     * 
     * @since 4.0.12
     */

    public Notification(String summary, String body, String icon, Widget attach) {
        super(NotifyNotification.createNotification(summary, body, icon, attach));
    }

    /**
     * Create a new notification attached to a {@link StatusIcon}. See
     * {@link #Notification(String,String,String,Widget) Notification()} for
     * other parameters.
     * 
     * @since 4.0.12
     */
    public Notification(String summary, String body, String icon, StatusIcon statusIcon) {
        super(NotifyNotification.createNotificationWithStatusIcon(summary, body, icon, statusIcon));
    }

    /**
     * Updates the notification with given parameters see
     * {@link #Notification(String,String,String,Widget) Notification()} for
     * parameters.
     * 
     * @since 4.0.12
     */
    public void update(String summary, String body, String icon) {
        if (!NotifyNotification.update(this, summary, body, icon)) {
            throw new RuntimeException("Notification update failed.");
        }
    }

    /**
     * Attaches Notification to a {@link Widget} setting hints to its
     * location.
     * 
     * @since 4.0.12
     */
    public void attach(Widget attach) {
        NotifyNotification.attachToWidget(this, attach);
    }

    /**
     * Attaches Notification to a {@link StatusIcon} setting hints to its
     * location.
     * 
     * @since 4.0.12
     */
    public void attach(StatusIcon statusIcon) {
        NotifyNotification.attachToStatusIcon(this, statusIcon);
    }

    /**
     * Sets the position of the notification to display on screen.
     * 
     * @since 4.0.12
     */
    public void setGeometryHints(Screen screen, int x, int y) {
        NotifyNotification.setGeometryHints(this, screen, x, y);
    }

    /**
     * Display the notification on screen.
     * 
     * @since 4.0.12
     */
    public void show() {
        try {
            NotifyNotification.show(this);
        } catch (GlibException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Set the timeout that the notification disappears in milliseconds.<br>
     * Use {@link Notification#NOTIFY_EXPIRES_DEFAULT NOTIFY_EXPIRES_DEFAULT}
     * for the default timeout duration.<br>
     * Use {@link Notification#NOTIFY_EXPIRES_NEVER NOTIFY_EXPIRES_NEVER} for
     * infinite timeout duration.
     * 
     * @since 4.0.12
     */
    public void setTimeout(int timeout) {
        NotifyNotification.setTimeout(this, timeout);
    }

    /**
     * Set the category of the notification which may later be used to display
     * or filter out the notification.<br>
     * See <em>Hints</em> section for defined categories.
     * 
     * @since 4.0.12
     */
    public void setCategory(String category) {
        NotifyNotification.setCategory(this, category);
    }

    /**
     * Sets the urgency to one of {@link Urgency#LOW LOW},
     * {@link Urgency#NORMAL NORMAL}, {@link Urgency#CRITICAL CRITICAL}.
     * 
     * @since 4.0.12
     */
    public void setUrgency(Urgency urgency) {
        NotifyNotification.setUrgency(this, urgency);
    }

    /**
     * Sets the icon of the notification from a {@link Pixbuf}.
     * 
     * @since 4.0.12
     */
    public void setIcon(Pixbuf icon) {
        NotifyNotification.setIconFromPixbuf(this, icon);
    }

    /**
     * Sets a hint with an integer value.
     * 
     * @see #setHint(String, double)
     * @since 4.0.12
     */
    public void setHint(String key, int value) {
        NotifyNotification.setHintInt32(this, key, value);
    }

    /**
     * Sets a hint with a integer value. See the "Hints" section in <i>Desktop
     * Notifications Specification</i> for standard hints.
     * 
     * @since 4.0.12
     */
    public void setHint(String key, double value) {
        NotifyNotification.setHintDouble(this, key, value);
    }

    /**
     * Sets a hint with a string value.
     * 
     * @see #setHint(String, double)
     * @since 4.0.12
     */
    public void setHint(String key, String value) {
        NotifyNotification.setHintString(this, key, value);
    }

    /**
     * Sets a hint with a byte value.<br>
     * <b>Although this method accepts a short value, the value must be within
     * 0-255 limits.</b>
     * 
     * @see #setHint(String, double)
     * @since 4.0.12
     */
    public void setHint(String key, short value) {
        NotifyNotificationOverride.setHintByte(this, key, value);
    }
    
    /**
     * Sets a hint with a byte array data.
     * 
     * @see #setHint(String, double)
     * @since 4.0.12
     */
    public void setHint(String key, byte[] value) {
        NotifyNotificationOverride.setHintByteArray(this, key, value);
    }

    /**
     * Clear hints from the notification.
     * 
     * @since 4.0.12
     */
    public void clearHints() {
        NotifyNotification.clearHints(this);
    }

    /**
     * Hide the notification on screen.
     * 
     * @since 4.0.12
     */
    public void close() {
        try {
            NotifyNotification.close(this);
        } catch (GlibException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Signal emmitted when a notification is closed. This can happen in 3
     * ways:
     * <ul>
     * <li>When notification timeout expires.</li> <li>When user dismisses it
     * by closing.</li> <li>When {@link #close()} is called.</li>
     * </ul>
     * 
     * @author Serkan Kaba
     * @since 4.0.12
     */
    public interface Closed extends NotifyNotification.ClosedSignal
    {
        public void onClosed(Notification source);
    }

    /**
     * Hook up a handler to receive <code>Notification.Closed</code> events on
     * this Notification.
     * 
     * @since 4.0.12
     */
    public void connect(Notification.Closed handler) {
        NotifyNotification.connect(this, handler, false);
    }

    /**
     * Callback invoked when an added action is invoked.
     * 
     * @see Notification#addAction(String, String, Notification.Action)
     * @author Serkan Kaba
     * @since 4.0.12
     */
    public interface Action
    {
        public void onAction(Notification source, String action);
    }

    private static class ActionHandler implements NotifyNotification.ActionSignal
    {
        private final Action handler;

        private final String actionID;

        private ActionHandler(String actionID, Action handler) {
            this.handler = handler;
            this.actionID = actionID;
        }

        public void onAction(Notification source, String action) {
            if (actionID.equals(action))
                handler.onAction(source, action);
        }
    }

    /**
     * Add an action to a notification. Notification-daemon displays these as
     * buttons. Notify-OSD of Ubuntu doesn't support actions at all. To
     * determine if notification system supports actions look for "actions" in
     * capabilities.
     * 
     * @see Notify#getServerCapabilities()
     * @see Notification.Action
     * @since 4.0.12
     */
    public void addAction(String actionID, String label, Notification.Action action) {
        NotifyNotificationOverride.addAction(this, actionID, label);
        NotifyNotification.connect(this, new ActionHandler(actionID, action), false);
    }

    /**
     * Remove all added actions.
     * 
     * @since 4.0.12
     */
    public void clearActions() {
        NotifyNotification.clearActions(this);
        NotifyNotificationOverride.disconnectAllActions(this);
    }
}
