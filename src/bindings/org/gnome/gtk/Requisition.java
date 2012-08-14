/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
 * The size that will be (is being) requested by a Widget. You get the
 * Requisition object for a given Widget by calling that its
 * {@link Widget#getRequisition() getRequisition()} method.
 * 
 * <p>
 * As a general principle across GTK programming, we encourage people to leave
 * Widgets alone to work out their own needs. You will <i>not</i> be able to
 * forecast the impact of a user's fonts, accessibility settings, and theme
 * engine, not to mention the actual implementation of the Containers you are
 * packing your Widget into. The great part is that you don't need to; the
 * GNOME libraries are really good at Doing The Right Thing (tm) and you will
 * do well by them.
 * 
 * <p>
 * When an occasion does arise where you need to force one or both dimensions,
 * you can either:
 * 
 * <ul>
 * <li>directly influence the size of an individual Widget using
 * {@link Widget#setSizeRequest(int, int) setSizeRequest()};
 * <li>use a {@link SizeGroup} to influence a set of related Widgets to have a
 * dimension in common; or
 * <li>if all you're trying to do is add padding, call Container's
 * {@link Container#setBorderWidth(int) setBorderWidth()}.
 * </ul>
 * 
 * <p>
 * GTK's box packing model works on a size-request/size-allocation process.
 * Each Widget is asked by it's parent Container how much space it wants on
 * the screen, in pixels. These <var>requests</var> are aggregated and in turn
 * represented up the tree until the top-level Window is reached. The Window
 * then negotiates with the window manager and with the X server, with the
 * result being that the Window is <var>allocated</var>. The Containers start
 * divvying up the allocation they were given amongst their children according
 * to whatever algorithms and settings are in place. Eventually your Widget
 * will be told how much space it has been given in it's {@link Allocation}
 * object, and it will have to then carry on accordingly. The important point
 * to note here is that requests are just that; a Widget must be able to cope
 * with any size down to <code>1x1</code>. Ideally it will degrade gracefully,
 * although that's not always easy.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public final class Requisition extends Boxed
{
    protected Requisition(long pointer) {
        super(pointer);
    }

    Requisition() {
        super(GtkRequisitionOverride.createRequisition());
    }

    protected void release() {
        GtkRequisitionOverride.free(this);
    }

    /**
     * The width that has been requested.
     */
    public int getWidth() {
        return GtkRequisition.getWidth(this);
    }

    /**
     * The height that has been requested.
     */
    public int getHeight() {
        return GtkRequisition.getHeight(this);
    }

    public String toString() {
        return this.getClass().getName() + ": " + getWidth() + "x" + getHeight();
    }
}
