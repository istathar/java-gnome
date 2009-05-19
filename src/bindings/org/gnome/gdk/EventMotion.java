/*
 * EventMotion.java
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

/**
 * Event data describing the position of the mouse in the screen, when it is
 * moved over a Widget.
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public final class EventMotion extends Event
{
    protected EventMotion(long pointer) {
        super(pointer);
    }

    /**
     * Get the horizontal location that this Event occured at, relative to the
     * <code>[org.gnome.gdk]</code> Window. In most cases you will get an
     * integral return; in any case, most usages of this return value will
     * want a whole number of pixels, so cast to <code>int</code> as
     * necessary.
     * 
     * @since 4.0.12
     */
    public double getX() {
        return GdkEventMotion.getX(this);
    }

    /**
     * Get the vertical location that this Event occured at, relative to the
     * <code>[org.gnome.gdk]</code> Window.
     * 
     * @since 4.0.12
     */
    public double getY() {
        return GdkEventMotion.getY(this);
    }
}
