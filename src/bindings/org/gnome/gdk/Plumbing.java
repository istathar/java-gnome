/*
 * Plumbing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gdk;

import org.freedesktop.bindings.Proxy;
import org.gnome.glib.Boxed;

/**
 * Provide handling for the special cases of the GdkEvent union.
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
public abstract class Plumbing extends org.gnome.glib.Plumbing
{
    protected Plumbing() {}

    /**
     * GdkEvent is a union, which would ordinarily present an insurmountable
     * problem, but since the GdkEvent structures all begin with a
     * GdkEventType we write a very hacky override which uses that as a
     * discriminator.
     */
    protected static Boxed boxedFor(Class type, long pointer) {
        Proxy proxy;

        if (pointer == 0L) {
            return null;
        }

        proxy = instanceFor(pointer);

        if (proxy != null) {
            /*
             * A Proxy already exists for this. Somewhat unexpected, but good
             * all the same. Simply return it.
             */
            return (Boxed) proxy;
        }

        /*
         * Handle the special case of the GdkEvent union.
         */
        if (type == Event.class) {
            int ordinal = getEventTypeOrdinal(pointer);
            switch (ordinal) {

            // FIXME this must be wrong, but what else should we do with it?
            case GdkEventType.DELETE:
                type = EventAny.class;
                break;

            case GdkEventType.KEY_PRESS:
            case GdkEventType.KEY_RELEASE:
                type = EventKey.class;
                break;

            case GdkEventType.BUTTON_PRESS:
            case GdkEventType.BUTTON_PRESS_DOUBLE:
            case GdkEventType.BUTTON_PRESS_TRIPLE:
            case GdkEventType.BUTTON_RELEASE:
                type = EventButton.class;
                break;

            case GdkEventType.SCROLL:
                type = EventScroll.class;
                break;

            // others?
            case GdkEventType.MOTION_NOTIFY:
                type = EventMotion.class;
                break;

            case GdkEventType.EXPOSE:
                type = EventExpose.class;
                break;

            case GdkEventType.VISIBILITY_NOTIFY:
                type = EventVisibility.class;
                break;

            case GdkEventType.ENTER_NOTIFY:
            case GdkEventType.LEAVE_NOTIFY:
                type = EventCrossing.class;
                break;

            case GdkEventType.FOCUS_CHANGE:
                type = EventFocus.class;
                break;

            case GdkEventType.CONFIGURE:
                type = EventConfigure.class;
                break;

            case GdkEventType.PROPERTY_NOTIFY:
                type = EventProperty.class;
                break;

            case GdkEventType.SELECTION_CLEAR:
            case GdkEventType.SELECTION_NOTIFY:
            case GdkEventType.SELECTION_REQUEST:
                type = EventSelection.class;
                break;

            case GdkEventType.DRAG_ENTER:
            case GdkEventType.DRAG_LEAVE:
            case GdkEventType.DRAG_MOTION:
            case GdkEventType.DRAG_STATUS:
            case GdkEventType.DROP_START:
            case GdkEventType.DROP_FINISHED:
                type = EventDragAndDrop.class;
                break;

            case GdkEventType.PROXIMITY_IN:
            case GdkEventType.PROXIMITY_OUT:
                type = EventProximity.class;
                break;

            case GdkEventType.WINDOW_STATE:
                type = EventWindowState.class;
                break;

            /*
             * And here we list values what we don't feel like mapping or
             * don't know what to do with. The .defs data for GdkEventType may
             * not be 100% complete, hence the default block. If you get here
             * and you think the event should be legitimately exposed, add a
             * public API class for it.
             */
            case GdkEventType.SETTING:
            case GdkEventType.CLIENT_EVENT:
            case GdkEventType.NO_EXPOSE:
            default:
                throw new UnsupportedOperationException();
            }
        }

        proxy = org.gnome.glib.Plumbing.boxedFor(type, pointer);
        return (Boxed) proxy;
    }

    private static native final int getEventTypeOrdinal(long pointer);
}
