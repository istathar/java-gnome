/*
 * ScrollDirection.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2009 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

import org.freedesktop.bindings.Constant;

/**
 * The direction of an {@link EventScroll}. With traditional wheel mice you will
 * only receive UP and DOWN directions. Most recent mice also allow to move the
 * wheel right and left, so RIGHT and LEFT directions refer to that. 
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public final class ScrollDirection extends Constant
{
    private ScrollDirection(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The Window is scrolled up.
     */
    public static final ScrollDirection UP = new ScrollDirection(GdkScrollDirection.UP, "UP");

    /**
     * The Window is scrolled down.
     */
    public static final ScrollDirection DOWN = new ScrollDirection(GdkScrollDirection.DOWN, "DOWN");

    /**
     * The Window is scrolled to the left
     */
    public static final ScrollDirection RIGHT = new ScrollDirection(GdkScrollDirection.RIGHT, "RIGHT");

    /**
     * The Window is scrolled to the right.
     */
    public static final ScrollDirection LEFT = new ScrollDirection(GdkScrollDirection.LEFT, "LEFT");
}
