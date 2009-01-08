/*
 * SnapshotContextArc.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
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
public class SnapshotMatrixRotate extends SnapshotMatrix
{
    public SnapshotMatrixRotate() {
        super("rotate");
    }

    protected void draw(Context cr) {
        final Matrix matrix;

        super.drawAxis(cr);
        super.drawBox(cr, true);

        matrix = new Matrix();
        matrix.rotate(-Math.PI / 4.0);
        cr.transform(matrix);

        super.drawBox(cr, false);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotMatrixRotate());
        Gtk.main();
    }
}
