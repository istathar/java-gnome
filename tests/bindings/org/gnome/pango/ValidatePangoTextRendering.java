/*
 * ValidatePangoTextRendering.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.pango;

import java.io.IOException;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.freedesktop.cairo.PdfSurface;
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

        cr.setSource(0.1, 0.9, 0.2, 1.0);
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

    /*
     * This verifies our having normalized the co-ordinate spaces to Cairo
     * units.
     */
    public final void testWidthAndHeightNormalization() throws IOException {
        Surface surface;
        Context cr;
        Layout layout;
        double units, pixels;

        surface = new ImageSurface(Format.ARGB32, 150, 150);
        cr = new Context(surface);

        layout = draw(cr);

        layout.setWidth(60.0);

        units = layout.getSizeWidth();
        assertEquals(units, layout.getPixelWidth(), 0.001);

        /*
         * Now test with a Surface whose device units are not integral pixels.
         */

        surface = new PdfSurface(null, 150, 150);
        cr = new Context(surface);

        layout = draw(cr);

        units = layout.getSizeWidth();
        pixels = layout.getPixelWidth();
        assertTrue(pixels > units);

        // something really wide!
        layout.setText("Big brother is watching you, though you probably didn't know. In any case, you're not that interesting so really, the shame is on the poor sod who has to stare at the footage all day.");

        units = layout.getSizeWidth();
        assertTrue(units > 150);

        /*
         * Does calling setWidth() re-layout the Layout?
         */

        layout.setWidth(120);

        units = layout.getSizeWidth();
        assertTrue(units < 120);

        /*
         * yes, it does. Impressive.
         */
    }

    public final void testLineCount() {
        final Surface surface;
        final Context cr;
        final Layout layout;

        surface = new ImageSurface(Format.ARGB32, 150, 150);
        cr = new Context(surface);
        layout = draw(cr);

        assertEquals(1, layout.getLineCount());
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
