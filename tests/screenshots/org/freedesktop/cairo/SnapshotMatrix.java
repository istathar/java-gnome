/*
 * SnapshotMatrix.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
