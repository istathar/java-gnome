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

final class NotifyNotificationOverride extends Plumbing
{
    private NotifyNotificationOverride() {}

    static final void setHintByte(Notification self, String key, byte value) {
        if (self == null) {
            throw new IllegalArgumentException("self can't be null");
        }

        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }

        synchronized (lock) {
            notify_notification_set_hint_byte(pointerOf(self), key, value);
        }
    }

    private static native final void notify_notification_set_hint_byte(long self, String key, byte value);

    static final void setHintByteArray(Notification self, String key, byte[] value) {
        if (self == null) {
            throw new IllegalArgumentException("self can't be null");
        }

        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }

        if (value == null) {
            throw new IllegalArgumentException("value can't be null");
        }

        if (value.length == 0) {
            throw new IllegalArgumentException("array lenght should be greater than 0");
        }

        synchronized (lock) {
            notify_notification_set_hint_byte_array(pointerOf(self), key, value);
        }
    }

    private static native final void notify_notification_set_hint_byte_array(long self, String key,
            byte[] value);

    /**
     * Manually hookup the function that will emit our custom action signal.
     */
    static final void addAction(Notification self, String action, String label) {
        notify_notification_add_action(pointerOf(self), action, label);
    }

    private static native final void notify_notification_add_action(long self, String action,
            String label);

    static final void disconnectAllActions(Notification self) {
        notify_notification_disconnect_all_actions(pointerOf(self));
    }

    private static native final void notify_notification_disconnect_all_actions(long pointerOf);
}
