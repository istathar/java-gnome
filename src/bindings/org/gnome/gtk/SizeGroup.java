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

import org.gnome.glib.Object;

/**
 * Cause a group of Widgets to have the same size request in either the
 * horizontal dimension, the vertical dimension, or both. A SizeGroup is
 * somewhat like a Container, although it is a helper class for Widgets and
 * not itself a Widget. When you create a SizeGroup, you specify the mode
 * indicating the way you want it to act upon the Widgets placed into it, and
 * then add Widgets. All the Widgets "in" the SizeGroup will then all have
 * have their width request in common ({@link SizeGroupMode#HORIZONTAL
 * HORIZONTAL}) or their height request in common (
 * {@link SizeGroupMode#VERTICAL VERTICAL}), or, in somewhat rarer
 * circumstances, have both their width and height requests in common
 * {@link SizeGroupMode#BOTH BOTH}). The size requested will be that of the
 * Widget in the group that is the largest in that dimension.
 * 
 * <p>
 * When doing data entry across a large number of fields, it is a GNOME
 * usability standard that the Entry boxes be common width and aligned
 * vertically. While there are certainly cases where you have a good reason
 * for doing otherwise, if you are filling in a form then using a SizeGroup
 * can help you create a nice uniform appearance.
 * 
 * <p>
 * SizeGroups can be fantastically useful to create the "table" effect but
 * across an uneven series of HBoxes nested in VBoxes with but with other
 * things in between.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class SizeGroup extends Object
{
    protected SizeGroup(long pointer) {
        super(pointer);
    }

    /**
     * Instantiate a new SizeGroup, constraining per the <code>mode</code>
     * parameter.
     * 
     * @since 4.0.6
     */
    public SizeGroup(SizeGroupMode mode) {
        super(GtkSizeGroup.createSizeGroup(mode));
    }

    /**
     * Specify a Widget to be constrained as a member of this SizeGroup.
     * 
     * @since 4.0.6
     */
    /*
     * This, and remove(), have been adjusted from exact API mapping to GTK in
     * order to be parallel to the Container API style.
     */
    public void add(Widget widget) {
        GtkSizeGroup.addWidget(this, widget);
    }

    /**
     * Remove a Widget that was previously added to the SizeGroup.
     * 
     * @since 4.0.6
     */
    public void remove(Widget widget) {
        GtkSizeGroup.removeWidget(this, widget);
    }
}
