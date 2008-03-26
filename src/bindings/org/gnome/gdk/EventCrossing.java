/*
 * EventCrossing.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

import org.gnome.gtk.Widget;

/**
 * Event data describing a mouse entering or leaving a Window.
 * 
 * <p>
 * This is used by {@link Widget.ENTER_NOTIFY_EVENT ENTER_NOTIFY_EVENT} and
 * {@link Widget.LEAVE_NOTIFY_EVENT LEAVE_NOTIFY_EVENT}.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public final class EventCrossing extends Event
{
    protected EventCrossing(long pointer) {
        super(pointer);
    }

    public CrossingMode getMode() {
        return GdkEventCrossing.getMode(this);
    }

    public NotifyType getDetail() {
        return GdkEventCrossing.getDetail(this);
    }
}
