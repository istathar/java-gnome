/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2016 Operational Dynamics Consulting, Pty Ltd and Others
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
 * @author Will Temperley
 *
 * The curve_to example from:
 * http://cairographics.org/samples/curve_to/
 *
 * The red lines demonstrate the relationship between the
 * start and end points of the curve and the control points.
 *
 * In depth discussion of the maths here:
 * http://pomax.github.io/bezierinfo/
 *
 */
public class ExampleDrawingCurves
{

  public static void main(String[] args) {
        final Window w;
        final DrawingArea d;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Drawing");
        w.setDefaultSize(300, 300);

        d = new DrawingArea();
        w.add(d);
        w.showAll();

        d.connect(new Widget.Draw() {
            public boolean onDraw(Widget source, Context cr) {
                final Pattern linear, radial;

                /*
                 * Draw a bézier curve.
                 */
                double x=25.6,  y=128.0;
                double x1=102.4, y1=230.4,
                    x2=153.6, y2=25.6,
                    x3=230.4, y3=128.0;

                cr.moveTo (x, y);
                cr.curveTo (x1, y1, x2, y2, x3, y3);

                cr.setLineWidth (10.0);
                cr.stroke ();

                cr.setSource(1, 0.2, 0.2, 0.6);
                cr.setLineWidth (6.0);
                cr.moveTo (x,y);
                cr.lineTo (x1,y1);
                cr.moveTo (x2,y2);
                cr.lineTo (x3,y3);

                cr.stroke();


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
