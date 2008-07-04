/*
 * ExampleDrawingInExposeEvent.java
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
import org.freedesktop.cairo.LinearPattern;
import org.freedesktop.cairo.PDFSurface;
import org.freedesktop.cairo.Pattern;
import org.freedesktop.cairo.RadialPattern;
import org.freedesktop.cairo.Surface;
import org.gnome.gtk.Gtk;

public class ExampleDrawingPdf
{
    public static void main(String[] args) throws IOException {
        Gtk.init(args);

        final Context cr;
        final Surface surface;
        final Pattern linear, radial;

        surface = new PDFSurface("picture.pdf", 150, 150);
        cr = new Context(surface);

        /*
         * Now, finally do some drawing:
         */

        cr.setSourceRGBA(1.0, 0.1, 0.0, 1.0);
        cr.moveTo(10, 40);
        cr.lineTo(120, 145);
        cr.stroke();

        /*
         * If youre used to using RGB triplets, just normalize them to the 0.0
         * to 1.0 range by dividing by 255. It's all the same to Cairo,
         * really.
         */

        cr.setSourceRGBA(225 / 255.0, 148 / 255.0, 11 / 255.0, 1.0);
        cr.rectangle(70, 70, 20, 40);
        cr.fill();

        /*
         * Now a much more complicated example of drawing: a linear colour
         * gradiant with a radial alpha mask.
         */

        linear = new LinearPattern(0, 0, 150, 150);
        linear.addColorStopRGB(0.0, 0.0, 0.3, 0.8);
        linear.addColorStopRGB(1.0, 0.0, 0.8, 0.3);

        radial = new RadialPattern(75, 75, 15, 75, 75, 60);
        radial.addColorStopRGBA(0, 0.0, 0.0, 0.0, 0.0);
        radial.addColorStopRGBA(1, 0.0, 0.0, 0.0, 1.0);

        cr.setSource(linear);
        cr.mask(radial);

        surface.finish();
    }
}
