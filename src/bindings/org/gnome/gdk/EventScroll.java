/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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
