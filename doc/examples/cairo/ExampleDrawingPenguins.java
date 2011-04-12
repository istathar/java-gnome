/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2011 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 */
package cairo;

import java.io.FileNotFoundException;

import org.freedesktop.cairo.Context;
import org.gnome.gdk.Event;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.rsvg.DimensionData;
import org.gnome.rsvg.Handle;
import org.gnome.rsvg.Rsvg;

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
        final Handle graphic;
        final DimensionData size;
        final int width, height;

        Gtk.init(args);
        Rsvg.init();

        w = new Window();
        w.setTitle("Tux");

        graphic = new Handle("tests/bindings/org/gnome/rsvg/Linux_Tux.svg");

        size = graphic.getDimensions();
        width = size.getWidth();
        height = size.getHeight();
        w.setDefaultSize(width, height);

        d = new DrawingArea();
        w.add(d);
        w.showAll();

        d.connect(new Widget.Draw() {
            public boolean onDraw(Widget source, Context cr) {
                cr.showHandle(graphic);
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
