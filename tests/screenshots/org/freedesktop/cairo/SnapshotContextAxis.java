/*
 * SnapshotContextAxis.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.pango.FontDescription;
import org.gnome.pango.Layout;
import org.gnome.pango.Rectangle;

/**
 * Draw an x and y axis, emphasizing that +y is downwards.
 * 
 * @author Andrew Cowie
 */

abstract class SnapshotContextAxis extends SnapshotContext
{
    public SnapshotContextAxis(String suffix) {
        super(suffix);
    }

    protected void drawAxis(Context cr) {
        final Layout text;
        final FontDescription desc;
        Rectangle rect;

        text = new Layout(cr);

        cr.setSource(0, 0, 0);
        cr.setLineWidth(2.0);

        // horizontal
        cr.moveTo(5, 50);
        cr.lineTo(95, 50);
        cr.stroke();

        cr.moveTo(95, 50);
        cr.lineRelative(-5, -3);
        cr.lineRelative(0, 6);
        cr.fill();

        desc = new FontDescription("Liberation Serif, 12");
        text.setFontDescription(desc);
        text.setMarkup("+<i>x</i>");
        rect = text.getExtentsLogical();
        cr.moveTo(95 - rect.getWidth(), 50 - rect.getHeight() - 2);
        cr.showLayout(text);

        // vertical
        cr.moveTo(50, 5);
        cr.lineTo(50, 95);
        cr.stroke();

        cr.moveTo(50, 95);
        cr.lineRelative(-3, -5);
        cr.lineRelative(+6, 0);
        cr.fill();

        text.setMarkup("+<i>y</i>");
        rect = text.getExtentsLogical();
        cr.moveTo(50 - rect.getWidth() - 5, 95 - rect.getHeight());
        cr.showLayout(text);
    }
}
