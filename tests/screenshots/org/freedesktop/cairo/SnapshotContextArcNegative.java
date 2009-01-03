/*
 * SnapshotContextArcNegative.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.gtk.Gtk;

/**
 * @author Andrew Cowie
 */
/*
 * This code is a close copy of SnapshotContextArc (and both inherit from
 * SnapshotContextAxis). If you change one, change the other.
 */
public class SnapshotContextArcNegative extends SnapshotContextAxis
{
    public SnapshotContextArcNegative() {
        super("arcNegative");
    }

    protected void draw(Context cr) {
        final double x, y;

        super.drawAxis(cr);

        // arc negative
        cr.setSource(0, 0, 1);
        cr.setLineWidth(2.0);

        cr.moveTo(50 + 30, 50);
        cr.arcNegative(50, 50, 30, 0, -(Math.PI * 3.0 / 4.0));

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
        runExample(new SnapshotContextArcNegative());
        Gtk.main();
    }
}
