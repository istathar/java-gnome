/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Event data describing a button on a pointing device that was pressed or
 * released. Notably, you can find out which button on the device was clicked
 * with {@link #getButton() getButton()}, and whether any modifier keys were
 * being held down by the user with {@link #getState() getState()}.
 * 
 * @author Andrew Cowie
 * @author Srichand Pendyala
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
     * <code>[org.gnome.gdk]</code> Window. In most cases you will get an
     * integral return; in any case, most usages of this return value will
     * want a whole number of pixels, so cast to <code>int</code> as
     * necessary.
     * 
     * @since 4.0.9
     */
    public double getX() {
        return GdkEventButton.getX(this);
    }

    /**
     * Get the vertical location that this Event occured at, relative to the
     * <code>[org.gnome.gdk]</code> Window.
     * 
     * @since 4.0.9
     */
    public double getY() {
        return GdkEventButton.getY(this);
    }
}
