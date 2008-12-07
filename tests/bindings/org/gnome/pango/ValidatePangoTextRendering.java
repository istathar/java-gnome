/*
 * ValidatePangoTextRendering.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.pango;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.freedesktop.cairo.Surface;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.TestCaseGtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * @author Andrew Cowie
 */
public class ValidatePangoTextRendering extends TestCaseGtk
{
    private static Layout draw(final Context cr) {
        final Layout layout;

        cr.setSourceRGBA(0.1, 0.9, 0.2, 1.0);
        cr.moveTo(10, 80);
        layout = new Layout(cr);
        layout.setText("Hello");

        cr.showLayout(layout);

        return layout;
    }

    public final void testPropertyDefaults() {
        final Surface surface;
        final Context cr;
        final Layout layout;
        double width;

        surface = new ImageSurface(Format.ARGB32, 150, 150);
        cr = new Context(surface);

        layout = draw(cr);

        /*
         * Initial conditions
         */

        assertEquals(0.0, layout.getIndent());
        assertEquals(false, layout.getJustify());

        /*
         * Round trips
         */

        layout.setIndent(5.0);
        assertEquals(5.0, layout.getIndent());

        layout.setJustify(true);
        assertEquals(true, layout.getJustify());
        layout.setJustify(false);
        assertEquals(false, layout.getJustify());
    }

    public final void testWidthAndHeightNormalization() {
        final Surface surface;
        final Context cr;
        final Layout layout;
        double width;

        surface = new ImageSurface(Format.ARGB32, 150, 150);
        cr = new Context(surface);

        layout = draw(cr);

        layout.setWidth(60.0);

        width = layout.getSizeWidth();
        assertEquals(width, (double) layout.getPixelWidth());
    }

    public static void main(String[] args) {
        final Window w;
        final Image i;

        Gtk.init(args);

        w = new Window();
        i = new Image();
        w.add(i);

        i.connect(new Widget.ExposeEvent() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;

                cr = new Context(source.getWindow());

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
        w.showAll();
        Gtk.main();
    }
}
