/*
 * WindowType.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Enum;

/**
 * The type of Window. When constructing {@link org.gnome.gtk.Window Window}s
 * you need to specify what kind of Window it is to be. Since TOPLEVEL is almost
 * always what you want the default constructor of Window chooses this
 * automatically.
 * 
 * @author Andrew Cowie
 */
public final class WindowType extends Enum {

    private WindowType(int sequence) {
        super(sequence);
    }

    /**
     * Most things you'd consider a "window" should have type TOPLEVEL; windows
     * with this type are managed by the window manager and have a frame by
     * default. [The frame is what you might think of the "border", although
     * border is actually a characteristic of Wiget. You can call
     * {@link org.gnome.gtk.Window#setDecorated(boolean) setDecorated(false)} to turn
     * the window manager's frame off]
     */
    public static final WindowType TOPLEVEL = new WindowType(
            GtkWindowType.TOPLEVEL);

    /**
     * Windows with type POPUP are ignored by the window manager; window manager
     * keybindings won't work on them, the window manager won't decorate the
     * window with a frame (ie borders), and many GTK features that rely on the
     * window manager will not work (for example resize grips and
     * maximize/minimize). WindowType POPUP is used to implement widgets such as
     * menus and tooltips - things that you wouldn't think of as Windows and
     * don't program as such. In particular, do not use this to turn off Window
     * borders! That's what
     * {@link org.gnome.gtk.Window#setDecorated(boolean) setDecorated(false)} is for.
     */
    public static final WindowType POPUP = new WindowType(GtkWindowType.POPUP);

}
