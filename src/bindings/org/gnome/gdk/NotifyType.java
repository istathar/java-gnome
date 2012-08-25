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
 * The kind of motion in an EventCrossing. These Constants describe the
 * relationship between the GDK Window that the mouse pointer left, and the
 * GDK Window that the mouse pointer entered.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 * @see <a
 *      href="http://xorg.freedesktop.org/releases/X11R7.0/doc/PDF/xlib.pdf">The
 *      XLib programming manual, section 10.6</a>
 */
public final class NotifyType extends Constant
{
    private NotifyType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The mouse has moved from an inferior Window to a superior or enclosing
     * one.
     */
    public static final NotifyType ANCESTOR = new NotifyType(GdkNotifyType.ANCESTOR, "ANCESTOR");

    /**
     * This event is generated for Windows that lay between the receiving
     * Window and the departing Window.
     */
    public static final NotifyType VIRTUAL = new NotifyType(GdkNotifyType.VIRTUAL, "VIRTUAL");

    /**
     * The mouse has moved from a superior (parent, enclosing) Window to an
     * inferior (child) one.
     */
    public static final NotifyType INFERIOR = new NotifyType(GdkNotifyType.INFERIOR, "INFERIOR");

    /**
     * The mouse moved between unrelated Windows. This is what seems to occur
     * most of the time when you exit the app and cross to the root X Window
     * or some other application's Window. Not terribly helpful otherwise.
     */
    public static final NotifyType NONLINEAR = new NotifyType(GdkNotifyType.NONLINEAR, "NONLINEAR");

    public static final NotifyType NONLINEAR_VIRTUAL = new NotifyType(GdkNotifyType.NONLINEAR_VIRTUAL,
            "NONLINEAR_VIRTUAL");

    public static final NotifyType UNKNOWN = new NotifyType(GdkNotifyType.UNKNOWN, "UNKNOWN");
}
