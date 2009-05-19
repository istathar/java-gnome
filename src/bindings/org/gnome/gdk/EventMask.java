/*
 * EventMask.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

import org.freedesktop.bindings.Flag;
import org.gnome.gtk.Widget;

/**
 * The events a Widget will receive. You can use the Flags defined here to
 * control which events will be received by a Widget.
 * 
 * <p>
 * While most common events are enabled by default, some of them need to be
 * {@link Widget#enableEvents(EventMask) enabled} in order to be received.
 * Such cases are properly documented together with each event signal, so
 * unless specified there you usually do not need to worry about this at all.
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
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
     * Creates a new EventMask containing all flags enabled in the first
     * EventMask that were disabled in the second EventMask.
     */
    public static EventMask minus(EventMask one, EventMask two) {
        return (EventMask) Flag.minusTwoFlags(one, two);
    }

    /**
     * Enable/disable {@link Widget.ExposeEvent Expose} events.
     */
    public static final EventMask EXPOSURE = new EventMask(GdkEventMask.EXPOSURE_MASK, "EXPOSURE");

    /**
     * Enable/disable all {@link Widget.MotionNotifyEvent MotionNotify}
     * events.
     */
    public static final EventMask POINTER_MOTION = new EventMask(GdkEventMask.POINTER_MOTION_MASK,
            "POINTER_MOTION");

    /*
     * FUTURE this flag needs more researching before exposing it.
     */
    static final EventMask POINTER_MOTION_HINT = new EventMask(GdkEventMask.POINTER_MOTION_HINT_MASK,
            "POINTER_MOTION_HINT");

    /**
     * Enable/disable {@link Widget.MotionNotifyEvent MotionNotify} events
     * when any mouse button is pressed.
     */
    public static final EventMask BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON_MOTION_MASK,
            "BUTTON_MOTION");

    /**
     * Enable/disable {@link Widget.MotionNotifyEvent MotionNotify} events
     * when the left button is pressed.
     */
    public static final EventMask LEFT_BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON1_MOTION_MASK,
            "LEFT_BUTTON_MOTION");

    /**
     * Enable/disable {@link Widget.MotionNotifyEvent MotionNotify} events
     * when the middle button is pressed.
     */
    public static final EventMask MIDDLE_BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON2_MOTION_MASK,
            "MIDDLE_BUTTON_MOTION");

    /**
     * Enable/disable {@link Widget.MotionNotifyEvent MotionNotify} events
     * when the right button is pressed.
     */
    public static final EventMask RIGHT_BUTTON_MOTION = new EventMask(GdkEventMask.BUTTON3_MOTION_MASK,
            "RIGHT_BUTTON_MOTION");

    /**
     * Enable/disable {@link Widget.ButtonPressEvent ButtonPress} events.
     */
    public static final EventMask BUTTON_PRESS = new EventMask(GdkEventMask.BUTTON_PRESS_MASK,
            "BUTTON_PRESS");

    /**
     * Enable/disable {@link Widget.ButtonReleaseEvent ButtonRelease} events.
     */
    public static final EventMask BUTTON_RELEASE = new EventMask(GdkEventMask.BUTTON_RELEASE_MASK,
            "BUTTON_RELEASE");

    /**
     * Enable/disable {@link Widget.KeyPressEvent KeyPress} events.
     */
    public static final EventMask KEY_PRESS = new EventMask(GdkEventMask.KEY_PRESS_MASK, "KEY_PRESS");

    /**
     * Enable/disable {@link Widget.KeyReleaseEvent KeyRelease} events.
     */
    public static final EventMask KEY_RELEASE = new EventMask(GdkEventMask.KEY_RELEASE_MASK,
            "KEY_RELEASE");

    /**
     * Enable/disable {@link Widget.EnterNotifyEvent EnterNotify} events.
     */
    public static final EventMask ENTER_NOTIFY = new EventMask(GdkEventMask.ENTER_NOTIFY_MASK,
            "ENTER_NOTIFY");

    /**
     * Enable/disable {@link Widget.LeaveNotifyEvent LeaveNotify} events.
     */
    public static final EventMask LEAVE_NOTIFY = new EventMask(GdkEventMask.LEAVE_NOTIFY_MASK,
            "LEAVE_NOTIFY");

    /**
     * Enable/disable focus related events, such as
     * {@link Widget.FocusInEvent FocusIn} and {@link Widget.FocusOutEvent
     * FocusOut}.
     */
    public static final EventMask FOCUS_CHANGE = new EventMask(GdkEventMask.FOCUS_CHANGE_MASK,
            "FOCUS_CHANGE");

    /**
     * Enable/disable {@link Widget.VisibilityNotifyEvent VisibilityNotify}
     * events.
     */
    public static final EventMask VISIBILITY_NOTIFY = new EventMask(GdkEventMask.VISIBILITY_NOTIFY_MASK,
            "VISIBILITY_NOTIFY");

    /**
     * Enable/disable {@link Widget.ScrollEvent Scroll} events.
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
