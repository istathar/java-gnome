/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gdk;

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

    static {
        isLibraryReady();

        /*
         * When you call getScreen(), the object returned appears to be a
         * sublcass of GdkScreen, in this case GdkX11Screen. It doesn't seem
         * to be public, thus making GdkScreen the "concrete interface" to it
         * anyway. This actually reflects the fact that we don't have an
         * architectural treatment for dealing with getting GObject instances
         * back that are subtypes we don't know about.
         */
        registerType("GdkX11Screen", org.gnome.gdk.Screen.class);
        registerType("GdkX11Display", org.gnome.gdk.Display.class);
        registerType("GdkX11Window", org.gnome.gdk.Window.class);
    }

    /**
     * GdkEvent is a union, which would ordinarily present an insurmountable
     * problem, but since the GdkEvent structures all begin with a
     * GdkEventType we write a very hacky override which uses that as a
     * discriminator.
     */
    protected static Boxed boxedFor(Class<? extends Boxed> type, long pointer) {
        Boxed proxy;

        if (pointer == 0L) {
            return null;
        }

        /*
         * Handle the special case of the GdkEvent union.
         */
        if (type == Event.class) {
            int ordinal = getEventTypeOrdinal(pointer);
            switch (ordinal) {

            // FIXME this must be wrong, but what else should we do with it?
            case GdkEventType.DELETE:
            case GdkEventType.MAP:
            case GdkEventType.UNMAP:
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

            case GdkEventType.OWNER_CHANGE:
                type = EventOwnerChange.class;
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
                throw new UnsupportedOperationException("What GdkEventType is this?");
            }
        }

        proxy = org.gnome.glib.Plumbing.boxedFor(type, pointer);
        return proxy;
    }

    private static native final int getEventTypeOrdinal(long pointer);
}
