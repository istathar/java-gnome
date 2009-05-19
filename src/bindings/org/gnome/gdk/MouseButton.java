/*
 * MouseButton.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c)      2009 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
     * Mouse button <code>4</code>.
     * 
     * @deprecated Do not use this, connect to
     *             {@link org.gnome.gtk.Widget.ScrollEvent Widget.ScrollEvent}
     *             instead.
     */
    public static final MouseButton FOURTH = new MouseButton(4, "FOURTH");

    /**
     * Mouse button <code>5</code>.
     * 
     * @deprecated Do not use this, connect to
     *             {@link org.gnome.gtk.Widget.ScrollEvent Widget.ScrollEvent}
     *             instead.
     */
    public static final MouseButton FIFTH = new MouseButton(5, "FIFTH");

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
