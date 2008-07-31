/*
 * ExampleDrawingInExposeEvent.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package cairo;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.LinearPattern;
import org.freedesktop.cairo.Pattern;
import org.freedesktop.cairo.RadialPattern;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventExpose;
import org.gnome.gdk.Rectangle;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Exercise drawing with the Cairo API. If you are rendering a custom Widget
 * or otherwise drawing stuff with Cairo that is to be presented by GTK, you
 * are expected to do this in the Widget.ExposeEvent handler for that Widget.
 * 
 * @author Andrew Cowie
 * @author Carl Worth
 */
/*
 * TODO rename this once we actually draw something interesting!
 */
/*
 * Gradient example from the Cairo Tutorial.
 */
public class ExampleDrawingInExposeEvent
{
    public static void main(String[] args) {
        final Window w;
        final Image i;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Expose");
        w.setDefaultSize(150, 150);

        i = new Image();
        w.add(i);
        w.showAll();

        i.connect(new Widget.ExposeEvent() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;
                final Rectangle rect;
                final Pattern linear, radial;

                /*
                 * Out of interest, where is this occuring?
                 */

                rect = event.getArea();
                System.out.println("Widget.ExposeEvent bounded by " + rect.getWidth() + "x"
                        + rect.getHeight() + " at " + rect.getX() + "," + rect.getY());

                /*
                 * With that out of the way, we get to the heart of the
                 * matter: creating a Cairo Context based on (and mapped to)
                 * the Drawable underlying the Widget. The key here is that
                 * the Widget is mapped unlike earlier when we were
                 * constructing it. The first Widget.ExposeEvent does not
                 * occur until after the Widget is realized; indeed, that is
                 * when it is triggered.
                 */

                cr = new Context(source.getWindow());

                /*
                 * Now, finally do some drawing:
                 */

                cr.setSourceRGBA(1.0, 0.1, 0.0, 1.0);
                cr.moveTo(10, 40);
                cr.lineTo(120, 145);
                cr.stroke();

                /*
                 * If youre used to using RGB triplets, just normalize them to
                 * the 0.0 to 1.0 range by dividing by 255. It's all the same
                 * to Cairo, really.
                 */

                cr.setSourceRGBA(225 / 255.0, 148 / 255.0, 11 / 255.0, 1.0);
                cr.rectangle(70, 70, 20, 40);
                cr.fill();

                /*
                 * Now a much more complicated example of drawing: a linear
                 * colour gradiant with a radial alpha mask.
                 */

                linear = new LinearPattern(0, 0, 150, 150);
                linear.addColorStopRGB(0.0, 0.0, 0.3, 0.8);
                linear.addColorStopRGB(1.0, 0.0, 0.8, 0.3);

                radial = new RadialPattern(75, 75, 15, 75, 75, 60);
                radial.addColorStopRGBA(0, 0.0, 0.0, 0.0, 0.0);
                radial.addColorStopRGBA(1, 0.0, 0.0, 0.0, 1.0);

                cr.setSource(linear);
                cr.mask(radial);

                return false;
            }
        });

        /*
         * And that's it. Conclude with connecting the usual tear-down
         * handler, and then fire up the main loop.
         */

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }
}
