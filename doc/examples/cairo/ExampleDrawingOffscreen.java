/*
 * ExampleDrawingOffscreen.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package cairo;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Operator;
import org.freedesktop.cairo.Status;
import org.gnome.gdk.Event;
import org.gnome.gdk.Pixmap;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Exercise drawing with the Cairo API to an off-screen Drawable.
 * 
 * @author Andrew Cowie
 */
public class ExampleDrawingOffscreen
{
    public static void main(String[] args) {
        final Window w;
        final Context cr;
        final Pixmap m;
        final Image i;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Offscreen");
        w.setDefaultSize(150, 150);

        m = new Pixmap(100, 100, 24);

        cr = new Context(m);
        check(cr);

        cr.setOperator(Operator.CLEAR);
        cr.paint();
        check(cr);

        cr.setSourceRGBA(0.0, 0.0, 1.0, 0.8);
        cr.moveTo(10, 10);
        cr.lineTo(20, 45);
        cr.stroke();
        check(cr);

        i = new Image(m, null);
        w.add(i);
        w.showAll();

        w.connect(new Window.DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }

    private static void check(Context cr) {
        final Status status;

        status = cr.getStatus();

        if (status != Status.SUCCESS) {
            throw new AssertionError(status.toString());
        }
    }
}
