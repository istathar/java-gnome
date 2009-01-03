/*
 * SnapshotContextRectangle.java
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
public class SnapshotContextRectangle extends SnapshotContext
{
    public SnapshotContextRectangle() {
        super("rectangle");
    }

    protected void draw(Context cr) {
        cr.setSource(0.5, 1.0, 0.0, 0.8);
        cr.rectangle(30, 20, 60, 60);
        cr.fill();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotContextRectangle());
        Gtk.main();
    }
}
