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

/**
 * @author Andrew Cowie
 */
abstract class SnapshotMatrix extends SnapshotCairoAxis
{
    public SnapshotMatrix(String suffix) {
        super(Matrix.class, suffix);
    }

    protected void drawAxis(Context cr) {
        final Matrix reset;

        super.drawAxis(cr);

        reset = new Matrix();
        reset.translate(50.0, 50.0);
        cr.transform(reset);
    }

    protected void drawBox(Context cr, boolean first) {
        cr.setLineWidth(2.0);

        if (first) {
            cr.setSource(0.7, 0.8, 0.8);
        } else {
            cr.setSource(0.0, 0.0, 1.0);
        }
        cr.rectangle(5, 5, 25, 15);
        cr.stroke();
    }
}
