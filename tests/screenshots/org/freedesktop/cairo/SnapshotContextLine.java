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

/**
 * @author Andrew Cowie
 */
public class SnapshotContextLine extends SnapshotCairo
{
    public SnapshotContextLine() {
        super(Context.class, "line");
    }

    protected void draw(Context cr) {
        cr.setSourceRGB(1.0, 0.0, 0.0);
        cr.moveTo(10, 10);
        cr.lineTo(90, 50);
        cr.stroke();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotContextLine());
        Gtk.main();
    }
}
