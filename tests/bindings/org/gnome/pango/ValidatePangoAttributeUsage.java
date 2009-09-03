/*
 * ValidatePangoAttributeUsage.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008      Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
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
import org.gnome.gdk.EventFocus;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Evaluate problems relating to memory management of Pango Attribute structs.
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 */
public class ValidatePangoAttributeUsage extends GraphicalTestCase
{
    private static Layout draw(Context cr) {
        final Layout layout;
        FontDescription desc;
        final AttributeList list;
        Attribute attr;

        /*
         * ...that we use to create a Pango Layout. The Context represents the
         * backend where the text will be actually drawn. The Layout
         * represents the text and its format attributes.
         */
        layout = new Layout(cr);

        /*
         * You can set the default font description.
         */
        desc = new FontDescription("DejaVu Serif, 18");
        layout.setFontDescription(desc);

        /*
         * Build up the text and its formatting
         */

        list = new AttributeList();

        layout.setText("H€lloworldPeace武道");

        attr = new StyleAttribute(Style.ITALIC);
        attr.setIndices(0, 5);
        list.insert(attr);

        desc = new FontDescription();
        desc.setWeight(Weight.BOLD);

        attr = new FontDescriptionAttribute(desc);
        attr.setIndices(5, 10);
        list.insert(attr);

        attr = new ForegroundColorAttribute(0.9, 0.1, 0.2);
        attr.setIndices(10, 5);
        list.insert(attr);

        attr = new StyleAttribute(Style.NORMAL);
        attr.setIndices(10, 5);
        list.insert(attr);

        attr = new BackgroundColorAttribute(1.0, 1.0, 0.0);
        attr.setIndices(10, 5);
        list.insert(attr);

        attr = new WeightAttribute(Weight.NORMAL);
        attr.setIndices(11, 4);
        list.insert(attr);

        attr = new VariantAttribute(Variant.SMALL_CAPS);
        attr.setIndices(11, 4);
        list.insert(attr);

        attr = new ForegroundColorAttribute(0.0, 0.0, 0.0);
        attr.setIndices(15, 2);
        list.insert(attr);
        attr = new FallbackAttribute(false);
        attr.setIndices(15, 2);
        list.insert(attr);

        attr = new ForegroundColorAttribute(0.1, 0.5, 0.7);
        list.insert(attr);

        layout.setAttributes(list);

        /*
         * You can set the alignment of the Layout. Note that you should set
         * its width too.
         */
        layout.setWidth(290.0);
        layout.setAlignment(Alignment.CENTER);

        /*
         * And finally, we draw the text!
         */
        cr.showLayout(layout);

        return layout;
    }

    public static void main(String[] args) {
        final Window w;
        final Image i;

        Gtk.init(args);

        w = new Window();
        w.setDefaultSize(300, 70);

        i = new Image();
        w.add(i);
        w.showAll();

        i.connect(new Widget.ExposeEvent() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;

                /*
                 * We need a Cairo context...
                 */
                cr = new Context(source.getWindow());

                draw(cr);

                return false;
            }
        });

        /*
         * This allows us to test the memory management problem where we were
         * crashing the VM due to our not being aware that AttributeList's
         * insert() steals the ownership of an Attribute.
         */
        w.connect(new Widget.FocusOutEvent() {
            public boolean onFocusOutEvent(Widget source, EventFocus event) {
                System.gc();
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

    public final void testPangoAttributeOwnership() throws InterruptedException {
        final Surface surface;
        final Context cr;
        Layout layout;

        System.gc();
        Thread.sleep(50);

        surface = new ImageSurface(Format.ARGB32, 150, 150);
        cr = new Context(surface);
        layout = draw(cr);

        assertNotNull(PangoLayout.getAttributes(layout));
        layout = null;

        System.gc();
        Thread.sleep(50);
    }
}
