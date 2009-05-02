/*
 * NotificationOverride.java
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

final class NotificationOverride extends Plumbing
{
    private NotificationOverride() {}
    
    static final void setHintByte(Notification self, String key, short value) {
        if (self == null) {
            throw new IllegalArgumentException("self can't be null");
        }

        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        
        if(value < 0 || value > 255)
            throw new IllegalArgumentException("value should be between 0-255 inclusive");
        
        synchronized (lock) {
            notify_notification_set_hint_byte(pointerOf(self), key, value);
        }
    }

    private static native final void notify_notification_set_hint_byte(long self, String key, short value);
}
