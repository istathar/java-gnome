/*
 * SnapshotHScale.java
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
public class SnapshotHScale extends Snapshot
{
    public SnapshotHScale() {
        super(HScale.class);

        final HScale hs;

        window = new Window();
        window.setDecorated(false);
        window.setDefaultSize(200, -1);

        hs = new HScale(0, 100, 10);
        hs.setValue(35);

        window.add(hs);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotHScale());
        Gtk.main();
    }
}
