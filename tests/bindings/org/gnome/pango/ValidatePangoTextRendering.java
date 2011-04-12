/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

import java.io.IOException;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.freedesktop.cairo.PdfSurface;
import org.freedesktop.cairo.Surface;
import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * @author Andrew Cowie
 */
public class ValidatePangoTextRendering extends GraphicalTestCase
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
        assertEquals(0.0, layout.getSpacing());

        /*
         * Round trips
         */

        layout.setIndent(5.0);
        assertEquals(5.0, layout.getIndent());

        layout.setJustify(true);
        assertEquals(true, layout.getJustify());
        layout.setJustify(false);
        assertEquals(false, layout.getJustify());

        layout.setSpacing(3.5);
        assertEquals(3.5, layout.getSpacing());
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

        surface = new PdfSurface("tmp/tests/org/gnome/pango/ValidatePangoTextRendering.pdf", 150, 150);
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

        i.connect(new Widget.Draw() {
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
        w.showAll();
        Gtk.main();
    }

    public final void testAttributeCreation() {
        final FontDescription desc;
        Attribute attr;

        desc = new FontDescription("Serif, 12");
        attr = new FontDescriptionAttribute(desc);

        assertNotNull(attr);
    }

    public final void testAttributeListUse() {
        final Attribute attr;
        final AttributeList list;
        final Surface surface;
        final Context cr;
        final Layout layout;

        surface = new ImageSurface(Format.ARGB32, 150, 150);
        cr = new Context(surface);
        layout = new Layout(cr);

        layout.setText("H€lloWorld");
        list = new AttributeList();

        /*
         * Now set some Attributes.
         */

        attr = new StyleAttribute(Style.ITALIC);
        attr.setIndices(5, 5);

        list.insert(attr);
        layout.setAttributes(list);

        assertEquals(7, PangoAttribute.getStartIndex(attr));
        assertEquals(12, PangoAttribute.getEndIndex(attr));
    }
}
