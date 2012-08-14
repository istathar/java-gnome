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
package org.gnome.gtk;

import org.freedesktop.bindings.Flag;

/**
 * The current state of a Widget with respect to drawing and theming. Within
 * GTK this is used for sub-elements making up a Widget, and different
 * elements may be in different states. In practise, you only use this for
 * rare occasions when you need to override the defaults for example
 * background colour of a Widget. As this will conflict with the Style
 * settings of the users theme and end up creating inconsistencies in visual
 * appearance on the Desktop, methods using StateType should only be used with
 * deliberate care.
 * 
 * @author Andrew Cowie
 * @since 4.0.20
 */
/*
 * This is a GTK 3 class, we've included it here to aide porting.
 */
// cloned from StateType
public final class StateFlags extends Flag
{
    private StateFlags(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The ordinary state of a Widget
     */
    public static final StateFlags NORMAL = new StateFlags(GtkStateFlags.NORMAL, "NORMAL");

    /**
     * A Widget that is currently active. The definition of this will vary
     * from Widget to Widget, but a Button, for example, is active while it is
     * depressed.
     */
    public static final StateFlags ACTIVE = new StateFlags(GtkStateFlags.ACTIVE, "ACTIVE");

    /**
     * The mouse pointer is currently hovering over the Widget, and the Widget
     * will be responding to mouse clicks.
     */
    public static final StateFlags PRELIGHT = new StateFlags(GtkStateFlags.PRELIGHT, "PRELIGHT");

    /**
     * The element is selected. The canonical example is a row in a TreeView
     * which has been selected; most themes show this by doing a form of
     * reverse video, swapping foreground and background colours, etc.
     */
    public static final StateFlags SELECTED = new StateFlags(GtkStateFlags.SELECTED, "SELECTED");

    /**
     * The Widget is not responding to events. See
     * {@link Window#setSensitive(boolean) setSensitive()} on Window for more
     * information about this state.
     */
    public static final StateFlags INSENSITIVE = new StateFlags(GtkStateFlags.INSENSITIVE, "INSENSITIVE");
}
