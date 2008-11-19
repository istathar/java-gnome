/*
 * SnapshotArrow.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * @author Andrew Cowie
 */
public class SnapshotArrow extends Snapshot
{
    public SnapshotArrow() {
        super(Arrow.class);

        final HBox x;
        final Arrow a1, a2, a3, a4;

        window = new Window();
        window.setDecorated(false);
        window.setBorderWidth(6);

        x = new HBox(true, 6);

        a1 = new Arrow(ArrowType.UP, ShadowType.OUT);
        x.packStart(a1, false, false, 0);
        a2 = new Arrow(ArrowType.DOWN, ShadowType.OUT);
        x.packStart(a2, false, false, 0);
        a3 = new Arrow(ArrowType.LEFT, ShadowType.OUT);
        x.packStart(a3, false, false, 0);
        a4 = new Arrow(ArrowType.RIGHT, ShadowType.OUT);
        x.packStart(a4, false, false, 0);

        window.add(x);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotArrow());
        Gtk.main();
    }
}
