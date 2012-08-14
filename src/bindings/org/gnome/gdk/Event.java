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

import org.gnome.glib.Boxed;

/**
 * The events used to communicate data describing the internal details of
 * activities that occur to or between GDK resources.
 * 
 * <p>
 * <i>C side, <code>GdkEvent</code> is a union of various event structs such
 * as <code>GdkEventExpose</code> and <code>GdkEventKey</code>. Each struct in
 * this family starts with the same fields, and these fields are represented
 * by <code>GdkEventAny</code>. We have exposed those fields here on
 * Event.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
public abstract class Event extends Boxed
{
    protected Event(long pointer) {
        super(pointer);
    }

    protected void release() {
        GdkEvent.free(this);
    }

    /**
     * Get the type of event that occurred. There are fairly tight
     * relationships between these type constants and the concrete Event
     * subclasses, see {@link EventType EventType} for an example.
     * 
     * @since 4.0.3
     */
    public EventType getType() {
        return GdkEventAny.getType(this);
    }

    /**
     * Get the underlying [GDK] Window which received the event.
     * 
     * @since 4.0.3
     */
    public Window getWindow() {
        return GdkEventAny.getWindow(this);
    }
}
