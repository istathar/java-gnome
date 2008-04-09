/*
 * SnapshotVScale.java
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
public class SnapshotVScale extends Snapshot
{
    public SnapshotVScale() {
        super(VScale.class);

        final VScale vs;

        window = new Window();
        window.setDecorated(false);
        window.setDefaultSize(-1, 160);

        vs = new VScale(0, 100, 10);
        vs.setValue(35);
        // vs.setValuePosition(PositionType.BOTTOM);

        window.add(vs);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotVScale());
        Gtk.main();
    }
}
