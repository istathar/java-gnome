/*
 * ExampleDrawingText.java
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
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Image;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.pango.Alignment;
import org.gnome.pango.FontDescription;
import org.gnome.pango.Layout;

/**
 * Little example of how to draw text with Pango and Cairo.
 * 
 * @author Vreixo Formoso
 */
public class ExampleDrawingText
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
                final FontDescription desc;

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
                 * The usage of Pango markup is an easy way to get text
                 * formatted.
                 */
                layout.setMarkup("<big>Hello</big>!\n"
                        + "This is an example of a how to write a <i>paragraph</i> "
                        + "of text with Pango, and how to <span foreground=\"#0000FF\">"
                        + "format</span> it with <span font_desc=\"Sans Italic 12\">Pango Markup</span>");

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
                 * We can also specify some attributes (such as the color)
                 * with usual Cairo functions.
                 */
                cr.setSource(0.1, 0.5, 0.7);

                /*
                 * And finally, we draw the text!
                 */
                cr.showLayout(layout);
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
