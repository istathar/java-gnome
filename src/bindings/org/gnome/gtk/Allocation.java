/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.glib.Boxed;

/**
 * An object with information about the size of the rectangle that has been
 * allocated to a Widget and its position within its parent Container as a
 * result of consideration of the Widget's size request. See Widget's
 * {@link Widget#setSizeRequest(int, int) setSizeRequest()} for a more
 * detailed discussion of the size request-allocation process. You get the
 * Allocation currently given to a Widget with Widget's
 * {@link Widget#getAllocation() getAllocation()}.
 * 
 * <p>
 * Before the request-allocation process has occurred, you can expect this
 * class to report a size of <code>1</code>x<code>1</code> at position
 * <code>-1</code>,<code>-1</code>. You probably don't want to rely on those
 * numbers; but that's what the initial values are.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public final class Allocation extends Boxed
{
    protected Allocation(long pointer) {
        super(pointer);
    }

    Allocation() {
        super(GtkAllocationOverride.createAllocation());
    }

    protected void release() {
        GtkAllocationOverride.free(this);
    }

    /**
     * The width that has been allocated.
     */
    public int getWidth() {
        return GtkAllocation.getWidth(this);
    }

    /**
     * The height that has been allocated.
     */
    public int getHeight() {
        return GtkAllocation.getHeight(this);
    }

    /**
     * The horizontal co-ordinate of the top left corner of the rectangle.
     * This is relative to (ie, an offset from the top left corner of) the
     * parent's Allocation).
     */
    public int getX() {
        return GtkAllocation.getX(this);
    }

    /**
     * The vertical co-ordinate of the top left corner of the rectangle. This
     * is relative to (ie, an offset from the top left corner of) the parent's
     * Allocation).
     */
    public int getY() {
        return GtkAllocation.getY(this);
    }

    public String toString() {
        return this.getClass().getName() + ": " + getWidth() + "x" + getHeight() + " at " + getX() + ","
                + getY();
    }
}
