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
package org.gnome.pango;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.freedesktop.cairo.Surface;
import org.gnome.gdk.Event;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Ensure our accessors to PangoLayoutLine structs (and conversion to
 * character offsets) is accurate.
 * 
 * @author Andrew Cowie
 */
public class ValidatePangoWrapBehaviour extends GraphicalTestCase
{
    private static Layout draw(Context cr) {
        final Layout layout;
        FontDescription desc;

        layout = new Layout(cr);
        layout.setWrapMode(WrapMode.CHAR);
        layout.setWidth(100.0);

        desc = new FontDescription("DejaVu Serif, 18");
        layout.setFontDescription(desc);
        layout.setText("H€lloworldPeace武道");

        cr.showLayout(layout);

        return layout;
    }

    public static void main(String[] args) {
        final Window w;
        final DrawingArea d;

        Gtk.init(args);

        w = new Window();
        w.setDefaultSize(150, 150);

        d = new DrawingArea();
        w.add(d);
        w.showAll();

        d.connect(new Widget.Draw() {
            public boolean onDraw(Widget source, Context cr) {
                draw(cr);
                return false;
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

    public final void testLayoutLineOffsetConversion() throws InterruptedException {
        final Surface surface;
        final Context cr;
        final Layout layout;
        final LayoutLine[] lines;
        LayoutLine line;

        surface = new ImageSurface(Format.ARGB32, 150, 150);
        cr = new Context(surface);
        layout = draw(cr);

        lines = layout.getLinesReadonly();

        assertNotNull(lines);
        assertEquals(3, lines.length);

        // H€lloW
        line = lines[0];
        assertEquals(0, line.getStartIndex());
        assertEquals(6, line.getLength());

        // orldPea
        line = lines[1];
        assertEquals(6, line.getStartIndex());
        assertEquals(7, line.getLength());

        // ce武道
        line = lines[2];
        assertEquals(13, line.getStartIndex());
        assertEquals(4, line.getLength());
    }
}
