/*
 * SnapshotButton.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * @author Andrew Cowie
 */
public class SnapshotCalendar extends Snapshot
{
    public SnapshotCalendar() {
        super(Calendar.class);

        final Calendar b;

        window = new Window();
        window.setDecorated(false);

        b = new Calendar();
        b.selectMonth(12, 2007);
        b.selectDay(21);

        window.add(b);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotCalendar());
        Gtk.main();
    }
}
