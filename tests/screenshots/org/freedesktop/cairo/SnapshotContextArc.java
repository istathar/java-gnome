/*
 * SnapshotContextLine.java
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
import org.gnome.pango.FontDescription;
import org.gnome.pango.Layout;
import org.gnome.pango.Rectangle;

/**
 * @author Andrew Cowie
 */
public class SnapshotContextArc extends SnapshotContext
{
    public SnapshotContextArc() {
        super("arc");
    }

    protected void draw(Context cr) {
        final double x, y;
        final Layout text;
        final FontDescription desc;
        Rectangle rect;

        text = new Layout(cr);

        cr.setSourceRGB(0, 0, 0);
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

        // arc positive
        cr.setSourceRGB(0, 0, 1);
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
