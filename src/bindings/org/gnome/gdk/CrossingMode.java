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
 * Constants relating to the nature of the event when a mouse enters or leaves
 * a GDK Window.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 * @see <a
 *      href="http://xorg.freedesktop.org/releases/X11R7.0/doc/PDF/xlib.pdf">The
 *      XLib programming manual, section 10.6</a>
 */
/*
 * FIXME Improve the explanation of these occurrences.
 */
public final class CrossingMode extends Constant
{
    private CrossingMode(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The EventCrossing occurred because of pointer motion (by the user).
     */
    public static final CrossingMode NORMAL = new CrossingMode(GdkCrossingMode.NORMAL, "NORMAL");

    /**
     * Event occurred because a grab was activated.
     */
    public static final CrossingMode GRAB = new CrossingMode(GdkCrossingMode.GRAB, "GRAB");

    /**
     * Event occurred because an ungrab happened.
     */
    public static final CrossingMode UNGRAB = new CrossingMode(GdkCrossingMode.UNGRAB, "UNGRAB");

    /**
     * Event occurred because a "GTK grab" happened. FIXME This means what?
     * How is it different from GRAB?
     */
    public static final CrossingMode GTK_GRAB = new CrossingMode(GdkCrossingMode.GTK_GRAB, "GTK_GRAB");

    /**
     * Event occurred because a "GTK ungrab" happened. FIXME This means what?
     * How is it different from the previously existing UNGRAB?
     */
    public static final CrossingMode GTK_UNGRAB = new CrossingMode(GdkCrossingMode.GTK_UNGRAB,
            "GTK_UNGRAB");

    /**
     * Event occurred because a Widget changed state.
     */
    public static final CrossingMode STATE_CHANGED = new CrossingMode(GdkCrossingMode.STATE_CHANGED,
            "STATE_CHANGED");
}
