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

import org.freedesktop.bindings.Constant;

/**
 * The type of a low-level Event received. Although an {@link Event} is
 * delivered as a parameter of many signals (the "event" signals, in fact), in
 * general you will rarely need to make use of their information.
 * 
 * <p>
 * <b>WARNING</b> Many of these constants are clearly internal. There's a
 * strong argument to be made that some or all of these should not be publicly
 * visible.
 * 
 * <p>
 * TODO Most of these descriptions are terribly sparse and copied from the
 * underlying documentation. Ordinarily we wouldn't do that, but with any luck
 * you'll not need to be using these classes directly. If anyone has further
 * information on the detailed significance of these constants, <i>please</i>
 * contribute improvements to this class.
 * 
 * <p>
 * <i>This is principally used to discriminate what kind of concrete Event
 * subclass to create. Note that we are in GDK here; These events refer to the
 * low level constructs delivered to the <code>GdkWindow</code>s that underlie
 * Widgets.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
/*
 * If you need to see the precise mappings of EventTypes to Event subclasses,
 * see boxedFor() in org.gnome.gdk.Plumbing.
 */
public final class EventType extends Constant
{
    private EventType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /*
     * Note that "NOTHING" is not bound; in the C header files it has the
     * value -1. Eeek! Switch this to a flags instead of an enum if it turns
     * out we really need this ordinal.
     */

    /**
     * The top level Window should be hidden or destroyed, presumably due to
     * the user having clicked the close window button presented in the window
     * manager's decorations.
     */
    public static final EventType DELETE = new EventType(GdkEventType.DELETE, "DELETE");

    /**
     * The Window has been destroyed.
     */
    public static final EventType DESTROY = new EventType(GdkEventType.DESTROY, "DESTROY");

    /**
     * Part or all of the Window needs to be redrawn due to its having become
     * visible.
     */
    public static final EventType EXPOSE = new EventType(GdkEventType.EXPOSE, "EXPOSE");

    /**
     * The pointer has moved.
     */
    public static final EventType MOTION_NOTIFY = new EventType(GdkEventType.MOTION_NOTIFY,
            "MOTION_NOTIFY");

    /**
     * A mouse button has been pressed. As you would expect, an Event having
     * this EventType will be a {@link EventButton}.
     */
    public static final EventType BUTTON_PRESS = new EventType(GdkEventType.BUTTON_PRESS, "BUTTON_PRESS");

    /**
     * A mouse button has been double clicked. Each click should also have
     * generated a {@link EventType#BUTTON_PRESS BUTTON_PRESS} event;
     * regardless this will be handled in higher level semantics.
     * 
     * <p>
     * <i>Note that this is the constant <code>GDK_2BUTTON_PRESS</code> in the
     * underlying library; we had to rename it slightly for translation into a
     * legal Java identifier.</i>
     */
    public static final EventType BUTTON_PRESS_DOUBLE = new EventType(GdkEventType.BUTTON_PRESS_DOUBLE,
            "BUTTON_PRESS_DOUBLE");

    /**
     * A mouse button has been clicked three times in a brief period. You
     * would probably see this generated from the sequence:
     * 
     * <ul>
     * <li>click (<code>BUTTON_PRESS</code> emitted),
     * <li>click (<code>BUTTON_PRESS</code> then
     * <code>BUTTON_PRESS_DOUBLE</code> emitted),
     * <li>click (<code>BUTTON_PRESS</code> then
     * <code>BUTTON_PRESS_TRIPLE</code> emitted).
     * </ul>
     * Your results may vary, however, depending on what else might be
     * intercepting events along the way.
     * 
     * <p>
     * <i>Note that this is the constant <code>GDK_3BUTTON_PRESS</code> in the
     * underlying library; we had to rename it slightly for translation into a
     * legal Java identifier.</i>
     */
    /*
     * Thanks to Ryan Lortie for helping document the Event sequence.
     */
    public static final EventType BUTTON_PRESS_TRIPLE = new EventType(GdkEventType.BUTTON_PRESS_TRIPLE,
            "BUTTON_PRESS_TRIPLE");

    /**
     * A previously pressed mouse button has been released. This EventType
     * will be found in a {@link EventButton} Event.
     */
    public static final EventType BUTTON_RELEASE = new EventType(GdkEventType.BUTTON_RELEASE,
            "BUTTON_RELEASE");

    /**
     * A key has been pressed.
     */
    public static final EventType KEY_PRESS = new EventType(GdkEventType.KEY_PRESS, "KEY_PRESS");

    /**
     * A key has been released
     */
    public static final EventType KEY_RELEASE = new EventType(GdkEventType.KEY_RELEASE, "KEY_RELEASE");

    /**
     * The pointer has entered a Window. This corresponds to
     * {@link EventCrossing}.
     */
    public static final EventType ENTER_NOTIFY = new EventType(GdkEventType.ENTER_NOTIFY, "ENTER_NOTIFY");

    /**
     * The pointer has left a Window. This corresponds to
     * {@link EventCrossing}.
     */
    public static final EventType LEAVE_NOTIFY = new EventType(GdkEventType.LEAVE_NOTIFY, "LEAVE_NOTIFY");

    /**
     * The keyboard focus has entered or left the Window.
     */
    public static final EventType FOCUS_CHANGE = new EventType(GdkEventType.FOCUS_CHANGE, "FOCUS_CHANGE");

    /**
     * The size, position, or stacking order of a Window has been changed.
     * Apparently GTK discards these events for child Windows, but then you
     * won't notice this because you're not using these constants directly
     * anyway, right?
     */
    public static final EventType CONFIGURE = new EventType(GdkEventType.CONFIGURE, "CONFIGURE");

    /**
     * The Window has been mapped to the screen.
     */
    public static final EventType MAP = new EventType(GdkEventType.MAP, "MAP");

    /**
     * The Window has been unmapped from the screen.
     */
    public static final EventType UNMAP = new EventType(GdkEventType.UNMAP, "UNMAP");

    /**
     * A low level [ie X-windows] property of the Window has been changed or
     * removed.
     */
    public static final EventType PROPERTY_NOTIFY = new EventType(GdkEventType.PROPERTY_NOTIFY,
            "PROPERTY_NOTIFY");

    /**
     * Another application has requested a selection.
     */
    public static final EventType SELECTION_REQUEST = new EventType(GdkEventType.SELECTION_REQUEST,
            "SELECTION_REQUEST");

    /**
     * A selection has been received (from another application?).
     */
    public static final EventType SELECTION_NOTIFY = new EventType(GdkEventType.SELECTION_NOTIFY,
            "SELECTION_NOTIFY");

    /**
     * The application has been told that it no longer owns a selection.
     */
    public static final EventType SELECTION_CLEAR = new EventType(GdkEventType.SELECTION_CLEAR,
            "SELECTION_CLEAR");

    /**
     * An input device has moved into contact with a sensing surface (e.g. a
     * touchscreen or graphics tablet).
     */
    public static final EventType PROXIMITY_IN = new EventType(GdkEventType.PROXIMITY_IN, "PROXIMITY_IN");

    /**
     * An input device has moved out of contact with a sensing surface,
     * whatever that actually means.
     */
    public static final EventType PROXIMITY_OUT = new EventType(GdkEventType.PROXIMITY_OUT,
            "PROXIMITY_OUT");

    /**
     * The pointer has entered the Window during a drag operation.
     */
    public static final EventType DRAG_ENTER = new EventType(GdkEventType.DRAG_ENTER, "DRAG_ENTER");

    /**
     * The pointer has left the Window during a drag operation.
     */
    public static final EventType DRAG_LEAVE = new EventType(GdkEventType.DRAG_LEAVE, "DRAG_LEAVE");

    /**
     * The pointer has moved within the Window during a drag operation (why
     * would you ever care?).
     */
    public static final EventType DRAG_MOTION = new EventType(GdkEventType.DRAG_MOTION, "DRAG_MOTION");

    /**
     * The status of a drag operation initiated by this Window has changed
     * (TODO to?).
     */
    public static final EventType DRAG_STATUS = new EventType(GdkEventType.DRAG_STATUS, "DRAG_STATUS");

    /**
     * A drop operation onto this Window has started.
     */
    public static final EventType DROP_START = new EventType(GdkEventType.DROP_START, "DROP_START");

    /**
     * The drop operation initiated by the Window has been completed.
     */
    public static final EventType DROP_FINISHED = new EventType(GdkEventType.DROP_FINISHED,
            "DROP_FINISHED");

    /**
     * A message has been received from another application (TODO meaning
     * what?).
     */
    public static final EventType CLIENT_EVENT = new EventType(GdkEventType.CLIENT_EVENT, "CLIENT_EVENT");

    /**
     * The Window's visibility status has changed (TODO meaning what?)
     */
    public static final EventType VISIBILITY_NOTIFY = new EventType(GdkEventType.VISIBILITY_NOTIFY,
            "VISIBILITY_NOTIFY");

    /**
     * The source region was completely available when parts of a drawable
     * were copied. "This is not very useful" says the underlying API
     * documentation. Really!
     */
    public static final EventType NO_EXPOSE = new EventType(GdkEventType.NO_EXPOSE, "NO_EXPOSE");

    /**
     * The scroll wheel was turned.
     */
    public static final EventType SCROLL = new EventType(GdkEventType.SCROLL, "SCROLL");

    /**
     * The {@link WindowState state} of a Window has changed.
     */
    public static final EventType WINDOW_STATE = new EventType(GdkEventType.WINDOW_STATE, "WINDOW_STATE");

    /**
     * A setting (TODO what kind of setting?) has been modified.
     */
    public static final EventType SETTING = new EventType(GdkEventType.SETTING, "SETTING");

}
