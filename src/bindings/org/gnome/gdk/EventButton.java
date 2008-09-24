/*
 * EventButton.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

/**
 * Event data describing a button on a pointing device that was pressed or
 * released. Notably, you can find out which button on the device was clicked
 * with {@link #getButton() getButton()}, and whether any modifier keys were
 * being held down by the user with {@link #getState() getState()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public final class EventButton extends Event
{
    protected EventButton(long pointer) {
        super(pointer);
    }

    /**
     * Which button on the pointing device was pressed?
     * 
     * @since 4.0.6
     */
    public MouseButton getButton() {
        return GdkMouseButtonOverride.enumFor(GdkEventButton.getButton(this));
    }

    /**
     * Get the state of the modifier keys. This will be
     * {@link ModifierType#NONE NONE} if no modifiers are being held down. See
     * EventKey's {@link EventKey#getState() getState()} and
     * {@link ModifierType} for usage details.
     * 
     * @since 4.0.6
     */
    public ModifierType getState() {
        return GdkKeyvalOverride.flagFor(GdkEventButton.getState(this));
    }

    /**
     * Get the horizontal location that this Event occured at, relative to the
     * <code>[org.gnome.gdk]</code> Window.
     * 
     * @since 4.0.9
     */
    /*
     * Why on earth are they double? No one has been able to give us a
     * coherent answer, and all the C code examples I've seen relating to
     * TreeView selections cast these fields to (gint) so we'll do the same.
     * FUTURE if anyone does an Override for this, then do this in C so we
     * save shipping the double across the boundary.
     */
    public int getX() {
        return (int) GdkEventButton.getX(this);
    }

    /**
     * Get the vertical location that this Event occured at, relative to the
     * <code>[org.gnome.gdk]</code> Window.
     * 
     * @since 4.0.9
     */
    public int getY() {
        return (int) GdkEventButton.getY(this);
    }
}
