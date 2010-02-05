/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 */
package org.freedesktop.cairo;

import org.gnome.gtk.Gtk;

/**
 * @author Andrew Cowie
 */
public class SnapshotContextArc extends SnapshotCairoAxis
{
    public SnapshotContextArc() {
        super(Context.class, "arc");
    }

    protected void draw(Context cr) {
        final double x, y;

        super.drawAxis(cr);

        // arc positive
        cr.setSource(0, 0, 1);
        cr.setLineWidth(2.0);

        cr.moveTo(50 + 30, 50);
        cr.arc(50, 50, 30, 0, Math.PI / 3);

        x = cr.getCurrentPointX();
        y = cr.getCurrentPointY();
        cr.stroke();

        cr.moveTo(x, y + 0.2);
        cr.lineRelative(2, -6);
        cr.lineRelative(4.5, 5.5);
        cr.fill();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotContextArc());
        Gtk.main();
    }
}
