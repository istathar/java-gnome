/*
 * ExampleDrawingPdf.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package cairo;

import java.io.IOException;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.PDFSurface;
import org.freedesktop.cairo.Surface;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.PaperSize;
import org.gnome.gtk.Unit;
import org.gnome.pango.FontDescription;
import org.gnome.pango.Layout;
import org.gnome.pango.LayoutLine;
import org.gnome.pango.Rectangle;

/**
 * FIXME This isn't much of an example yet, and won't be really until we can
 * get some serious amounts of text rendered, PDF being good for documents,
 * apparently.
 * 
 * @author Andrew Cowie
 */
public class ExampleDrawingPdf
{
    public static void main(String[] args) throws IOException {
        final Context cr;
        final Surface surface;
        final Layout layout;
        final FontDescription desc;
        final PaperSize paper;
        final double pageWidth;
        final double pageHeight;
        final double topMargin;
        final double leftMargin;
        int i;
        Rectangle rect;
        double y, v, b;

        Gtk.init(args);

        paper = PaperSize.getDefault();
        pageWidth = paper.getWidth(Unit.POINTS);
        pageHeight = paper.getHeight(Unit.POINTS);
        topMargin = 10;
        leftMargin = 35;

        surface = new PDFSurface("picture.pdf", (int) pageWidth, (int) pageHeight);
        cr = new Context(surface);

        cr.moveTo(leftMargin, topMargin);

        layout = new Layout(cr);
        desc = new FontDescription("Liberation Serif 10");
        layout.setFontDescription(desc);

        layout.setWidth(pageWidth * 0.9);
        /*
         * FIXME instead do something with Pango itself to identify paragraph
         * ends and then manually add the inter paragraph spacing instead of
         * just doubling the newline
         */
        layout.setText(textview.LoremIpsum.text.replace("\n", "\n\n"));

        i = 0;

        for (LayoutLine line : layout.getLinesReadonly()) {
            rect = line.getExtentsLogical();
            v = rect.getHeight();
            b = rect.getAscent();

            y = 10 + b + (v * i);

            cr.moveTo(leftMargin, y);
            cr.showLayout(line);

            cr.moveTo(0, y);
            cr.lineTo(pageWidth, y);

            i++;
        }

        cr.moveTo(10, topMargin);
        String str = "";
        for (i = 1; i < 88; i++) {
            str += i + "\n";
        }
        layout.setText(str);
        cr.showLayout(layout);

        cr.setSourceRGBA(0, 0, 199 / 255.0, 1.0);
        cr.setLineWidth(0.1);
        cr.stroke();

        cr.moveTo(leftMargin, 0);
        cr.lineTo(leftMargin, pageHeight);
        cr.setSourceRGBA(255.0 / 255.0, 0.0, 0.0, 1.0);
        cr.stroke();

        surface.finish();
    }
}
