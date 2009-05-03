/*
 * NotifyMainOverride.java
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

final class NotifyMainOverride extends Plumbing
{
    private NotifyMainOverride() {}

    static final String[] getServerCapabilities() {
        String[] result;

        synchronized (lock) {
            result = notify_get_server_caps();

            return result;
        }
    }

    private static native final String[] notify_get_server_caps();

}
