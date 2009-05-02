/*
 * NotifyMain.java
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

final class NotifyMain extends Plumbing
{
    private NotifyMain() {}

    static final boolean init(String applicationName) {
        return notify_init(applicationName);
    }

    private static native final boolean notify_init(String applicationName);

    static final void uninit() {
        notify_uninit();
    }

    private static native final void notify_uninit();

    static final boolean isInitted() {
        return notify_is_initted();
    }

    private static native final boolean notify_is_initted();
    
    static final String[] getServerCapabilities() {
        return notify_get_server_caps();
    }
    
    private static native final String[] notify_get_server_caps();

}
