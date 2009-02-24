/*
 * SnapshotOperatorSource.java
 *
 * Copyright (c) 2009 Kenneth Prugh
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.gtk.Gtk;

/**
 * @author Kenneth Prugh
 */
public class SnapshotOperatorSource extends SnapshotCairo
{
    public SnapshotOperatorSource() {
        super(Operator.class, "source");
    }

    protected void draw(Context cr) {
        cr.setSource(0.0, 1.0, 0.0, 0.8);
        cr.rectangle(15, 10, 50, 50);
        cr.fill();

        cr.setSource(1.0, 0.0, 0.0, 0.8);
        cr.rectangle(35, 35, 50, 50);
        cr.setOperator(Operator.SOURCE);
        cr.fill();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotOperatorSource());
        Gtk.main();
    }
}
