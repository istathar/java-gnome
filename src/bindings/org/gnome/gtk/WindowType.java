/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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

import org.freedesktop.bindings.Constant;

/**
 * The type of Window. When constructing {@link org.gnome.gtk.Window Window}s
 * you need to specify what kind of Window it is to be. Since TOPLEVEL is
 * almost always what you want the default constructor of Window chooses this
 * automatically.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public final class WindowType extends Constant
{
    private WindowType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Most things you'd consider a "window" should have type TOPLEVEL;
     * windows with this type are managed by the window manager and have a
     * frame by default. [The frame is what you might think of the "border",
     * although border is actually a characteristic of Widget. You can call
     * {@link org.gnome.gtk.Window#setDecorated(boolean) setDecorated(false)}
     * to turn the window manager's frame off]
     */
    public static final WindowType TOPLEVEL = new WindowType(GtkWindowType.TOPLEVEL, "TOPLEVEL");

    /**
     * Windows with type POPUP are ignored by the window manager; window
     * manager keybindings won't work on them, the window manager won't
     * decorate the window with a frame (ie borders), and many GTK features
     * that rely on the window manager will not work (for example resize grips
     * and maximize/minimize). WindowType POPUP is used to implement widgets
     * such as menus and tooltips - things that you wouldn't think of as
     * Windows and don't program as such. In particular, do not use this to
     * turn off Window borders! That's what
     * {@link org.gnome.gtk.Window#setDecorated(boolean) setDecorated(false)}
     * is for.
     */
    public static final WindowType POPUP = new WindowType(GtkWindowType.POPUP, "POPUP");
}
