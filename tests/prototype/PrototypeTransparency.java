/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.LinearPattern;
import org.freedesktop.cairo.Operator;
import org.freedesktop.cairo.Pattern;
import org.freedesktop.cairo.RadialPattern;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventExpose;
import org.gnome.gdk.Screen;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Draw a floating, windowless (ie, lacking border and title decorations),
 * non-rectangular shape on screen. You'll need a compositing window manager
 * for this to work.
 * 
 * @author Andrew Cowie
 */
public class PrototypeTransparency
{
    public static void main(String[] args) {
        final Window w;
        final DrawingArea d;
        final org.gnome.gdk.Window underlying;
        final Screen screen;

        Gtk.init(args);

        w = new Window();
        w.setDecorated(false);
        w.setDefaultSize(150, 150);

        d = new DrawingArea();
        w.add(d);
        screen = w.getScreen();
        w.setColormap(screen.getColormapRGBA());

        w.realize();
        underlying = w.getWindow();
        underlying.setBackingPixmap(null, false);

        w.showAll();

        d.connect(new Widget.ExposeEvent() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;
                final Pattern linear, radial;

                cr = new Context(source.getWindow());

                cr.setSource(1.0, 1.0, 1.0, 1.0);
                cr.setOperator(Operator.CLEAR);
                cr.paint();

                cr.setOperator(Operator.OVER);

                cr.setSource(1.0, 0.1, 0.0, 1.0);
                cr.moveTo(10, 40);
                cr.lineTo(120, 145);
                cr.stroke();

                cr.setSource(225 / 255.0, 148 / 255.0, 11 / 255.0, 1.0);
                cr.rectangle(70, 70, 20, 40);
                cr.fill();

                linear = new LinearPattern(0, 0, 150, 150);
                linear.addColorStopRGB(0.0, 0.0, 0.3, 0.8);
                linear.addColorStopRGB(1.0, 0.0, 0.8, 0.3);

                radial = new RadialPattern(75, 75, 15, 75, 75, 60);
                radial.addColorStopRGBA(0, 0.0, 0.0, 0.0, 1.0); // reversed
                radial.addColorStopRGBA(1, 0.0, 0.0, 0.0, 0.0);

                cr.setSource(linear);
                cr.mask(radial);

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
