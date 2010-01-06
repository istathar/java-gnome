/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
import org.freedesktop.cairo.Operator;
import org.gnome.gdk.Event;
import org.gnome.gdk.Pixmap;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Exercise drawing with the Cairo API to an off-screen Drawable.
 * 
 * FIXME THIS EXAMPLE DOES NOT YET FUNCTION CORRECTLY.
 * 
 * The result of using this is either a garbage background, a solid black
 * square, or a crash. Help?
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

        // the problem is here, with the bit depth
        m = new Pixmap(100, 100, 24);

        cr = new Context(m);

        // recommended by Carl, but doesn't seem to help
        cr.setOperator(Operator.CLEAR);
        cr.setSource(1.0, 1.0, 1.0, 1.0);
        cr.paint();

        cr.setSource(0.0, 0.0, 1.0, 0.8);
        cr.moveTo(10, 10);
        cr.lineTo(20, 45);
        cr.stroke();

        i = new Image(m, null);
        w.add(i);
        w.showAll();

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }
}
