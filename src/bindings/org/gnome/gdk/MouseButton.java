/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2009      Vreixo Formoso
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
 * Constants representing which mouse button was pressed.
 * 
 * <p>
 * Note that mouse buttons 4 to 7 have not a corresponding constant. These
 * buttons refer to mouse wheel actions, directions up, down, left and right,
 * respectively. GDK will present such events as a
 * <code>Widget.ScrollEvent</code>, so if you are interested on them you will
 * need to
 * {@link org.gnome.gtk.Widget#connect(org.gnome.gtk.Widget.ScrollEvent)
 * connect()} to such event.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.6
 */
/*
 * This is something we just cooked up locally. It's not in GDK.
 */
public class MouseButton extends Constant
{
    protected MouseButton(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * A "left click", mouse button <code>1</code>.
     * 
     * @since 4.0.6
     */
    public static final MouseButton LEFT = new MouseButton(1, "LEFT");

    /**
     * A "centre click", mouse button <code>2</code>. Some mice don't have a
     * middle button; in such cases your X server may be configured to
     * generate the middle button press if you press both right and left
     * simultaneously. Mice with scroll wheels will often generated this
     * button if the wheel is clicked (not scrolled, but pressed).
     * 
     * @since 4.0.6
     */
    public static final MouseButton MIDDLE = new MouseButton(2, "MIDDLE");

    /**
     * A "right click", mouse button <code>3</code>.
     * 
     * @since 4.0.6
     */
    public static final MouseButton RIGHT = new MouseButton(3, "RIGHT");

    /**
     * Mouse button <code>8</code>. It corresponds to the button typically
     * mapped to the "back" action, for example on web browsers. Note that
     * many mice do not have a BACK button, so if you plan to add an
     * application action to this button, do not forget to ensure it can be
     * also executed by other means, such a key press, ToolButton, etc
     * 
     * @since 4.0.12
     */
    public static final MouseButton BACK = new MouseButton(8, "BACK");

    /**
     * Mouse button <code>9</code>. It corresponds to the button typically
     * mapped to the "forward" action, for example on web browsers. Note that
     * many mice do not have a FORWARD button, so if you plan to add an
     * application action to this button, do not forget to ensure it can be
     * also executed by other means, such a key press, ToolButton, etc
     * 
     * @since 4.0.12
     */
    public static final MouseButton FORWARD = new MouseButton(9, "FORWARD");
}
