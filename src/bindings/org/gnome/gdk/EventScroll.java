/*
 * EventScroll.java
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

/**
 * Event describing a mouse scroll operation. In most cases, it means that the
 * mouse wheel has been turned.
 * 
 * <p>
 * In fact, this event is related with mouse buttons 4 to 7. In most cases,
 * buttons 4 and 5 are mapped to the mouse wheel.
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public final class EventScroll extends Event
{
    protected EventScroll(long pointer) {
        super(pointer);
    }

    /**
     * Get the state of the modifier keys. This will be
     * {@link ModifierType#NONE NONE} if no modifiers are being held down. See
     * EventKey's {@link EventKey#getState() getState()} and
     * {@link ModifierType} for usage details.
     */
    public ModifierType getState() {
        return GdkKeyvalOverride.flagFor(GdkEventScroll.getState(this));
    }

    /**
     * Get the direction of the scroll.
     */
    public ScrollDirection getDirection() {
        return GdkEventScroll.getDirection(this);
    }
}
