/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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

import org.freedesktop.bindings.Flag;

/**
 * The events a Widget will receive. You can use the Flags defined here to
 * control which events will be received by a Widget.
 * 
 * <p>
 * While most common events are enabled by default, some of them need to be
 * {@link org.gnome.gtk.Widget#addEvents(EventMask) enabled} in order to be
 * received. Such cases are properly documented together with each event
 * signal, so unless specified there you usually do not need to worry about
 * this at all.
 * 
 * @author Vreixo Formoso
 * @since 4.0.15
 */
public final class EventMask extends Flag
{
    private EventMask(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Creates a new EventMask flag as the OR'ing or combination of two
     * EventMask flags.
     */
    public static EventMask or(EventMask one, EventMask two) {
        return (EventMask) Flag.orTwoFlags(one, two);
    }

    /**
     * FIXME?
     */
    public static final EventMask EXPOSURE = new EventMask(GdkEventMask.EXPOSURE_MASK, "EXPOSURE");

    /**
     * Enable/disable all {@link org.gnome.gtk.Widget.MotionNotifyEvent
     * Widget.MotionNotify} events.
     */
    public static final EventMask POINTER_MOTION = new EventMask(GdkEventMask.POINTER_MOTION_MASK,
            "POINTER_MOTION");

    /*
     * FUTURE this flag needs more researching before exposing it.
     */
    static final EventMask POINTER_MOTION_HINT = new EventMask(GdkEventMask.POINTER_MOTION_HINT_MASK,
            "POINTER_MOTION_HINT");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.MotionNotifyEvent
     * Widget.MotionNotify} events when any mouse button is pressed.
     */
    public static final EventMask BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON_MOTION_MASK,
            "BUTTON_MOTION");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.MotionNotifyEvent
     * Widget.MotionNotifyEvent} events when the left button is pressed.
     */
    public static final EventMask LEFT_BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON1_MOTION_MASK,
            "LEFT_BUTTON_MOTION");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.MotionNotifyEvent
     * Widget.MotionNotifyEvent} when the middle button is pressed.
     */
    public static final EventMask MIDDLE_BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON2_MOTION_MASK,
            "MIDDLE_BUTTON_MOTION");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.MotionNotifyEvent
     * Widget.MotionNotifyEvent} when the right button is pressed.
     */
    public static final EventMask RIGHT_BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON3_MOTION_MASK,
            "RIGHT_BUTTON_MOTION");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.ButtonPressEvent
     * Widget.ButtonPressEvent} events.
     */
    public static final EventMask BUTTON_PRESS = new EventMask(GdkEventMask.BUTTON_PRESS_MASK,
            "BUTTON_PRESS");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.ButtonReleaseEvent
     * Widget.ButtonReleaseEvent} events.
     */
    public static final EventMask BUTTON_RELEASE = new EventMask(GdkEventMask.BUTTON_RELEASE_MASK,
            "BUTTON_RELEASE");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.KeyPressEvent
     * Widget.KeyPressEvent} events.
     */
    public static final EventMask KEY_PRESS = new EventMask(GdkEventMask.KEY_PRESS_MASK, "KEY_PRESS");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.KeyReleaseEvent
     * Widget.KeyReleaseEvent} events.
     */
    public static final EventMask KEY_RELEASE = new EventMask(GdkEventMask.KEY_RELEASE_MASK,
            "KEY_RELEASE");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.EnterNotifyEvent
     * Widget.EnterNotifyEvent} events.
     */
    public static final EventMask ENTER_NOTIFY = new EventMask(GdkEventMask.ENTER_NOTIFY_MASK,
            "ENTER_NOTIFY");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.LeaveNotifyEvent
     * Widget.LeaveNotifyEvent} events.
     */
    public static final EventMask LEAVE_NOTIFY = new EventMask(GdkEventMask.LEAVE_NOTIFY_MASK,
            "LEAVE_NOTIFY");

    /**
     * Enable/disable focus related events, such as
     * {@link org.gnome.gtk.Widget.FocusInEvent Widget.FocusInEvent} and
     * {@link org.gnome.gtk.Widget.FocusOutEvent Widget.FocusOutEvent}.
     */
    public static final EventMask FOCUS_CHANGE = new EventMask(GdkEventMask.FOCUS_CHANGE_MASK,
            "FOCUS_CHANGE");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.VisibilityNotifyEvent
     * Widget.VisibilityNotifyEvent} events.
     */
    public static final EventMask VISIBILITY_NOTIFY = new EventMask(GdkEventMask.VISIBILITY_NOTIFY_MASK,
            "VISIBILITY_NOTIFY");

    /**
     * Enable/disable {@link org.gnome.gtk.Widget.ScrollEvent
     * Widget.ScrollEvent} events.
     */
    public static final EventMask SCROLL = new EventMask(GdkEventMask.SCROLL_MASK, "SCROLL");

    public static final EventMask STRUCTURE = new EventMask(GdkEventMask.STRUCTURE_MASK, "STRUCTURE");

    /*
     * FUTURE the following flags are undocumented for now, as they seem
     * unneeded or they need more researching.
     */
    static final EventMask PROPERTY_CHANGE = new EventMask(GdkEventMask.PROPERTY_CHANGE_MASK,
            "PROPERTY_CHANGE");

    static final EventMask PROXIMITY_IN = new EventMask(GdkEventMask.PROXIMITY_IN_MASK, "PROXIMITY_IN");

    static final EventMask PROXIMITY_OUT = new EventMask(GdkEventMask.PROXIMITY_OUT_MASK,
            "PROXIMITY_OUT");

    static final EventMask SUBSTRUCTURE = new EventMask(GdkEventMask.SUBSTRUCTURE_MASK, "SUBSTRUCTURE");

    static final EventMask ALL_EVENTS = new EventMask(GdkEventMask.ALL_EVENTS_MASK, "ALL_EVENTS");
}
