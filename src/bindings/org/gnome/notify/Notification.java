/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.gdk.Pixbuf;
import org.gnome.glib.GlibException;
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
     * identifying the icon that appears next to text.
     * 
     * <p>
     * Note that all but summary can be <code>null</code>.
     * 
     * @since 4.0.19
     */

    public Notification(String summary, String body, String icon) {
        super(NotifyNotification.createNotification(summary, body, icon));
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
     * Set the timeout that the notification disappears in milliseconds. Use
     * {@link Notification#NOTIFY_EXPIRES_DEFAULT NOTIFY_EXPIRES_DEFAULT} for
     * the default timeout duration. Use
     * {@link Notification#NOTIFY_EXPIRES_NEVER NOTIFY_EXPIRES_NEVER} for
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
     * See the "Categories" section in <i>Desktop Notifications
     * Specification</i> for predefined categories and their meanings.
     * 
     * @since 4.0.12
     */
    public void setCategory(String category) {
        NotifyNotification.setCategory(this, category);
    }

    /**
     * Sets the urgency to one of {@link Urgency#LOW LOW},
     * {@link Urgency#NORMAL NORMAL}, or {@link Urgency#CRITICAL CRITICAL}.
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
     * Sets a hint with an integer value. See {@link #setHint(String, byte)
     * setHint()} for further details.
     * 
     * <p>
     * Example: <code>"x"</code> (sets the horizontal position of the
     * notification)
     * 
     * @since 4.0.12
     */
    public void setHint(String key, int value) {
        NotifyNotification.setHintInt32(this, key, value);
    }

    /**
     * Sets a hint with a double value. See {@link #setHint(String, byte)
     * setHint()} for further details.
     * 
     * @since 4.0.12
     */
    public void setHint(String key, double value) {
        NotifyNotification.setHintDouble(this, key, value);
    }

    /**
     * Sets a hint with a string value. See {@link #setHint(String, byte)
     * setHint()} for further details.
     * 
     * <p>
     * Example: <code>"sound-file"</code> (adds sound to the notification)
     * 
     * @since 4.0.12
     */
    public void setHint(String key, String value) {
        NotifyNotification.setHintString(this, key, value);
    }

    /**
     * Sets a hint with a byte value.
     * 
     * <p>
     * Hints are interpreted by the notification system in various ways to
     * modify the notification behavior and/or appearance. See the "Hints"
     * section in <i>Desktop Notifications Specification</i> for standard
     * hints and their interpretations.
     * 
     * @since 4.0.12
     */
    public void setHint(String key, byte value) {
        NotifyNotificationOverride.setHintByte(this, key, value);
    }

    /**
     * Sets a hint with a byte array data. See {@link #setHint(String, byte)
     * setHint()} for further details.
     * 
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
     * Signal emmitted when a notification is closed. This can happen in three
     * ways:
     * 
     * <ul>
     * <li>When notification timeout expires.</li>
     * <li>When user dismisses it by closing.</li>
     * <li>When {@link #close() close()} is called.</li>
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
     * Callback invoked when an added action is invoked. See Notification's
     * {@link Notification#addAction(String, String, Notification.Action)
     * addAction()}, which is the where this is used from.
     * 
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
            if (actionID.equals(action)) {
                handler.onAction(source, action);
            }
        }
    }

    /**
     * Add an action to a notification. Notification-daemon displays these as
     * buttons.
     * 
     * <p>
     * <i>Note that for some bizarre reason the new Notify-OSD of Ubuntu Linux
     * does not support actions at all. To determine if notification system
     * supports actions look for</i> <code>"actions"</code> <i>in
     * capabilities.</i>
     * 
     * @see Notify#getServerCapabilities()
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
