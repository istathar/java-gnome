/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.LinearPattern;
import org.freedesktop.cairo.Pattern;
import org.freedesktop.cairo.RadialPattern;
import org.gnome.gdk.Event;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
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
 * Gradient example from the Cairo Tutorial.
 */
public class ExampleDrawingGradients
{
    public static void main(String[] args) {
        final Window w;
        final DrawingArea d;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Drawing");
        w.setDefaultSize(150, 150);

        d = new DrawingArea();
        w.add(d);
        w.showAll();

        d.connect(new Widget.Draw() {
            public boolean onDraw(Widget source, Context cr) {
                final Pattern linear, radial;

                /*
                 * Do some drawing:
                 */

                cr.setSource(1.0, 0.1, 0.0, 1.0);
                cr.moveTo(10, 40);
                cr.lineTo(120, 145);
                cr.stroke();

                /*
                 * If youre used to using RGB triplets, just normalize them to
                 * the 0.0 to 1.0 range by dividing by 255. It's all the same
                 * to Cairo, really.
                 */

                cr.setSource(225 / 255.0, 148 / 255.0, 11 / 255.0, 1.0);
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

                return true;
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
