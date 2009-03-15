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
import org.gnome.gtk.Widget;

public class Notification extends org.gnome.glib.Object
{
    protected Notification(long pointer) {
        super(pointer);
    }
    
    public Notification(String summary, String body, String icon, Widget attach) {
        super(NotifyNotification.createNotification(summary, body, icon, attach));
    }
    
    public void show() throws GlibException {
        NotifyNotification.show(this);
    }

}
