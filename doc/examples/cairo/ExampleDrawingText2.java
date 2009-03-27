/*
 * ExampleDrawingText2.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008      Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package cairo;

import org.freedesktop.cairo.Context;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventExpose;
import org.gnome.gdk.EventFocus;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.pango.Alignment;
import org.gnome.pango.Attribute;
import org.gnome.pango.AttributeList;
import org.gnome.pango.BackgroundColorAttribute;
import org.gnome.pango.FontDescription;
import org.gnome.pango.FontDescriptionAttribute;
import org.gnome.pango.ForegroundColorAttribute;
import org.gnome.pango.Layout;
import org.gnome.pango.Style;
import org.gnome.pango.StyleAttribute;
import org.gnome.pango.Variant;
import org.gnome.pango.VariantAttribute;
import org.gnome.pango.Weight;
import org.gnome.pango.WeightAttribute;

/**
 * Little example of how to draw text with Pango and Cairo.
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 */
public class ExampleDrawingText2
{
    public static void main(String[] args) {
        final Window w;
        final Image i;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Simple Pango example");
        w.setDefaultSize(150, 150);

        i = new Image();
        w.add(i);
        w.showAll();

        i.connect(new Widget.ExposeEvent() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;
                final Layout layout;
                FontDescription desc;
                final AttributeList list;
                Attribute attr;

                /*
                 * We need a Cairo context...
                 */
                cr = new Context(source.getWindow());

                /*
                 * ...that we use to create a Pango Layout. The Context
                 * represents the backend where the text will be actually
                 * drawn. The Layout represents the text and its format
                 * attributes.
                 */
                layout = new Layout(cr);

                /*
                 * You can set the default font description.
                 */
                desc = new FontDescription("DejaVu Serif, Book 12");
                layout.setFontDescription(desc);

                /*
                 * Build up the text and its formatting
                 */

                list = new AttributeList();

                layout.setText("H€lloworldPeace");

                attr = new StyleAttribute(Style.ITALIC);
                attr.setIndices(layout, 0, 5);
                list.insert(attr);

                desc = new FontDescription();
                desc.setWeight(Weight.BOLD);

                attr = new FontDescriptionAttribute(desc);
                attr.setIndices(layout, 5, 10);
                list.insert(attr);

                attr = new ForegroundColorAttribute(0.9, 0.1, 0.2);
                attr.setIndices(layout, 10, 5);
                list.insert(attr);

                attr = new StyleAttribute(Style.NORMAL);
                attr.setIndices(layout, 10, 5);
                list.insert(attr);

                attr = new BackgroundColorAttribute(1.0, 1.0, 0.0);
                attr.setIndices(layout, 10, 5);
                list.insert(attr);

                attr = new WeightAttribute(Weight.NORMAL);
                attr.setIndices(layout, 11, 4);
                list.insert(attr);

                attr = new VariantAttribute(Variant.SMALL_CAPS);
                attr.setIndices(layout, 11, 4);
                list.insert(attr);

                attr = new ForegroundColorAttribute(0.1, 0.5, 0.7);
                list.insert(attr);

                layout.setAttributes(list);

                /*
                 * You can set the alignment of the Layout. Note that you
                 * should set its width too.
                 */
                layout.setWidth(150.0);
                layout.setAlignment(Alignment.CENTER);

                /*
                 * If you want to ensure the full text fits in the Widget,
                 * setting the size-request is generally useful.
                 */
                source.setSizeRequest(layout.getPixelWidth(), layout.getPixelHeight());

                /*
                 * And finally, we draw the text!
                 */
                cr.showLayout(layout);
                return false;
            }
        });

        w.connect(new Widget.FocusOutEvent() {
            public boolean onFocusOutEvent(Widget source, EventFocus event) {
                System.out.println("gc();");
                System.gc();
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
