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
package org.gnome.gtk;

/**
 * A Container which holds a variable number of Widgets in a single vertical
 * row. All the children of this VBox are allocated the same width - that of
 * the widest Widget packed into the VBox.
 * 
 * <p>
 * A VBox is almost always the first thing (and only thing, of course) added
 * to a new Window; doing so gives you a natural mechanism to layout the
 * contents of a Window in logical sequence from Menu through to Buttons and
 * Statusbar.
 * 
 * <p>
 * All the methods you need add widgets to VBoxes and to manipulate their
 * characteristics are on parent class Box. See its
 * {@link Box#packStart(Widget) packStart()} as a good first step.
 * 
 * @author Andrew Cowie
 * @see HBox
 * @since 4.0.1
 */
public class VBox extends Box
{
    protected VBox(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new VBox.
     * 
     * @param homogeneous
     *            If <code>true</code>, all children will be given equal space
     *            allotments.
     * @param spacing
     *            the number of pixels to place (by default) between children.
     */
    public VBox(boolean homogeneous, int spacing) {
        super(GtkVBox.createVBox(homogeneous, spacing));
    }
}
