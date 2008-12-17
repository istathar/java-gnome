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
 * Some poor kid sitting through latin class doodling on his lined paper.
 * 
 * This is an example of using Pango to render text along side Cairo drawing
 * primitives, and using Cairo's PDFSurface back end to produce a PDF that can
 * subsequently be previewd or printed to paper.
 * 
 * @author Andrew Cowie
 */
public class ExampleLinedPaper
{
    public static void main(String[] args) throws IOException {
        final Context cr;
        final Surface surface;
        final Layout layout;
        final FontDescription desc;
        final String[] paras;
        final LayoutLine first;
        final PaperSize paper;
        final double pageWidth, pageHeight;
        final double topMargin, leftMargin, rightMargin;
        Rectangle rect;
        double y, v, b;

        Gtk.init(args);

        paper = PaperSize.getDefault();
        pageWidth = paper.getWidth(Unit.POINTS);
        pageHeight = paper.getHeight(Unit.POINTS);
        topMargin = 25;
        leftMargin = 45;
        rightMargin = 20;

        surface = new PDFSurface("doc/examples/cairo/ExampleLinedPaper.pdf", (int) pageWidth,
                (int) pageHeight);
        cr = new Context(surface);

        cr.moveTo(leftMargin, topMargin);

        layout = new Layout(cr);
        desc = new FontDescription("Liberation Serif 10");
        layout.setFontDescription(desc);

        /*
         * Set a width for the Layouts. This will kick off word wrapping.
         */

        layout.setWidth(pageWidth - (leftMargin + rightMargin));

        /*
         * Now, given some source text, split it up into individual
         * paragraphs. Pango's Layout is capable of doing multiple paragraphs
         * at once, but this allows us to control the spacing between
         * paragraphs.
         */

        paras = textview.LoremIpsum.text.split("\n");

        /*
         * Before we start rendering, we need some information about the line
         * height. Given that all lines are going to be rendered in the same
         * font, we can get the metrics of a piece of arbitrary text and then
         * use that height to do the line spacing for both the text and the
         * lines.
         */

        layout.setText("Lorem");
        first = layout.getLineReadonly(0);
        rect = first.getExtentsLogical();
        v = rect.getHeight();
        b = rect.getAscent();

        /*
         * And now we lay out the text. We go to the trouble of laying out
         * lines individually; this is then paralleled by drawing the
         * horizontal rules in a moment.
         */

        y = topMargin + b;
        for (String para : paras) {
            layout.setText(para);

            for (LayoutLine line : layout.getLinesReadonly()) {
                y += v;

                cr.moveTo(leftMargin, y);
                cr.showLayout(line);
            }

            y += v; // blank line between paras
        }

        /*
         * Draw the horizontal rules in blue. These will cunningly be drawn on
         * the font baseline, and given that the LayoutLines are drawn with
         * reference to this latitude it looks like the person writing is very
         * good at staying between the lines.
         */

        y = topMargin + b;
        while (y < pageHeight) {
            cr.moveTo(0, y);
            cr.lineTo(pageWidth, y);

            y += v;
        }

        cr.setSourceRGBA(0, 0, 199 / 255.0, 1.0);
        cr.setLineWidth(0.1);
        cr.stroke();

        /*
         * Draw the vertical red left margin rule.
         */

        cr.moveTo(leftMargin, 0);
        cr.lineTo(leftMargin, pageHeight);
        cr.setSourceRGBA(255.0 / 255.0, 0.0, 0.0, 1.0);
        cr.stroke();

        /*
         * Flush the drawing out to the Surface and through it on out to the
         * PDF document. This is very important! If you don't reach this point
         * the file on disk will be incomplete and won't render in a PDF
         * viewer.
         */

        surface.finish();
    }
}
