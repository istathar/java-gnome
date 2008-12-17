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

import static java.lang.Math.PI;
import static textview.LoremIpsum.text;

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
        final Surface surface;
        final Context cr;
        final Layout layout;
        final FontDescription desc;
        final String[] paras;
        final LayoutLine first;
        final PaperSize paper;
        final double pageWidth, pageHeight;
        final double topMargin, leftMargin, rightMargin;
        final Rectangle rect;
        final double[] holes;
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
         * Draw the horizontal rules in blue. These will cunningly be drawn on
         * the font baseline, and given that the LayoutLines below will be
         * drawn with reference to this latitude it will end up looking like
         * the person writing is very good at staying between the lines.
         */

        y = topMargin + b;
        while (y < pageHeight) {
            cr.moveTo(0, y);
            cr.lineTo(pageWidth, y);

            y += v;
        }

        cr.setSourceRGB(0, 0, 199.0 / 255.0);
        cr.setLineWidth(0.1);
        cr.stroke();

        /*
         * Draw a vertical red line as the left margin rule.
         */

        cr.moveTo(leftMargin, 0);
        cr.lineTo(leftMargin, pageHeight);
        cr.setSourceRGB(255.0 / 255.0, 0.0, 0.0);
        cr.stroke();

        /*
         * Now draw the "holes" making this three-hole punched lined paper.
         * The holes array are fractions of the page height which is where we
         * will draw the circles with arc(). We wil preserve the path so we
         * can use it again to full with white, making it look like the paper
         * was punched out.
         */

        holes = new double[] {
                1.0 / 7.0, 1.0 / 2.0, 6.0 / 7.0
        };

        cr.setLineWidth(1.0);

        for (double hole : holes) {
            cr.arc(leftMargin / 2.0, pageHeight * hole, leftMargin / 4.0, 0.0, 2 * PI);

            cr.setSourceRGB(1.0, 1.0, 1.0);
            cr.fillPreserve();

            cr.setSourceRGB(0.5, 0.5, 0.5);
            cr.stroke();
        }

        /*
         * And finally we lay out the words. Given some source text, split it
         * up into individual paragraphs. Pango's Layout is capable of doing
         * multiple paragraphs at once, but this allows us to control the
         * spacing between paragraphs.
         */

        paras = text.split("\n");

        /*
         * Set a width for the Layouts. This will kick-off word wrapping. And
         * reset the source colour so that the text will be black.
         */

        layout.setWidth(pageWidth - (leftMargin + rightMargin));

        cr.setSourceRGB(0.0, 0.0, 0.0);

        /*
         * We did the lines first so that the typeset text will be over the
         * ruled lines. We go to the trouble of drawing the lines
         * individually, making it easier to keep things aligned with the
         * baselines of the rules that we've already drawn.
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
         * Finally, flush the drawing out to the Surface and through it on out
         * to the PDF document. This is very important! If you don't reach
         * this point the file on disk will be incomplete and won't render in
         * a PDF viewer.
         */

        surface.finish();
    }
}
