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

        Gtk.init(args);

        surface = new PDFSurface("picture.pdf", 150, 150);
        cr = new Context(surface);

        /*
         * Do some simple vector drawing.
         */

        cr.setSourceRGBA(1.0, 0.1, 0.0, 1.0);
        cr.moveTo(10, 40);
        cr.lineTo(120, 145);
        cr.stroke();

        cr.setSourceRGBA(225 / 255.0, 148 / 255.0, 11 / 255.0, 1.0);
        cr.rectangle(70, 70, 20, 40);
        cr.fill();

        surface.showPage();

        /*
         * Page 2
         */

        cr.setSourceRGBA(0, 0, 199 / 255.0, 1.0);
        cr.moveTo(50, 120);
        cr.arcNegative(80, 80, 39, 0, -Math.PI / 2);
        cr.stroke();

        surface.finish();
    }
}
