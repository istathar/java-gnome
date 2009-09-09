/*
 * ExampleDrawingPenguins.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package cairo;

import java.io.FileNotFoundException;

import org.freedesktop.cairo.Context;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventExpose;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.rsvg.DimensionData;
import org.gnome.rsvg.Handle;

/**
 * Draw an SVG iamge to a Cairo surface.
 * 
 * @author Andrew Cowie
 */
public class ExampleDrawingPenguins
{
    public static void main(String[] args) throws FileNotFoundException {
        final Window w;
        final DrawingArea d;
        final Handle svg;
        final DimensionData size;
        final int width, height;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Tux");

        svg = new Handle("tests/bindings/org/gnome/rsvg/Linux_Tux.svg");

        size = svg.getDimensions();
        width = size.getWidth();
        height = size.getHeight();
        w.setDefaultSize(width, height);

        d = new DrawingArea();
        w.add(d);
        w.showAll();

        d.connect(new Widget.ExposeEvent() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;

                cr = new Context(source.getWindow());
                cr.showHandle(svg);

                return true;
            }
        });

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }
}
